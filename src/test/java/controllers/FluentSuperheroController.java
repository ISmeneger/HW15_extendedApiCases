package controllers;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
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

    @Step("Update superhero by id")
    public HttpResponse updateSuperhero(SuperheroModel superhero, Integer id) {
        this.requestSpecification.body(superhero);
        return new HttpResponse(given(this.requestSpecification).put("superheroes/" + id).then());
    }

    @Step("Get all superheroes")
    public HttpResponse getAllSuperheroes() {
        return new HttpResponse(given(this.requestSpecification).get("superheroes").then());
    }

    @Step("Get superheroe by id")
    public HttpResponse getSuperheroesById(Integer id) {
        return new HttpResponse(given(this.requestSpecification).get("superheroes/" + id).then());
    }

    @Step("Delete superhero by id")
    public HttpResponse deleteSuperheroById(Integer id) {
        return new HttpResponse(given(this.requestSpecification).delete(String.format("superheroes/" + id)).then());
    }
}
