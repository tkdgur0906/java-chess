package dao;

import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;

import java.util.Arrays;

public enum PieceMapper {

    WHITE_KING(new King(Color.WHITE)),
    WHITE_QUEEN(new Queen(Color.WHITE)),
    WHITE_ROOK(new Rook(Color.WHITE)),
    WHITE_KNIGHT(new Knight(Color.WHITE)),
    WHITE_BISHOP(new Bishop(Color.WHITE)),
    WHITE_PAWN(new Pawn(Color.WHITE)),
    BLACK_KING(new King(Color.BLACK)),
    BLACK_QUEEN(new Queen(Color.BLACK)),
    BLACK_ROOK(new Rook(Color.BLACK)),
    BLACK_KNIGHT(new Knight(Color.BLACK)),
    BLACK_BISHOP(new Bishop(Color.BLACK)),
    BLACK_PAWN(new Pawn(Color.BLACK)),
    EMPTY(new Empty());

    private final Piece piece;

    PieceMapper(Piece piece) {
        this.piece = piece;
    }

    public static Piece textToPiece(String input) {
        return Arrays.stream(values())
                .filter(pieceMapper -> pieceMapper.piece.asString().equals(input))
                .map(pieceMapper -> pieceMapper.piece)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 말의 모양입니다."));
    }
}
