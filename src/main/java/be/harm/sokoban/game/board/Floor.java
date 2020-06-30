package be.harm.sokoban.game.board;


import java.io.Serializable;

public class Floor extends Field implements Serializable {

    private boolean hasChest;

    public Floor() {
        hasChest = false;
    }

    public Floor(boolean hasChest) {
        this.hasChest = hasChest;
    }

    @Override
    public char getRepresentation() {
        if(hasChest) {
           return 'U';
        }
        return ' ';
    }

    public boolean setHasChest() {
        return hasChest;
    }

    public void setHasChest(boolean hasChest) {
        this.hasChest = hasChest;
    }

}
