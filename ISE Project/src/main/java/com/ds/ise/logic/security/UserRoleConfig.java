package com.ds.ise.logic.security;

import com.ds.ise.entity.additional.UserRole;

import java.util.Set;

/**
 * This entity wraps specified URL pattern and {@code UserRole}
 * constants that have permission to access this URL.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class UserRoleConfig {

    private String urlPattern;
    private Set<UserRole> roles;

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRoleConfig)) {
            return false;
        }

        UserRoleConfig config = (UserRoleConfig) o;

        if (roles == null? config.roles != null: !roles.equals(config.roles)) {
            return false;
        }
        if (urlPattern == null? config.urlPattern != null: !urlPattern.equals(config.urlPattern)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = urlPattern.hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserRoleConfig{" +
                "urlPattern='" + urlPattern + '\'' +
                ", roles=" + roles +
                '}';
    }
}