package com.ds.ise.web.rest;

import com.ds.ise.service.resource.ResultResource;
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
@Path("/jsr/getResult")
public class GetResultRestful {

    private static final Logger LOG = Logger.getLogger(GetResultRestful.class);

    @EJB
    private ResultResource resultResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response resource(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        JSONObject response = resultResource.getResource(session);

        return Response.status(200).entity(response.toString()).build();
    }
}
