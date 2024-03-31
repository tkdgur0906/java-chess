package command;

import domain.game.ChessGame;
import view.CommandMapper;

public class End implements Command {

    private End() {
    }

    public static End from(String command) {
        validate(command);
        return new End();
    }

    private static void validate(String command) {
        if (!CommandMapper.END.commandText().equals(command)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    @Override
    public void process(ChessGame chessGame) {
        chessGame.end();
    }
}
