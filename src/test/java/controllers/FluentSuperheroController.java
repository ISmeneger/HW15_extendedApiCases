package controllers;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.SuperheroModel;

import static constants.CommonConstants.BASE_URL;
import static io.restassured.RestAssured.given;

public class FluentSuperheroController {
    RequestSpecification requestSpecification = given();

    public FluentSuperheroController() {
        RestAssured.defaultParser = Parser.JSON;
        this.requestSpecification.contentType(ContentType.JSON);
        this.requestSpecification.accept(ContentType.JSON);
        this.requestSpecification.baseUri(BASE_URL);
        this.requestSpecification.filter(new AllureRestAssured());
    }

    @Step("Create a new superhero")
    public HttpResponse createSuperhero(SuperheroModel superhero) {
        this.requestSpecification.body(superhero);
        return new HttpResponse(given(this.requestSpecification).post("superheroes").then());
    }

    @Step("Update superhero by id with retry")
    public HttpResponse updateSuperhero(SuperheroModel superhero, Integer id) throws InterruptedException {
        this.requestSpecification.body(superhero);
        Response response = given(this.requestSpecification).put("superheroes/" + id);
        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Waiting total " + (i + 1) + " second");
                Thread.sleep(1000);
                response = given(this.requestSpecification).put("superheroes/" + id);
                statusCode = response.getStatusCode();
                if (statusCode == 200) {
                    break;
                }
            }
        }
        return new HttpResponse(response.then());
    }

    @Step("Get all superheroes")
    public HttpResponse getAllSuperheroes() {
        return new HttpResponse(given(this.requestSpecification).get("superheroes").then());
    }

    @Step("Get superhero by id")
    public HttpResponse getSuperheroesById(Integer id){
        return new HttpResponse(given(this.requestSpecification).get("superheroes/" + id).then());
    }

    @Step("Get superhero by id with retry")
    public HttpResponse getSuperheroesByIdWithRetry(Integer id) throws InterruptedException {
        Response response = given(this.requestSpecification).get("superheroes/" + id);
        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Waiting total " + (i + 1) + " second");
                Thread.sleep(1000);
                response = given(this.requestSpecification).get("superheroes/" + id);
                statusCode = response.getStatusCode();
                if (statusCode == 200) {
                    break;
                }
            }
        }
        return new HttpResponse(response.then());
    }

    @Step("Delete superhero by id")
    public HttpResponse deleteSuperheroById (Integer id){
        return new HttpResponse(given(this.requestSpecification).delete(String.format("superheroes/" + id)).then());
    }
}

