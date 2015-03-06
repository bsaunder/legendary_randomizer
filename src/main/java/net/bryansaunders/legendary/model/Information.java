package net.bryansaunders.legendary.model;

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


/**
 * Information Object that contains Application Information.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class Information {

    /**
     * Application Name.
     */
    private String name;

    /**
     * Application Description.
     */
    private String description;

    /**
     * Application Version.
     */
    private String version;

    /**
     * Get the name.
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name.
     * 
     * @param pName
     *            the name to set
     */
    public void setName(final String pName) {
        this.name = pName;
    }

    /**
     * Get the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description.
     * 
     * @param pDescription
     *            the description to set
     */
    public void setDescription(final String pDescription) {
        this.description = pDescription;
    }

    /**
     * Get the version.
     * 
     * @return the version
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Set the version.
     * 
     * @param pVersion
     *            the version to set
     */
    public void setVersion(final String pVersion) {
        this.version = pVersion;
    }

}
