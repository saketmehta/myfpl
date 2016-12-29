package club.myfpl.services;

import club.myfpl.beans.Team;

import java.util.List;

/**
 * User: Saket
 * Date: 29/12/16
 * Time: 5:30 PM
 */
public interface TeamService {
    List<Team> fetchTeams();
}
