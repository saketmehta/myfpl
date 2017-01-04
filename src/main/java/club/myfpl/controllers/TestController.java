package club.myfpl.controllers;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * User: Saket
 * Date: 27/12/16
 * Time: 4:29 PM
 */
@Service
@RestController
@RequestMapping(value = "test")
public class TestController {

    @RequestMapping
    public Principal testing(Principal principal) {
        return principal;
    }
}
