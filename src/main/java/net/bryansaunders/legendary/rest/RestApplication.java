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

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.wordnik.swagger.jaxrs.config.BeanConfig;

/**
 * Initializes the REST Services API.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@ApplicationPath("/rest")
public class RestApplication extends Application {

    /**
     * Default Constructor.
     */
    public RestApplication() {
        final BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("hhttp://bts-fsw.airdns.org:30454/rest");
        beanConfig.setResourcePackage("net.bryansaunders.legendary.rest.impl");
        beanConfig.setDescription("Game Setup API for Marvel Legendary");
        beanConfig.setScan(true);
    }
}
