package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.Map;

public class StoreSteps {

    @Step("Creating a store with name: {0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}, lat: {7}, lng: {8}, hours: {9}, services: {10}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city,
                                           String state, String zip, double lat, double lng, String hours,
                                           Map<String, Object> services) {

        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        return SerenityRest.given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(storePojo)
                .post(EndPoints.CREATE_STORE)
                .then();

    }

    @Step("Getting product information with storeId :{0}")
    public ValidatableResponse getStoreInfoById(int storeId) {
        return SerenityRest.given()
                .pathParam("storeID", storeId)
                .when()
                .get(EndPoints.GET_STORE_BY_ID)
                .then();
    }

    @Step("Updating a store with storeId: {0}, name: {1}, type: {2}, address: {3}, address2: {4}, city: {5}, state: {6}, zip: {7}, lat: {8}, lng: {9}, hours: {10}, services: {11}")
    public ValidatableResponse updateStoreInfoById(int storeId,String name, String type, String address, String address2, String city,
                                           String state, String zip, double lat, double lng, String hours,
                                           Map<String, Object> services) {

        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        return SerenityRest.given()
                .pathParam("storeID",storeId)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(storePojo)
                .patch(EndPoints.PATCH_STORE_BY_ID)
                .then();
    }

    @Step("Deleting product information with storeId: {0}")
    public ValidatableResponse deleteStoreById(int storeId) {
        return SerenityRest.given()
                .pathParam("storeID", storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }
}
