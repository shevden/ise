package com.ds.ise.web.rest;

import com.ds.ise.service.resource.FulfillEntropyResource;
import com.ds.ise.service.resource.FulfillProbabilityResource;
import com.ds.ise.service.resource.MostRelevantQuestionResource;
import com.ds.ise.web.util.json.merger.JsonMerger;
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
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@Path("/jsr/onLoadAdmin")
public class OnLoadAdminRestful {

    private static final Logger LOG = Logger.getLogger(OnLoadAdminRestful.class);

    @EJB
    private FulfillEntropyResource fulfillEntropyResource;

    @EJB
    private FulfillProbabilityResource fulfillProbabilityResource;

    @EJB
    private MostRelevantQuestionResource mostRelevantQuestionResource;

    @EJB
    private JsonMerger jsonMerger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response resource(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        List<JSONObject> jsons = new ArrayList<>();
        jsons.add(fulfillEntropyResource.getResource(session));
        jsons.add(fulfillProbabilityResource.getResource(session));
        jsons.add(mostRelevantQuestionResource.getResource(session));
        JSONObject response = jsonMerger.merge(jsons);

        return Response.status(200).entity(response.toString()).build();
    }
}
