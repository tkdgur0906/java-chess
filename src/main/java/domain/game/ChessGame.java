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

    public double calculateWhiteScore() {
        return board.calculateWhiteScore();
    }

    public double calculateBlackScore() {
        return board.calculateBlackScore();
    }

    public WinStatus findWinner() {
        if (calculateBlackScore() > calculateWhiteScore()) {
            return WinStatus.BLACK;
        }
        if (calculateBlackScore() < calculateWhiteScore()) {
            return WinStatus.WHITE;
        }
        return WinStatus.DRAW;
    }

    public boolean isEnd() {
        return turn.isEnd();
    }

    public Board getBoard() {
        return board;
    }
}
