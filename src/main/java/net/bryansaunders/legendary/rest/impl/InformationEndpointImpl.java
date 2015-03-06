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

import net.bryansaunders.legendary.camel.CamelRouteBuilder;
import net.bryansaunders.legendary.rest.IInformationEndpoint;
import net.bryansaunders.legendary.util.Resources;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.ContextName;

/**
 * @see IInformationEndpoint
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
public class InformationEndpointImpl implements IInformationEndpoint {
    
    /**
     * Camel Context
     */
    @Inject
    @ContextName(Resources.CAMEL_CONTEXT_NAME)
    private CamelContext context;

    /**
     * Camel Producer Template.
     */
    ProducerTemplate camelProducer;
    
    /**
     * Initialize the Producer. 
     * @throws Exception
     */
    @PostConstruct
    public void initProducer() throws Exception {
        this.camelProducer = this.context.createProducerTemplate();
        this.camelProducer.start();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.dl2.service.rest.InfoService#getInformation()
     */
    @Override
    public Response getInformation() {
        return (Response) this.camelProducer.sendBody(CamelRouteBuilder.GET_INFORMATION_URI,ExchangePattern.InOut, "");
    }
}
