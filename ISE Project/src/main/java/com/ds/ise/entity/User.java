package com.ds.ise.entity;

import com.ds.ise.entity.additional.UserRole;

import javax.persistence.*;

/**
 * Entity represents both clients and administrators.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private int passwordHash;

    @Column(name = "avatar_name")
    private String avatarName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_role_id")
    private UserRole role;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        if (id != user.id) {
            return false;
        }
        if (passwordHash != user.passwordHash) {
            return false;
        }
        if (firstName == null ? user.firstName != null : !firstName.equals(user.firstName)) {
            return false;
        }
        if (lastName == null ? user.lastName != null : !lastName.equals(user.lastName)) {
            return false;
        }
        if (email == null ? user.email != null : !email.equals(user.email)) {
            return false;
        }
        if (avatarName == null ? user.avatarName != null : !avatarName.equals(user.avatarName)) {
            return false;
        }
        if (role == null ? user.role != null : role != user.role) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + passwordHash;
        result = 31 * result + avatarName.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash=" + passwordHash +
                ", avatarName='" + avatarName + '\'' +
                ", role=" + role +
                '}';
    }
}
