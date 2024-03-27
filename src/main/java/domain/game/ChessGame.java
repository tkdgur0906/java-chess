package domain.game;

import domain.board.Board;
import domain.board.Position;
import domain.piece.Piece;

import static domain.game.Turn.*;

public class ChessGame {

    private final Board board;
    private Turn turn;

    public ChessGame(Board board) {
        this.board = board;
        this.turn = PRE_START;
    }

    public Board startTurn(Position source, Position target) {
        Piece piece = board.findPieceAt(target);
        Board movedBoard = board.move(source, target, turn);
        changeTurn(piece);
        return movedBoard;
    }

    private void changeTurn(Piece piece) {
        if (piece.isKing() && piece.isBlack()) {
            turn = turn.blackKingCaptured();
            return;
        }
        if (piece.isKing() && piece.isWhite()) {
            turn = turn.whiteKingCaptured();
            return;
        }
        turn = turn.changeTurn();
    }

    public void start() {
        if (turn != PRE_START) {
            throw new IllegalStateException("게임을 종료하고 다시 시작해주세요");
        }
        turn = makeInitialTurn();
    }

    public void end() {
        turn = turn.end();
    }

    public double calculateWhiteScore() {
        return board.calculateWhiteScore();
    }

    public double calculateBlackScore() {
        return board.calculateBlackScore();
    }

    public WinStatus findWinner() {
        if (isBlackWin()) {
            return WinStatus.BLACK;
        }
        if (isWhiteWin()) {
            return WinStatus.WHITE;
        }
        return WinStatus.DRAW;
    }

    private boolean isBlackWin() {
        return turn == WHITE_KING_CAPTURED_END || calculateBlackScore() > calculateWhiteScore();
    }

    private boolean isWhiteWin() {
        return turn == BLACK_KING_CAPTURED_END || calculateBlackScore() < calculateWhiteScore();
    }

    public boolean isEnd() {
        return turn.isEnd();
    }

    public Board getBoard() {
        return board;
    }
}
