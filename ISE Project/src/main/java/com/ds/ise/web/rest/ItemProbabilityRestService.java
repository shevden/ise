package com.ds.ise.web.rest;

import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.entity.Item;
import com.ds.ise.web.constant.AttributeConstant;
import com.ds.ise.web.util.json.builder.TripleValueArrayJsonBuilder;
import com.ds.ise.web.util.json.builder.ProbabilityJsonBuilder;
import org.apache.log4j.Logger;

import javax.annotation.ManagedBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ManagedBean
@Path("/jsr/probability")
public class ItemProbabilityRestService {

    private static final Logger LOG = Logger.getLogger(ItemProbabilityRestService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response jsonREST(@Context HttpServletRequest request) {
        TripleValueArrayJsonBuilder<Item, Double> jsonBuilder = new ProbabilityJsonBuilder();
        HttpSession session = request.getSession(true);
        SessionDataContainer sessionDataContainer =
                (SessionDataContainer) session.getAttribute(AttributeConstant.SESSION_DATA_CONTAINER);
        jsonBuilder.buildTripleValueArrayJson(sessionDataContainer.getItemProbabilities());
        String response = jsonBuilder.toString();
        LOG.trace("Response to send: " + response);

        return Response.status(200).entity(response).build();
    }

}
