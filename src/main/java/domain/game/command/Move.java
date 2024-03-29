package domain.game.command;

import domain.board.Position;
import domain.game.ChessGame;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Move implements Command {

    private static final Pattern MOVE_REGEX = Pattern.compile("^move [a-h][1-8] [a-h][1-8]$");

    private final Position source;
    private final Position target;

    private Move(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    public static Move from(String command) {
        validate(command);
        List<String> commands = Arrays.stream(command.split(" ")).toList();
        String source = commands.get(1);
        String target = commands.get(2);
        return new Move(resolvePosition(source), resolvePosition(target));
    }

    private static void validate(String command) {
        if (!MOVE_REGEX.matcher(command).matches()) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    private static Position resolvePosition(String positionText) {
        int sourceFile = FileResolver.resolveFile(positionText.charAt(0));
        int sourceRank = Character.getNumericValue(positionText.charAt(1));
        return Position.of(sourceFile, sourceRank);
    }

    @Override
    public void process(ChessGame chessGame) {
        chessGame.startTurn(source, target);
    }
}
