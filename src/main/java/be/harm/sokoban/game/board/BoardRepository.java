package be.harm.sokoban.game.board;

import be.harm.sokoban.game.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
