package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.repr.RoleRepr;

import java.util.List;

public interface RoleService {

    List<RoleRepr> findAll();
    List<RoleRepr> findAllWithOutSU();
    RoleRepr findById(Long id);
    RoleRepr findByName(String name);
    void save(RoleRepr role);
    void remove(Long id);
}
