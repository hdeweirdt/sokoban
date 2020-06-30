package be.harm.sokoban.game.board;

import java.io.Serializable;

public class Goal extends Field implements Serializable {
    @Override
    public char getRepresentation() {
        return '*';
    }
}
