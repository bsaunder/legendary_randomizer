package net.bryansaunders.legendary.rest.impl;

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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import net.bryansaunders.legendary.camel.HeroRouteBuilder;
import net.bryansaunders.legendary.model.Hero;
import net.bryansaunders.legendary.rest.IHeroEndpoint;
import net.bryansaunders.legendary.util.Resources;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.ContextName;

/**
 * @see IHeroEndpoint
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
public class HeroEndpointImpl implements IHeroEndpoint {

    /**
     * Camel Context.
     */
    @Inject
    @ContextName(Resources.CAMEL_CONTEXT_NAME)
    private CamelContext context;

    /**
     * Camel Producer Template.
     */
    private ProducerTemplate camelProducer = null;

    /**
     * Initialize the Producer.
     * 
     * @throws Exception
     *             Throws Exception if an error occurs starting the Producer Template.
     */
    @PostConstruct
    public void initProducer() throws Exception {
        this.camelProducer = this.context.createProducerTemplate();
        this.camelProducer.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#getAllHeroes()
     */
    @Override
    public Response getAllHeroes() {
        return (Response) this.camelProducer.sendBody(HeroRouteBuilder.GET_ALL_HEROES_URI, ExchangePattern.InOut, "");
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#getHeroById(java.lang.Integer)
     */
    @Override
    public Response getHeroById(final Integer id) {
        return (Response) this.camelProducer.sendBody(HeroRouteBuilder.GET_HERO_BY_ID_URI, ExchangePattern.InOut, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#addHero(net.bryansaunders.legendary.model.Hero)
     */
    @Override
    public Response addHero(final Hero hero) {
        return (Response) this.camelProducer.sendBody(HeroRouteBuilder.POST_ADD_HERO_URI, ExchangePattern.InOut, hero);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#updateHero(net.bryansaunders.legendary.model.Hero)
     */
    @Override
    public Response updateHero(Integer id, Hero hero) {
        return (Response) this.camelProducer.sendBody(HeroRouteBuilder.PUT_UPDATE_HERO_URI, ExchangePattern.InOut,
                hero);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#deleteHero(java.lang.Integer)
     */
    @Override
    public Response deleteHero(Integer id) {
        return (Response) this.camelProducer.sendBody(HeroRouteBuilder.DELETE_HERO_URI, ExchangePattern.InOut, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#getRandomHeroes(java.lang.Integer)
     */
    @Override
    public Response getRandomHeroes(Integer count) {
        return (Response) this.camelProducer.sendBody(HeroRouteBuilder.GET_RANDOM_HEROES_URI, ExchangePattern.InOut,
                count);
    }
}
