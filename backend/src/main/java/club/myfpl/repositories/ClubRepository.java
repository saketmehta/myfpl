package club.myfpl.repositories;

import club.myfpl.domain.Club;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Saket
 * @since 23/04/17.
 */
public interface ClubRepository extends MongoRepository<Club, Long> {
}
