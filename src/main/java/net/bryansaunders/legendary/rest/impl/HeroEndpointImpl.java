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

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import net.bryansaunders.legendary.model.Hero;
import net.bryansaunders.legendary.rest.IHeroEndpoint;
import net.bryansaunders.legendary.service.HeroService;

/**
 * @see IHeroEndpoint
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
public class HeroEndpointImpl implements IHeroEndpoint {

    /**
     * Hero Service.
     */
    @Inject
    private HeroService heroService;

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#getAllHeroes()
     */
    @Override
    public Response getAllHeroes() {
        final List<Hero> heroes = this.heroService.getAllHeroes();
        return Response.status(Status.OK).entity(heroes).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#getHeroById(java.lang.Integer)
     */
    @Override
    public Response getHeroById(final Integer id) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final Hero hero = this.heroService.getHero(id);
            responseBuilder = responseBuilder.entity(hero);
        } catch (final NoResultException e) {
            responseBuilder = responseBuilder.status(Status.NOT_FOUND).entity("{\"error\":\""+e.getMessage()+"\"}");
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#addHero(net.bryansaunders.legendary.model.Hero)
     */
    @Override
    public Response addHero(final Hero hero) {
          
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final Hero savedHero = this.heroService.saveHero(hero);
            responseBuilder = responseBuilder.entity(savedHero);
        } catch (final EJBTransactionRolledbackException e) {
            // Really should Handle this Better.. Its for Non-Unique Names
            responseBuilder = responseBuilder.status(Status.BAD_REQUEST).entity("{\"error\":\""+e.getMessage()+"\"}");
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#deleteHero(java.lang.Integer)
     */
    @Override
    public Response deleteHero(final Integer id) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            this.heroService.deleteHero(id);
        } catch (final NoResultException e) {
            responseBuilder = responseBuilder.status(Status.NOT_FOUND).entity("{\"error\":\""+e.getMessage()+"\"}");
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IHeroEndpoint#getRandomHeroes(java.lang.Integer)
     */
    @Override
    public Response getRandomHeroes(final Integer count) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final List<Hero> heroes = this.heroService.getRandomHeroes(count);
            responseBuilder = responseBuilder.entity(heroes);
        } catch (final IllegalArgumentException e) {
            responseBuilder = responseBuilder.status(Status.BAD_REQUEST).entity("{\"error\":\""+e.getMessage()+"\"}");
        }

        return responseBuilder.build();
    }
}
