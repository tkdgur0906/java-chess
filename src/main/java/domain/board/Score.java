package domain.board;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;

import java.util.Arrays;

public enum Score {

    KING(King.class, 0),
    QUEEN(Queen.class, 9),
    ROOK(Rook.class, 5),
    BISHOP(Bishop.class, 3),
    KNIGHT(Knight.class, 2.5),
    PAWN(Pawn.class, 1);

    private final Class<? extends Piece> type;
    private final double value;

    Score(Class<? extends Piece> type, double value) {
        this.type = type;
        this.value = value;
    }

    public static double valueByPiece(Piece piece) {
        return Arrays.stream(values())
                .filter(score -> score.type == piece.getClass())
                .map(score -> score.value)
                .findFirst()
                .orElse(0.0);
    }
}
