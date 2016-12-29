package club.myfpl.controllers;

import club.myfpl.beans.Fixture;
import club.myfpl.services.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Saket
 * Date: 28/12/16
 * Time: 2:56 PM
 */
@Service
@RestController
@RequestMapping("fixtures")
public class FixtureController {
    private final FixtureService fixtureService;

    @Autowired
    public FixtureController(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }

    @RequestMapping("current")
    public ResponseEntity getCurrentFixtures() {
        List<Fixture> fixtures = fixtureService.fetchCurrentFixtures();
        return ResponseEntity.ok(fixtures);
    }

    @RequestMapping("{fixtureId}")
    public ResponseEntity getFixture(@PathVariable long fixtureId) {
        Fixture fixture = fixtureService.fetchFixture(fixtureId);
        return ResponseEntity.ok(fixture);
    }
}
