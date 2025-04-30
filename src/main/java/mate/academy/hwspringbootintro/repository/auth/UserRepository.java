package mate.academy.hwspringbootintro.repository.auth;

import jakarta.persistence.Tuple;
import jakarta.validation.constraints.NotNull;
import mate.academy.hwspringbootintro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Tuple findByEmail(@NotNull String email);
}
