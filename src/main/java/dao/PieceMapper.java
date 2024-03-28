package dao;

import domain.piece.*;

import java.util.Arrays;

public enum PieceMapper {

    WHITE_KING("k", new King(Color.WHITE)),
    WHITE_QUEEN("q", new Queen(Color.WHITE)),
    WHITE_ROOK("r", new Rook(Color.WHITE)),
    WHITE_KNIGHT("n", new Knight(Color.WHITE)),
    WHITE_BISHOP("b", new Bishop(Color.WHITE)),
    WHITE_PAWN("p", new Pawn(Color.WHITE)),
    BLACK_KING("K", new King(Color.BLACK)),
    BLACK_QUEEN("Q", new Queen(Color.BLACK)),
    BLACK_ROOK("R", new Rook(Color.BLACK)),
    BLACK_KNIGHT("N", new Knight(Color.BLACK)),
    BLACK_BISHOP("B", new Bishop(Color.BLACK)),
    BLACK_PAWN("P", new Pawn(Color.BLACK)),
    EMPTY(".", new Empty());

    private final String pieceText;
    private final Piece piece;


    PieceMapper(String pieceText, Piece piece) {
        this.pieceText = pieceText;
        this.piece = piece;
    }

    public static Piece textToPiece(String input) {
        return Arrays.stream(values())
                .filter(pieceMapper -> pieceMapper.pieceText.equals(input))
                .map(pieceMapper -> pieceMapper.piece)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 말의 모양입니다."));

    }
}
