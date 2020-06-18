package be.harm.sokoban.user;

import be.harm.sokoban.user.roles.Role;
import be.harm.sokoban.user.roles.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
class UserJPAService implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserJPAService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(getDefaultAdminRoles());
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(getDefaultUserRoles());
        return userRepository.save(user);
    }

    private HashSet<Role> getDefaultAdminRoles() {
        HashSet<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(roleRepository.findByName(Role.USER));
        defaultRoles.add(roleRepository.findByName(Role.ADMIN));
        return defaultRoles;
    }

    private HashSet<Role> getDefaultUserRoles() {
        HashSet<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(roleRepository.findByName(Role.USER));
        return defaultRoles;
    }

}
