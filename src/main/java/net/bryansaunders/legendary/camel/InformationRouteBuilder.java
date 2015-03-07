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
import javax.ws.rs.core.Response.Status;

import net.bryansaunders.legendary.service.InformationService;
import net.bryansaunders.legendary.util.JaxRsResponseBuilder;
import net.bryansaunders.legendary.util.Resources;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

/**
 * Builds the Information Related Camel Routes for the Application.
 */
@Startup
@ApplicationScoped
@ContextName(Resources.CAMEL_CONTEXT_NAME)
public class InformationRouteBuilder extends RouteBuilder {

    /**
     * URI for the Get Information Endpoint.
     */
    public static final String GET_INFORMATION_URI = "direct:get-rest-info";

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

        this.onException(Exception.class).setHeader(JaxRsResponseBuilder.RESPONSE_STATUS_HEADER)
                .constant(Status.INTERNAL_SERVER_ERROR).setBody().simple("").bean(JaxRsResponseBuilder.class);

        // GET rest/info
        this.from(InformationRouteBuilder.GET_INFORMATION_URI).bean(InformationService.class, "getInformation").bean(
                JaxRsResponseBuilder.class, "generateResponse");
    }
}
