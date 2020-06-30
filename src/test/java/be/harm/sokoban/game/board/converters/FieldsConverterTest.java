package be.harm.sokoban.game.board.converters;

import be.harm.sokoban.game.board.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldsConverterTest {
    
    private final FieldsConverter converter = new FieldsConverter();

    @Test
    public void shouldMapEmptyBoardCorrectly() {
        // Given
        Field[][] emptyBoard = createEmptyBoard();

        // When
        String result = converter.convertToDatabaseColumn(emptyBoard);

        // Then
        assertEquals(result,
                "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          "
        );
    }

    @Test
    public void shouldMapGoalsCorrectly() {
        // Given
        Field[][] board = createEmptyBoard();
        board[0][0] = new Goal();
        board[0][1] = new Goal();
        board[1][1] = new Goal();

        // When
        String result = converter.convertToDatabaseColumn(board);

        // Then
        assertEquals(result,
                "**        " +
                        " *        " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          "
        );

    }

    @Test
    public void shouldMapChestsCorrectly() {
        // Given
        Field[][] board = createEmptyBoard();
        Floor floorWithChest = new Floor();
        floorWithChest.setHasChest(true);
        board[2][2] = floorWithChest;

        // When
        String result = converter.convertToDatabaseColumn(board);

        // Then
        assertEquals(result,
                "          " +
                        "          " +
                        "  U       " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          "
        );
    }

    @Test
    public void shouldMapWallsCorrectly() {
        // Given
        Field[][] board = createEmptyBoard();
        board[0][0] = new Wall();
        board[0][1] = new Wall();
        board[1][1] = new Wall();

        // When
        String result = converter.convertToDatabaseColumn(board);

        // Then
        assertEquals(result,
                "##        " +
                        " #        " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          "
        );
    }

    @Test
    public void shouldMapStringWithWallsCorrectly() {
        // Given
        String boardWithMappedWalls =
                "##        " +
                        " #        " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          " +
                        "          ";

        // When
        Field[][] mappedBoard = converter.convertToEntityAttribute(boardWithMappedWalls);

        // Then
        assertTrue(mappedBoard[0][0] instanceof Wall);
        assertTrue(mappedBoard[0][1] instanceof Wall);
        assertTrue(mappedBoard[1][1] instanceof Wall);
        assertTrue(mappedBoard[0][2] instanceof Floor);
        assertTrue(mappedBoard[2][2] instanceof Floor);
    }


    private Field[][] createEmptyBoard() {
        Field[][] emptyBoard = new Field[10][10];
        for (int i = 0; i < emptyBoard.length; i++) {
            for (int j = 0; j < emptyBoard[i].length; j++) {
                emptyBoard[i][j] =  new Floor();
            }
        }
        return emptyBoard;
    }
}