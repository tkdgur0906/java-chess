package domain.board;

import domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreTest {

    @Test
    @DisplayName("말의 종류에 따라 점수를 반환한다.")
    void valueByPiece() {
        assertAll(
                () -> assertThat(Score.valueByPiece(new King(Color.BLACK))).isEqualTo(0),
                () -> assertThat(Score.valueByPiece(new Queen(Color.BLACK))).isEqualTo(9),
                () -> assertThat(Score.valueByPiece(new Rook(Color.BLACK))).isEqualTo(5),
                () -> assertThat(Score.valueByPiece(new Bishop(Color.BLACK))).isEqualTo(3),
                () -> assertThat(Score.valueByPiece(new Knight(Color.BLACK))).isEqualTo(2.5),
                () -> assertThat(Score.valueByPiece(new Pawn(Color.BLACK))).isEqualTo(1)
        );
    }
}
