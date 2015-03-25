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

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 * Endpoint for Importing Bulk CSV Data.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Path("/import")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
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
    Response loadMastermindData(MultipartFormDataInput pMastermindCsv);
}
