package be.harm.sokoban.game;

import java.util.Optional;
import java.util.Set;

public interface GameService {
    Set<Game> findAll();

    Optional<Game> findById(Long id);
}
