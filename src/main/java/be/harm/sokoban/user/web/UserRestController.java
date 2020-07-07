package be.harm.sokoban.user.web;

import be.harm.sokoban.user.User;
import be.harm.sokoban.user.UserService;
import be.harm.sokoban.user.commands.CreateUserCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("api/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public Set<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("")
    public User registerNewUser(@RequestBody CreateUserCommand command) {
        return userService.saveUser(command.mapToUser());
    }
}
