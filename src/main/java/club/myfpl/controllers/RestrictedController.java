package club.myfpl.controllers;

import club.myfpl.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Saket
 * Date: 16/12/16
 * Time: 2:08 AM
 */
@Service
@RestController
@RequestMapping("restricted")
public class RestrictedController {

    private final LeagueService leagueService;

    @Autowired
    public RestrictedController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @PostMapping("{leagueId}/lock")
    public ResponseEntity lockLeague(@PathVariable long leagueId) {
        boolean wasLocked = leagueService.lockLeague(leagueId);
        return ResponseEntity.ok(wasLocked);
    }

    @PostMapping("{leagueId}/unlock")
    public ResponseEntity unlockLeague(@PathVariable long leagueId) {
        boolean wasUnlocked = leagueService.unlockLeague(leagueId);
        return ResponseEntity.ok(wasUnlocked);
    }
}
