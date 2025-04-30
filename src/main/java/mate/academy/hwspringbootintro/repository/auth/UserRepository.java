package mate.academy.hwspringbootintro.repository.auth;

import mate.academy.hwspringbootintro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
