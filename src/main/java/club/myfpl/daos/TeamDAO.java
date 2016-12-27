package club.myfpl.daos;

import club.myfpl.beans.Team;

import java.util.List;

/**
 * @author Saket on 15/09/16.
 */
public interface TeamDAO {
    void updateTeams(List<Team> teams);
}
