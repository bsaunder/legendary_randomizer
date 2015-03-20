package net.bryansaunders.legendary.dao.impl;

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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import net.bryansaunders.legendary.model.Leadable;
import net.bryansaunders.legendary.model.LeadableType;

/**
 * Leadable DAO.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class LeadableDao extends GenericDaoImpl<Leadable> {

    /**
     * Gets Random Leadables.
     * 
     * @param count
     *            Number of Leadables.
     * @param type
     *            Type of Leadables to get
     * @return List of Leadables.
     */
    public List<Leadable> getRandom(final Integer count, final LeadableType type) {
        final List<Leadable> leadables = new LinkedList<Leadable>();

        // Get All IDs
        String queryString = "SELECT DISTINCT l.id FROM Leadable l";
        if (type != null) {
            queryString += " WHERE l.type = :type";
        }
        final Query query = this.getEntityManager().createQuery(queryString);

        if (type != null) {
            query.setParameter("type", type);
        }
        final List<?> idList = query.getResultList();

        if (idList.size() < count) {
            throw new IllegalArgumentException("Not Enough Leadables");
        }

        // Shuffle
        Collections.shuffle(idList);

        // Get Heroes
        for (int i = 0; i < count; i++) {
            final Integer id = (Integer) idList.get(i);
            final Leadable leadable = this.get(id);
            leadables.add(leadable);
        }

        return leadables;
    }

    /**
     * Gets Random Leadables.
     * 
     * @param count
     *            Number of Leadables.
     * @return List of Leadables.
     */
    public List<Leadable> getRandom(final Integer count) {
        return this.getRandom(count, null);
    }

    /**
     * Gets the Leadable for the Specified Name.
     * 
     * @param name
     *            Leadable Name
     * @return Leadable
     */
    public Leadable getByName(final String name) {
        Leadable leadable = null;

        final String queryString = "SELECT l FROM Leadable l WHERE l.name = :name";
        final TypedQuery<Leadable> query = this.getEntityManager().createQuery(queryString, Leadable.class);
        query.setParameter("name", name);

        leadable = query.getSingleResult();

        return leadable;
    }
}
