package api;

import controllers.FluentSuperheroController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static testdata.SuperheroTestData.*;

public class FluentControllerSuperheroApiTests {
    FluentSuperheroController fluentSuperheroController = new FluentSuperheroController();
    List<Integer> ids = new ArrayList<>();

    @BeforeEach
    @AfterEach
    void clear() {
        fluentSuperheroController.deleteSuperheroById(CREATE_NEW_SUPERHERO.getId());
        for (int id : ids) {
            fluentSuperheroController.deleteSuperheroById(id);
        }
    }

    @Test
    @DisplayName("Check create new superhero is returns 200 status ok")
    void checkAddNewSuperheroTest() {
        fluentSuperheroController.createSuperhero(CREATE_NEW_SUPERHERO).statusCodeIs(200);
    }

    @Test
    @DisplayName("Create superhero without phone, check status code and response body")
    void checkSuperheroWithoutPhoneTest() throws InterruptedException {
        int id = Integer.parseInt(fluentSuperheroController.createSuperhero(SUPERHERO_WITHOUT_PHONE)
                .statusCodeIs(200)
                .getJsonValue("id"));

        Thread.sleep(3000);
        ids.add(id);
        fluentSuperheroController.getSuperheroesById(id)
                .statusCodeIs(200)
                .jsonValueIs("birthDate", SUPERHERO_WITHOUT_PHONE.getBirthDate())
                .jsonValueIs("city", SUPERHERO_WITHOUT_PHONE.getCity())
                .jsonValueIs("fullName", SUPERHERO_WITHOUT_PHONE.getFullName())
                .jsonValueIs("gender", String.valueOf(SUPERHERO_WITHOUT_PHONE.getGender()))
                .jsonValueIs("id", String.valueOf(id))
                .jsonValueIs("mainSkill", SUPERHERO_WITHOUT_PHONE.getMainSkill())
                .jsonValueIs("phone", null);
    }

    @Test
    @DisplayName("Create superhero without phone, check status code and response body with retry")
    void checkSuperheroWithoutPhoneRetryTest() throws InterruptedException {
        int id = Integer.parseInt(fluentSuperheroController.createSuperhero(SUPERHERO_WITHOUT_PHONE)
                .statusCodeIs(200)
                .getJsonValue("id"));

        Thread.sleep(3000);
        ids.add(id);
        fluentSuperheroController.getSuperheroesByIdWithRetry(id)
                .statusCodeIs(200)
                .jsonValueIs("birthDate", SUPERHERO_WITHOUT_PHONE.getBirthDate())
                .jsonValueIs("city", SUPERHERO_WITHOUT_PHONE.getCity())
                .jsonValueIs("fullName", SUPERHERO_WITHOUT_PHONE.getFullName())
                .jsonValueIs("gender", String.valueOf(SUPERHERO_WITHOUT_PHONE.getGender()))
                .jsonValueIs("id", String.valueOf(id))
                .jsonValueIs("mainSkill", SUPERHERO_WITHOUT_PHONE.getMainSkill())
                .jsonValueIs("phone", null);
    }

    @Test
    @DisplayName("Check get all superhero")
    void checkGetAllSuperheroesTest() {
        fluentSuperheroController.getAllSuperheroes().statusCodeIs(200);
    }

    @Test
    @DisplayName("Check get superhero by id")
    void checkGetSuperheroByIdTest() {
        fluentSuperheroController.getSuperheroesById(286).statusCodeIs(200);
    }

    @Test
    @DisplayName("Check get superhero by id with retry")
    void checkGetSuperheroByIdRetryTest() throws InterruptedException {
        int id = Integer.parseInt(fluentSuperheroController.createSuperhero(SUPERHERO_WITHOUT_PHONE)
                .statusCodeIs(200)
                .getJsonValue("id"));

        fluentSuperheroController.getSuperheroesByIdWithRetry(id).statusCodeIs(200);
    }

    @Test
    @DisplayName("Check update superhero by id with retry")
    void checkUpdateSuperheroByIdTest() throws InterruptedException {
        int id = Integer.parseInt(fluentSuperheroController.createSuperhero(CREATE_NEW_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValue("id"));

        Thread.sleep(3000);
        ids.add(id);
        fluentSuperheroController.updateSuperhero(UPDATE_SUPERHERO, id)
                .statusCodeIs(200);
    }

    @Test
    @DisplayName("Check update superhero by id")
    void checkDeleteSuperheroByIdTest() {
        fluentSuperheroController.deleteSuperheroById(10).statusCodeIs(200);
    }
}


