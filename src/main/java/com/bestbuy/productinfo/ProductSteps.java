package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class ProductSteps {

    @Step("Creating product with name: {0}, type: {1}, price: {2}, shipping: {3}, upc: {4}, description: {5}, manufacturer: {6}, model: {7}, url: {8}, image: {9}")
    public ValidatableResponse createProduct(String name, String type, double price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .post(EndPoints.CREATE_PRODUCT)
                .then();
    }

    @Step("Getting product information with productId :{0}")
    public ValidatableResponse getProductInfoById(int productId) {
        return SerenityRest.given()
                .pathParam("productID", productId)
                .when()
                .get(EndPoints.GET_PRODUCT_BY_ID)
                .then();
    }

    @Step("Updating product information with productId: {0}, name: {1}, type: {2}, price: {3}, shipping: {4}, upc: {5}, description: {6}, manufacturer: {7}, model: {8}, url: {9}, image: {10}")
    public ValidatableResponse updateProductInfoById(int productId, String name, String type, double price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, shipping, upc, description, manufacturer, model, url, image);

        return SerenityRest.given()
                .pathParam("productID", productId)
                .header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .patch(EndPoints.PATCH_PRODUCT_BY_ID)
                .then();
    }

    @Step("Deleting product information with productID: {0}")
    public ValidatableResponse deleteProduct(int productId) {

        return SerenityRest.given()
                .pathParam("productID", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

}
