/**
 * 
 */
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

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.bryansaunders.legendary.model.ImportResult;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * Endpoint for Importing Bulk CSV Data.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Path("/import")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/import", description = "Data Import Service")
public interface IImportEndpoint {

    /**
     * Loads Hero CSV Data.
     * 
     * @param pHeroCsv
     *            CSV Data
     * @return Response
     */
    @POST
    @RolesAllowed("ADMIN")
    @Path("/hero")
    @ApiOperation(value = "Import Hero Data", notes = "Data will be appended to any existing data", response = ImportResult.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Importing Data"),
            @ApiResponse(code = 200, message = "Import Successful, Check Results for Import Status") })
    Response loadHeroData(MultipartFormDataInput pHeroCsv);

    /**
     * Loads Leadable CSV Data.
     * 
     * @param pLeadableCsv
     *            CSV Data
     * @return Response
     */
    @POST
    @RolesAllowed("ADMIN")
    @Path("/leadable")
    @ApiOperation(value = "Import Leadable Data", notes = "Data will be appended to any existing data", response = ImportResult.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Importing Data"),
            @ApiResponse(code = 200, message = "Import Successful, Check Results for Import Status") })
    Response loadLeadableData(MultipartFormDataInput pLeadableCsv);

    /**
     * Loads Scheme CSV Data.
     * 
     * @param pSchemeCsv
     *            CSV Data
     * @return Response
     */
    @POST
    @RolesAllowed("ADMIN")
    @Path("/scheme")
    @ApiOperation(value = "Import Scheme Data", notes = "Data will be appended to any existing data", response = ImportResult.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Importing Data"),
            @ApiResponse(code = 200, message = "Import Successful, Check Results for Import Status") })
    Response loadSchemeData(MultipartFormDataInput pSchemeCsv);

    /**
     * Loads Mastermind CSV Data.
     * 
     * @param pMastermindCsv
     *            CSV Data
     * @return Response
     */
    @POST
    @RolesAllowed("ADMIN")
    @Path("/mastermind")
    @ApiOperation(value = "Import Mastermind Data", notes = "Data will be appended to any existing data", response = ImportResult.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Error Importing Data"),
            @ApiResponse(code = 200, message = "Import Successful, Check Results for Import Status") })
    Response loadMastermindData(MultipartFormDataInput pMastermindCsv);
}
