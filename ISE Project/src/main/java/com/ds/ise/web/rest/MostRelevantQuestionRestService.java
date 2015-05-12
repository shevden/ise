package com.ds.ise.web.rest;

import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.entity.Question;
import com.ds.ise.logic.calculator.EntropyCalculator;
import com.ds.ise.web.constant.AttributeConstant;
import com.ds.ise.web.util.json.builder.QuestionJsonBuilder;
import com.ds.ise.web.util.json.builder.SingleValueJsonBuilder;
import org.apache.log4j.Logger;

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
@Path("/jsr/question")
public class MostRelevantQuestionRestService {

    private static final Logger LOG = Logger.getLogger(MostRelevantQuestionRestService.class);

    @EJB
    private EntropyCalculator entropyCalculator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response jsonREST44(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        SessionDataContainer sessionDataContainer =
                (SessionDataContainer) session.getAttribute(AttributeConstant.SESSION_DATA_CONTAINER);
        entropyCalculator.recalculateEntropy(sessionDataContainer);
        Question mostRelevantQuestion = entropyCalculator.getMinEntropyQuestion(sessionDataContainer);
        sessionDataContainer.setLastAskedQuestion(mostRelevantQuestion);
        sessionDataContainer.removeIndexedQuestion(mostRelevantQuestion);
        SingleValueJsonBuilder<Question> jsonBuilder = new QuestionJsonBuilder();
        jsonBuilder.buildSingleValueJson(mostRelevantQuestion);
        String response = jsonBuilder.toString();

        return Response.status(200).entity(response).build();
    }
}
