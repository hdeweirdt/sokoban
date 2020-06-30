package be.harm.sokoban.game.board;

import be.harm.sokoban.game.Game;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Board {
    public static final int DEFAULT_BOARD_WIDTH = 10;
    public static final int DEFAULT_BOARD_HEIGHT = 10;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne()
    @Getter
    @Setter
    @JoinColumn(name="game_id")
    private Game game;

    @Getter
    @Convert(converter = FieldsConverter.class, attributeName = "field")
    private Field[][] fields;

    public Board(Field[][] fields) {
        this.fields = fields;
    }

    public Board() {
        fillFieldWithFloors();
    }

    private void fillFieldWithFloors() {
        fields = new Field[DEFAULT_BOARD_WIDTH][DEFAULT_BOARD_HEIGHT];
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = new Floor();
            }
        }
    }
}
