package mate.academy.hwspringbootintro.repository.auth;

import mate.academy.hwspringbootintro.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
