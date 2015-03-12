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

import net.bryansaunders.legendary.model.Scheme;
import net.bryansaunders.legendary.rest.ISchemeEndpoint;
import net.bryansaunders.legendary.service.SchemeService;

/**
 * @see ISchemeEndpoint
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class SchemeEndpointImpl implements ISchemeEndpoint {
    
    /**
     * Scheme Service.
     */
    @Inject
    private SchemeService schemeService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.ISchemeEndpoint#getAllSchemes()
     */
    @Override
    public Response getAllSchemes() {
        final List<Scheme> schemees = this.schemeService.getAllSchemes();
        return Response.status(Status.OK).entity(schemees).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.ISchemeEndpoint#getSchemeById
     * (java.lang.Integer)
     */
    @Override
    public Response getSchemeById(final Integer id) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final Scheme scheme = this.schemeService.getScheme(id);
            responseBuilder = responseBuilder.entity(scheme);
        } catch (final NoResultException e) {
            responseBuilder = responseBuilder.status(Status.NOT_FOUND);
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.ISchemeEndpoint#addScheme(net
     * .bryansaunders.legendary.model.Scheme)
     */
    @Override
    public Response addScheme(final Scheme scheme) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final Scheme savedScheme = this.schemeService.saveScheme(scheme);
            responseBuilder = responseBuilder.entity(savedScheme);
        } catch (final EJBTransactionRolledbackException e) {
            // Really should Handle this Better.. Its for Non-Unique Names
            responseBuilder = responseBuilder.status(Status.BAD_REQUEST);
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.ISchemeEndpoint#deleteScheme
     * (java.lang.Integer)
     */
    @Override
    public Response deleteScheme(final Integer id) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            this.schemeService.deleteScheme(id);
        } catch (final NoResultException e) {
            responseBuilder = responseBuilder.status(Status.NOT_FOUND);
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.ISchemeEndpoint#getRandomSchemes
     * (java.lang.Integer)
     */
    @Override
    public Response getRandomSchemes(final Integer count) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final List<Scheme> schemes = this.schemeService.getRandomSchemes(count);
            responseBuilder = responseBuilder.entity(schemes);
        } catch (final IllegalArgumentException e) {
            responseBuilder = responseBuilder.status(Status.BAD_REQUEST);
        }

        return responseBuilder.build();
    }

}
