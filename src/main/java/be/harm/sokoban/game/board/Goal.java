package be.harm.sokoban.game.board;

import java.io.Serializable;

public class Goal extends Field implements Serializable {
    @Override
    public char getRepresentation() {
        return '*';
    }

    @Override
    public boolean canBeCrossed() {
        return !hasChest();
    }

    @Override
    public boolean canHoldChest() {
        return true;
    }
}
