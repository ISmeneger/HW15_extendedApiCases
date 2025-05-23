package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.PdfUtils.savePdf;

class DownloadTests {

    @Test
    void downloadHttpClientTBankTest() {
        String endpoint = "https://cdn.tbank.ru/static/documents/9e3b3324-f3a4-4060-927b-9147ede2958c.pdf";
        String fileName = "downloaded.pdf";

        Response response =
                given().
                        when().
                        get(endpoint).
                        then().
                        contentType("application/pdf").
                        statusCode(200).
                        extract().response();

        savePdf(response, fileName);

        File downloadedFile = new File(fileName);
        assertThat(downloadedFile).exists();
    }
}
