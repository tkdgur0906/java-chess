package command;

import dao.BoardDao;
import dao.TurnDao;
import domain.game.ChessGame;

import static view.OutputView.printSaveMessage;

public class Save implements Command {

    private static final String SAVE_COMMAND = "save";

    private Save() {
    }

    public static Save from(String command) {
        validate(command);
        return new Save();
    }

    private static void validate(String command) {
        if (!SAVE_COMMAND.equals(command)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    @Override
    public void process(ChessGame chessGame) {
        BoardDao.saveBoard(chessGame.getBoard());
        TurnDao.saveTurn(chessGame.getTurn());
        printSaveMessage();
    }
}
