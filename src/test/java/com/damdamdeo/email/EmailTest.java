package com.damdamdeo.email;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class EmailTest {

    @Inject
    Mailer mailer;

    @BeforeEach
    public void setup() {
        given(new RequestSpecBuilder().setBaseUri("http://localhost").setPort(8025).build())
                .when()
                .delete("/api/v1/messages")
                .then()
                .statusCode(200);
    }

    @Test
    public void should_send_email_using_rest() {
        given()
                .when()
                .get("/simple")
                .then()
                .statusCode(202)
        ;
    }

    @Test
    public void should_send_email_from_mailer() {
        // When
        mailer.send(Mail.withText("damien.clementdhuart@gmail.com", "A simple email from quarkus Mailer", "This is my body"));

        // Then
        given(new RequestSpecBuilder().setBaseUri("http://localhost").setPort(8025).build())
                .when()
                .get("/api/v1/messages")
                .then()
                .statusCode(200)
                .body("[0].Content.Headers.Subject[0]", equalTo("A simple email from quarkus Mailer"))
                .body("[0].MIME.Parts[0].Body", equalTo("This is my body"));
    }

}
