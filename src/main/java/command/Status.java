package command;

import domain.game.ChessGame;
import view.CommandMapper;

import static view.OutputView.printScore;
import static view.OutputView.printWinner;

public class Status implements Command {

    private Status() {
    }

    public static Status from(String command) {
        validate(command);
        return new Status();
    }

    private static void validate(String command) {
        if (!CommandMapper.STATUS.commandText().equals(command)) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    @Override
    public void process(ChessGame chessGame) {
        printScore(chessGame.calculateBlackScore(), chessGame.calculateWhiteScore());
        printWinner(chessGame.findWinner());
    }
}
