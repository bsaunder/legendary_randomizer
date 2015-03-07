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
 * Card Classes.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public enum CardClass {

    /**
     * Strength.
     */
    STR("Strength"),

    /**
     * Instinct.
     */
    INS("Instinct"),

    /**
     * Covert.
     */
    COV("Covert"),

    /**
     * Technology.
     */
    TEC("Technology"),

    /**
     * Ranged.
     */
    RNG("Ranged");

    /**
     * Set Description.
     */
    private String description;

    /**
     * Constructor.
     * 
     * @param pDescription
     *            the description to set
     */
    CardClass(final String pDescription) {
        this.description = pDescription;
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

}
