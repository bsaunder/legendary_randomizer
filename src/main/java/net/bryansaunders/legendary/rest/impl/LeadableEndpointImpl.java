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

import net.bryansaunders.legendary.model.Leadable;
import net.bryansaunders.legendary.rest.ILeadableEndpoint;
import net.bryansaunders.legendary.service.LeadableService;

/**
 * @see ILeadableEndpoint
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class LeadableEndpointImpl implements ILeadableEndpoint {

    /**
     * Leadable Service.
     */
    @Inject
    private LeadableService leadableService;

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.ILeadableEndpoint#getAllLeadables()
     */
    @Override
    public Response getAllLeadables() {
        final List<Leadable> leadablees = this.leadableService.getAllLeadables();
        return Response.status(Status.OK).entity(leadablees).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.ILeadableEndpoint#getLeadableById(java
     * .lang.Integer)
     */
    @Override
    public Response getLeadableById(final Integer id) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final Leadable leadable = this.leadableService.getLeadable(id);
            responseBuilder = responseBuilder.entity(leadable);
        } catch (final NoResultException e) {
            responseBuilder = responseBuilder.status(Status.NOT_FOUND);
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.ILeadableEndpoint#addLeadable(net.
     * bryansaunders.legendary.model.Leadable)
     */
    @Override
    public Response addLeadable(final Leadable leadable) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final Leadable savedLeadable = this.leadableService.saveLeadable(leadable);
            responseBuilder = responseBuilder.entity(savedLeadable);
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
     * net.bryansaunders.legendary.rest.ILeadableEndpoint#deleteLeadable(java
     * .lang.Integer)
     */
    @Override
    public Response deleteLeadable(final Integer id) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            this.leadableService.deleteLeadable(id);
        } catch (final NoResultException e) {
            responseBuilder = responseBuilder.status(Status.NOT_FOUND);
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.ILeadableEndpoint#getRandomLeadables
     * (java.lang.Integer)
     */
    @Override
    public Response getRandomLeadables(final Integer count) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final List<Leadable> leadablees = this.leadableService.getRandomLeadables(count);
            responseBuilder = responseBuilder.entity(leadablees);
        } catch (final IllegalArgumentException e) {
            responseBuilder = responseBuilder.status(Status.BAD_REQUEST);
        }

        return responseBuilder.build();
    }
}
