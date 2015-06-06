package com.ds.ise.data.dao;

import com.ds.ise.entity.User;

import javax.ejb.Stateless;
import java.util.List;

/**
 * This class is a data access object for {@code User} entities
 * table in the database connected to the application.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
@Stateless
public class UserDAO extends CommonDAO<User> {

    public static final String EMAIL_FIELD = "email";

    public UserDAO(){
        super(User.class);
    }

    /**
     * If the user with the specified email is not found,
     * returns {@code null}.
     */
    public User findByEmail(String email) {
        List<User> users = findFilteredResults(EMAIL_FIELD, email);
        if(users == null || users.size() == 0){
            return null;
        }
        return users.get(0);
    }

    public void delete(long id) {
        super.delete(id);
    }

}
