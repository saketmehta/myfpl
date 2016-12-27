package club.myfpl.myfpl.rest;

import club.myfpl.controllers.UserController;
import club.myfpl.controllers.dto.UserDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 4:25 PM
 */
@Service
public class UserControllerTest {

    @Autowired
    private UserController restAPI;

    @Test
    public void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("saket.m17+1@outlook.com");
        userDTO.setFullName("Saket 1 Mehta");
        userDTO.setPassword("IamSe7en!");
        restAPI.createUser(userDTO);

        userDTO.setEmail("saket.m17+2@outlook.com");
        userDTO.setFullName("Saket 2 Mehta");
        userDTO.setPassword("IamSe7en!");
        restAPI.createUser(userDTO);

        userDTO.setEmail("saket.m17+3@outlook.com");
        userDTO.setFullName("Saket 3 Mehta");
        userDTO.setPassword("IamSe7en!");
        restAPI.createUser(userDTO);

        userDTO.setEmail("saket.m17+4@outlook.com");
        userDTO.setFullName("Saket 4 Mehta");
        userDTO.setPassword("IamSe7en!");
        restAPI.createUser(userDTO);
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("saket.test@outlook.com");
        userDTO.setFullName("Saket Mehta");
        restAPI.updateUser(1004L, userDTO);
    }

    @Test
    public void testFetchUser() throws Exception {
        ResponseEntity responseEntity = restAPI.fetchUser(1000);
    }
}