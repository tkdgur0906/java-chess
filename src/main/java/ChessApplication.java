import domain.board.Board;
import domain.board.InitialBoardGenerator;
import domain.game.ChessGame;
import view.Command;
import view.InputView;
import view.OutputView;
import view.dto.MovePositionDto;

public class ChessApplication {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final ChessGame chessGame = new ChessGame(Board.generatedBy(new InitialBoardGenerator()));

    public static void main(String[] args) {
        outputView.printStartMessage();
        Command command = inputView.readCommand();
        if (command.isStart()) {
            outputView.printBoard(chessGame.getBoard());
            startTurn();
        }
    }

    private static void startTurn() {
        Command gameCommand = inputView.readCommand();
        if (gameCommand.isEnd()) {
            return;
        }
        if (gameCommand.isStatus()) {
            outputView.printScore(chessGame.calculateBlackScore(), chessGame.calculateWhiteScore());
            outputView.printWinner(chessGame.findWinner());
        }
        if (gameCommand.isMove()) {
            Board board = chessGame.startTurn(MovePositionDto.from(gameCommand));
            outputView.printBoard(board);
        }
        if (chessGame.isEnd()) {
            return;
        }
        startTurn();
    }
}
