import command.Command;
import command.Load;
import command.Save;
import command.Status;
import domain.board.Board;
import domain.board.InitialBoardGenerator;
import domain.game.ChessGame;

import static view.InputView.readCommand;
import static view.OutputView.*;

public class ChessApplication {

    private static final ChessGame chessGame = new ChessGame(Board.generatedBy(new InitialBoardGenerator()));

    public static void main(String[] args) {
        printStartMessage();
        while (!chessGame.isEnd()) {
            processCommand();
        }
        printScore(chessGame.calculateBlackScore(), chessGame.calculateWhiteScore());
        printWinner(chessGame.findWinner());
    }

    private static void processCommand() {
        try {
            Command command = readCommand();
            command.process(chessGame);
            handleCommands(command);
            printBoard(chessGame.getBoard());
        } catch (RuntimeException e) {
            printErrorMessage(e.getMessage());
        }
    }

    private static void handleCommands(Command command) {
        handleStatusCommand(command);
        handleSaveCommand(command);
        handLoadCommand(command);
    }

    private static void handleStatusCommand(Command command) {
        if (command.getClass() == Status.class) {
            printScore(chessGame.calculateBlackScore(), chessGame.calculateWhiteScore());
            printWinner(chessGame.findWinner());
        }
    }

    private static void handleSaveCommand(Command command) {
        if (command.getClass() == Save.class) {
            printSaveMessage();
        }
    }

    private static void handLoadCommand(Command command) {
        if (command.getClass() == Load.class) {
            printLoadMessage();
        }
    }
}
