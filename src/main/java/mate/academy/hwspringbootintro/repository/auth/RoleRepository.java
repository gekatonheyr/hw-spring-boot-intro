package mate.academy.hwspringbootintro.repository.auth;

import java.util.Optional;
import mate.academy.hwspringbootintro.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(Role.RoleName roleName);
}
