package be.harm.sokoban.game.web;

import be.harm.sokoban.game.Game;
import be.harm.sokoban.game.GameService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/games")
class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping({"", "/", "/all"})
    @PreAuthorize("hasAuthority('game:play')")
    public String showAllGames(Model model) {
        model.addAttribute("games", gameService.findAll());
        return "games/all";
    }

    @GetMapping({"/{id}"})
    @PreAuthorize("hasAuthority('game:play')")
    public String showGame(@PathVariable String id, Model model) {
        Optional<Game> foundGame = gameService.findById(Long.parseLong(id));
        model.addAttribute("game", foundGame.orElseThrow());
        return "games/show";
    }
}
