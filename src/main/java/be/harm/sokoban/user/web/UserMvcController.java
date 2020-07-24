package be.harm.sokoban.user.web;

import be.harm.sokoban.user.UserService;
import be.harm.sokoban.user.commands.CreateUserCommand;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserMvcController {
    private final UserService userService;

    public UserMvcController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/", "/all"})
    @PreAuthorize("hasAuthority('users:read')")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/all";
    }

    @GetMapping(value = {"/new",})
    public String registerNewUser() {
        return "users/new";
    }

    @PostMapping(value = {"/new",})
    public String registerUser(HttpServletRequest request, @Valid CreateUserCommand command, BindingResult bindingResult) throws ServletException {
        if (bindingResult.hasErrors()) {
            return "/users/new";
        } else {
            userService.saveUser(command.mapToUser());

            // Autologin
            request.login(command.getUserName(), command.getPassword());

            return "redirect:/games";
        }
    }
}
