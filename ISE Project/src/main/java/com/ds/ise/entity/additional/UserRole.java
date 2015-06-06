package com.ds.ise.entity.additional;

/**
 * This enumeration represents possible roles of
 * this system {@code User} entities.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public enum UserRole {
    CLIENT(0),
    ADMIN(1);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserRole getById(int idToSearchFor) {
        for (UserRole userRole : values()) {
            if (idToSearchFor == userRole.id) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("No UserRole with specified ID exists.");
    }

    public static UserRole getByName(String name) {
        for (UserRole userRole : values()) {
            if (name.toUpperCase().equals(userRole.name())) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("No UserRole with specified name exists.");
    }
}
