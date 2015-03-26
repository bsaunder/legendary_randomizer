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
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.bryansaunders.legendary.model.Hero;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * Hero Service for working with Heros.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
@Path("/hero")
@Api(value = "/hero", description = "Hero Management Service")
public interface IHeroEndpoint {

    /**
     * Returns a list of all of the Heroes in the System.
     * 
     * @return JSON String
     */
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List All Heroes", response = Hero.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Retrieving Data"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response getAllHeroes();

    /**
     * Returns the Specified Hero.
     * 
     * @param id
     *            Hero ID.
     * 
     * @return JSON String
     */
    @GET
    @PermitAll
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get Hero by ID", response = Hero.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Retrieving Data"),
            @ApiResponse(code = 404, message = "Hero Not Found"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response getHeroById(@PathParam("id") Integer id);

    /**
     * Adds the Supplied Hero to the Database. Returns the Updated Hero.
     * 
     * @param hero
     *            Hero to Add.
     * @return JSON String
     */
    @POST
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add Hero", notes = "Returns the Saved Hero", response = Hero.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Saving Data"),
            @ApiResponse(code = 400, message = "Invalid Request Data"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response addHero(Hero hero);

    /**
     * Delete the Specified Hero.
     * 
     * @param id
     *            Hero ID.
     * @return JSON String
     */
    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Delete Hero by ID")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Deleting Data"),
            @ApiResponse(code = 404, message = "Hero Not Found"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response deleteHero(@PathParam("id") Integer id);

    /**
     * Returns a random number of Heroes.
     * 
     * @param count
     *            Hero count.
     * 
     * @return JSON String
     */
    @GET
    @PermitAll
    @Path("/random/{count}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get A Random Number of Heroes", response = Hero.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Retrieving Data"),
            @ApiResponse(code = 400, message = "Not Enough Heroes"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response getRandomHeroes(@PathParam("count") Integer count);
}
