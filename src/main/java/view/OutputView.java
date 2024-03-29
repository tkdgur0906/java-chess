package view;

import domain.board.Board;
import domain.board.Position;
import domain.game.WinStatus;
import domain.piece.Piece;

public class OutputView {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String SAVE_COMMAND = "save";
    private static final String LOAD_COMMAND = "load";
    private static final String NEW_LINE = System.lineSeparator();

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다." + NEW_LINE +
                "> 게임 시작 : " + START_COMMAND + NEW_LINE +
                "> 게임 종료 : " + END_COMMAND + NEW_LINE +
                "> 게임 이동 : " + MOVE_COMMAND + " source위치 target위치 - 예. " + MOVE_COMMAND + " b2 b3" + NEW_LINE +
                "> 게임 저장 : " + SAVE_COMMAND + NEW_LINE +
                "> 게임 불러오기 : " + LOAD_COMMAND + NEW_LINE);
    }

    public void printBoard(Board board) {
        for (int rank = 8; rank >= 1; rank--) {
            printOneRank(board, rank);
        }
        System.out.println();
    }

    private void printOneRank(Board board, int rank) {
        for (int file = 1; file <= 8; file++) {
            Piece piece = board.findPieceAt(Position.of(file, rank));
            System.out.print(piece.asString());
        }
        System.out.println();
    }

    public void printScore(double blackScore, double whiteScore) {
        System.out.println("> 검은색 점수 : " + blackScore + NEW_LINE +
                "> 흰색 점수 : " + whiteScore + NEW_LINE);
    }

    public void printWinner(WinStatus winStatus) {
        if (winStatus == WinStatus.BLACK) {
            System.out.println("검은색이 승리했습니다!");
            return;
        }
        if (winStatus == WinStatus.WHITE) {
            System.out.println("흰색이 승리했습니다!");
            return;
        }
        System.out.println("검은색과 흰색이 동점입니다.");
    }

    public void printSaveMessage() {
        System.out.println("게임이 저장되었습니다.");
    }

    public void printLoadMessage() {
        System.out.println("저장된 게임을 불러왔습니다.");
    }
}
