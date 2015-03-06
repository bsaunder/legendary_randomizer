package net.bryansaunders.legendary.util;

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


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Header;

/**
 * Builds Response Objects.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 *
 */
public class JaxRsResponseBuilder {

    public static final String RESPONSE_STATUS_HEADER = "ResponseStatus";

    /**
     * Generates a Response Object from the Message. The Message Body becomes the Response Entity. The Status will be
     * set to the value of the "ResponseStatus" header or default to 200.
     * 
     * @param pBody Message Body.
     * @param pStatus "ResponseStatus" Header Value.
     * @return Response
     */
    @Handler
    public Response generateResponse(@Body Object pBody, @Header(RESPONSE_STATUS_HEADER) Status pStatus) {
        Status status = pStatus;
        if(status == null){
            status = Status.OK;
        }
        return Response.status(status).entity(pBody).build();
    }
}
