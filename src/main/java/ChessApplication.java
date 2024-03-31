import command.Command;
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
            printBoard(chessGame.getBoard());
        } catch (RuntimeException e) {
            printErrorMessage(e.getMessage());
        }
    }
}
