package be.harm.sokoban.game.board;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FieldsConverter implements AttributeConverter<Field[][], String> {

    @Override
    public String convertToDatabaseColumn(Field[][] fields) {
        StringBuilder mappedFields = new StringBuilder(100);
        for (Field[] row : fields) {
            for (Field field : row) {
                mappedFields.append(field.getRepresentation());
            }
        }
        return mappedFields.toString();
    }

    @Override
    public Field[][] convertToEntityAttribute(String inputString) {
        Field[][] fields = new Field[Board.DEFAULT_BOARD_WIDTH][Board.DEFAULT_BOARD_HEIGHT];
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] =  FieldFactory.getField(inputString.charAt(i*10+j));
            }
        }
        return fields;
    }
}
