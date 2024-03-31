package command;

import dao.BoardDao;
import dao.TurnDao;
import domain.game.ChessGame;
import view.CommandMapper;

import static view.OutputView.printLoadMessage;

public class Load implements Command {

    private Load() {
    }

    public static Load from(String command) {
        validate(command);
        return new Load();
    }

    private static void validate(String command) {
        if (!CommandMapper.LOAD.commandText().equals(command)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    @Override
    public void process(ChessGame chessGame) {
        chessGame.loadBoard(BoardDao.findAll());
        chessGame.loadTurn(TurnDao.findTurn());
        printLoadMessage();
    }
}
