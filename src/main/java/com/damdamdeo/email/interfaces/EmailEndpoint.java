package com.damdamdeo.email.interfaces;


import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class EmailEndpoint {

    @Inject
    Mailer mailer;

    @GET
    @Path("/simple")
    public Response sendASimpleEmail() {
        mailer.send(Mail.withText("damien.clementdhuart@gmail.com", "A simple email from quarkus", "This is my body"));
        return Response.accepted().build();
    }

}
