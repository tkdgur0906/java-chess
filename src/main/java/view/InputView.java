package view;

import domain.game.command.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Command readCommand() {
        return CommandMapper.mapToCommand(scanner.nextLine());
    }

    enum CommandMapper {
        START(Start::from, "start"),
        MOVE(Move::from, "move"),
        STATUS(Status::from, "status"),
        END(End::from, "end"),
        SAVE(Save::from, "save")
        ;

        private final Function<String, ? extends Command> commandFunction;
        private final String commandText;

        CommandMapper(Function<String, ? extends Command> commandFunction, String commandText) {
            this.commandFunction = commandFunction;
            this.commandText = commandText;
        }

        public static Command mapToCommand(String input) {
            return Arrays.stream(values())
                    .filter(command -> input.startsWith(command.commandText))
                    .map(commandMapper -> commandMapper.commandFunction)
                    .map(commandFunction -> commandFunction.apply(input))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("올바른 명령어를 입력해 주세요."));
        }
    }
}
