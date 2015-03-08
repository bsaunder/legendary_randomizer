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

import net.bryansaunders.legendary.dao.impl.MastermindDao;
import net.bryansaunders.legendary.model.Mastermind;

/**
 * Stores and Retrieves Mastermind Data.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Stateless
public class MastermindService {

    /**
     * Mastermind DAO.
     */
    @Inject
    private MastermindDao mastermindDao;

    /**
     * Returns the Mastermind with the Specified ID.
     * 
     * @param pId
     *            Mastermind ID.
     * @return Mastermind
     */
    public Mastermind getMastermind(final Integer pId) {
        return this.mastermindDao.get(pId);
    }

    /**
     * Returns All Masterminds.
     * 
     * @return List of Masterminds
     */
    public List<Mastermind> getAllMasterminds() {
        return this.mastermindDao.getAll();
    }

    /**
     * Updates the given Mastermind. Saves it if the ID does not exist.
     * 
     * @param id
     *            Mastermind ID
     * 
     * @param mastermind
     *            Updated Mastermind
     * @return Updated Mastermind
     */
    public Mastermind updateMastermind(final Integer id, final Mastermind mastermind) {
        return this.mastermindDao.save(mastermind);
    }

    /**
     * Deletes The Specified Mastermind.
     * 
     * @param id
     *            Mastermind ID.
     */
    public void deleteMastermind(final Integer id) {
        this.mastermindDao.delete(id);
    }

    /**
     * Gets the Specified Number of Random Masterminds.
     * 
     * @param count
     *            Number of Masterminds.
     * @return List of Masterminds
     */
    public List<Mastermind> getRandomMasterminds(final Integer count) {
        return this.mastermindDao.getRandom(count);
    }

    /**
     * Saves the Provided Mastermind.
     * 
     * @param mastermind
     *            Mastermind to Save
     * @return Saved Mastermind
     */
    public Mastermind saveMastermind(final Mastermind mastermind) {
        return this.mastermindDao.save(mastermind);
    }
}
