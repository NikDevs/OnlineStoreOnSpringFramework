package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.model.RoleRepr;

import java.util.List;

public interface RoleService {

    List<RoleRepr> findAll();
    List<RoleRepr> findAllWithOutSU();
    RoleRepr findById(int id);
    void save(RoleRepr role);
    void remove(int id);
}
