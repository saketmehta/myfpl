package club.myfpl.repositories;

import club.myfpl.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Saket on 15/09/16.
 */
public interface PlayerRepository extends MongoRepository<Player, Long> {
}
