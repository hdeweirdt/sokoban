package be.harm.sokoban.bootstrap;

import be.harm.sokoban.user.User;
import be.harm.sokoban.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        saveAdmin();
    }

    private void saveAdmin() {
        User admin = new User("adminUser", "AdminWachtwoord1");

        userService.saveAdmin(admin);
    }
}
