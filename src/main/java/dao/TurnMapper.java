package dao;

import domain.game.Turn;

public enum TurnMapper {

    WHITE("WHITE"),
    BLACK("BLACK");

    private final String turnText;

    TurnMapper(String turnText) {
        this.turnText = turnText;
    }


    public static Turn textToTurn(String input) {
        if (WHITE.turnText.equals(input)) {
            return Turn.WHITE;
        }
        return Turn.BLACK;
    }
}
