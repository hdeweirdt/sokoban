package be.harm.sokoban.game.board;

import be.harm.sokoban.game.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Board {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne()
    @Getter
    @Setter
    @JoinColumn(name="game_id")
    private Game game;
}
