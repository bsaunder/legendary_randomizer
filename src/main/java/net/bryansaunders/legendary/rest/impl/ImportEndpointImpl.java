/**
 * 
 */
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

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import net.bryansaunders.legendary.model.ImportResult;
import net.bryansaunders.legendary.rest.IImportEndpoint;
import net.bryansaunders.legendary.service.ImportService;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 * Implementation for Import Endpoint.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class ImportEndpointImpl implements IImportEndpoint {
    /**
     * Import Service.
     */
    @Inject
    private ImportService importService;

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IImportEndpoint#loadHeroData(java.io.File)
     */
    @Override
    public Response loadHeroData(final MultipartFormDataInput pHeroCsv) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final List<InputPart> inputParts = ImportService.getInputParts(pHeroCsv);
            for (final InputPart inputPart : inputParts) {

                final String body = inputPart.getBodyAsString();
                final List<String> lines = ImportService.getLinesAsList(body);

                final ImportResult result = this.importService.importHeroCsvData(lines);
                responseBuilder = responseBuilder.entity(result);
            }
        } catch (final IOException e) {
            responseBuilder = responseBuilder.status(Status.INTERNAL_SERVER_ERROR).entity(
                    "{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IImportEndpoint#loadLeadableData(java.io.File)
     */
    @Override
    public Response loadLeadableData(final MultipartFormDataInput pLeadableCsv) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final List<InputPart> inputParts = ImportService.getInputParts(pLeadableCsv);
            for (final InputPart inputPart : inputParts) {

                final String body = inputPart.getBodyAsString();
                final List<String> lines = ImportService.getLinesAsList(body);

                final ImportResult result = this.importService.importLeadableCsvData(lines);
                responseBuilder = responseBuilder.entity(result);
            }
        } catch (final IOException e) {
            responseBuilder = responseBuilder.status(Status.INTERNAL_SERVER_ERROR).entity(
                    "{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IImportEndpoint#loadSchemeData(java.io.File)
     */
    @Override
    public Response loadSchemeData(final MultipartFormDataInput pSchemeCsv) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final List<InputPart> inputParts = ImportService.getInputParts(pSchemeCsv);
            for (final InputPart inputPart : inputParts) {

                final String body = inputPart.getBodyAsString();
                final List<String> lines = ImportService.getLinesAsList(body);

                final ImportResult result = this.importService.importSchemeCsvData(lines);
                responseBuilder = responseBuilder.entity(result);
            }
        } catch (final IOException e) {
            responseBuilder = responseBuilder.status(Status.INTERNAL_SERVER_ERROR).entity(
                    "{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }

        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.legendary.rest.IImportEndpoint#loadMastermindData(java.io.File)
     */
    @Override
    public Response loadMastermindData(final MultipartFormDataInput pMastermindCsv) {
        ResponseBuilder responseBuilder = Response.status(Status.OK);
        try {
            final List<InputPart> inputParts = ImportService.getInputParts(pMastermindCsv);
            for (final InputPart inputPart : inputParts) {

                final String body = inputPart.getBodyAsString();
                final List<String> lines = ImportService.getLinesAsList(body);

                final ImportResult result = this.importService.importMastermindCsvData(lines);
                responseBuilder = responseBuilder.entity(result);
            }
        } catch (final IOException e) {
            responseBuilder = responseBuilder.status(Status.INTERNAL_SERVER_ERROR).entity(
                    "{\"error\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }

        return responseBuilder.build();
    }

}
