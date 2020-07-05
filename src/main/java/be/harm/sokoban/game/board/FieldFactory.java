package be.harm.sokoban.game.board;

import org.hibernate.MappingException;

public class FieldFactory {
    public static Field getField(char fieldRepresentation) {
        Field mappedField;
        switch(fieldRepresentation) {
            case '#' : mappedField = new Wall(); break;
            case '*' : mappedField = new Goal(); break;
            case ' ' : mappedField = new Floor(); break;
            case 'U' : mappedField = new Floor(true); break;
            default: throw new MappingException("Unknown field type");
        }
        return mappedField;
    }
}
