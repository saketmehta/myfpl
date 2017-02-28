package club.myfpl.services;

import club.myfpl.model.League;

import java.util.List;
import java.util.Optional;

/**
 * User: Saket
 * Date: 14/12/16
 * Time: 3:14 PM
 */
public interface LeagueService {
    League fetchLeague(long leagueId);

    List<League> fetchLeaguesForUser(long userId);

    League createOrUpdateLeague(League league);

    Optional<League> addUserToLeague(String inviteCode, long userId);

    boolean removeUserFromLeague(long leagueId, long userId);

    boolean lockLeague(long leagueId);

    boolean unlockLeague(long leagueId);
}
