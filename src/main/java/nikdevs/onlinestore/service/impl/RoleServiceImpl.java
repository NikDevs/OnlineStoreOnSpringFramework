package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.persist.model.Role;
import nikdevs.onlinestore.persist.repo.RoleRepository;
import nikdevs.onlinestore.service.interfaces.RoleService;
import nikdevs.onlinestore.service.model.RoleRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleRepr> findAll() {
        return roleRepository.findAllRoleRepr();
    }

    @Override
    public List<RoleRepr> findAllWithOutSU() {
        List<RoleRepr> roles = findAll();
        roles.remove(findById(1));
        return roles;
    }

    @Override
    public RoleRepr findById(int id) {
        return new RoleRepr(roleRepository.findById(id).get());
    }

    @Override
    public void save(RoleRepr roleRepr) {
        Role role = (roleRepr.getId() != null) ? roleRepository.findById(roleRepr.getId()).get() : new Role();
        role.setName(roleRepr.getName());
        roleRepository.save(role);
    }

    @Override
    public void remove(int id) {
        roleRepository.findById(id).ifPresent(roleRepository::delete);
    }
}
