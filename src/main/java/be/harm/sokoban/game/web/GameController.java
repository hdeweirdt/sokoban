package be.harm.sokoban.game.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
class GameController {

    public GameController() {
    }

    @GetMapping({"", "/", "/all"})
    public String showAllGames(Model model) {
        return "games/all";
    }
}
