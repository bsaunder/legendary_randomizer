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

/**
 * Scheme Service for working with Schemes.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
@Path("/scheme")
public interface ISchemeEndpoint {
    
    /**
     * Returns a list of all of the Schemes in the System.
     * 
     * @return JSON String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
    Response getSchemeById(@PathParam("id") Integer id);

    /**
     * Adds the Supplied Scheme to the Database. Returns the Updated Scheme.
     * 
     * @param scheme
     *            Scheme to Add.
     * @return JSON String
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("/random/{count}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getRandomSchemes(@PathParam("count") Integer count);

}
