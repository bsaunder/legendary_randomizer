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

import net.bryansaunders.legendary.model.Mastermind;
import net.bryansaunders.legendary.rest.IMastermindEndpoint;
import net.bryansaunders.legendary.service.MastermindService;

/**
 * @see IMastermindEndpoint
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class MastermindEndpointImpl implements IMastermindEndpoint {

    /**
     * Mastermind Service.
     */
    @Inject
    private MastermindService mastermindService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.IMastermindEndpoint#getAllMasterminds()
     */
    @Override
    public Response getAllMasterminds() {
        final List<Mastermind> mastermindes = this.mastermindService.getAllMasterminds();
        return Response.status(Status.OK).entity(mastermindes).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.IMastermindEndpoint#getMastermindById
     * (java.lang.Integer)
     */
    @Override
    public Response getMastermindById(final Integer id) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final Mastermind mastermind = this.mastermindService.getMastermind(id);
            responseBuilder = responseBuilder.entity(mastermind);
        } catch (final NoResultException e) {
            responseBuilder = responseBuilder.status(Status.NOT_FOUND);
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.IMastermindEndpoint#addMastermind(net
     * .bryansaunders.legendary.model.Mastermind)
     */
    @Override
    public Response addMastermind(final Mastermind mastermind) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final Mastermind savedMastermind = this.mastermindService.saveMastermind(mastermind);
            responseBuilder = responseBuilder.entity(savedMastermind);
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
     * net.bryansaunders.legendary.rest.IMastermindEndpoint#deleteMastermind
     * (java.lang.Integer)
     */
    @Override
    public Response deleteMastermind(final Integer id) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            this.mastermindService.deleteMastermind(id);
        } catch (final NoResultException e) {
            responseBuilder = responseBuilder.status(Status.NOT_FOUND);
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.bryansaunders.legendary.rest.IMastermindEndpoint#getRandomMasterminds
     * (java.lang.Integer)
     */
    @Override
    public Response getRandomMasterminds(final Integer count) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final List<Mastermind> masterminds = this.mastermindService.getRandomMasterminds(count);
            responseBuilder = responseBuilder.entity(masterminds);
        } catch (final IllegalArgumentException e) {
            responseBuilder = responseBuilder.status(Status.BAD_REQUEST);
        }

        return responseBuilder.build();
    }
}
