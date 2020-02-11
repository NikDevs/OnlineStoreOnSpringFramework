package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.persist.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    Role findById(int id);
    void save(Role role);
    void remove(int id);
}
