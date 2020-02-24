package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.persist.model.Role;
import nikdevs.onlinestore.persist.model.User;
import nikdevs.onlinestore.persist.repo.RoleRepository;
import nikdevs.onlinestore.persist.repo.UserRepository;
import nikdevs.onlinestore.service.interfaces.UserService;
import nikdevs.onlinestore.service.model.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceJpaImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceJpaImpl(UserRepository userRepository, RoleRepository roleRepository,
                              BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<SystemUser> findAll() {
        return userRepository.findAll().stream()
                .map(SystemUser::new)
                .collect(Collectors.toList());
    }

    @Override
    public SystemUser findById(Long id) {
        return new SystemUser(userRepository.findById(id).get());
    }

    @Override
    @Transactional
    public SystemUser findByUserName(String username) {
        User user = userRepository.findOneByUserName(username);
        return new SystemUser(user);
    }

    @Override
    @Transactional
    public boolean save(SystemUser systemUser) {
        User user = systemUser.getId() != null ? userRepository
                .findById(systemUser.getId())
                .orElse(new User()) : new User();
        user.setUserName(systemUser.getUserName());
        if (systemUser.getId() == null || (systemUser.getPassword() != null && !systemUser.getPassword().trim().isEmpty())) {
            user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        }
        user.setFirstName(systemUser.getFirstName());
        user.setLastName(systemUser.getLastName());
        user.setEmail(systemUser.getEmail());
        user.setRoles(systemUser.getRoles().stream().map(roleRepr -> roleRepository.findById(roleRepr.getId()).get()).collect(Collectors.toSet()));
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SystemUser user = findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles().stream().map(roleRepr -> roleRepository.findById(roleRepr.getId()).get()).collect(Collectors.toSet())));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }
}
