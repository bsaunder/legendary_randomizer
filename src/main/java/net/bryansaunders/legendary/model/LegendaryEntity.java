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

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * Base Entity for all Legendary Entities.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@MappedSuperclass
public class LegendaryEntity implements Serializable {

    /**
     * Serial Version ID.
     */
    @Transient
    private static final long serialVersionUID = 1L;

    /**
     * Id. Auto-Generated.
     */
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /**
     * Version.
     */
    @Version
    private Integer version;

    /**
     * Creation Date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    /**
     * Updated Date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated;

    /**
     * Entity Name.
     */
    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    /**
     * Card Set.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "cardSet")
    private CardSet cardSet;

    /**
     * Pre-Persist Logic.
     */
    @PrePersist
    protected void onCreate() {
        this.created = new Date();
        this.updated = this.created;
    }

    /**
     * Pre-Update Logic.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updated = new Date();
    }

    /**
     * Get the created.
     * 
     * @return the created
     */
    public Date getCreated() {
        Date date = null;
        if (this.created != null) {
            date = new Date(this.created.getTime());
        }
        return date;
    }

    /**
     * Set the created.
     * 
     * @param newCreated
     *            the newCreated to set
     */
    public void setCreated(final Date newCreated) {
        if (newCreated == null) {
            this.created = null;
        } else {
            this.created = new Date(newCreated.getTime());
        }
    }

    /**
     * Get the updated.
     * 
     * @return the updated
     */
    public Date getUpdated() {
        Date date = null;
        if (this.updated != null) {
            date = new Date(this.updated.getTime());
        }
        return date;
    }

    /**
     * Set the updated.
     * 
     * @param newUpdated
     *            the updated to set
     */
    public void setUpdated(final Date newUpdated) {
        if (newUpdated == null) {
            this.updated = null;
        } else {
            this.updated = new Date(newUpdated.getTime());
        }
    }

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
     * Get the cardSet.
     * 
     * @return the cardSet
     */
    public CardSet getCardSet() {
        return this.cardSet;
    }

    /**
     * Set the cardSet.
     * 
     * @param pCardSet
     *            the cardSet to set
     */
    public void setCardSet(final CardSet pCardSet) {
        this.cardSet = pCardSet;
    }

    /**
     * Get the id.
     * 
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Get the version.
     * 
     * @return the version
     */
    public Integer getVersion() {
        return this.version;
    }

    /**
     * Set the id.
     * 
     * @param pId
     *            the id to set
     */
    public void setId(final Integer pId) {
        this.id = pId;
    }

    /**
     * Set the version.
     * 
     * @param pVersion
     *            the version to set
     */
    public void setVersion(final Integer pVersion) {
        this.version = pVersion;
    }

}
