package be.harm.sokoban.game;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
class GameJPAService implements GameService {
    private final GameRepository gameRepository;

    GameJPAService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Set<Game> findAll() {
        Set<Game> games = new HashSet<>();
        gameRepository.findAll().forEach(games::add);
        return games;
    }

    @Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

}
