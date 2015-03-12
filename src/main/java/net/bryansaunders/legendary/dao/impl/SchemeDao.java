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

import net.bryansaunders.legendary.model.Scheme;

/**
 * Scheme DAO.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class SchemeDao extends GenericDaoImpl<Scheme> {

    /**
     * Gets Random Schemes.
     * 
     * @param count
     *            Number of Schemes.
     * @return List of Schemes.
     */
    public List<Scheme> getRandom(final Integer count) {
        final List<Scheme> schemes = new LinkedList<Scheme>();

        // Get All IDs
        final Query query = this.getEntityManager().createQuery("SELECT DISTINCT s.id FROM Scheme s");
        final List<?> idList = query.getResultList();

        if (idList.size() < count) {
            throw new IllegalArgumentException("Not Enough Schemes");
        }

        // Shuffle
        Collections.shuffle(idList);

        // Get Heroes
        for (int i = 0; i < count; i++) {
            final Integer id = (Integer) idList.get(i);
            final Scheme scheme = this.get(id);
            schemes.add(scheme);
        }

        return schemes;
    }

}
