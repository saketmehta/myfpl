package club.myfpl.controllers;

import club.myfpl.controllers.dto.UserDTO;
import club.myfpl.model.User;
import club.myfpl.services.UserService;
import club.myfpl.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 3:11 PM
 */
//@Secured
@Service
@RestController
@RequestMapping("user")
public class UserController {

    private final ValidationService validator;
    private final UserService userService;

    @Autowired
    public UserController(ValidationService validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
        validator.validate(userDTO);
        User user = userService.createUser(userDTO.toUser());
        return ResponseEntity.ok(user.getUserId());
    }

    @PostMapping("update/{userId}")
    public ResponseEntity updateUser(@PathVariable long userId, @RequestBody UserDTO userDTO) {
        validator.validate(userDTO);
        User user = userService.updateUser(userId, userDTO.toUser());
        return ResponseEntity.ok(user.getUserId());
    }

    @GetMapping("fetch/{userId}")
    public ResponseEntity fetchUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.fetchUser(userId));
    }
}
