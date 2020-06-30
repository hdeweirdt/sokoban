package be.harm.sokoban.game.board;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class BoardPosition {
    private int row;
    private int column;
}
