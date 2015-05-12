package com.ds.ise.web.listener;

import com.ds.ise.data.session.SessionDataContainer;
import com.ds.ise.data.session.SessionDataContainerFactory;
import com.ds.ise.web.constant.AttributeConstant;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class GeneralSessionListener implements HttpSessionListener {

    private static final Logger LOG = Logger.getLogger(GeneralSessionListener.class);

    @EJB
    private SessionDataContainerFactory sessionDataContainerFactory;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LOG.trace("HTTP session creation started.");
        HttpSession session = httpSessionEvent.getSession();
        SessionDataContainer sessionDataContainer = sessionDataContainerFactory.newSessionDataContainer();
        session.setAttribute(AttributeConstant.SESSION_DATA_CONTAINER, sessionDataContainer);
        LOG.trace("HTTP session creation finished.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        LOG.trace("HTTP session destruction started.");
        LOG.trace("HTTP session destruction finished.");
    }
}
