package com.ds.ise.web.rest;

import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.entity.additional.AnswerType;
import com.ds.ise.logic.calculator.ProbabilityCalculator;
import com.ds.ise.web.constant.AttributeConstant;
import com.ds.ise.web.util.json.parser.AnswerTypeJsonParser;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ManagedBean
@Path("/jsr/answer")
public class ProcessAnswerRestService {

    @EJB
    private AnswerTypeJsonParser jsonParser;

    @EJB
    private ProbabilityCalculator probabilityCalculator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response jsonRest3(String input, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionDataContainer sessionDataContainer =
                (SessionDataContainer) session.getAttribute(AttributeConstant.SESSION_DATA_CONTAINER);
        AnswerType answer = jsonParser.parseJson(input);
        sessionDataContainer.addAnswerToQuestion(answer);
        sessionDataContainer.nullifyLastAskedQuestion();
        probabilityCalculator.recalculateProbability(sessionDataContainer);

        return Response.status(200).build();
    }
}
