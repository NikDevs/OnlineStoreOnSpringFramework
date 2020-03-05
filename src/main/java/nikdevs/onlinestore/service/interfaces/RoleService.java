package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.model.RoleRepr;

import java.util.List;

public interface RoleService {

    List<RoleRepr> findAll();
    List<RoleRepr> findAllWithOutSU();
    RoleRepr findById(int id);
    RoleRepr findByName(String name);
    void save(RoleRepr role);
    void remove(int id);
}
