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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.bryansaunders.legendary.model.Hero;

/**
 * Hero Service for working with Heros.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
@Path("/hero")
public interface IHeroEndpoint {

    /**
     * Returns a list of all of the Heroes in the System.
     * 
     * @return JSON String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getHeroById(@PathParam("id") Integer id);

    /**
     * Adds the Supplied Hero to the Database. Returns the Updated Hero.
     * 
     * @param hero
     *            Hero to Add.
     * @return JSON String
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response addHero(Hero hero);

    /**
     * Updates the Supplied Hero in the Database.
     * 
     * @param id
     *            Hero ID
     * @param hero
     *            Updated Hero.
     * @return JSON String
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response updateHero(@PathParam("id") Integer id, Hero hero);

    /**
     * Delete the Specified Hero.
     * 
     * @param id
     *            Hero ID.
     * @return JSON String
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("/random/{count}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getRandomHeroes(@PathParam("count") Integer count);
}
