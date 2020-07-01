package be.harm.sokoban.game.board;

import java.io.Serializable;

public class Wall extends Field implements Serializable {
    @Override
    public char getRepresentation() {
        return '#';
    }

    @Override
    public boolean canBeCrossed() {
        return false;
    }

    @Override
    public boolean canHoldChest() {
        return false;
    }
}
