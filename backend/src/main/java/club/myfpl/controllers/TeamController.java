package club.myfpl.controllers;

import club.myfpl.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Saket
 * Date: 29/12/16
 * Time: 5:29 PM
 */
@Service
@RestController
@RequestMapping("teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping("all")
    public ResponseEntity fetchTeams() {
        return ResponseEntity.ok(teamService.fetchTeams());
    }
}
