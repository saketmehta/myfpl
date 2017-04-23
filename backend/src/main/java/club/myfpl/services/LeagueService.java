package club.myfpl.services;

import club.myfpl.domain.League;
import club.myfpl.exceptions.*;
import club.myfpl.repositories.LeagueRepository;
import club.myfpl.resources.dto.CreateLeagueDTO;
import club.myfpl.resources.dto.UpdateLeagueDTO;

/**
 * User: Saket
 * Date: 14/12/16
 * Time: 3:14 PM
 */
public interface LeagueService {

    LeagueRepository geLeagueRepository();

    League createLeague(CreateLeagueDTO createLeagueDTO) throws InviteCodeAlreadyInUserException;

    League updateLeague(UpdateLeagueDTO updateLeagueDTO) throws LeagueNotFoundException;

    League addUserToLeague(String inviteCode, String userId) throws LeagueIsLockedException, LeagueCapacityException;

    League removeUserFromLeague(String leagueId, String userId) throws LeagueIsLockedException, LeagueCapacityException, LeagueUserException;

    League lockLeague(String leagueId) throws LeagueCapacityException, LeagueNotFoundException;

    League unlockLeague(String leagueId) throws LeagueNotFoundException;

}
