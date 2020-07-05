package be.harm.sokoban.game.board;

import be.harm.sokoban.game.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BoardPosition {
    private int row;
    private int column;

    public BoardPosition getPositionOnSide(Direction direction) {
        BoardPosition side = null;
        switch(direction) {
            case UP: side = new BoardPosition(row, column-1); break;
            case DOWN: side = new BoardPosition(row, column +1); break;
            case LEFT: side = new BoardPosition(row-1, column); break;
            case RIGHT: side = new BoardPosition(row+1, column); break;
        }
        return side;
    }
}
