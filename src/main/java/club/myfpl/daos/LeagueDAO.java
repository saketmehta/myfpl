package club.myfpl.daos;

import club.myfpl.beans.League;

import java.util.List;

/**
 * @author Saket on 15/09/16.
 */
public interface LeagueDAO {
    void createLeague(League league);

    void updateLeague(League league);

    League findLeague(long leagueId);

    List<League> findLeaguesForAdmin(long adminUserId);

    List<League> findLeaguesForUser(long userId);

    League findLeagueByInviteCode(String inviteCode);
}
