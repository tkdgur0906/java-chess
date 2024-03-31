package command;

import domain.game.ChessGame;

public class End implements Command {

    private static final String END_COMMAND = "end";

    private End() {
    }

    public static End from(String command) {
        validate(command);
        return new End();
    }

    private static void validate(String command) {
        if (!END_COMMAND.equals(command)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    @Override
    public void process(ChessGame chessGame) {
        chessGame.end();
    }
}
