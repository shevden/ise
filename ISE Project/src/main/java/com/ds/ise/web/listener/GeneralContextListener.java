package com.ds.ise.web.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Context listener.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@WebListener
public class GeneralContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(GeneralContextListener.class);

    /**
     *
     * @param event for application context access.
     */
    @Override
    public void contextInitialized(ServletContextEvent event){
        LOG.info("Servlet context initialization started.");
        LOG.info("Servlet context initialization finished.");
    }


    /**
     *
     * @param event not used.
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOG.debug("Servlet context destruction started.");
        LOG.debug("Servlet context destruction finished.");
    }

}