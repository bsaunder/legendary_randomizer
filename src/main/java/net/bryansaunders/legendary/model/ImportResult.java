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

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the Result of a File Import.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class ImportResult {

    /**
     * Import Status.
     */

    private boolean importSuccess;
    /**
     * Successful Imports. <Name, ID>
     */

    private Map<String, String> successfulImports;

    /**
     * Failed Imports. <Name, Error>
     */
    private Map<String, String> failedImports;

    /**
     * Default Constructor.
     */
    public ImportResult() {
        this.successfulImports = new HashMap<String, String>();
        this.failedImports = new HashMap<String, String>();
        this.importSuccess = true;
    }

    /**
     * Get the importSuccess.
     * 
     * @return the importSuccess
     */
    public boolean isImportSuccess() {
        return this.importSuccess;
    }

    /**
     * Set the importSuccess.
     * 
     * @param pImportSuccess
     *            the importSuccess to set
     */
    public void setImportSuccess(final boolean pImportSuccess) {
        this.importSuccess = pImportSuccess;
    }

    /**
     * Get the successfulImports.
     * 
     * @return the successfulImports
     */
    public Map<String, String> getSuccessfulImports() {
        return this.successfulImports;
    }

    /**
     * Set the successfulImports.
     * 
     * @param pSuccessfulImports
     *            the successfulImports to set
     */
    public void setSuccessfulImports(final Map<String, String> pSuccessfulImports) {
        this.successfulImports = pSuccessfulImports;
    }

    /**
     * Get the failedImports.
     * 
     * @return the failedImports
     */
    public Map<String, String> getFailedImports() {
        return this.failedImports;
    }

    /**
     * Set the failedImports.
     * 
     * @param pFailedImports
     *            the failedImports to set
     */
    public void setFailedImports(final Map<String, String> pFailedImports) {
        this.failedImports = pFailedImports;
    }

}
