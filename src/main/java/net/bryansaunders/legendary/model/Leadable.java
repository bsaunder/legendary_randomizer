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


import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Represents a Charaacters that is Leadable by a Mastermind.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Entity
public class Leadable extends LegendaryEntity {
    
    /**
     * Serial Version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Leadable Type.
     */
    @NotNull
    private LeadableType type;

    /**
     * Get the type.
     * 
     * @return the type
     */
    public LeadableType getType() {
        return this.type;
    }

    /**
     * Set the type.
     * 
     * @param pType
     *            the type to set
     */
    public void setType(final LeadableType pType) {
        this.type = pType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Leadable [type=" + this.type + ", name=" + this.getName() + "]";
    }

}
