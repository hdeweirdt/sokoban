package be.harm.sokoban.game.web;

import be.harm.sokoban.game.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping({"", "/", "/all"})
    public String showAllGames(Model model) {
        model.addAttribute("games", gameService.findAll());
        return "games/all";
    }
}
