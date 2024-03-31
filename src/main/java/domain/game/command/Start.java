package domain.game.command;

import domain.game.ChessGame;

public class Start implements Command {

    private static final String START_COMMAND = "start";

    private Start() {
    }

    public static Start from(String command) {
        validate(command);
        return new Start();
    }

    private static void validate(String command) {
        if (!START_COMMAND.equals(command)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    @Override
    public void process(ChessGame chessGame) {
        chessGame.start();
    }
}
