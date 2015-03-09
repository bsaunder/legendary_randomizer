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

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Mastermind.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Entity
public class Mastermind extends LegendaryEntity {
    
    /**
     * Serial Version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Mastermind Always Leads these Groups in Setup.
     */
    @OneToMany
    private Set<Leadable> alwaysLeads;

    /**
     * Attack Value.
     */
    @NotNull
    private String attack;

    /**
     * Get the alwaysLeads.
     * 
     * @return the alwaysLeads
     */
    public Set<Leadable> getAlwaysLeads() {
        return this.alwaysLeads;
    }

    /**
     * Set the alwaysLeads.
     * 
     * @param pAlwaysLeads
     *            the alwaysLeads to set
     */
    public void setAlwaysLeads(final Set<Leadable> pAlwaysLeads) {
        this.alwaysLeads = pAlwaysLeads;
    }

    /**
     * Get the attack.
     * 
     * @return the attack
     */
    public String getAttack() {
        return this.attack;
    }

    /**
     * Set the attack.
     * 
     * @param pAttack
     *            the attack to set
     */
    public void setAttack(final String pAttack) {
        this.attack = pAttack;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Mastermind [name=" + this.getName() + ", alwaysLeads=" + this.alwaysLeads + ", attack=" + this.attack
                + "]";
    }

}
