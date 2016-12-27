package club.myfpl.controllers;

import club.myfpl.beans.League;
import club.myfpl.controllers.dto.LeagueDTO;
import club.myfpl.services.LeagueService;
import club.myfpl.validation.ValidationService;
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
@RequestMapping("league")
public class LeagueController {

    private final ValidationService validationService;
    private final LeagueService leagueService;

    @Autowired
    public LeagueController(ValidationService validationService, LeagueService leagueService) {
        this.validationService = validationService;
        this.leagueService = leagueService;
    }

    @GetMapping("fetch/{leagueId}")
    public ResponseEntity fetchLeague(@PathVariable long leagueId) {
        return ResponseEntity.ok(leagueService.fetchLeague(leagueId));
    }

    @GetMapping("fetch/all/{userId}")
    public ResponseEntity fetchLeagues(@PathVariable long userId) {
        return ResponseEntity.ok(leagueService.fetchLeaguesForUser(userId));
    }

    @PostMapping({"create", "update"})
    public ResponseEntity createorUpdateLeague(@RequestBody LeagueDTO leagueDTO) {
        validationService.validate(leagueDTO);
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
