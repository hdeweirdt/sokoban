package be.harm.sokoban.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @Getter
    private List<Board> boards = new ArrayList<>();

    public Game(String name) {
        this.name = name;
    }

    public void addBoard(Board board) {
        board.setGame(this);
        boards.add(board);
    }
}
