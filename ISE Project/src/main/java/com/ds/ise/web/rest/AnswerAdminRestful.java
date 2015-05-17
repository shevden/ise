package com.ds.ise.web.rest;


import com.ds.ise.service.processor.AdminAnswerProcessor;
import org.apache.log4j.Logger;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ManagedBean
@Path("/jsr/answerAdmin")
public class AnswerAdminRestful {

    private static final Logger LOG = Logger.getLogger(AnswerAdminRestful.class);

    @EJB
    private AdminAnswerProcessor adminAnswerProcessor;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response resource(String input, @Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        adminAnswerProcessor.process(input, session);

        return Response.status(200).build();
    }
}
