package view;

import domain.game.command.Command;
import domain.game.command.End;
import domain.game.command.Load;
import domain.game.command.Move;
import domain.game.command.Save;
import domain.game.command.Start;
import domain.game.command.Status;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Command readCommand() {
        try {
            return CommandMapper.mapToCommand(scanner.nextLine());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return readCommand();
        }
    }

    enum CommandMapper {
        START(Start::from, "start"),
        MOVE(Move::from, "move"),
        STATUS(Status::from, "status"),
        END(End::from, "end"),
        SAVE(Save::from, "save"),
        LOAD(Load::from, "load");

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
