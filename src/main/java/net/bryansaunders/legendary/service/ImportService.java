/**
 * 
 */
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.core.MultivaluedMap;

import net.bryansaunders.legendary.dao.impl.HeroDao;
import net.bryansaunders.legendary.dao.impl.LeadableDao;
import net.bryansaunders.legendary.dao.impl.MastermindDao;
import net.bryansaunders.legendary.dao.impl.SchemeDao;
import net.bryansaunders.legendary.model.CardClass;
import net.bryansaunders.legendary.model.CardSet;
import net.bryansaunders.legendary.model.Hero;
import net.bryansaunders.legendary.model.ImportResult;
import net.bryansaunders.legendary.model.Leadable;
import net.bryansaunders.legendary.model.LeadableType;
import net.bryansaunders.legendary.model.Mastermind;
import net.bryansaunders.legendary.model.Scheme;
import net.bryansaunders.legendary.model.Team;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 * Imports CSV Data.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Stateless
public class ImportService {

    /**
     * Regex for Splitting Strings on "|".
     */
    private static final String REGEX_PIPE = "\\|";

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
     * Import Hero CSV Data.
     * 
     * @param csvData
     *            CSV Data
     * @return Import Status
     */
    public ImportResult importHeroCsvData(final List<String> csvData) {

        final ImportResult result = new ImportResult();
        result.setImportSuccess(true);

        // Process Each Line
        for (final String line : csvData) {
            String[] tokens = new String[5];
            try {
                // Split Line on Comma
                tokens = line.split(",");

                // Process Tokens and Create Hero
                // [0] - Name
                // [1] - Set
                // [2] - Affiliation
                // [3] - Common Types
                // [4] - Uncommon Types
                // [5] - Rare Types

                final Hero hero = new Hero();
                hero.setName(tokens[0]);
                hero.setCardSet(CardSet.valueOf(tokens[1]));
                hero.setAffiliation(Team.valueOf(tokens[2]));

                final Set<CardClass> commonTypesSet = new HashSet<CardClass>();
                final String[] commonTypes = tokens[3].split(ImportService.REGEX_PIPE);
                for (final String type : commonTypes) {
                    commonTypesSet.add(CardClass.valueOf(type));
                }
                hero.setCommonTypes(commonTypesSet);

                final Set<CardClass> uncommonTypesSet = new HashSet<CardClass>();
                final String[] uncommonTypes = tokens[4].split(ImportService.REGEX_PIPE);
                for (final String type : uncommonTypes) {
                    uncommonTypesSet.add(CardClass.valueOf(type));
                }
                hero.setUncommonTypes(uncommonTypesSet);

                final Set<CardClass> rareTypesSet = new HashSet<CardClass>();
                final String[] rareTypes = tokens[5].split(ImportService.REGEX_PIPE);
                for (final String type : rareTypes) {
                    rareTypesSet.add(CardClass.valueOf(type));
                }
                hero.setRareTypes(rareTypesSet);

                final Hero savedHero = this.heroDao.save(hero);

                result.getSuccessfulImports().put(savedHero.getName(), savedHero.getId().toString());
            } catch (final NullPointerException npe) {
                result.getFailedImports().put(tokens[0], npe.getMessage());
                result.setImportSuccess(false);
            }
        }

        return result;
    }

    /**
     * Gets the List of InputPart objects from the Form Data.
     * 
     * @param pFormData
     *            Form Data
     * @return List of InputPart
     */
    public static List<InputPart> getInputParts(final MultipartFormDataInput pFormData) {
        final Map<String, List<InputPart>> uploadForm = pFormData.getFormDataMap();
        final List<InputPart> inputParts = uploadForm.get("csvFile");

        return inputParts;
    }

    /**
     * Gets the Filename from the InputPart Headers.
     * 
     * @param header
     *            Header Map
     * @return Filename
     */
    public static String getFileName(final MultivaluedMap<String, String> header) {
        String result = "unknown";

        final String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (final String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                final String[] name = filename.split("=");

                final String finalFileName = name[1].trim().replaceAll("\"", "");
                result = finalFileName;
            }
        }
        return result;
    }

    /**
     * Converts the String Body into a List, with one Line per Entry.
     * 
     * @param body
     *            String Body
     * @return List of Strings.
     * @throws IOException
     *             on Read Error.
     */
    public static List<String> getLinesAsList(final String body) throws IOException {
        final BufferedReader rdr = new BufferedReader(new StringReader(body));
        final List<String> lines = new ArrayList<String>();
        for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
            lines.add(line);
        }
        rdr.close();

        return lines;
    }

    /**
     * Import Leadable CSV Data.
     * 
     * @param csvData
     *            CSV Data
     * @return Import Status
     */
    public ImportResult importLeadableCsvData(final List<String> csvData) {
        final ImportResult result = new ImportResult();
        result.setImportSuccess(true);

        // Process Each Line
        for (final String line : csvData) {
            String[] tokens = new String[5];
            try {
                // Split Line on Comma
                tokens = line.split(",");

                // Process Tokens and Create Hero
                // [0] - Name
                // [1] - Set
                // [2] - Type

                final Leadable leadable = new Leadable();
                leadable.setName(tokens[0]);
                leadable.setCardSet(CardSet.valueOf(tokens[1]));
                leadable.setType(LeadableType.valueOf(tokens[2]));

                final Leadable savedLeadable = this.leadableDao.save(leadable);

                result.getSuccessfulImports().put(savedLeadable.getName(), savedLeadable.getId().toString());
            } catch (final NullPointerException npe) {
                result.getFailedImports().put(tokens[0], npe.getMessage());
                result.setImportSuccess(false);
            }
        }

        return result;
    }

    /**
     * Import Scheme CSV Data.
     * 
     * @param csvData
     *            CSV Data
     * @return Import Status
     */
    public ImportResult importSchemeCsvData(final List<String> csvData) {
        final ImportResult result = new ImportResult();
        result.setImportSuccess(true);

        // Process Each Line
        for (final String line : csvData) {
            String[] tokens = new String[5];
            try {
                // Split Line on Comma
                tokens = line.split(",");

                // Process Tokens and Create Hero
                // [0] - Name
                // [1] - Set
                // [2] - Special Instructions

                final Scheme scheme = new Scheme();
                scheme.setName(tokens[0]);
                scheme.setCardSet(CardSet.valueOf(tokens[1]));

                final Set<String> instructionsSet = new HashSet<String>();
                final String[] instructions = tokens[2].split(ImportService.REGEX_PIPE);
                for (final String instruction : instructions) {
                    instructionsSet.add(instruction);
                }
                scheme.setSpecialInstructions(instructionsSet);

                final Scheme savedScheme = this.schemeDao.save(scheme);

                result.getSuccessfulImports().put(savedScheme.getName(), savedScheme.getId().toString());
            } catch (final NullPointerException npe) {
                result.getFailedImports().put(tokens[0], npe.getMessage());
                result.setImportSuccess(false);
            }
        }

        return result;
    }

    /**
     * Import Scheme CSV Data.
     * 
     * @param csvData
     *            CSV Data
     * @return Import Status
     */
    public ImportResult importMastermindCsvData(final List<String> csvData) {
        final ImportResult result = new ImportResult();
        result.setImportSuccess(true);

        // Process Each Line
        for (final String line : csvData) {
            String[] tokens = new String[5];
            try {
                // Split Line on Comma
                tokens = line.split(",");

                // Process Tokens and Create Hero
                // [0] - Name
                // [1] - Set
                // [2] - Attack
                // [3] - Always Leads

                final Mastermind mastermind = new Mastermind();
                mastermind.setName(tokens[0]);
                mastermind.setCardSet(CardSet.valueOf(tokens[1]));
                mastermind.setAttack(tokens[2]);

                if(tokens.length == 4){
                    final Set<Leadable> alwaysLeads = new HashSet<Leadable>();
                    final String[] leadableNames = tokens[3].split(ImportService.REGEX_PIPE);
                    for (final String leadableName : leadableNames) {
                        // Get that Leadable.. Add it to the Mastermind
                        final Leadable leadable = this.leadableDao.getByName(leadableName);
                        alwaysLeads.add(leadable);
                    }
                    mastermind.setAlwaysLeads(alwaysLeads);
                }

                final Mastermind savedMastermind = this.mastermindDao.save(mastermind);

                result.getSuccessfulImports().put(savedMastermind.getName(), savedMastermind.getId().toString());
            } catch (final NullPointerException npe) {
                result.getFailedImports().put(tokens[0], npe.getMessage());
                result.setImportSuccess(false);
            } catch (final NoResultException nre) {
                result.getFailedImports().put(tokens[0], nre.getMessage());
                result.setImportSuccess(false);
            } catch (final ArrayIndexOutOfBoundsException e) {
                result.getFailedImports().put(tokens[0], e.getMessage());
                result.setImportSuccess(false);
            }
        }

        return result;
    }

}
