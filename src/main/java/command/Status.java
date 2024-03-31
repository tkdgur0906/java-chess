package command;

import domain.game.ChessGame;

public class Status implements Command {

    private static final String STATUS_COMMAND = "status";

    private Status() {
    }

    public static Status from(String command) {
        validate(command);
        return new Status();
    }

    private static void validate(String command) {
        if (!STATUS_COMMAND.equals(command)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    @Override
    public void process(ChessGame chessGame) {
    }
}
