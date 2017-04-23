package club.myfpl.services.impl;

import club.myfpl.exceptions.EmailAlreadyInUseException;
import club.myfpl.exceptions.UserNotFoundException;
import club.myfpl.domain.User;
import club.myfpl.repositories.UserRepository;
import club.myfpl.resources.dto.CreateUserDTO;
import club.myfpl.resources.dto.UpdateUserDTO;
import club.myfpl.resources.dto.UserCredentialsDTO;
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

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserDTO createUserDTO) throws EmailAlreadyInUseException {
        if (isEmailAlreadyInUse(createUserDTO.getEmail())) {
            throw new EmailAlreadyInUseException(createUserDTO.getEmail());
        }
        User user = new User();
        user.setFullName(createUserDTO.getFullName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        return userRepository.insert(user);
    }

    @Override
    public User updateUser(UpdateUserDTO updateUserDTO) throws EmailAlreadyInUseException, UserNotFoundException {
        User oldUser = userRepository.findOne(updateUserDTO.getId());
        if (oldUser == null) {
            throw new UserNotFoundException(updateUserDTO.getId());
        }
        if (!oldUser.getEmail().equals(updateUserDTO.getEmail()) && isEmailAlreadyInUse(updateUserDTO.getEmail())) {
            throw new EmailAlreadyInUseException(updateUserDTO.getEmail());
        }
        oldUser.setEmail(updateUserDTO.getEmail());
        oldUser.setFullName(updateUserDTO.getFullName());
        return userRepository.save(oldUser);
    }

    @Override
    public boolean updatePassword(UserCredentialsDTO userCredentialsDTO) {
        User user = userRepository.findOne(userCredentialsDTO.getId());
        if (user.getPassword().equals(userCredentialsDTO.getOldPassword())) {
            user.setPassword(userCredentialsDTO.getNewPassword());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    private boolean isEmailAlreadyInUse(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
