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

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import net.bryansaunders.legendary.model.GameSetup;
import net.bryansaunders.legendary.rest.ISetupEndpoint;
import net.bryansaunders.legendary.service.GameSetupService;

/**
 * Implementation for Setup Endpoint.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class SetupEndpointImpl implements ISetupEndpoint {
    
    @Inject
    private GameSetupService setupService;

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.ISetupEndpoint#getGameSetup(java.lang.Integer, java.lang.Integer,
     * java.lang.Integer)
     */
    @Override
    public Response getGameSetup(Integer playerCount, Integer schemeId, Integer mastermindId) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);

        try {
            GameSetup setup = this.setupService.buildGameSetup(playerCount, schemeId, mastermindId);
            responseBuilder = responseBuilder.entity(setup);
        } catch (final IllegalArgumentException e) {
            responseBuilder = responseBuilder.status(Status.BAD_REQUEST).entity("{\"error\":\""+e.getMessage()+"\"}");
            e.printStackTrace();
        } catch (final NoResultException e) {
            responseBuilder = responseBuilder.status(Status.NOT_FOUND).entity("{\"error\":\""+e.getMessage()+"\"}");
            e.printStackTrace();
        }

        return responseBuilder.build();
    }

}
