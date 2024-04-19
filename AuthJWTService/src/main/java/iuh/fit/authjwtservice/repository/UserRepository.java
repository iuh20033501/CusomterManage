package iuh.fit.authjwtservice.repository;

import iuh.fit.authjwtservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
