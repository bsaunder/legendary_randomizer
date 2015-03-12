package net.bryansaunders.legendary.rest.impl;

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


import net.bryansaunders.legendary.model.Hero;
import net.bryansaunders.legendary.model.Leadable;
import net.bryansaunders.legendary.model.LeadableType;
import net.bryansaunders.legendary.model.Mastermind;
import net.bryansaunders.legendary.model.Scheme;
import net.bryansaunders.legendary.rest.RestApiTest;
import net.bryansaunders.legendary.util.LegendaryEntityFactory;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

/**
 * Integration Test for Setup Service.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class SetupEndpointImplTest extends RestApiTest {
    
    @Test
    @InSequence(0)
    public void addTestData(){
        for (int i = 0; i < 8; i++) {
            final Hero hero = LegendaryEntityFactory.createHero();
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(hero)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.equalTo(hero.getName()))
                .body("cardSet", Matchers.equalTo(hero.getCardSet().toString()))
                .body("affiliation", Matchers.equalTo(hero.getAffiliation().toString()))
                .body("id", Matchers.notNullValue())
            .when()
                .post(RestApiTest.URL_ROOT + "/hero");
        }
        
        for (int i = 0; i < 4; i++) {
            final Mastermind mastermind = LegendaryEntityFactory.createMastermind();
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(mastermind)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.equalTo(mastermind.getName()))
                .body("cardSet", Matchers.equalTo(mastermind.getCardSet().toString()))
                .body("attack", Matchers.equalTo(mastermind.getAttack().toString()))
                .body("id", Matchers.notNullValue())
            .when()
                .post(RestApiTest.URL_ROOT + "/mastermind");
        }
        
        for (int i = 0; i < 4; i++) {
            final Scheme scheme = LegendaryEntityFactory.createScheme();
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(scheme)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.equalTo(scheme.getName()))
                .body("cardSet", Matchers.equalTo(scheme.getCardSet().toString()))
                .body("id", Matchers.notNullValue())
            .when()
                .post(RestApiTest.URL_ROOT + "/scheme");
        }
        
        for (int i = 0; i < 4; i++) {
            final Leadable leadable = LegendaryEntityFactory.createLeadable();
            leadable.setType(LeadableType.VILLAIN);
            
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(leadable)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.equalTo(leadable.getName()))
                .body("cardSet", Matchers.equalTo(leadable.getCardSet().toString()))
                .body("type", Matchers.equalTo(leadable.getType().toString()))
                .body("id", Matchers.notNullValue())
            .when()
                .post(RestApiTest.URL_ROOT + "/leadable");
        }
        
        for (int i = 0; i < 4; i++) {
            final Leadable leadable = LegendaryEntityFactory.createLeadable();
            leadable.setType(LeadableType.HENCHMAN);
            
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(leadable)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.equalTo(leadable.getName()))
                .body("cardSet", Matchers.equalTo(leadable.getCardSet().toString()))
                .body("type", Matchers.equalTo(leadable.getType().toString()))
                .body("id", Matchers.notNullValue())
            .when()
                .post(RestApiTest.URL_ROOT + "/leadable");
        }
    }

    @Test
    @InSequence(1)
    public void basicSetupWith2Players() {
        RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("players", Matchers.equalTo(2))
            .body("woundDeckCount", Matchers.equalTo(30))
            .body("bystanderDeckCount", Matchers.equalTo(24))
            .body("bystanderVillianCount", Matchers.equalTo(2))
            .body("heroCount", Matchers.equalTo(5))
            .body("villianCount", Matchers.equalTo(2))
            .body("henchmanCount", Matchers.equalTo(1))
            .body("masterStrikeCount", Matchers.equalTo(5))
        .when()
            .get(RestApiTest.URL_ROOT + "/setup/2");
    }

    @Test
    @InSequence(2)
    public void basicSetupWith3Players() {
        RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("players", Matchers.equalTo(3))
            .body("woundDeckCount", Matchers.equalTo(30))
            .body("bystanderDeckCount", Matchers.equalTo(24))
            .body("bystanderVillianCount", Matchers.equalTo(8))
            .body("heroCount", Matchers.equalTo(5))
            .body("villianCount", Matchers.equalTo(3))
            .body("henchmanCount", Matchers.equalTo(1))
            .body("masterStrikeCount", Matchers.equalTo(5))
        .when()
            .get(RestApiTest.URL_ROOT + "/setup/3");
    }

    @Test
    @InSequence(3)
    public void basicSetupWith4Players() {
        RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("players", Matchers.equalTo(4))
            .body("woundDeckCount", Matchers.equalTo(30))
            .body("bystanderDeckCount", Matchers.equalTo(24))
            .body("bystanderVillianCount", Matchers.equalTo(8))
            .body("heroCount", Matchers.equalTo(5))
            .body("villianCount", Matchers.equalTo(3))
            .body("henchmanCount", Matchers.equalTo(2))
            .body("masterStrikeCount", Matchers.equalTo(5))
        .when()
            .get(RestApiTest.URL_ROOT + "/setup/4");
    }

    @Test
    @InSequence(4)
    public void basicSetupWith5Players() {
        RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("players", Matchers.equalTo(5))
            .body("woundDeckCount", Matchers.equalTo(30))
            .body("bystanderDeckCount", Matchers.equalTo(24))
            .body("bystanderVillianCount", Matchers.equalTo(12))
            .body("heroCount", Matchers.equalTo(6))
            .body("villianCount", Matchers.equalTo(4))
            .body("henchmanCount", Matchers.equalTo(2))
            .body("masterStrikeCount", Matchers.equalTo(5))
        .when()
            .get(RestApiTest.URL_ROOT + "/setup/5");
    }
    
    @Test
    @InSequence(5)
    public void basicSetupWith6Players() {
        RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        .when()
            .get(RestApiTest.URL_ROOT + "/setup/6");
    }

}
