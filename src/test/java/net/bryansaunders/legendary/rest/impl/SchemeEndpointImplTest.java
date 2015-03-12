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

import net.bryansaunders.legendary.model.Scheme;
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
 * Integration Test for Scheme Service.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
public class SchemeEndpointImplTest extends RestApiTest {

    private static final Map<Integer, String> entityMap = new HashMap<>();

    @Test
    @InSequence(0)
    public void getAllWhenNoneExist() {
        final List<?> resultList = RestAssured.given().then().statusCode(HttpStatus.SC_OK).when()
                .get(RestApiTest.URL_ROOT + "/scheme").andReturn().getBody().as(List.class);

        Assert.assertEquals(0, resultList.size());
    }

    @Test
    @InSequence(1)
    public void add() {
        for (int i = 0; i < 4; i++) {
            final Scheme scheme = LegendaryEntityFactory.createScheme();

            final Scheme savedScheme = RestAssured.given().contentType(ContentType.JSON).body(scheme).then()
                    .statusCode(HttpStatus.SC_OK).body("name", Matchers.equalTo(scheme.getName()))
                    .body("cardSet", Matchers.equalTo(scheme.getCardSet().toString()))
                    .body("id", Matchers.notNullValue()).when().post(RestApiTest.URL_ROOT + "/scheme").andReturn()
                    .getBody().as(Scheme.class);

            Assert.assertNotNull(savedScheme.getId());

            System.out.println("Mapping Saved Scheme: " + savedScheme.getId() + " - " + savedScheme.getName());
            SchemeEndpointImplTest.entityMap.put(savedScheme.getId(), savedScheme.getName());
        }
    }

    @Test
    @InSequence(2)
    public void addDuplicateScheme() {
        final String name = SchemeEndpointImplTest.entityMap.values().iterator().next();

        final Scheme scheme = LegendaryEntityFactory.createScheme();
        scheme.setVersion(null);
        scheme.setName(name);

        RestAssured.given().contentType(ContentType.JSON).body(scheme).then().statusCode(HttpStatus.SC_BAD_REQUEST)
                .when().post(RestApiTest.URL_ROOT + "/scheme");
    }

    @Test
    @InSequence(3)
    public void getValidScheme() {
        final Integer id = SchemeEndpointImplTest.entityMap.keySet().iterator().next();

        RestAssured.given().then().statusCode(HttpStatus.SC_OK).body("name", Matchers.notNullValue())
                .body("cardSet", Matchers.notNullValue()).body("id", Matchers.equalTo(id)).when()
                .get(RestApiTest.URL_ROOT + "/scheme/" + id);
    }

    @Test
    @InSequence(4)
    public void getMissingScheme() {
        RestAssured.given().then().statusCode(HttpStatus.SC_NOT_FOUND).when()
                .get(RestApiTest.URL_ROOT + "/scheme/999999");
    }

    @Test
    @InSequence(5)
    public void deleteValidScheme() {
        final Integer id = SchemeEndpointImplTest.entityMap.keySet().iterator().next();

        RestAssured.given().then().statusCode(HttpStatus.SC_OK).when().delete(RestApiTest.URL_ROOT + "/scheme/" + id);
    }

    @Test
    @InSequence(6)
    public void getAllSchemes() {

        final List<?> resultList = RestAssured.given().then().statusCode(HttpStatus.SC_OK).when()
                .get(RestApiTest.URL_ROOT + "/scheme").andReturn().getBody().as(List.class);

        Assert.assertEquals(3, resultList.size());
    }

    @Test
    @InSequence(7)
    public void getRandomScheme() {

        final List<?> resultList = RestAssured.given().then().statusCode(HttpStatus.SC_OK).when()
                .get(RestApiTest.URL_ROOT + "/scheme/random/2").andReturn().getBody().as(List.class);

        Assert.assertEquals(2, resultList.size());
    }

    @Test
    public void deleteMissingScheme() {
        RestAssured.given().then().statusCode(HttpStatus.SC_NOT_FOUND).when()
                .delete(RestApiTest.URL_ROOT + "/scheme/999999");
    }

    @Test
    public void getRandomSchemeWhenNotEnoughExist() {
        RestAssured.given().then().statusCode(HttpStatus.SC_BAD_REQUEST).when()
                .get(RestApiTest.URL_ROOT + "/scheme/random/999999");
    }

}
