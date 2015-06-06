package com.ds.ise.web.rest;


import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.logic.trigger.AnswerTrigger;
import com.ds.ise.service.processor.AnswerProcessor;
import com.ds.ise.constant.AttributeConstant;
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
import java.net.URISyntaxException;

@ManagedBean
@Path("/jsr/processAnswer")
public class ProcessAnswerRestful {

    private static final Logger LOG = Logger.getLogger(ProcessAnswerRestful.class);

    @EJB
    private AnswerProcessor answerProcessor;

    @EJB
    private AnswerTrigger answerTrigger;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response resource(String input, @Context HttpServletRequest request) throws URISyntaxException {
        HttpSession session = request.getSession(true);
        answerProcessor.process(input, session);
        SessionDataContainer sessionDataContainer =
                (SessionDataContainer) session.getAttribute(AttributeConstant.SESSION_DATA_CONTAINER);
        boolean answerTriggerResult = answerTrigger.refreshReadinessState(sessionDataContainer);
        if(answerTriggerResult){
            return Response.status(302).build();
        } else {
            return Response.status(200).build();
        }
    }
}
