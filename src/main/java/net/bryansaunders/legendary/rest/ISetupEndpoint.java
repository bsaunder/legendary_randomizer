package net.bryansaunders.legendary.rest;

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


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Setup Service for getting a Game Setup.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
@Path("/setup")
public interface ISetupEndpoint {

    /**
     * Gets a Game Setup.
     * 
     * @param players
     *            Scheme ID.
     * 
     * @return JSON String
     */
    @GET
    @Path("/{players}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getGameSetup(@PathParam("players") Integer playerCount, @QueryParam("scheme") Integer schemeId,
            @QueryParam("mastermind") Integer mastermindId);
}
