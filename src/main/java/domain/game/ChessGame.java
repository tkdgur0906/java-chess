package domain.game;

import domain.board.Board;
import domain.piece.Piece;
import view.dto.MovePositionDto;

public class ChessGame {

    private final Board board;
    private Turn turn;

    public ChessGame(Board board) {
        this.board = board;
        this.turn = Turn.makeInitialTurn();
    }

    public Board startTurn(MovePositionDto movePositionDto) {
        Piece target = board.findPieceAt(movePositionDto.target());
        Board movedBoard = board.move(movePositionDto.source(), movePositionDto.target(), turn);
        if (target.isKing()) {
            turn = turn.end();
            return movedBoard;
        }
        turn = turn.changeTurn();
        return movedBoard;
    }

    public boolean isEnd() {
        return turn.isEnd();
    }

    public Board getBoard() {
        return board;
    }
}
