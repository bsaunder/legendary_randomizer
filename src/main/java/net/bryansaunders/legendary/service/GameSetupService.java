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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.bryansaunders.legendary.dao.impl.HeroDao;
import net.bryansaunders.legendary.dao.impl.LeadableDao;
import net.bryansaunders.legendary.dao.impl.MastermindDao;
import net.bryansaunders.legendary.dao.impl.SchemeDao;
import net.bryansaunders.legendary.model.GameSetup;
import net.bryansaunders.legendary.model.GameSetupFactory;
import net.bryansaunders.legendary.model.Hero;
import net.bryansaunders.legendary.model.Leadable;
import net.bryansaunders.legendary.model.LeadableType;
import net.bryansaunders.legendary.model.Mastermind;
import net.bryansaunders.legendary.model.Scheme;

/**
 * Service for Building Game Setups.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Stateless
public class GameSetupService {

    /**
     * Scheme Dao.
     */
    @Inject
    private SchemeDao schemeDao;

    /**
     * Mastermind Dao.
     */
    @Inject
    private MastermindDao mastermindDao;

    /**
     * Hero Dao.
     */
    @Inject
    private HeroDao heroDao;

    /**
     * Leadable Dao.
     */
    @Inject
    private LeadableDao leadableDao;

    /**
     * Build a Game Setup based on the Number of Players, Scheme, and
     * Mastermind.
     * 
     * @param pPlayerCount
     *            Number of Players.
     * @param pSchemeId
     *            Scheme.
     * @param pMastermindId
     *            Mastermind.
     * @return Game Setup
     */
    public GameSetup buildGameSetup(final Integer pPlayerCount, final Integer pSchemeId, final Integer pMastermindId) {
        final GameSetup setup = GameSetupFactory.createDefaultSetup(pPlayerCount);

        // Get Scheme
        if (pSchemeId != null) {
            final Scheme scheme = this.schemeDao.get(pSchemeId);
            setup.setScheme(scheme);
        } else {
            final Scheme scheme = this.schemeDao.getRandom(1).get(0);
            setup.setScheme(scheme);
        }

        // Get Heroes
        final List<Hero> heroes = this.heroDao.getRandom(setup.getHeroCount());
        setup.setHeroes(heroes);

        // Get Mastermind
        if (pMastermindId != null) {
            final Mastermind mastermind = this.mastermindDao.get(pMastermindId);
            setup.setMastermind(mastermind);
        } else {
            final Mastermind mastermind = this.mastermindDao.getRandom(1).get(0);
            setup.setMastermind(mastermind);
        }

        // Exclude AlwaysLeads
        Set<Leadable> excludedHenchman = new HashSet<Leadable>();
        Set<Leadable> excludedVillians = new HashSet<Leadable>();
        for (Leadable leadable : setup.getMastermind().getAlwaysLeads()) {
            if (leadable.getType() == LeadableType.HENCHMAN) {
                setup.setHenchmanCount(setup.getHenchmanCount() - 1);
                setup.getHenchman().add(leadable);
                excludedHenchman.add(leadable);
            } else {
                setup.setVillianCount(setup.getVillianCount() - 1);
                setup.getVillians().add(leadable);
                excludedVillians.add(leadable);
            }
        }

        // Get Henchman
        final List<Leadable> henchman = this.leadableDao.getRandomAndExclude(setup.getHenchmanCount(), LeadableType.HENCHMAN, excludedHenchman);
        setup.setHenchman(henchman);

        // Get Villians
        final List<Leadable> villians = this.leadableDao.getRandomAndExclude(setup.getVillianCount(), LeadableType.VILLAIN, excludedVillians);
        setup.setVillians(villians);

        return setup;
    }
}
