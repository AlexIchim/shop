package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
