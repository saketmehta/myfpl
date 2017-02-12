package club.myfpl.services.impl;

import club.myfpl.daos.UserDAO;
import club.myfpl.exceptions.EmailAlreadyInUseException;
import club.myfpl.exceptions.UserNotFoundException;
import club.myfpl.model.User;
import club.myfpl.resources.dto.UpdateUserDTO;
import club.myfpl.services.SequenceNumberService;
import club.myfpl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 28/11/16
 * Time: 5:07 PM
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDAO               userDAO;
    private final SequenceNumberService sequenceNumberService;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, SequenceNumberService sequenceNumberService) {
        this.userDAO = userDAO;
        this.sequenceNumberService = sequenceNumberService;
    }

    @Override
    public User createUser(User user) {
        if (isEmailAlreadyInUse(user.getEmail())) {
            throw new RuntimeException("That email is already in use!");
        }
        user.setUserId(sequenceNumberService.nextSequenceNumber(User.class));
        userDAO.createUser(user);
        return user;
    }

    @Override
    public User updateUser(UpdateUserDTO updateUserDTO) {
        User oldUser = userDAO.findUser(updateUserDTO.getUserId());
        if (oldUser == null) {
            throw new UserNotFoundException();
        }
        if (!oldUser.getEmail().equals(updateUserDTO.getEmail()) && isEmailAlreadyInUse(updateUserDTO.getEmail())) {
            throw new EmailAlreadyInUseException();
        }
        oldUser.setEmail(updateUserDTO.getEmail());
        oldUser.setFullName(updateUserDTO.getFullName());
        userDAO.updateUser(oldUser);
        oldUser.setPassword(null);
        return oldUser;
    }

    @Override
    public User fetchUser(long userId) {
        User user = userDAO.findUser(userId);
        user.setPassword(null);
        return user;
    }

    @Override
    public User fetchUserByEmail(String email) {
        User userByEmail = userDAO.findUserByEmail(email);
        userByEmail.setPassword(null);
        return userByEmail;
    }

    @Override
    public User authenticate(String email, String password) {
        User userByEmail = userDAO.findUserByEmail(email);
        if (userByEmail.getPassword().equals(password)) {
            userByEmail.setPassword(null);
            return userByEmail;
        }
        return null;
    }

    @Override
    public boolean updatePassword(long userId, String oldPassword, String newPassword) {
        User user = userDAO.findUser(userId);
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userDAO.updateUser(user);
            return true;
        }
        return false;
    }

    private boolean isEmailAlreadyInUse(String email) {
        User user = userDAO.findUserByEmail(email);
        return user != null;
    }
}
