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

import net.bryansaunders.legendary.dao.impl.LeadableDao;
import net.bryansaunders.legendary.model.Leadable;
import net.bryansaunders.legendary.model.LeadableType;

/**
 * Stores and Retrieves Leadable Data.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Stateless
public class LeadableService {

    /**
     * Leadable DAO.
     */
    @Inject
    private LeadableDao leadableDao;

    /**
     * Returns the Leadable with the Specified ID.
     * 
     * @param pId
     *            Leadable ID.
     * @return Leadable
     */
    public Leadable getLeadable(final Integer pId) {
        return this.leadableDao.get(pId);
    }

    /**
     * Returns All Leadables.
     * 
     * @return List of Leadables
     */
    public List<Leadable> getAllLeadables() {
        return this.leadableDao.getAll();
    }

    /**
     * Updates the given Leadable. Saves it if the ID does not exist.
     * 
     * @param id
     *            Leadable ID
     * 
     * @param leadable
     *            Updated Leadable
     * @return Updated Leadable
     */
    public Leadable updateLeadable(final Integer id, final Leadable leadable) {
        return this.leadableDao.save(leadable);
    }

    /**
     * Deletes The Specified Leadable.
     * 
     * @param id
     *            Leadable ID.
     */
    public void deleteLeadable(final Integer id) {
        this.leadableDao.delete(id);
    }

    /**
     * Gets the Specified Number of Random Leadables.
     * 
     * @param count
     *            Number of Leadables.
     * @param type
     *            Type of Leadables
     * @return List of Leadables
     */
    public List<Leadable> getRandomLeadables(final Integer count, final LeadableType type) {
        return this.leadableDao.getRandom(count, type);
    }

    /**
     * Saves the Provided Leadable.
     * 
     * @param leadable
     *            Leadable to Save
     * @return Saved Leadable
     */
    public Leadable saveLeadable(final Leadable leadable) {
        return this.leadableDao.save(leadable);
    }
}
