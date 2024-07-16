package com.bestbuy.crudtest;

import com.bestbuy.productinfo.ProductSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.testutils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {

    static String name = "Prime Cell " + TestUtils.getRandomValue();
    static String type = "PrimeTypeSP " + TestUtils.getRandomValue();
    static double price = 4.2;
    static int shipping = 0;
    static String upc = "023234433";
    static String description = "Testing; AAA Size; Prime Power " + TestUtils.getRandomValue();
    static String manufacturer = "PrimeDuracell " + TestUtils.getRandomValue();
    static String model = TestUtils.getRandomValue() + " PT2334";
    static String url = "www.primetest.com/" + TestUtils.getRandomValue();
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

    static int productId;

    @Steps
    ProductSteps productSteps;

    @Title("Test001 - Create a product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.statusCode(201);
        response.log().body();

        productId = response.extract().path("id");
        System.out.println(productId);
    }

    @Title("Test002 - Get product info by id and verify its detail")
    @Test
    public void test002() {
        ValidatableResponse response = productSteps.getProductInfoById(productId);
        response.statusCode(200);
        System.out.println("Name is : " + response.extract().path("name"));
        response.body("price", equalTo(4.2F));
    }

    @Title("Test003 - Updating product info by id and verify updated details")
    @Test
    public void test003() {
        String uname = "Prime Update Cell";
        String utype = "PrimeTypeSP Update " + TestUtils.getRandomValue();
        double uprice = 40.70;

        ValidatableResponse response = productSteps.updateProductInfoById(productId, uname, utype, uprice, shipping, upc, description, manufacturer, model, url, image);
        response.statusCode(200);
        response.body("name",equalTo(uname));
    }

    @Title("Test004 - Deleting product and verify it deleted")
    @Test
    public void test004(){
        productSteps.deleteProduct(productId).statusCode(200);
        productSteps.getProductInfoById(productId).statusCode(404);

    }


}
