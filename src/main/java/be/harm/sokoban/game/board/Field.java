package be.harm.sokoban.game.board;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class Field {
    private boolean hasChest;


    public abstract char getRepresentation();
    public abstract boolean canBeCrossed();
    public abstract boolean canHoldChest();

    public boolean hasChest() {
        return hasChest;
    }

    public final void putChest() {
        if(!canHoldChest()) {
            throw new IllegalArgumentException("This field cannot hold chests.");
        }
        if(hasChest) {
            throw new IllegalArgumentException("This field already holds a chest.");
        }
        this.hasChest = true;
    }

    public final void removeChest() {
        this.hasChest = false;
    }
}
