package be.harm.sokoban.user.web;

import be.harm.sokoban.user.User;
import be.harm.sokoban.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/all";
    }

    @GetMapping(value = {"/new",})
    public String registerNewUser() {
        return "users/new";
    }

    @PostMapping(value = {"/new",})
    public String registerUser(HttpServletRequest request, User user) throws ServletException {
        String passwordBeforeHash = user.getPassword();
        User savedUser = userService.saveUser(user);

        // Autologin
        request.login(savedUser.getUserName(), passwordBeforeHash);

        return "redirect:/users";
    }
}
