package be.harm.sokoban.game.board;

import be.harm.sokoban.game.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardPositionTest {

    private final BoardPosition startPosition= new BoardPosition(5,5);


    @Test
    public void positionOnUpSideShouldHaveColumnMinusOne() {
        // When
        var resultingPosition = startPosition.getPositionOnSide(Direction.UP);

        // Then
        assertEquals(5, resultingPosition.getRow());
        assertEquals(4, resultingPosition.getColumn());
    }

    @Test
    public void positionOnDownSideShouldHaveColumnPlusOne() {
        // When
        var resultingPosition = startPosition.getPositionOnSide(Direction.DOWN);

        // Then
        assertEquals(5, resultingPosition.getRow());
        assertEquals(6, resultingPosition.getColumn());
    }

    @Test
    public void positionOnLeftSideShouldHaveRowPlusOne() {
        // When
        var resultingPosition = startPosition.getPositionOnSide(Direction.LEFT);

        // Then
        assertEquals(4, resultingPosition.getRow());
        assertEquals(5, resultingPosition.getColumn());
    }

    @Test
    public void positionOnRightSideShouldHaveRowPlusOne() {
        // When
        var resultingPosition = startPosition.getPositionOnSide(Direction.RIGHT);

        // Then
        assertEquals(6, resultingPosition.getRow());
        assertEquals(5, resultingPosition.getColumn());
    }

}