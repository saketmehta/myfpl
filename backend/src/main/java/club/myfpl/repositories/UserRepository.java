package club.myfpl.repositories;

import club.myfpl.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Saket on 15/09/16.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

}
