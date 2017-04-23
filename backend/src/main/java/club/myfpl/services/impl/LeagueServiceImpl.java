package club.myfpl.services.impl;

import club.myfpl.domain.League;
import club.myfpl.exceptions.*;
import club.myfpl.repositories.LeagueRepository;
import club.myfpl.resources.dto.CreateLeagueDTO;
import club.myfpl.resources.dto.UpdateLeagueDTO;
import club.myfpl.services.LeagueService;
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

    private final LeagueRepository leagueRepository;

    @Autowired
    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public LeagueRepository geLeagueRepository() {
        return leagueRepository;
    }

    @Override
    public League createLeague(CreateLeagueDTO createLeagueDTO) throws InviteCodeAlreadyInUserException {
        if (!isInviteCodeAvailable(createLeagueDTO.getInviteCode())) {
            throw new InviteCodeAlreadyInUserException("Invite code is already in use: " + createLeagueDTO.getInviteCode());
        }
        League league = createLeagueDTO.toLeague();
        return leagueRepository.insert(league);
    }

    @Override
    public League updateLeague(UpdateLeagueDTO updateLeagueDTO) throws LeagueNotFoundException {
        League oldLeague = leagueRepository.findOne(updateLeagueDTO.getId());
        if (oldLeague == null) {
            throw new LeagueNotFoundException("Cannot find league with ID: " + updateLeagueDTO.getId());
        }
        oldLeague.setName(updateLeagueDTO.getName());
        oldLeague.setCapacity(updateLeagueDTO.getCapacity());
        oldLeague.setInviteCode(updateLeagueDTO.getInviteCode());
        return leagueRepository.save(oldLeague);
    }

    @Override
    public League addUserToLeague(String inviteCode, String userId) throws LeagueIsLockedException, LeagueCapacityException {
        League league = leagueRepository.findByInviteCode(inviteCode);
        if (league.getLocked()) {
            throw new LeagueIsLockedException("Cannot add user to a locked league: " + league.getName());
        }
        if (league.getCapacity() <= league.getUsers().size()) {
            throw new LeagueCapacityException("Capacity exceeded. League: " + league.getName());
        }
        league.getUsers().add(userId);
        return leagueRepository.save(league);
    }

    @Override
    public League removeUserFromLeague(String leagueId, String userId) throws LeagueIsLockedException, LeagueCapacityException, LeagueUserException {
        League league = leagueRepository.findOne(leagueId);
        if (league.getAdminUserId().equals(userId)) {
            throw new LeagueUserException("Cannot remove league admin from league: " + league.getName());
        }
        if (league.getLocked()) {
            throw new LeagueIsLockedException("Cannot remove user from a locked league: " + league.getName());
        }
        if (league.getUsers().size() <= 1) {
            throw new LeagueCapacityException("Cannot remove the last member from league: " + league.getName());
        }
        league.getUsers().remove(userId);
        return leagueRepository.save(league);
    }

    @Override
    public League lockLeague(String leagueId) throws LeagueCapacityException, LeagueNotFoundException {
        League league = leagueRepository.findOne(leagueId);
        if (league == null) {
            throw new LeagueNotFoundException("League not found with ID: " + leagueId);
        }
        List<String> users = Lists.newArrayList(league.getUsers());
        if (users.size() > league.getCapacity()) {
            throw new LeagueCapacityException("Capacity exceeded, remove some users first. League: " + league.getName());
        } else if (users.size() <= 1) {
            throw new LeagueCapacityException("Add some users to this league to lock it. League: " + league.getName());
        }
        int transferTurnUserId = ThreadLocalRandom.current().nextInt(0, users.size());
        league.setFirstTransferUserId(users.get(transferTurnUserId));
        league.setTransferTurnUserId(users.get(transferTurnUserId));
        Map<String, String> transferOrder = Maps.newHashMap();
        for (int i = 0; i < users.size(); i++) {
            transferOrder.put(users.get(i), users.get((i + 1) % users.size()));
        }
        league.setTransferOrder(transferOrder);
        league.setLocked(true);
        return leagueRepository.save(league);
    }

    @Override
    public League unlockLeague(String leagueId) throws LeagueNotFoundException {
        League league = leagueRepository.findOne(leagueId);
        if (league == null) {
            throw new LeagueNotFoundException("League not found with ID: " + leagueId);
        }
        league.setLocked(false);
        return leagueRepository.save(league);
    }

    private boolean isInviteCodeAvailable(String inviteCode) {
        return leagueRepository.findByInviteCode(inviteCode) != null;
    }

}
