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


import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Transient;

/**
 * Scheme.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Entity
public class Scheme extends LegendaryEntity {

    /**
     * Default Serial ID.
     */
    @Transient
    private static final long serialVersionUID = 1L;

    /**
     * Special Instructions.
     */
    @ElementCollection(fetch=FetchType.EAGER)
    private Set<String> specialInstructions;

    /**
     * Get the specialInstructions.
     * 
     * @return the specialInstructions
     */
    public Set<String> getSpecialInstructions() {
        return this.specialInstructions;
    }

    /**
     * Set the specialInstructions.
     * 
     * @param pSpecialInstructions
     *            the specialInstructions to set
     */
    public void setSpecialInstructions(Set<String> pSpecialInstructions) {
        this.specialInstructions = pSpecialInstructions;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Scheme [specialInstructions=" + this.specialInstructions + ", getName()=" + this.getName() + "]";
    }
    
    

}
