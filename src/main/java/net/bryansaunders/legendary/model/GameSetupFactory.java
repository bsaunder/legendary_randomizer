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


public final class GameSetupFactory {

    /**
     * Creates a Default Game Setup.
     * 
     * @param playerCount
     *            Number of Players
     * @return Game Setup Object with Defaults
     */
    public static GameSetup createDefaultSetup(Integer playerCount) {
        GameSetup setup = new GameSetup();

        // Standard Defaults
        setup.setPlayers(playerCount);
        setup.setMasterStrikeCount(5);
        setup.setWoundDeckCount(30);
        setup.setBystanderDeckCount(24);
        
        switch (playerCount) {
            case 2:
                setup.setHeroCount(5);
                setup.setVillianCount(2);
                setup.setHenchmanCount(1);
                setup.setBystanderVillianCount(2);
                break;
            case 3:
                setup.setHeroCount(5);
                setup.setVillianCount(3);
                setup.setHenchmanCount(1);
                setup.setBystanderVillianCount(8);
                break;
            case 4:
                setup.setHeroCount(5);
                setup.setVillianCount(3);
                setup.setHenchmanCount(2);
                setup.setBystanderVillianCount(8);
                break;
            case 5:
                setup.setHeroCount(6);
                setup.setVillianCount(4);
                setup.setHenchmanCount(2);
                setup.setBystanderVillianCount(12);
                break;
            default:
                throw new IllegalArgumentException("Player Count must be 2-5");
        }

        return setup;
    }
}
