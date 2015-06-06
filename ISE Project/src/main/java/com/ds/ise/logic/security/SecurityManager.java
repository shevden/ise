package com.ds.ise.logic.security;

import com.ds.ise.entity.User;
import com.ds.ise.entity.additional.UserRole;

import java.util.Set;

/**
 * This class manages access to URLs that are processed.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class SecurityManager {

    /**
     * Checks if the specified {@code User} has rights to access current URL.
     *
     * @param uri             that is checked.
     * @param userRoleConfigs that contains constraints about URLs.
     * @param user            that is checked.
     * @return redirection {@code AccessRedirectOption} that contains redirection URL or accept option.
     */
    public AccessRedirectOption checkPermission(String uri, Set<UserRoleConfig> userRoleConfigs, User user) {
        for (UserRoleConfig config : userRoleConfigs) {
            if (uri.contains(config.getUrlPattern())) {
                if (user == null) {
                    return AccessRedirectOption.LOGIN;
                }
                for (UserRole role : config.getRoles()) {
                    if (role == user.getRole()) {
                        return AccessRedirectOption.ACCEPTED;
                    }
                }

                return AccessRedirectOption.REJECTED;
            }
        }

        return AccessRedirectOption.ACCEPTED;
    }
}
