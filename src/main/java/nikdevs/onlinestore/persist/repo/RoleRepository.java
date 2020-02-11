package nikdevs.onlinestore.persist.repo;


import nikdevs.onlinestore.persist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findOneByName(String name);
}
