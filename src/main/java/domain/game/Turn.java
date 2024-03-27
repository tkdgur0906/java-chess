package domain.game;

import domain.piece.Color;

public enum Turn {

    BLACK,
    WHITE,
    BLACK_KING_CAPTURED_END,
    WHITE_KING_CAPTURED_END,
    PRE_START,
    END;

    public static Turn makeInitialTurn() {
        return WHITE;
    }

    public Turn changeTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isEnd() {
        return this == END || this == BLACK_KING_CAPTURED_END || this == WHITE_KING_CAPTURED_END;
    }

    public Color getColor() {
        if (this == END) {
            throw new IllegalStateException("이미 게임이 종료되었습니다.");
        }
        if (this == BLACK) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public Turn end() {
        return END;
    }

    public Turn blackKingCaptured() {
        return BLACK_KING_CAPTURED_END;
    }

    public Turn whiteKingCaptured() {
        return WHITE_KING_CAPTURED_END;
    }
}
