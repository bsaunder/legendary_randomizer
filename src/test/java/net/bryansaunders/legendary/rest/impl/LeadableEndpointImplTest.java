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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bryansaunders.legendary.model.Leadable;
import net.bryansaunders.legendary.model.LeadableType;
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
 * Integration Test for Leadable Service.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class LeadableEndpointImplTest extends RestApiTest {

    private static final Map<Integer, String> entityMap = new HashMap<>();

    @Test
    @InSequence(0)
    public void getAllWhenNoneExist() {
        final List<?> resultList = RestAssured.given().then().statusCode(HttpStatus.SC_OK).when()
                .get(RestApiTest.URL_ROOT + "/leadable").andReturn().getBody().as(List.class);

        Assert.assertEquals(0, resultList.size());
    }

    @Test
    @InSequence(1)
    public void add() {
        for (int i = 0; i < 4; i++) {
            final Leadable leadable = LegendaryEntityFactory.createLeadable();

            final Leadable savedLeadable = RestAssured.given().contentType(ContentType.JSON).body(leadable).then()
                    .statusCode(HttpStatus.SC_OK).body("name", Matchers.equalTo(leadable.getName()))
                    .body("cardSet", Matchers.equalTo(leadable.getCardSet().toString()))
                    .body("type", Matchers.equalTo(leadable.getType().toString())).body("id", Matchers.notNullValue())
                    .when().post(RestApiTest.URL_ROOT + "/leadable").andReturn().getBody().as(Leadable.class);

            Assert.assertNotNull(savedLeadable.getId());
            LeadableEndpointImplTest.entityMap.put(savedLeadable.getId(), savedLeadable.getName());
        }
        
        for (int i = 0; i < 4; i++) {
            final Leadable leadable = LegendaryEntityFactory.createLeadable();
            leadable.setType(LeadableType.VILLAIN);

            final Leadable savedLeadable = RestAssured.given().contentType(ContentType.JSON).body(leadable).then()
                    .statusCode(HttpStatus.SC_OK).body("name", Matchers.equalTo(leadable.getName()))
                    .body("cardSet", Matchers.equalTo(leadable.getCardSet().toString()))
                    .body("type", Matchers.equalTo(leadable.getType().toString())).body("id", Matchers.notNullValue())
                    .when().post(RestApiTest.URL_ROOT + "/leadable").andReturn().getBody().as(Leadable.class);

            Assert.assertNotNull(savedLeadable.getId());
            LeadableEndpointImplTest.entityMap.put(savedLeadable.getId(), savedLeadable.getName());
        }
    }

    @Test
    @InSequence(2)
    public void addDuplicateLeadable() {
        final String name = LeadableEndpointImplTest.entityMap.values().iterator().next();

        final Leadable leadable = LegendaryEntityFactory.createLeadable();
        leadable.setVersion(null);
        leadable.setName(name);

        RestAssured.given().contentType(ContentType.JSON).body(leadable).then().statusCode(HttpStatus.SC_BAD_REQUEST)
                .when().post(RestApiTest.URL_ROOT + "/leadable");
    }

    @Test
    @InSequence(3)
    public void getValidLeadable() {

        final Integer id = LeadableEndpointImplTest.entityMap.keySet().iterator().next();

        RestAssured.given().then().statusCode(HttpStatus.SC_OK).body("name", Matchers.notNullValue())
                .body("cardSet", Matchers.notNullValue()).body("type", Matchers.notNullValue())
                .body("id", Matchers.equalTo(id)).when().get(RestApiTest.URL_ROOT + "/leadable/" + id);
    }

    @Test
    @InSequence(4)
    public void getMissingLeadable() {
        RestAssured.given().then().statusCode(HttpStatus.SC_NOT_FOUND).when()
                .get(RestApiTest.URL_ROOT + "/leadable/999999");
    }

    @Test
    @InSequence(5)
    public void deleteValidLeadable() {
        final Integer id = LeadableEndpointImplTest.entityMap.keySet().iterator().next();

        RestAssured.given().then().statusCode(HttpStatus.SC_OK).when().delete(RestApiTest.URL_ROOT + "/leadable/" + id);
    }

    @Test
    @InSequence(6)
    public void getAllLeadablees() {

        final List<?> resultList = RestAssured.given().then().statusCode(HttpStatus.SC_OK).when()
                .get(RestApiTest.URL_ROOT + "/leadable").andReturn().getBody().as(List.class);

        Assert.assertEquals(7, resultList.size());
    }

    @Test
    @InSequence(7)
    public void getRandomLeadable() {

        final List<?> resultList = RestAssured.given().then().statusCode(HttpStatus.SC_OK).when()
                .get(RestApiTest.URL_ROOT + "/leadable/random/2").andReturn().getBody().as(List.class);

        Assert.assertEquals(2, resultList.size());
    }
    
    @Test
    @InSequence(8)
    public void getRandomHenchmanLeadable() {

        final List<Leadable> resultList = Arrays.asList(RestAssured.given()
                .then()
                    .statusCode(HttpStatus.SC_OK)
                .when()
                    .get(RestApiTest.URL_ROOT + "/leadable/random/2?type="+LeadableType.HENCHMAN)
                .andReturn()
                    .getBody().as(Leadable[].class));

            Assert.assertEquals(2, resultList.size());
            for(Leadable leadable : resultList){
                Assert.assertEquals(LeadableType.HENCHMAN, leadable.getType());
            }
    }
    
    @Test
    @InSequence(9)
    public void getRandomVillainLeadable() {

        final List<Leadable> resultList = Arrays.asList(RestAssured.given()
            .then()
                .statusCode(HttpStatus.SC_OK)
            .when()
                .get(RestApiTest.URL_ROOT + "/leadable/random/2?type="+LeadableType.VILLAIN)
            .andReturn()
                .getBody().as(Leadable[].class));

        Assert.assertEquals(2, resultList.size());
        for(Leadable leadable : resultList){
            Assert.assertEquals(LeadableType.VILLAIN, leadable.getType());
        }
    }

    @Test
    public void deleteMissingLeadable() {
        RestAssured.given().then().statusCode(HttpStatus.SC_NOT_FOUND).when()
                .delete(RestApiTest.URL_ROOT + "/leadable/999999");
    }

    @Test
    public void getRandomLeadableWhenNotEnoughExist() {
        RestAssured.given().then().statusCode(HttpStatus.SC_BAD_REQUEST).when()
                .get(RestApiTest.URL_ROOT + "/leadable/random/999999");
    }
}
