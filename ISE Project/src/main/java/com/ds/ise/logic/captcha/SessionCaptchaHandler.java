package com.ds.ise.logic.captcha;


import com.ds.ise.exception.captcha.ExpiredRegistrationException;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Implements captcha handler through the session context data storing.
 * Provides removing of deprecated session data and control of registration
 * process expiration by itself.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@Stateless
public class SessionCaptchaHandler {

    public static final String CAPTCHA_STRING_PARAM_NAME = "captchaString";
    public static final String CAPTCHA_GET_TIME_PARAM_NAME = "captchaGetTime";

    private static final Logger LOG = Logger.getLogger(SessionCaptchaHandler.class);

    // Registration timeout in seconds.
    public static final int timeout = 300;

    @EJB
    private CaptchaGenerator captchaGenerator;

    public void setCaptchaString(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Set captcha string method started.");
        String captchaString = captchaGenerator.generateCaptchaString();
        LOG.debug("Captcha String has been generated with value: " + captchaString);
        HttpSession session = request.getSession();

        // Saves captcha string, that has been drawn on the clients side image.
        session.setAttribute(CAPTCHA_STRING_PARAM_NAME, captchaString);

        // Saves time in seconds, NOT milliseconds.
        session.setAttribute(CAPTCHA_GET_TIME_PARAM_NAME, System.currentTimeMillis() / 1000L);
        LOG.trace("Set captcha string method finished.");
    }

    public String getCaptchaString(HttpServletRequest request) {
        LOG.trace("Get captcha string method started.");
        HttpSession session = request.getSession();
        String captchaString = (String) session.getAttribute(CAPTCHA_STRING_PARAM_NAME);
        LOG.trace("Get captcha string method finished. Value to return: " + captchaString);

        return captchaString;
    }

    //@Override
    public boolean validateCaptcha(HttpServletRequest request, HttpServletResponse response, String captchaInput) {
        LOG.trace("Validate captcha method started.");
        HttpSession session = request.getSession();
        boolean isRightCaptcha = captchaInput.equals(session.getAttribute(CAPTCHA_STRING_PARAM_NAME));
        try {
            if (isRightCaptcha) {

                // Gets current time in seconds, NOT milliseconds.
                long currentTime = System.currentTimeMillis() / 1000L;
                long getCaptchaTime = (Long) session.getAttribute(CAPTCHA_GET_TIME_PARAM_NAME);
                if (currentTime - getCaptchaTime >= timeout) {
                    throw new ExpiredRegistrationException("Registration process has broke time itemsOnPage.");
                }
            }
        } finally {

            // Removes captcha data even if the registration process is expired.
            session.removeAttribute(CAPTCHA_STRING_PARAM_NAME);
            session.removeAttribute(CAPTCHA_GET_TIME_PARAM_NAME);
            LOG.debug("Captcha data removed from storage.");
            LOG.debug("Captcha validation result: " + isRightCaptcha);
            LOG.trace("Validate captcha method finished.");
        }

        return isRightCaptcha;
    }

}
