package be.harm.sokoban.game.board;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Floor extends Field {

    public Floor(boolean hasChest) {
        if(hasChest) { putChest(); } else { removeChest(); }
    }

    @Override
    public char getRepresentation() {
        if(hasChest()) {
           return 'U';
        }
        return ' ';
    }

    @Override
    public boolean canBeCrossed() {
        return !hasChest();
    }

    @Override
    public boolean canHoldChest() {
        return !hasChest();
    }

}
