package domain.game.command;

import domain.game.ChessGame;

public class Load implements Command {

    private static final String LOAD_COMMAND = "load";

    private final String command;

    private Load(String command) {
        this.command = command;
    }

    public static Load from(String command) {
        validate(command);
        return new Load(command);
    }

    private static void validate(String command) {
        if (!LOAD_COMMAND.equals(command)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    @Override
    public void process(ChessGame chessGame) {
        chessGame.load();
    }
}
