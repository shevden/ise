package com.ds.ise.service.user;

import com.ds.ise.data.dao.UserDAO;
import com.ds.ise.entity.User;
import com.ds.ise.exception.login.LoginException;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

@Stateless
public class UserService {

    public static final String ERROR_MESSAGE_NO_EMAIL = "No user with such email found.";
    public static final String ERROR_MESSAGE_DO_NOT_MATCH = "Specified email and password do not match any account.";

    @EJB
    private UserDAO userDAO;

    public boolean doesExist(String email) {
        return userDAO.findByEmail(email) != null;
    }

    public User getAuthorizedUser(String email, String password) throws LoginException {
        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new LoginException(ERROR_MESSAGE_NO_EMAIL);
        }
        if (user.getPasswordHash() != password.hashCode()) {
            throw new LoginException(ERROR_MESSAGE_DO_NOT_MATCH);
        }
        return user;
    }

    public void register(User user) {
        userDAO.save(user);
    }

    public void delete(long id) {
        userDAO.delete(id);
    }
}
