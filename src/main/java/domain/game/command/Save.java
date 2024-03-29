package domain.game.command;

import domain.game.ChessGame;

public class Save implements Command {

    private static final String SAVE_COMMAND = "save";

    private final String command;

    private Save(String command) {
        this.command = command;
    }

    public static Save from(String command){
        validate(command);
        return new Save(command);
    }

    private static void validate(String command) {
        if (!SAVE_COMMAND.equals(command)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    @Override
    public void process(ChessGame chessGame) {
        chessGame.save();
    }
}
