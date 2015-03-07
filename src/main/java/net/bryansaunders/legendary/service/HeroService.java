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

import net.bryansaunders.legendary.dao.HeroDao;
import net.bryansaunders.legendary.model.Hero;

/**
 * Stores and Retrieves Hero Data.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Stateless
public class HeroService {

    /**
     * Hero DAO.
     */
    @Inject
    private HeroDao heroDao;

    /**
     * Returns the Hero with the Specified ID.
     * 
     * @param pId
     *            Hero ID.
     * @return Hero
     */
    public Hero getHero(final Integer pId) {
        return this.heroDao.get(pId);
    }

    /**
     * Returns All Heroes.
     * 
     * @return List of Heroes
     */
    public List<Hero> getAllHeroes() {
        return this.heroDao.getAll();
    }

    /**
     * Updates the given Hero. Saves it if the ID does not exist.
     * 
     * @param id
     *            Hero ID
     * 
     * @param hero
     *            Updated Hero
     * @return Updated Hero
     */
    public Hero updateHero(final Integer id, final Hero hero) {
        return this.heroDao.save(hero);
    }

    /**
     * Deletes The Specified Hero.
     * 
     * @param id
     *            Hero ID.
     */
    public void deleteHero(final Integer id) {
        this.heroDao.delete(id);
    }

    /**
     * Gets the Specified Number of Random Heroes.
     * 
     * @param count
     *            Number of Heroes.
     * @return List of Heroes
     */
    public List<Hero> getRandomHeroes(Integer count) {
        return this.heroDao.getRandom(count);
    }
}
