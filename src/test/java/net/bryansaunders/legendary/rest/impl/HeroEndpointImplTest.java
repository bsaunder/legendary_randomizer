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

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import net.bryansaunders.legendary.dao.impl.HeroDao;
import net.bryansaunders.legendary.model.CardClass;
import net.bryansaunders.legendary.model.CardSet;
import net.bryansaunders.legendary.model.Hero;
import net.bryansaunders.legendary.model.Team;
import net.bryansaunders.legendary.rest.RestApiTest;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.hamcrest.Matchers;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

/**
 * Integration Test for Hero Service.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class HeroEndpointImplTest extends RestApiTest {
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;

    @Inject
    private HeroDao heroDao;

    @Before
    public void setupHeroTable() throws Exception {
        this.utx.begin();
        this.em.joinTransaction();
        this.heroDao.deleteAll();
        this.utx.commit();
        
        this.utx.begin();
        this.em.joinTransaction();
    }
    
    @After
    public void commitTransaction() throws Exception {
        this.utx.commit();
    }

    @Test
    public void getAllHeroesWhenNoneExist() {
        Assert.assertTrue(true);

    }

    @Test
    public void addHero() {
        this.createAndStoreHero();
    }

    @Test
    public void addDuplicateHero() {
        final Hero hero = this.createAndStoreHero();
        hero.setVersion(null);
        
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(hero)
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        .when()
            .post(RestApiTest.URL_ROOT + "/hero");
    }

    @Test
    public void getValidHero() throws JsonParseException, JsonMappingException, IOException {
        final Hero hero = this.createAndStoreHero();
        
        RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("name", Matchers.equalTo(hero.getName()))
            .body("cardSet", Matchers.equalTo(hero.getCardSet().toString()))
            .body("affiliation", Matchers.equalTo(hero.getAffiliation().toString()))
            .body("id", Matchers.equalTo(hero.getId()))
        .when()
            .get(RestApiTest.URL_ROOT + "/hero/"+hero.getId());
    }

    @Test
    public void getMissingHero() {
        RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
        .when()
            .get(RestApiTest.URL_ROOT + "/hero/999999");
    }

    @Test
    public void getAllHeroes() {
        this.createAndStoreHero();
        this.createAndStoreHero();
        this.createAndStoreHero();
        
        final List<?> resultList = RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_OK)
        .when()
            .get(RestApiTest.URL_ROOT + "/hero")
        .andReturn()
            .getBody().as(List.class);
        
        Assert.assertEquals(3, resultList.size());
    }

    @Test
    public void getRandomHero() {
        this.createAndStoreHero();
        this.createAndStoreHero();
        this.createAndStoreHero();
        
        final List<?> resultList = RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_OK)
        .when()
            .get(RestApiTest.URL_ROOT + "/hero/random/2")
        .andReturn()
            .getBody().as(List.class);
        
        Assert.assertEquals(2, resultList.size());
    }

    @Test
    public void deleteValidHero() {
        final Hero hero = this.createAndStoreHero();
        
        RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_OK)
        .when()
            .delete(RestApiTest.URL_ROOT + "/hero/"+hero.getId());
    }

    @Test
    public void deleteMissingHero() {
        RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
        .when()
            .delete(RestApiTest.URL_ROOT + "/hero/999999");
    }

    @Test
    public void getRandomHeroWhenNotEnoughExist() {
        RestAssured.given()
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
        .when()
            .get(RestApiTest.URL_ROOT + "/hero/random/999999");
    }
    

    public Hero createHero() {
        return this.createHero(UUID.randomUUID().toString());
    }

    public Hero createHero(final String name) {
        final Set<CardClass> types = new HashSet<>();
        types.add(CardClass.RNG);

        final Hero hero = new Hero();
        hero.setName(name);
        hero.setAffiliation(Team.AV);
        hero.setCardSet(CardSet.BASE);
        hero.setCommonTypes(types);
        hero.setUncommonTypes(types);
        hero.setRareTypes(types);

        return hero;
    }
    
    public Hero createAndStoreHero(){
        final Hero hero = this.createHero();
        
        final Hero savedHero = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(hero)
        .then()
            .statusCode(HttpStatus.SC_OK)
        .when()
            .post(RestApiTest.URL_ROOT + "/hero")
        .andReturn()
            .getBody().as(Hero.class);
        
        return savedHero;
    }
}
