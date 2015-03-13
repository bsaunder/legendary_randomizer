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

    @Inject
    private SchemeDao schemeDao;

    @Inject
    private MastermindDao mastermindDao;

    @Inject
    private HeroDao heroDao;

    @Inject
    private LeadableDao leadableDao;

    public GameSetup buildGameSetup(Integer playerCount, Integer schemeId, Integer mastermindId) {
        GameSetup setup = GameSetupFactory.createDefaultSetup(playerCount);

        // Get Scheme
        if (schemeId != null) {
            Scheme scheme = this.schemeDao.get(schemeId);
            setup.setScheme(scheme);
        } else {
            Scheme scheme = this.schemeDao.getRandom(1).get(0);
            setup.setScheme(scheme);
        }

        // Get Heroes
        List<Hero> heroes = this.heroDao.getRandom(setup.getHeroCount());
        setup.setHeroes(heroes);

        // Get Mastermind
        if (mastermindId != null) {
            Mastermind mastermind = this.mastermindDao.get(mastermindId);
            setup.setMastermind(mastermind);
        } else {
            Mastermind mastermind = this.mastermindDao.getRandom(1).get(0);
            setup.setMastermind(mastermind);
        }

        // Get Henchman
        List<Leadable> henchman = this.leadableDao.getRandom(setup.getHenchmanCount(), LeadableType.HENCHMAN);
        setup.setHenchman(henchman);

        // Get Villians
        List<Leadable> villians = this.leadableDao.getRandom(setup.getVillianCount(), LeadableType.VILLAIN);
        setup.setVillians(villians);

        return setup;
    }
}
