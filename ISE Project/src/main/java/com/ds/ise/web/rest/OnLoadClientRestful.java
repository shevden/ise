package com.ds.ise.web.rest;

import com.ds.ise.service.resource.MostRelevantQuestionResource;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ManagedBean
@Path("/jsr/onLoadClient")
public class OnLoadClientRestful {

    private static final Logger LOG = Logger.getLogger(OnLoadClientRestful.class);

    @EJB
    private MostRelevantQuestionResource mostRelevantQuestionResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response resource(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        JSONObject response = mostRelevantQuestionResource.getResource(session);

        return Response.status(200).entity(response.toString()).build();
    }
}
