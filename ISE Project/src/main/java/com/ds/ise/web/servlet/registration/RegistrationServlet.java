package com.ds.ise.web.servlet.registration;


import com.ds.ise.constant.PathConstant;
import com.ds.ise.entity.User;
import com.ds.ise.entity.additional.UserRole;
import com.ds.ise.exception.captcha.ExpiredRegistrationException;
import com.ds.ise.logic.avatar.AvatarService;
import com.ds.ise.logic.captcha.SessionCaptchaHandler;
import com.ds.ise.service.user.UserService;
import com.ds.ise.validator.Validator;
import com.ds.ise.validator.user.UserEmailCondition;
import com.ds.ise.validator.user.UserFirstNameCondition;
import com.ds.ise.validator.user.UserLastNameCondition;
import com.ds.ise.validator.user.UserPasswordCondition;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Provides registration page access on the GET requests and processes
 * registration in of the particular {@code User} on the POST requests.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@MultipartConfig
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    public static final String FIRST_NAME_PARAM_NAME = "fn";
    public static final String LAST_NAME_PARAM_NAME = "ln";
    public static final String AVATAR_NAME_PARAM_NAME = "an";
    public static final String EMAIL_PARAM_NAME = "em";
    public static final String NOTIFICATIONS_PARAM_NAME = "nt";
    public static final String PASSWORD_PARAM_NAME = "password";
    public static final String PASSWORD_RETYPE_PARAM_NAME = "passwordRetype";

    public static final String URI_DELIMITER = "?";
    public static final String ASSIGN_PARAM = "=";
    public static final String PARAM_DELIMITER = "&";
    public static final String CAPTCHA_INPUT = "captchaInput";

    public static final String ERROR_MESSAGE_PARAM_NAME = "regErrorMessage";
    public static final String ERROR_MESSAGE_USER_EXISTS = "User with specified email already exists.";
    public static final String ERROR_MESSAGE_WRONG_CAPTCHA = "Wrong test number has been entered.";

    private static final int serialVersionUID = 1;

    private static final Logger LOG = Logger.getLogger(RegistrationServlet.class);

    private Validator validator;

    @EJB
    private SessionCaptchaHandler captchaHandler;

    @EJB
    private UserService userService;

    @EJB
    private AvatarService avatarService;

    @Override
    public void init() throws ServletException {
        LOG.debug("Servlet initialization started.");
        LOG.debug("Starts registration data validator initializing.");
        validator = new Validator();
        validator.addCondition("firstName", new UserFirstNameCondition());
        validator.addCondition("lastName", new UserLastNameCondition());
        validator.addCondition("email", new UserEmailCondition());
        validator.addCondition("password", new UserPasswordCondition());
        LOG.debug("Registration data validator has been set up.");
        LOG.debug("Servlet initialization finished.");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        LOG.trace("Do GET started.");
        HttpSession session = request.getSession(true);
        session.setAttribute(PathConstant.CURRENT_URL_PATH_ATTR_NAME, getFullURL(request));
        captchaHandler.setCaptchaString(request, response);
        LOG.trace("Do GET finished: forward on: " + PathConstant.PAGE_REGISTRATION);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PathConstant.PAGE_REGISTRATION);
        dispatcher.forward(request, response);
    }

    /**
     * Gets full URL from the incoming request - includes URL
     * and query with delimiters.
     *
     * @param request that contains URL data.
     * @return full URL of the request.
     */
    private String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURI());
        requestURL.deleteCharAt(0);
        requestURL.append('?');
        String queryString = request.getQueryString();
        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append(queryString).toString();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        LOG.trace("Do POST started.");
        String email = request.getParameter(EMAIL_PARAM_NAME);
        if (userService.doesExist(email)) {
            selfRedirect(request, response, ERROR_MESSAGE_USER_EXISTS);

            return;
        }

        String captchaInput = request.getParameter(CAPTCHA_INPUT);
        try {
            if (!captchaHandler.validateCaptcha(request, response, captchaInput)) {
                selfRedirect(request, response, ERROR_MESSAGE_WRONG_CAPTCHA);

                return;
            }
        } catch (ExpiredRegistrationException ex) {
            selfRedirect(request, response, ex.getMessage());

            return;
        }

        // Just log received notifications according to task.
        LOG.info("Chosen notifications: " + Arrays.toString(request.getParameterValues(NOTIFICATIONS_PARAM_NAME)));
        String errorMessage = validateUser(request);
        if (errorMessage != null) {
            selfRedirect(request, response, errorMessage);

            return;
        }
        registerUser(request);
        LOG.debug("Do POST finished, redirection on: " + PathConstant.REQUEST_CLIENT);
        response.sendRedirect(PathConstant.REQUEST_CLIENT);
    }

    private String validateUser(HttpServletRequest request) {
        Map<String, String[]> attributes = new LinkedHashMap<>(10);
        attributes.put("firstName", new String[]{request.getParameter(FIRST_NAME_PARAM_NAME)});
        attributes.put("lastName", new String[]{request.getParameter(LAST_NAME_PARAM_NAME)});
        attributes.put("email", new String[]{request.getParameter(EMAIL_PARAM_NAME)});
        attributes.put("password", new String[]{request.getParameter(PASSWORD_PARAM_NAME), request.getParameter(PASSWORD_RETYPE_PARAM_NAME)});
        return validator.validate(attributes);
    }

    private void registerUser(HttpServletRequest request) throws ServletException, IOException {
        User userToRegister = new User();
        userToRegister.setEmail(request.getParameter(EMAIL_PARAM_NAME));
        userToRegister.setFirstName(request.getParameter(FIRST_NAME_PARAM_NAME));
        userToRegister.setLastName(request.getParameter(LAST_NAME_PARAM_NAME));
        userToRegister.setPasswordHash(request.getParameter(PASSWORD_PARAM_NAME).hashCode());
        userToRegister.setRole(UserRole.CLIENT);

        // Set up avatar of the user.
        Part imagePart = request.getPart(AVATAR_NAME_PARAM_NAME);
        avatarService.setUpAvatar(imagePart, userToRegister);

        // Insert new user into database.
        userService.register(userToRegister);
    }

    private void selfRedirect(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        LOG.trace("Self redirection prepared: error message: " + errorMessage);
        LOG.debug("Do POST finished, redirects on: '" + PathConstant.REQUEST_REGISTRATION);
        response.sendRedirect(PathConstant.REQUEST_REGISTRATION + getRegistrationQuery(request, errorMessage));
    }

    private String getRegistrationQuery(HttpServletRequest request, String errorMessage) throws IOException, ServletException {
        StringBuilder sb = new StringBuilder(URI_DELIMITER);
        sb.append(FIRST_NAME_PARAM_NAME).append(ASSIGN_PARAM)
                .append(request.getParameter(FIRST_NAME_PARAM_NAME)).append(PARAM_DELIMITER);
        sb.append(LAST_NAME_PARAM_NAME).append(ASSIGN_PARAM)
                .append(request.getParameter(LAST_NAME_PARAM_NAME)).append(PARAM_DELIMITER);
        sb.append(EMAIL_PARAM_NAME).append(ASSIGN_PARAM)
                .append(request.getParameter(EMAIL_PARAM_NAME)).append(PARAM_DELIMITER);
        sb.append(ERROR_MESSAGE_PARAM_NAME).append(ASSIGN_PARAM)
                .append(errorMessage).append(PARAM_DELIMITER);
        String[] notifications = request.getParameterValues(NOTIFICATIONS_PARAM_NAME);
        if (notifications != null) {
            for (int i = 0; i < notifications.length; ++i) {
                sb.append("c").append(Integer.toString(i + 1)).append(ASSIGN_PARAM)
                        .append("y").append(PARAM_DELIMITER);
            }
        }

        return sb.toString();
    }
}
