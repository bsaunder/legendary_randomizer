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


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.bryansaunders.legendary.dao.impl.SchemeDao;
import net.bryansaunders.legendary.model.Scheme;

/**
 * Stores and Retrieves Scheme Data.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Stateless
public class SchemeService {

    /**
     * Scheme DAO.
     */
    @Inject
    private SchemeDao schemeDao;

    /**
     * Returns the Scheme with the Specified ID.
     * 
     * @param pId
     *            Scheme ID.
     * @return Scheme
     */
    public Scheme getScheme(final Integer pId) {
        return this.schemeDao.get(pId);
    }

    /**
     * Returns All Schemes.
     * 
     * @return List of Schemes
     */
    public List<Scheme> getAllSchemes() {
        return this.schemeDao.getAll();
    }

    /**
     * Updates the given Scheme. Saves it if the ID does not exist.
     * 
     * @param id
     *            Scheme ID
     * 
     * @param scheme
     *            Updated Scheme
     * @return Updated Scheme
     */
    public Scheme updateScheme(final Integer id, final Scheme scheme) {
        return this.schemeDao.save(scheme);
    }

    /**
     * Deletes The Specified Scheme.
     * 
     * @param id
     *            Scheme ID.
     */
    public void deleteScheme(final Integer id) {
        this.schemeDao.delete(id);
    }

    /**
     * Gets the Specified Number of Random Schemes.
     * 
     * @param count
     *            Number of Schemes.
     * @return List of Schemes
     */
    public List<Scheme> getRandomSchemes(final Integer count) {
        return this.schemeDao.getRandom(count);
    }

    /**
     * Saves the Provided Scheme.
     * 
     * @param scheme
     *            Scheme to Save
     * @return Saved Scheme
     */
    public Scheme saveScheme(final Scheme scheme) {
        return this.schemeDao.save(scheme);
    }

}
