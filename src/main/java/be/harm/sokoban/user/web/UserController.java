package be.harm.sokoban.user.web;

import be.harm.sokoban.user.UserService;
import be.harm.sokoban.user.commands.CreateUserCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/", "/all"})
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/all";
    }

    @GetMapping(value = {"/new",})
    public String registerNewUser() {
        return "users/new";
    }

    @PostMapping(value = {"/new",})
    public String registerUser(HttpServletRequest request, CreateUserCommand command) throws ServletException {
        userService.saveUser(command.mapToUser());

        // Autologin
        request.login(command.getUserName(), command.getPassword());

        return "redirect:/games";
    }
}
