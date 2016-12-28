package club.myfpl.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Saket
 * Date: 27/12/16
 * Time: 4:29 PM
 */
@Service
@RestController
@RequestMapping(value = "test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestController {

    @RequestMapping
    public String testing() {
        return "Hello!";
    }
}
