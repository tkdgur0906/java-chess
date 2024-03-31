package view;

import command.Command;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static Command readCommand() {
        return CommandMapper.mapToCommand(scanner.nextLine());
    }
}
