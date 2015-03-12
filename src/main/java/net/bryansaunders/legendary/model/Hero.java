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

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Hero.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Entity
public class Hero extends LegendaryEntity {

    /**
     * Serial Version ID.
     */
    @Transient
    private static final long serialVersionUID = 1L;

    /**
     * Team.
     */
    @NotNull
    private Team affiliation;

    /**
     * Common Card Types.
     */
    @NotNull
    @ElementCollection(targetClass = CardClass.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "hero_commonType")
    @Column(name = "commonType")
    private Set<CardClass> commonTypes;

    /**
     * Uncommon Card Types.
     */
    @NotNull
    @ElementCollection(targetClass = CardClass.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "hero_uncommonType")
    @Column(name = "uncommonType")
    private Set<CardClass> uncommonTypes;

    /**
     * Rare Card Types.
     */
    @NotNull
    @ElementCollection(targetClass = CardClass.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "hero_rareType")
    @Column(name = "rareType")
    private Set<CardClass> rareTypes;

    /**
     * Get the affiliation.
     * 
     * @return the affiliation
     */
    public Team getAffiliation() {
        return this.affiliation;
    }

    /**
     * Set the affiliation.
     * 
     * @param pAffiliation
     *            the affiliation to set
     */
    public void setAffiliation(final Team pAffiliation) {
        this.affiliation = pAffiliation;
    }

    /**
     * Get the commonTypes.
     * 
     * @return the commonTypes
     */
    public Set<CardClass> getCommonTypes() {
        return this.commonTypes;
    }

    /**
     * Set the commonTypes.
     * 
     * @param pCommonTypes
     *            the commonTypes to set
     */
    public void setCommonTypes(final Set<CardClass> pCommonTypes) {
        this.commonTypes = pCommonTypes;
    }

    /**
     * Get the uncommonTypes.
     * 
     * @return the uncommonTypes
     */
    public Set<CardClass> getUncommonTypes() {
        return this.uncommonTypes;
    }

    /**
     * Set the uncommonTypes.
     * 
     * @param pUncommonTypes
     *            the uncommonTypes to set
     */
    public void setUncommonTypes(final Set<CardClass> pUncommonTypes) {
        this.uncommonTypes = pUncommonTypes;
    }

    /**
     * Get the rareTypes.
     * 
     * @return the rareTypes
     */
    public Set<CardClass> getRareTypes() {
        return this.rareTypes;
    }

    /**
     * Set the rareTypes.
     * 
     * @param pRareTypes
     *            the rareTypes to set
     */
    public void setRareTypes(final Set<CardClass> pRareTypes) {
        this.rareTypes = pRareTypes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Hero [affiliation=" + this.affiliation + ", commonTypes=" + this.commonTypes + ", uncommonTypes="
                + this.uncommonTypes + ", rareTypes=" + this.rareTypes + "]";
    }

}
