package be.harm.sokoban.game.board;

import be.harm.sokoban.game.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    private void resetBoard() {
        board = new Board();
    }

    @Test
    public void shouldNotAllowMovingOffTheBoard() {
        // Given player on the left edge
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.movePlayer(Direction.LEFT));

        // Given player on the top edge
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.movePlayer(Direction.UP));

        // Given player on the right edge
        board.setPlayerPosition(new BoardPosition(board.getFields().length -1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.movePlayer(Direction.RIGHT));

        // Given player on the bottom edge
        board.setPlayerPosition(new BoardPosition(0,board.getFields()[0].length -1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.movePlayer(Direction.DOWN));
    }

}