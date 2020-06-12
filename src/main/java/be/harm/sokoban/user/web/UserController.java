package be.harm.sokoban.user.web;

import be.harm.sokoban.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String getUsers (Model model){
        model.addAttribute("users", userService.findAll());
        return "users/all";
    }
}
