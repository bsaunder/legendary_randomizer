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

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.bryansaunders.legendary.model.GameSetup;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * Setup Service for getting a Game Setup.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
@Path("/setup")
@Api(value = "/setup", description = "Game Setup Service")
public interface ISetupEndpoint {

    /**
     * Gets a Game Setup.
     * 
     * @param playerCount
     *            Player Count.
     * @param schemeId
     *            Scheme.
     * @param mastermindId
     *            Mastermind.
     * @return JSON String
     */
    @GET
    @Path("/{players}")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Build a new Game Setup", notes = "Number of players must be between 2 and 5", response = GameSetup.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Invalid ID supplied for Scheme or Mastermind"),
            @ApiResponse(code = 500, message = "Error Building Game Setup"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response getGameSetup(
            @ApiParam(value = "Player Count", allowableValues = "range[2,5]", required = true) @PathParam("players") Integer playerCount,
            @ApiParam(value = "Scheme ID", required = false) @QueryParam("scheme") Integer schemeId,
            @ApiParam(value = "Mastermind ID", required = false) @QueryParam("mastermind") Integer mastermindId);
}
