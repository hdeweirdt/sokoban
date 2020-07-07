package be.harm.sokoban;

import be.harm.sokoban.user.User;
import be.harm.sokoban.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
class IndexController {
    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/login",})
    public String login() {
        return "login";
    }

    @PostMapping(value = {"/new",})
    public String registerUser(HttpServletRequest request, User user) throws ServletException {
        String passwordBeforeHash = user.getPassword();
        User savedUser = userService.saveUser(user);

        // Autologin
        request.login(savedUser.getUserName(), passwordBeforeHash);

        return "redirect:games/all";
    }
}
