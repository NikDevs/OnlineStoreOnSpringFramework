package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.aspect.TrackTime;
import nikdevs.onlinestore.persist.model.Role;
import nikdevs.onlinestore.persist.repo.RoleRepository;
import nikdevs.onlinestore.service.interfaces.RoleService;
import nikdevs.onlinestore.service.repr.RoleRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService, Serializable {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @TrackTime
    public List<RoleRepr> findAll() {
        return roleRepository.findAllRoleRepr();
    }

    @Override
    @TrackTime
    public List<RoleRepr> findAllWithOutSU() {
        List<RoleRepr> roles = findAll();
        roles.remove(findById(1L));
        return roles;
    }

    @Override
    @TrackTime
    public RoleRepr findById(Long id) {
        return new RoleRepr(roleRepository.findById(id).get());
    }

    @Override
    public RoleRepr findByName(String name) {
        return new RoleRepr(roleRepository.findByName(name));
    }

    @Override
    @TrackTime
    public void save(RoleRepr roleRepr) {
        Role role = (roleRepr.getId() != null) ? roleRepository.findById(roleRepr.getId()).get() : new Role();
        role.setName(roleRepr.getName());
        roleRepository.save(role);
    }

    @Override
    @TrackTime
    public void remove(Long id) {
        roleRepository.findById(id).ifPresent(roleRepository::delete);
    }
}
