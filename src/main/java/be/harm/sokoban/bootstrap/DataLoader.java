package be.harm.sokoban.bootstrap;

import be.harm.sokoban.game.GameService;
import be.harm.sokoban.game.board.Board;
import be.harm.sokoban.game.Game;
import be.harm.sokoban.game.GameRepository;
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
    private final GameService gameService;

    public DataLoader(UserService userService, RoleRepository roleRepository, GameService gameService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) {
        saveRoles();
        saveAdmin();
        // Disabled until saving boards works
        saveGames();
    }

    private void saveGames() {
        Board board1 = new Board();
        Board board2 = new Board();
        Board board3 = new Board();

        Game game1 = new Game("Game1");
        game1.addBoard(board1);
//        game1.addBoard(board2);
        gameService.save(game1);
        Game game2 = new Game("Game2");
//        game2.addBoard(board3);
        gameService.save(game2);
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
