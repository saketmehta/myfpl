package club.myfpl.services.impl;

import club.myfpl.daos.LeagueDAO;
import club.myfpl.model.League;
import club.myfpl.services.LeagueService;
import club.myfpl.services.SequenceNumberService;
import club.myfpl.services.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * User: Saket
 * Date: 14/12/16
 * Time: 3:34 PM
 */
@Service
public class LeagueServiceImpl implements LeagueService {

    private final LeagueDAO             leagueDAO;
    private final UserService           userService;
    private final SequenceNumberService sequenceNumberService;

    @Autowired
    public LeagueServiceImpl(LeagueDAO leagueDAO, UserService userService, SequenceNumberService sequenceNumberService) {
        this.leagueDAO = leagueDAO;
        this.userService = userService;
        this.sequenceNumberService = sequenceNumberService;
    }

    @Override
    public League fetchLeague(long leagueId) {
        return leagueDAO.findLeague(leagueId);
    }

    @Override
    public List<League> fetchLeaguesForUser(long userId) {
        return leagueDAO.findLeaguesForUser(userId);
    }

    @Override
    public League createOrUpdateLeague(League league) {
        if (league.getLeagueId() != null) {
            return updateLeague(league);
        } else {
            return createLeague(league);
        }
    }

    private League createLeague(League league) {
        if (!isLeagueNameAvailable(league)) {
            throw new RuntimeException("A league with same name already exists!");
        }
        league.setLeagueId(sequenceNumberService.nextSequenceNumber(League.class));
        leagueDAO.createLeague(league);
        return league;
    }

    private League updateLeague(League league) {
        League oldLeague = leagueDAO.findLeague(league.getLeagueId());
        if (oldLeague == null) {
            throw new RuntimeException("Couldn't find a league to update!");
        }
        if (!oldLeague.getName().equals(league.getName())) {
            if (!isLeagueNameAvailable(league)) {
                throw new RuntimeException("A league with same name already exists!");
            }
        }
        oldLeague.setName(league.getName());
        oldLeague.setAdminUserId(league.getAdminUserId());
        oldLeague.setCapacity(league.getCapacity());
        leagueDAO.updateLeague(oldLeague);
        return oldLeague;
    }

    @Override
    public boolean addUserToLeague(String inviteCode, long userId) {
        League league = leagueDAO.findLeagueByInviteCode(inviteCode);
        if (league.getLocked()) {
            return false;
        }
        if (league.getCapacity() == league.getUsers().size()) {
            return false;
        }
        if (userService.fetchUser(userId) == null) {
            return false;
        }
        league.addUser(userId);
        leagueDAO.updateLeague(league);
        return true;
    }

    @Override
    public boolean removeUserFromLeague(long leagueId, long userId) {
        League league = leagueDAO.findLeague(leagueId);
        if (league.getLocked()) {
            return false;
        }
        if (league.getUsers().size() <= 1) {
            return false;
        }
        league.removeUser(userId);
        leagueDAO.updateLeague(league);
        return true;
    }

    @Override
    public boolean lockLeague(long leagueId) {
        League league = leagueDAO.findLeague(leagueId);
        List<Long> users = Lists.newArrayList(league.getUsers());
        if (users.size() > league.getCapacity()) {
            throw new RuntimeException("Capacity exceeded, remove some users first!");
        } else if (users.size() <= 1) {
            throw new RuntimeException("Add some users to this league to lock it!");
        }
        int transferTurnUserId = ThreadLocalRandom.current().nextInt(0, users.size());
        league.setFirstTransferUserId(users.get(transferTurnUserId));
        league.setTransferTurnUserId(users.get(transferTurnUserId));
        Map<Long, Long> transferOrder = Maps.newHashMap();
        for (int i = 0; i < users.size(); i++) {
            transferOrder.put(users.get(i), users.get((i + 1) % users.size()));
        }
        league.setTransferOrder(transferOrder);
        league.setLocked(true);
        leagueDAO.updateLeague(league);
        return true;
    }

    @Override
    public boolean unlockLeague(long leagueId) {
        League league = leagueDAO.findLeague(leagueId);
        league.setLocked(false);
        leagueDAO.updateLeague(league);
        return true;
    }

    private boolean isLeagueNameAvailable(League league) {
        List<League> leaguesForAdmin = leagueDAO.findLeaguesForAdmin(league.getAdminUserId());
        for (League leagueInList : leaguesForAdmin) {
            if (league.getName().equals(leagueInList.getName())) {
                return false;
            }
        }
        return true;
    }
}
