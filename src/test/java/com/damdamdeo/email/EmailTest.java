package com.damdamdeo.email;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class EmailTest {

    @Inject
    Mailer mailer;

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
        mailer.send(Mail.withText("damien.clementdhuart@gmail.com", "A simple email from quarkus Mailer", "This is my body"));
    }

}
