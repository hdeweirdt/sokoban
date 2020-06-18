package be.harm.sokoban.bootstrap;

import be.harm.sokoban.user.User;
import be.harm.sokoban.user.UserService;
import be.harm.sokoban.user.roles.Role;
import be.harm.sokoban.user.roles.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public DataLoader(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        saveRoles();
        saveAdmin();
    }

    private void saveRoles() {
        roleRepository.save(new Role(Role.ADMIN));
        roleRepository.save(new Role(Role.USER));
    }

    private void saveAdmin() {
        User admin = new User("adminUser", "AdminWachtwoord1");

        userService.saveAdmin(admin);
    }
}
