package nikdevs.onlinestore.persist.repo;

import nikdevs.onlinestore.persist.model.Role;
import nikdevs.onlinestore.service.model.RoleRepr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select new nikdevs.onlinestore.service.model.RoleRepr(r.id, r.name, count(u.id)) " +
            "from Role r " +
            "left join r.users u " +
            "group by r.id, r.name")
    List<RoleRepr> findAllRoleRepr();
}
