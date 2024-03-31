import command.Command;
import command.Load;
import command.Save;
import command.Status;
import domain.board.Board;
import domain.board.InitialBoardGenerator;
import domain.game.ChessGame;
import view.InputView;
import view.OutputView;

public class ChessApplication {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final ChessGame chessGame = new ChessGame(Board.generatedBy(new InitialBoardGenerator()));

    public static void main(String[] args) {
        outputView.printStartMessage();
        while (!chessGame.isEnd()) {
            processCommand();
        }
        outputView.printScore(chessGame.calculateBlackScore(), chessGame.calculateWhiteScore());
        outputView.printWinner(chessGame.findWinner());
    }

    private static void processCommand() {
        try {
            Command command = inputView.readCommand();
            command.process(chessGame);
            handleCommands(command);
            outputView.printBoard(chessGame.getBoard());
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private static void handleCommands(Command command) {
        handleStatusCommand(command);
        handleSaveCommand(command);
        handLoadCommand(command);
    }

    private static void handleStatusCommand(Command command) {
        if (command.getClass() == Status.class) {
            outputView.printScore(chessGame.calculateBlackScore(), chessGame.calculateWhiteScore());
            outputView.printWinner(chessGame.findWinner());
        }
    }

    private static void handleSaveCommand(Command command) {
        if (command.getClass() == Save.class) {
            outputView.printSaveMessage();
        }
    }

    private static void handLoadCommand(Command command) {
        if (command.getClass() == Load.class) {
            outputView.printLoadMessage();
        }
    }
}
