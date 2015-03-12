package net.bryansaunders.legendary.util;

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
import java.util.Set;
import java.util.UUID;

import net.bryansaunders.legendary.model.CardClass;
import net.bryansaunders.legendary.model.CardSet;
import net.bryansaunders.legendary.model.Hero;
import net.bryansaunders.legendary.model.Leadable;
import net.bryansaunders.legendary.model.LeadableType;
import net.bryansaunders.legendary.model.Mastermind;
import net.bryansaunders.legendary.model.Scheme;
import net.bryansaunders.legendary.model.Team;

public abstract class LegendaryEntityFactory {

    public static Hero createHero() {
        final Set<CardClass> types = new HashSet<>();
        types.add(CardClass.RNG);

        final Hero hero = new Hero();
        hero.setName(UUID.randomUUID().toString());
        hero.setAffiliation(Team.AV);
        hero.setCardSet(CardSet.BASE);
        hero.setCommonTypes(types);
        hero.setUncommonTypes(types);
        hero.setRareTypes(types);

        return hero;
    }

    public static Leadable createLeadable() {
        final Leadable leadable = new Leadable();
        leadable.setName(UUID.randomUUID().toString());
        leadable.setCardSet(CardSet.DARKCITY);
        leadable.setType(LeadableType.HENCHMAN);
        
        return leadable;
    }
    
    public static Mastermind createMastermind(){
        final Mastermind mastermind = new Mastermind();
        mastermind.setName(UUID.randomUUID().toString());
        mastermind.setAttack("15+");
        mastermind.setCardSet(CardSet.FF4);
        
        Set<Leadable> leadables = new HashSet<>();
        leadables.add(createLeadable());
        mastermind.setAlwaysLeads(leadables);
        
        return mastermind;
    }

    public static Scheme createScheme() {
        Scheme scheme = new Scheme();
        scheme.setName(UUID.randomUUID().toString());
        scheme.setCardSet(CardSet.GOTG);
        
        Set<String> instructions = new HashSet<>();
        instructions.add("Setup Step 1");
        scheme.setSpecialInstructions(instructions);
        
        return scheme;
    }
}
