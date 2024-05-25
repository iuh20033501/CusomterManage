package iuh.fit.authjwtservice.repository;

import iuh.fit.authjwtservice.entity.Token;
import iuh.fit.authjwtservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);
//    Optional<User> findById(Long id);
}
