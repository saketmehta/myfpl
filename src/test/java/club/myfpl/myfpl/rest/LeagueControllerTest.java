package club.myfpl.myfpl.rest;

import club.myfpl.controllers.LeagueController;
import club.myfpl.controllers.dto.LeagueDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 16/12/16
 * Time: 12:57 AM
 */
@Service
public class LeagueControllerTest {

    @Autowired
    private LeagueController leagueController;

    @Test
    public void testCreateLeague() throws Exception {
        LeagueDTO leagueDTO = new LeagueDTO();
        leagueDTO.setName("Test League");
        leagueDTO.setAdminUserId(1011);
        leagueDTO.setCapacity(5);
        leagueDTO.setInviteCode("abc");
        leagueController.createorUpdateLeague(leagueDTO);
    }

    @Test
    public void testLeagueRestAPI() throws Exception {
        leagueController.addUserToLeague("abc", 1012);
        leagueController.addUserToLeague("abc", 1013);
        leagueController.addUserToLeague("abc", 1014);
        leagueController.removeUserFromLeague(1000, 1013);
    }

    @Test
    public void testUpdateLeague() throws Exception {
        LeagueDTO leagueDTO = new LeagueDTO();
        leagueDTO.setLeagueId(1000);
        leagueDTO.setName("My League");
        leagueDTO.setAdminUserId(1011);
        leagueDTO.setCapacity(5);
        leagueDTO.setInviteCode("abc");
        leagueController.createorUpdateLeague(leagueDTO);
    }
}