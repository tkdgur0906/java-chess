package view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Command readCommand() {
        return new Command(scanner.nextLine());
    }
}
