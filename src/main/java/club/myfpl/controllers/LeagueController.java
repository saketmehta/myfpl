package club.myfpl.controllers;

import club.myfpl.controllers.dto.LeagueDTO;
import club.myfpl.model.League;
import club.myfpl.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * User: Saket
 * Date: 14/12/16
 * Time: 3:04 PM
 */
@Service
@RestController
@RequestMapping("leagues")
public class LeagueController {

    private final LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping("fetch/{leagueId}")
    public ResponseEntity fetchLeague(@PathVariable long leagueId) {
        return ResponseEntity.ok(leagueService.fetchLeague(leagueId));
    }

    @GetMapping("fetch/all")
    public ResponseEntity fetchLeagues() {
        return ResponseEntity.ok(leagueService.fetchLeaguesForUser(1000));
    }

    @PostMapping({"create", "update"})
    public ResponseEntity createorUpdateLeague(@RequestBody LeagueDTO leagueDTO) {
        League league = leagueService.createOrUpdateLeague(leagueDTO.toLeague());
        return ResponseEntity.ok(league.getLeagueId());
    }

    @PostMapping("add-user")
    public ResponseEntity addUserToLeague(@RequestParam String inviteCode, @RequestParam long userId) {
        boolean wasAdded = leagueService.addUserToLeague(inviteCode, userId);
        return ResponseEntity.ok(wasAdded);
    }

    @PostMapping("{leagueId}/remove-user/{userId}")
    public ResponseEntity removeUserFromLeague(@PathVariable long leagueId, @PathVariable long userId) {
        boolean wasRemoved = leagueService.removeUserFromLeague(leagueId, userId);
        return ResponseEntity.ok(wasRemoved);
    }
}
