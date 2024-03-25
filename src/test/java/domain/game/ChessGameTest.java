package domain.game;

import domain.board.Board;
import domain.board.Position;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.dto.MovePositionDto;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    @Test
    @DisplayName("King이 잡히면 게임이 종료된다.")
    void startTurn_End() {
        Position source = Position.of(4, 4);
        Position target = Position.of(4, 5);
        Rook rook = new Rook(Color.WHITE);
        King king = new King(Color.BLACK);
        Board board = Board.generatedBy(() -> new HashMap<>(
                Map.of(
                        source, rook,
                        target, king
                )
        ));
        ChessGame chessGame = new ChessGame(board);
        chessGame.startTurn(MovePositionDto.from("move d4 d5"));

        assertThat(chessGame.isEnd()).isTrue();
    }
}
