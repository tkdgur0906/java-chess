package domain.board;

import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;

import java.util.HashMap;
import java.util.Map;

public class InitialBoardGenerator implements BoardGenerator {

    private static final int MIN_FILE = 1;
    private static final int MAX_FILE = 8;

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        placePawns(board);
        placeRooks(board);
        placeKnights(board);
        placeBishops(board);
        placeQueens(board);
        placeKings(board);
        placeEmpty(board);
        return board;
    }

    private void placePawns(Map<Position, Piece> board) {
        for (int i = MIN_FILE; i <= MAX_FILE; i++) {
            board.put(Position.of(i, 2), new Pawn(Color.WHITE));
            board.put(Position.of(i, 7), new Pawn(Color.BLACK));
        }
    }

    private void placeRooks(Map<Position, Piece> board) {
        board.put(Position.of(1, 1), new Rook(Color.WHITE));
        board.put(Position.of(8, 1), new Rook(Color.WHITE));
        board.put(Position.of(1, 8), new Rook(Color.BLACK));
        board.put(Position.of(8, 8), new Rook(Color.BLACK));
    }

    private void placeKnights(Map<Position, Piece> board) {
        board.put(Position.of(2, 1), new Knight(Color.WHITE));
        board.put(Position.of(7, 1), new Knight(Color.WHITE));
        board.put(Position.of(2, 8), new Knight(Color.BLACK));
        board.put(Position.of(7, 8), new Knight(Color.BLACK));
    }

    private void placeBishops(Map<Position, Piece> board) {
        board.put(Position.of(3, 1), new Bishop(Color.WHITE));
        board.put(Position.of(6, 1), new Bishop(Color.WHITE));
        board.put(Position.of(3, 8), new Bishop(Color.BLACK));
        board.put(Position.of(6, 8), new Bishop(Color.BLACK));
    }

    private void placeQueens(Map<Position, Piece> board) {
        board.put(Position.of(4, 1), new Queen(Color.WHITE));
        board.put(Position.of(4, 8), new Queen(Color.BLACK));
    }

    private void placeKings(Map<Position, Piece> board) {
        board.put(Position.of(5, 8), new King(Color.BLACK));
        board.put(Position.of(5, 1), new King(Color.WHITE));
    }

    private void placeEmpty(Map<Position, Piece> board) {
        for (int rank = 3; rank <= 6; rank++) {
            placeEmptyToOneRank(board, rank);
        }
    }

    private void placeEmptyToOneRank(Map<Position, Piece> board, int rank) {
        for (int file = MIN_FILE; file <= MAX_FILE; file++) {
            board.put(Position.of(file, rank), new Empty());
        }
    }
}
