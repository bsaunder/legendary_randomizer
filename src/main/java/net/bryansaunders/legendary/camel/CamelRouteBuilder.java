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

import net.bryansaunders.legendary.service.InformationService;
import net.bryansaunders.legendary.util.JaxRsResponseBuilder;
import net.bryansaunders.legendary.util.Resources;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

@Startup
@ApplicationScoped
@ContextName(Resources.CAMEL_CONTEXT_NAME)
public class CamelRouteBuilder extends RouteBuilder {
    
    /**
     * URI for the Get Information Endpoint.
     */
    public static final String GET_INFORMATION_URI = "direct:get-rest-info";

    /*
     * (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {
        
        // GET rest/info
        from(CamelRouteBuilder.GET_INFORMATION_URI)
        .bean(InformationService.class, "getInformation")
        .bean(JaxRsResponseBuilder.class, "generateResponse");
    }
}
