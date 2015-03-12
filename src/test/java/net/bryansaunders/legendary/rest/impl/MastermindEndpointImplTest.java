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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bryansaunders.legendary.model.Mastermind;
import net.bryansaunders.legendary.rest.RestApiTest;
import net.bryansaunders.legendary.util.LegendaryEntityFactory;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

/**
 * Integration Test for Mastermind Service.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class MastermindEndpointImplTest extends RestApiTest {

    private static final Map<Integer, String> entityMap = new HashMap<>();

    @Test
    @InSequence(0)
    public void getAllWhenNoneExist() {
        final List<?> resultList = RestAssured.given().then().statusCode(HttpStatus.SC_OK).when()
                .get(RestApiTest.URL_ROOT + "/mastermind").andReturn().getBody().as(List.class);

        Assert.assertEquals(0, resultList.size());
    }

    @Test
    @InSequence(1)
    public void add() {
        for (int i = 0; i < 4; i++) {
            final Mastermind mastermind = LegendaryEntityFactory.createMastermind();

            final Mastermind savedMastermind = RestAssured.given().contentType(ContentType.JSON).body(mastermind)
                    .then().statusCode(HttpStatus.SC_OK).body("name", Matchers.equalTo(mastermind.getName()))
                    .body("cardSet", Matchers.equalTo(mastermind.getCardSet().toString()))
                    .body("attack", Matchers.equalTo(mastermind.getAttack().toString()))
                    .body("id", Matchers.notNullValue()).when().post(RestApiTest.URL_ROOT + "/mastermind").andReturn()
                    .getBody().as(Mastermind.class);

            Assert.assertNotNull(savedMastermind.getId());

            System.out.println("Mapping Saved Mastermind: " + savedMastermind.getId() + " - "
                    + savedMastermind.getName());
            MastermindEndpointImplTest.entityMap.put(savedMastermind.getId(), savedMastermind.getName());
        }
    }

    @Test
    @InSequence(2)
    public void addDuplicateMastermind() {
        final String name = MastermindEndpointImplTest.entityMap.values().iterator().next();

        final Mastermind mastermind = LegendaryEntityFactory.createMastermind();
        mastermind.setVersion(null);
        mastermind.setName(name);

        RestAssured.given().contentType(ContentType.JSON).body(mastermind).then().statusCode(HttpStatus.SC_BAD_REQUEST)
                .when().post(RestApiTest.URL_ROOT + "/mastermind");
    }

    @Test
    @InSequence(3)
    public void getValidMastermind() {
        final Integer id = MastermindEndpointImplTest.entityMap.keySet().iterator().next();

        RestAssured.given().then().statusCode(HttpStatus.SC_OK).body("name", Matchers.notNullValue())
                .body("cardSet", Matchers.notNullValue()).body("attack", Matchers.notNullValue())
                .body("id", Matchers.equalTo(id)).when().get(RestApiTest.URL_ROOT + "/mastermind/" + id);
    }

    @Test
    @InSequence(4)
    public void getMissingMastermind() {
        RestAssured.given().then().statusCode(HttpStatus.SC_NOT_FOUND).when()
                .get(RestApiTest.URL_ROOT + "/mastermind/999999");
    }

    @Test
    @InSequence(5)
    public void deleteValidMastermind() {
        final Integer id = MastermindEndpointImplTest.entityMap.keySet().iterator().next();

        RestAssured.given().then().statusCode(HttpStatus.SC_OK).when()
                .delete(RestApiTest.URL_ROOT + "/mastermind/" + id);
    }

    @Test
    @InSequence(6)
    public void getAllMasterminds() {

        final List<?> resultList = RestAssured.given().then().statusCode(HttpStatus.SC_OK).when()
                .get(RestApiTest.URL_ROOT + "/mastermind").andReturn().getBody().as(List.class);

        Assert.assertEquals(3, resultList.size());
    }

    @Test
    @InSequence(7)
    public void getRandomMastermind() {

        final List<?> resultList = RestAssured.given().then().statusCode(HttpStatus.SC_OK).when()
                .get(RestApiTest.URL_ROOT + "/mastermind/random/2").andReturn().getBody().as(List.class);

        Assert.assertEquals(2, resultList.size());
    }

    @Test
    public void deleteMissingMastermind() {
        RestAssured.given().then().statusCode(HttpStatus.SC_NOT_FOUND).when()
                .delete(RestApiTest.URL_ROOT + "/mastermind/999999");
    }

    @Test
    public void getRandomMastermindWhenNotEnoughExist() {
        RestAssured.given().then().statusCode(HttpStatus.SC_BAD_REQUEST).when()
                .get(RestApiTest.URL_ROOT + "/mastermind/random/999999");
    }

}
