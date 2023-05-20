package command.ScannerArgumentCommand;

import util.Collection;

import java.util.Scanner;

public class AddCommand extends ScannerCommand {
    public AddCommand(Collection collection, Scanner scanner) {
        super(collection, scanner);
    }
    @Override
    public void execute(Scanner in) {
        collection.add(in);
    }
}
