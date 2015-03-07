package net.bryansaunders.legendary.camel;

/*
 * #%L
 * Legendary Card Randomizer
 * %%
 * Copyright (C) 2015 Bryan Saunders
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.Response.Status;

import net.bryansaunders.legendary.model.Hero;
import net.bryansaunders.legendary.service.HeroService;
import net.bryansaunders.legendary.util.JaxRsResponseBuilder;
import net.bryansaunders.legendary.util.Resources;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jpa.JpaEndpoint;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * Builds the Hero Related Camel Routes for the Application.
 */
@Startup
@ApplicationScoped
@ContextName(Resources.CAMEL_CONTEXT_NAME)
public class HeroRouteBuilder extends RouteBuilder {

    /**
     * URI for the Get All Heroes Endpoint.
     */
    public static final String GET_ALL_HEROES_URI = "direct:get-rest-hero";

    /**
     * URI for the Get Random Heroes Endpoint.
     */
    public static final String GET_RANDOM_HEROES_URI = "direct:get-rest-hero-random-COUNT";

    /**
     * URI for the Get Hero By ID Endpoint.
     */
    public static final String GET_HERO_BY_ID_URI = "direct:get-rest-hero-ID";

    /**
     * URI for the Update Hero Endpoint.
     */
    public static final String PUT_UPDATE_HERO_URI = "direct:put-rest-hero-ID";

    /**
     * URI for the Add Hero Endpoint.
     */
    public static final String POST_ADD_HERO_URI = "direct:post-rest-hero";

    /**
     * URI for the Delete Hero Endpoint.
     */
    public static final String DELETE_HERO_URI = "direct:delete-rest-hero-ID";

    /**
     * JPA Entity Manager.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Transaction Manager.
     */
    @Inject
    private UserTransaction userTransaction;

    /**
     * Camel Context.
     */
    @Inject
    @ContextName(Resources.CAMEL_CONTEXT_NAME)
    private CamelContext camelContext;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {

        // Configure a transaction manager
        final JtaTransactionManager transactionManager = new JtaTransactionManager();
        transactionManager.setUserTransaction(this.userTransaction);
        transactionManager.afterPropertiesSet();

        // Exception Handler
        this.onException(Exception.class).setHeader(JaxRsResponseBuilder.RESPONSE_STATUS_HEADER)
                .constant(Status.INTERNAL_SERVER_ERROR).setBody().simple("").bean(JaxRsResponseBuilder.class);

        // GET rest/hero/{ID}
        this.from(HeroRouteBuilder.GET_HERO_BY_ID_URI).doTry().bean(HeroService.class, "getHero(${body})")
                .log("Found Hero: ${body}").doCatch(NoResultException.class)
                .setHeader(JaxRsResponseBuilder.RESPONSE_STATUS_HEADER).constant(Status.NOT_FOUND).setBody().simple("")
                .end().bean(JaxRsResponseBuilder.class);

        // GET rest/hero
        this.from(HeroRouteBuilder.GET_ALL_HEROES_URI).doTry().bean(HeroService.class, "getAllHeroes")
                .log("Found All Heroes").doCatch(NoResultException.class)
                .setHeader(JaxRsResponseBuilder.RESPONSE_STATUS_HEADER).constant(Status.NOT_FOUND).setBody().simple("")
                .end().bean(JaxRsResponseBuilder.class);

        // PUT rest/hero/{ID}
        this.from(HeroRouteBuilder.PUT_UPDATE_HERO_URI).setHeader(JaxRsResponseBuilder.RESPONSE_STATUS_HEADER)
                .constant(Status.NOT_IMPLEMENTED).setBody().simple("").log("Update Not Implemented")
                .bean(JaxRsResponseBuilder.class);

        // POST rest/hero
        this.from(HeroRouteBuilder.POST_ADD_HERO_URI).to(this.getJpaEndpoint(transactionManager, Hero.class))
                .log("Hero Added: ${body}").bean(JaxRsResponseBuilder.class);

        // DELETE rest/hero/{ID}
        this.from(HeroRouteBuilder.DELETE_HERO_URI).doTry().bean(HeroService.class, "deleteHero(${body})")
                .log("Deleted Hero: ${body}").doCatch(NoResultException.class)
                .setHeader(JaxRsResponseBuilder.RESPONSE_STATUS_HEADER).constant(Status.NOT_FOUND).setBody().simple("")
                .end().bean(JaxRsResponseBuilder.class);

        // GET rest/hero/random/{COUNT}
        this.from(HeroRouteBuilder.GET_RANDOM_HEROES_URI).doTry().bean(HeroService.class, "getRandomHeroes(${body})")
                .log("Found Heroes: ${body}").doCatch(Exception.class)
                .setHeader(JaxRsResponseBuilder.RESPONSE_STATUS_HEADER).constant(Status.BAD_REQUEST).setBody()
                .simple("").end().bean(JaxRsResponseBuilder.class);
    }

    /**
     * Builds a JpaEndpoint for a Camel Route.
     * 
     * @param transactionManager
     *            TransactionManager to Use.
     * @param jpaEntity
     *            JPA Entity for the Endpoint
     * @return Returns JPA Endpoint.
     */
    private JpaEndpoint getJpaEndpoint(final JtaTransactionManager transactionManager, final Class jpaEntity) {
        final EntityManagerFactory entityManagerFactory = this.em.getEntityManagerFactory();

        final JpaEndpoint jpaEndpoint = new JpaEndpoint();
        jpaEndpoint.setCamelContext(this.camelContext);
        jpaEndpoint.setEntityType(jpaEntity);
        jpaEndpoint.setEntityManagerFactory(entityManagerFactory);
        jpaEndpoint.setTransactionManager(transactionManager);

        return jpaEndpoint;
    }
}
