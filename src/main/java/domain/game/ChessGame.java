package domain.game;

import domain.board.Board;
import domain.board.Position;
import domain.piece.Piece;

import static domain.game.Turn.PRE_START;
import static domain.game.Turn.makeInitialTurn;

public class ChessGame {

    private final Board board;
    private Turn turn;

    public ChessGame(Board board) {
        this.board = board;
        this.turn = Turn.makeInitialTurn();
    }

    public Board startTurn(Position source, Position target) {
        Piece piece = board.findPieceAt(target);
        Board movedBoard = board.move(source, target, turn);
        if (piece.isKing()) {
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
