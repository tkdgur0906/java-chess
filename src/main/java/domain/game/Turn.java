package domain.game;

import domain.piece.Color;

public enum Turn {

    BLACK,
    WHITE,
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

    public Color getColor() {
        if(this == END) {
            throw new IllegalStateException("이미 게임이 종료되었습니다.");
        }
        if(this == BLACK) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public Turn end() {
        return END;
    }
}
