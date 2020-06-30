package be.harm.sokoban.game.board;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public abstract class Field implements Serializable {
    public abstract char getRepresentation();
}
