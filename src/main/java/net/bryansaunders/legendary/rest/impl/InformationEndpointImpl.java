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

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.bryansaunders.legendary.model.Information;
import net.bryansaunders.legendary.rest.IInformationEndpoint;
import net.bryansaunders.legendary.service.InformationService;

/**
 * @see IInformationEndpoint
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 */
public class InformationEndpointImpl implements IInformationEndpoint {
    
    /**
     * Information Service.
     */
    @Inject
    private InformationService infoService;

    /*
     * (non-Javadoc)
     * 
     * @see net.bryansaunders.dl2.service.rest.InfoService#getInformation()
     */
    @Override
    public Response getInformation() {
        final Information info = this.infoService.getInformation();
        return Response.status(Status.OK).entity(info).build();
    }
}
