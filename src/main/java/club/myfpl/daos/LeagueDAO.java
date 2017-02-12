package club.myfpl.daos;

import club.myfpl.model.League;

import java.util.List;

/**
 * @author Saket on 15/09/16.
 */
public interface LeagueDAO {
    League findLeague(long leagueId);

    List<League> findLeaguesForUser(long userId);

    void createLeague(League league);

    void updateLeague(League league);

    List<League> findLeaguesForAdmin(long adminUserId);

    League findLeagueByInviteCode(String inviteCode);
}
