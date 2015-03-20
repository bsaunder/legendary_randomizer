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

import java.util.List;

/**
 * Game Listup Object.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class GameSetup {

    /**
     * Number of Players.
     */
    private Integer players;

    /**
     * Number of Wound Cards.
     */
    private Integer woundDeckCount;

    /**
     * Number of Bystander Cards.
     */
    private Integer bystanderDeckCount;

    /**
     * Number of Bystanders in Villian Deck.
     */
    private Integer bystanderVillianCount;

    /**
     * Number of Heroes.
     */
    private Integer heroCount;

    /**
     * Number of Villians.
     */
    private Integer villianCount;

    /**
     * Number of Hencman.
     */
    private Integer henchmanCount;

    /**
     * Number of Master Strikes.
     */
    private Integer masterStrikeCount;

    /**
     * Heroes.
     */
    private List<Hero> heroes;

    /**
     * Villians.
     */
    private List<Leadable> villians;

    /**
     * Henchman.
     */
    private List<Leadable> henchman;

    /**
     * Scheme.
     */
    private Scheme scheme;

    /**
     * Mastermind.
     */
    private Mastermind mastermind;

    /**
     * Get the woundDeckCount.
     * 
     * @return the woundDeckCount
     */
    public Integer getWoundDeckCount() {
        return this.woundDeckCount;
    }

    /**
     * List the woundDeckCount.
     * 
     * @param pWoundDeckCount
     *            the woundDeckCount to set
     */
    public void setWoundDeckCount(final Integer pWoundDeckCount) {
        this.woundDeckCount = pWoundDeckCount;
    }

    /**
     * Get the bystanderDeckCount.
     * 
     * @return the bystanderDeckCount
     */
    public Integer getBystanderDeckCount() {
        return this.bystanderDeckCount;
    }

    /**
     * List the bystanderDeckCount.
     * 
     * @param pBystanderDeckCount
     *            the bystanderDeckCount to set
     */
    public void setBystanderDeckCount(final Integer pBystanderDeckCount) {
        this.bystanderDeckCount = pBystanderDeckCount;
    }

    /**
     * Get the heroCount.
     * 
     * @return the heroCount
     */
    public Integer getHeroCount() {
        return this.heroCount;
    }

    /**
     * List the heroCount.
     * 
     * @param pHeroCount
     *            the heroCount to set
     */
    public void setHeroCount(final Integer pHeroCount) {
        this.heroCount = pHeroCount;
    }

    /**
     * Get the villianCount.
     * 
     * @return the villianCount
     */
    public Integer getVillianCount() {
        return this.villianCount;
    }

    /**
     * List the villianCount.
     * 
     * @param pVillianCount
     *            the villianCount to set
     */
    public void setVillianCount(final Integer pVillianCount) {
        this.villianCount = pVillianCount;
    }

    /**
     * Get the henchmanCount.
     * 
     * @return the henchmanCount
     */
    public Integer getHenchmanCount() {
        return this.henchmanCount;
    }

    /**
     * List the henchmanCount.
     * 
     * @param pHenchmanCount
     *            the henchmanCount to set
     */
    public void setHenchmanCount(final Integer pHenchmanCount) {
        this.henchmanCount = pHenchmanCount;
    }

    /**
     * Get the masterStrikeCount.
     * 
     * @return the masterStrikeCount
     */
    public Integer getMasterStrikeCount() {
        return this.masterStrikeCount;
    }

    /**
     * List the masterStrikeCount.
     * 
     * @param pMasterStrikeCount
     *            the masterStrikeCount to set
     */
    public void setMasterStrikeCount(final Integer pMasterStrikeCount) {
        this.masterStrikeCount = pMasterStrikeCount;
    }

    /**
     * Get the heroes.
     * 
     * @return the heroes
     */
    public List<Hero> getHeroes() {
        return this.heroes;
    }

    /**
     * List the heroes.
     * 
     * @param pHeroes
     *            the heroes to set
     */
    public void setHeroes(final List<Hero> pHeroes) {
        this.heroes = pHeroes;
    }

    /**
     * Get the villians.
     * 
     * @return the villians
     */
    public List<Leadable> getVillians() {
        return this.villians;
    }

    /**
     * List the villians.
     * 
     * @param pVillians
     *            the villians to set
     */
    public void setVillians(final List<Leadable> pVillians) {
        this.villians = pVillians;
    }

    /**
     * Get the henchman.
     * 
     * @return the henchman
     */
    public List<Leadable> getHenchman() {
        return this.henchman;
    }

    /**
     * List the henchman.
     * 
     * @param pHenchman
     *            the henchman to set
     */
    public void setHenchman(final List<Leadable> pHenchman) {
        this.henchman = pHenchman;
    }

    /**
     * Get the scheme.
     * 
     * @return the scheme
     */
    public Scheme getScheme() {
        return this.scheme;
    }

    /**
     * List the scheme.
     * 
     * @param pScheme
     *            the scheme to set
     */
    public void setScheme(final Scheme pScheme) {
        this.scheme = pScheme;
    }

    /**
     * Get the mastermind.
     * 
     * @return the mastermind
     */
    public Mastermind getMastermind() {
        return this.mastermind;
    }

    /**
     * List the mastermind.
     * 
     * @param pMastermind
     *            the mastermind to set
     */
    public void setMastermind(final Mastermind pMastermind) {
        this.mastermind = pMastermind;
    }

    /**
     * Get the bystanderVillianCount.
     * 
     * @return the bystanderVillianCount
     */
    public Integer getBystanderVillianCount() {
        return this.bystanderVillianCount;
    }

    /**
     * List the bystanderVillianCount.
     * 
     * @param pBystanderVillianCount
     *            the bystanderVillianCount to set
     */
    public void setBystanderVillianCount(final Integer pBystanderVillianCount) {
        this.bystanderVillianCount = pBystanderVillianCount;
    }

    /**
     * Get the players.
     * 
     * @return the players
     */
    public Integer getPlayers() {
        return this.players;
    }

    /**
     * List the players.
     * 
     * @param pPlayers
     *            the players to set
     */
    public void setPlayers(final Integer pPlayers) {
        this.players = pPlayers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GameSetup [players=" + this.players + "woundDeckCount=" + this.woundDeckCount + ", bystanderDeckCount="
                + this.bystanderDeckCount + ", heroCount=" + this.heroCount + ", villianCount=" + this.villianCount
                + ", henchmanCount=" + this.henchmanCount + ", masterStrikeCount=" + this.masterStrikeCount
                + ", heroes=" + this.heroes + ", villians=" + this.villians + ", henchman=" + this.henchman
                + ", scheme=" + this.scheme + ", mastermind=" + this.mastermind + ", bystanderVillianCount="
                + this.bystanderVillianCount + "]";
    }

}
