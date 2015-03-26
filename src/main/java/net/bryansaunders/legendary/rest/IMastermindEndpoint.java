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

import net.bryansaunders.legendary.model.Mastermind;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * Mastermind Service for working with Masterminds.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
@Path("/mastermind")
@Api(value = "/mastermind", description = "Mastermind Management Service")
public interface IMastermindEndpoint {

    /**
     * Returns a list of all of the Masterminds in the System.
     * 
     * @return JSON String
     */
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List All Masterminds", response = Mastermind.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Retrieving Data"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response getAllMasterminds();

    /**
     * Returns the Specified Mastermind.
     * 
     * @param id
     *            Mastermind ID.
     * 
     * @return JSON String
     */
    @GET
    @PermitAll
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get Mastermind by ID", response = Mastermind.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Retrieving Data"),
            @ApiResponse(code = 404, message = "Mastermind Not Found"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response getMastermindById(@PathParam("id") Integer id);

    /**
     * Adds the Supplied Mastermind to the Database. Returns the Updated
     * Mastermind.
     * 
     * @param mastermind
     *            Mastermind to Add.
     * @return JSON String
     */
    @POST
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add Mastermind", notes = "Returns the Saved Mastermind", response = Mastermind.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Saving Data"),
            @ApiResponse(code = 400, message = "Invalid Request Data"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response addMastermind(Mastermind mastermind);

    /**
     * Delete the Specified Mastermind.
     * 
     * @param id
     *            Mastermind ID.
     * @return JSON String
     */
    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Delete Mastermind by ID")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Deleting Data"),
            @ApiResponse(code = 404, message = "Mastermind Not Found"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response deleteMastermind(@PathParam("id") Integer id);

    /**
     * Returns a random number of Masterminds.
     * 
     * @param count
     *            Mastermind count.
     * 
     * @return JSON String
     */
    @GET
    @PermitAll
    @Path("/random/{count}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get A Random Number of Masterminds", response = Mastermind.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Retrieving Data"),
            @ApiResponse(code = 400, message = "Not Enough Masterminds"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response getRandomMasterminds(@PathParam("count") Integer count);
}
