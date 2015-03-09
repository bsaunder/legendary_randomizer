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

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Ignore;

/**
 * Builds a Default Arquillian Deployment.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@Ignore
public class DeploymentFactory {

    /**
     * Default Constructor.
     */
    private DeploymentFactory() {
        // Do Nothing
    }

    /**
     * Gets a Default WebArchive Deployment for the REST API.
     * 
     * @return REST API Arquillian Deployment
     */
    public static WebArchive getRestApiDeployment() {

        final WebArchive war = ShrinkWrap.create(WebArchive.class, "legendary_randomizer_test.war");

        // Add Project Code
        war.addPackages(true, "net.bryansaunders.legendary");

        // Add Test Properties
        war.addAsResource("application.properties");

        // Add Maven Deps
        final File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve()
                .withTransitivity().asFile();
        for (final File file : libs) {
            war.addAsLibrary(file);
        }
        
        // Add ee-config
        /*File[] files = Maven.resolver().resolve("com.github.chrisruffalo:ee-config:1.4").withTransitivity().asFile();
        for (final File file : files) {
            war.addAsLibrary(file);
        }*/

        // Setup CDI/Persistence/Web
        war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        war.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");

        return war;
    }
}
