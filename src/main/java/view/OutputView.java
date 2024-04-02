package view;

import domain.board.Board;
import domain.board.Position;
import domain.game.WinStatus;
import domain.piece.Piece;

public class OutputView {

    private static final int MIN_RANK = 1;
    private static final int MAX_RANK = 8;
    private static final int MIN_FILE = 1;
    private static final int MAX_FILE = 8;

    public static void printStartMessage() {
        System.out.printf("""
                        > 체스 게임을 시작합니다.
                        > 게임 시작 : %s
                        > 게임 종료 : %s
                        > 게임 이동 : %s source위치 target위치 - 예. %s b2 b3
                        > 게임 저장 : %s
                        > 게임 불러오기 : %s
                        %n""", CommandMapper.START.commandText(),
                CommandMapper.END.commandText(),
                CommandMapper.MOVE.commandText(),
                CommandMapper.MOVE.commandText(),
                CommandMapper.SAVE.commandText(),
                CommandMapper.LOAD.commandText()
        );
    }


    public static void printBoard(Board board) {
        for (int rank = MAX_RANK; rank >= MIN_RANK; rank--) {
            printOneRank(board, rank);
        }
        System.out.println();
    }

    private static void printOneRank(Board board, int rank) {
        for (int file = MIN_FILE; file <= MAX_FILE; file++) {
            Piece piece = board.findPieceAt(Position.of(file, rank));
            System.out.print(piece.asString());
        }
        System.out.println();
    }

    public static void printScore(double blackScore, double whiteScore) {
        System.out.printf("""
                > 검은색 점수 : %d점
                > 흰색 점수: %d점
                %n""", (int) blackScore, (int) whiteScore);
    }

    public static void printWinner(WinStatus winStatus) {
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

    public static void printSaveMessage() {
        System.out.println("게임이 저장되었습니다.");
    }

    public static void printLoadMessage() {
        System.out.println("저장된 게임을 불러왔습니다.");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
