package view;

import java.util.regex.Pattern;

public class Command {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String STATUS_COMMAND = "status";
    private static final Pattern MOVE_REGEX = Pattern.compile("^move [a-h][1-8] [a-h][1-8]$");

    private final String value;

    public Command(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (!(START_COMMAND.equals(value) || END_COMMAND.equals(value)
                || STATUS_COMMAND.equals(value) || MOVE_REGEX.matcher(value).matches())) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
    }

    public String resolveSourceToText() {
        String[] splitCommand = value.split(" ");
        validateMoveCommandLength(splitCommand);
        return splitCommand[1];
    }

    public String resolveTargetToText() {
        String[] splitCommand = value.split(" ");
        validateMoveCommandLength(splitCommand);
        return splitCommand[2];
    }

    private void validateMoveCommandLength(String[] splitCommand) {
        if (splitCommand.length != 3) {
            throw new IllegalArgumentException("형식에 맞는 입력을 해주세요 ex) move b2 b3");
        }
    }

    public boolean isStart() {
        return START_COMMAND.equals(value);
    }

    public boolean isEnd() {
        return END_COMMAND.equals(value);
    }

    public boolean isMove() {
        return MOVE_REGEX.matcher(value).matches();
    }

    public boolean isStatus() {
        return STATUS_COMMAND.equals(value);
    }
}
