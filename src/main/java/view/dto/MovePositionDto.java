package view.dto;

import domain.board.Position;
import view.Command;

public class MovePositionDto {

    private final Position source;
    private final Position target;

    private MovePositionDto(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    public static MovePositionDto from(Command command) {
        return new MovePositionDto(resolvePosition(command.resolveSourceToText()), resolvePosition(command.resolveTargetToText()));
    }

    private static Position resolvePosition(String positionText) {
        int sourceFile = FileResolver.resolveFile(positionText.charAt(0));
        int sourceRank = Character.getNumericValue(positionText.charAt(1));
        return Position.of(sourceFile, sourceRank);
    }

    public Position source() {
        return source;
    }

    public Position target() {
        return target;
    }
}
