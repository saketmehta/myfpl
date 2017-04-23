package club.myfpl.repositories;

import club.myfpl.domain.League;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Saket on 15/09/16.
 */
public interface LeagueRepository extends MongoRepository<League, String> {

    List<League> findByUsersContaining(String userId);

    List<League> findByAdminUserId(String adminUserId);

    League findByInviteCode(String inviteCode);

}
