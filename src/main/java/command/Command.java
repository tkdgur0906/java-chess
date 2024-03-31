package command;

import domain.game.ChessGame;

public interface Command {

    void process(ChessGame chessGame);
}
