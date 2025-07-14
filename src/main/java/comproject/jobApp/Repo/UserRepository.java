package comproject.jobApp.Repo;

import comproject.jobApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<String> findById(String name);

    Optional<User> findByName(String name);

}
