package com.ds.ise.web.filter;

import com.ds.ise.constant.PathConstant;
import com.ds.ise.entity.User;
import com.ds.ise.logic.security.AccessRedirectOption;
import com.ds.ise.logic.security.SecurityConfigParser;
import com.ds.ise.logic.security.SecurityManager;
import com.ds.ise.logic.security.UserRoleConfig;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * This filter processes all requests and checks if the
 * client do has permission to get requested resource.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class AccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AccessFilter.class);

    private Set<UserRoleConfig> userRoleConfigs;
    private com.ds.ise.logic.security.SecurityManager securityManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Initialization started.");
        LOG.trace("Security configuration initialization started.");
        String securityConfigFile = filterConfig.getInitParameter("securityConfigFile");
        SecurityConfigParser securityConfigParser = new SecurityConfigParser();
        try {
            userRoleConfigs = securityConfigParser.parseUserRoleConfigs(securityConfigFile);
        } catch (Exception ex) {
            LOG.error("Cannot parse security configuration: " + ex);
            throw new RuntimeException(ex);
        }
        LOG.trace("Security configuration initialization finished.");

        LOG.trace("Security manager initialization started.");
        securityManager = new SecurityManager();
        LOG.trace("Security manager initialization finished.");

        LOG.debug("Initialization finished.");
    }

    @Override
    public void destroy() {
        LOG.debug("Destruction started.");
        LOG.debug("Destruction finished.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("Filter started.");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String uri = httpRequest.getRequestURI();
        LOG.trace("Request URL: " + uri);
        User user = (User)session.getAttribute(PathConstant.USER_ATTR_NAME);
        AccessRedirectOption option = securityManager.checkPermission(uri, userRoleConfigs, user);
        if(option != AccessRedirectOption.ACCEPTED) {
            if (option == AccessRedirectOption.LOGIN) {
                LOG.debug("Filter finished, forward on: " + PathConstant.REQUEST_CLIENT);
                RequestDispatcher rd = httpRequest.getRequestDispatcher(PathConstant.REQUEST_CLIENT);
                rd.forward(request, response);
            } else {
                LOG.debug("Filter finished, redirect on: " + PathConstant.REQUEST_NO_PERMISSION);
                httpResponse.sendRedirect(PathConstant.REQUEST_NO_PERMISSION);
            }
            return;
        }
        LOG.debug("Filter finished.");
        filterChain.doFilter(request, response);
    }
}
