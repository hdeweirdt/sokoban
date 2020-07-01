package be.harm.sokoban.game.board;

import be.harm.sokoban.game.Direction;
import be.harm.sokoban.game.Game;
import lombok.AccessLevel;
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
    @JoinColumn(name = "game_id")
    private Game game;

    @Getter
    @Convert(converter = FieldsConverter.class, attributeName = "field")
    private Field[][] fields;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "row", column = @Column(name = "player_row")),
            @AttributeOverride(name = "column", column = @Column(name = "player_column"))}
    )
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private BoardPosition playerPosition = new BoardPosition(0, 0);

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

    public void movePlayer(Direction direction) {
        final BoardPosition resultingPosition = playerPosition.getPositionOnSide(direction);

        if (!positionOnBoard(resultingPosition)) {
            throw new IllegalArgumentException("Cannot go off-board");
        }

        final Field resultingField = fields[resultingPosition.getRow()][resultingPosition.getColumn()];
        if (resultingField.canBeCrossed()) {
            setPlayerPosition(resultingPosition);
            return;
        }
        if (resultingField.hasChest()) {
            final var positionNextToChest = resultingPosition.getPositionOnSide(direction);
            final var fieldNextToChest = fields[positionNextToChest.getRow()][positionNextToChest.getColumn()];
            if (fieldNextToChest.canHoldChest()) {
                // Move chest from from 1 field away to two fields away
                fieldNextToChest.putChest();
                resultingField.removeChest();

                setPlayerPosition(resultingPosition);
            }
        }

    }

    private boolean positionOnBoard(BoardPosition resultingPosition) {
        return resultingPosition.getRow() >= 0 && resultingPosition.getRow() < fields.length
                && resultingPosition.getColumn() >= 0 && resultingPosition.getColumn() < fields[0].length;
    }
}
