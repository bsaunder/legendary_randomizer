package net.bryansaunders.legendary.service;

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

import java.util.Properties;

import javax.inject.Inject;

import net.bryansaunders.legendary.model.Information;

import com.github.chrisruffalo.eeconfig.annotations.Configuration;
import com.github.chrisruffalo.eeconfig.annotations.Source;

/**
 * Retrieves Information about the Running Application.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class InformationService {

    /**
     * Application Configuration.
     */
    @Inject
    @Configuration(sources = { @Source(value = "resource:application.properties") })
    private Properties appConfig;

    /**
     * Gets the Application Information.
     * 
     * @return Information
     */
    public Information getInformation() {
        final Information info = new Information();
        info.setName(this.appConfig.getProperty("project.name"));
        info.setDescription(this.appConfig.getProperty("project.description"));
        info.setVersion(this.appConfig.getProperty("project.version"));

        return info;
    }
}


