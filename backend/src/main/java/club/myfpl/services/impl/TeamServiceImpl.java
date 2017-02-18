package club.myfpl.services.impl;

import club.myfpl.daos.TeamDAO;
import club.myfpl.model.Team;
import club.myfpl.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Saket
 * Date: 29/12/16
 * Time: 5:31 PM
 */
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamDAO teamDAO;

    @Autowired
    public TeamServiceImpl(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public List<Team> fetchTeams() {
        return teamDAO.fetchTeams();
    }
}
