package exercise.repository;

import exercise.dto.UserDto;
import exercise.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // BEGIN
    Optional<User> findUserByUsername(String username);
    // END

    Optional<User> findByEmail(String email);

}
