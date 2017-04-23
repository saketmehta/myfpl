package club.myfpl.services;

import club.myfpl.exceptions.EmailAlreadyInUseException;
import club.myfpl.exceptions.UserNotFoundException;
import club.myfpl.domain.User;
import club.myfpl.repositories.UserRepository;
import club.myfpl.resources.dto.CreateUserDTO;
import club.myfpl.resources.dto.UpdateUserDTO;
import club.myfpl.resources.dto.UserCredentialsDTO;

/**
 * User: Saket
 * Date: 28/11/16
 * Time: 5:03 PM
 */
public interface UserService {

    User createUser(CreateUserDTO createUserDTO) throws EmailAlreadyInUseException;

    User updateUser(UpdateUserDTO updateUserDTO) throws EmailAlreadyInUseException, UserNotFoundException;

    boolean updatePassword(UserCredentialsDTO userCredentialsDTO);

    UserRepository getUserRepository();

}
