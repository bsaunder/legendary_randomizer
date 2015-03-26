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

import net.bryansaunders.legendary.model.Scheme;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * Scheme Service for working with Schemes.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
@Path("/scheme")
@Api(value = "/scheme", description = "Scheme Management Service")
public interface ISchemeEndpoint {

    /**
     * Returns a list of all of the Schemes in the System.
     * 
     * @return JSON String
     */
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List All Schemes", response = Scheme.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Retrieving Data"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response getAllSchemes();

    /**
     * Returns the Specified Scheme.
     * 
     * @param id
     *            Scheme ID.
     * 
     * @return JSON String
     */
    @GET
    @Path("/{id}")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get Scheme by ID", response = Scheme.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Retrieving Data"),
            @ApiResponse(code = 404, message = "Scheme Not Found"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response getSchemeById(@PathParam("id") Integer id);

    /**
     * Adds the Supplied Scheme to the Database. Returns the Updated Scheme.
     * 
     * @param scheme
     *            Scheme to Add.
     * @return JSON String
     */
    @POST
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add Scheme", notes = "Returns the Saved Scheme", response = Scheme.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Saving Data"),
            @ApiResponse(code = 400, message = "Invalid Request Data"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response addScheme(Scheme scheme);

    /**
     * Delete the Specified Scheme.
     * 
     * @param id
     *            Scheme ID.
     * @return JSON String
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Delete Scheme by ID")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Deleting Data"),
            @ApiResponse(code = 404, message = "Scheme Not Found"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response deleteScheme(@PathParam("id") Integer id);

    /**
     * Returns a random number of Schemes.
     * 
     * @param count
     *            Scheme count.
     * 
     * @return JSON String
     */
    @GET
    @PermitAll
    @Path("/random/{count}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get A Random Number of Schemes", response = Scheme.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Retrieving Data"),
            @ApiResponse(code = 400, message = "Not Enough Schemes"),
            @ApiResponse(code = 200, message = "Request Successful") })
    Response getRandomSchemes(@PathParam("count") Integer count);

}
