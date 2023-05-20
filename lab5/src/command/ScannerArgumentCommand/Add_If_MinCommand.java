package command.ScannerArgumentCommand;

import util.Collection;

import java.util.Scanner;

public class Add_If_MinCommand  extends ScannerCommand{
    public Add_If_MinCommand(Collection collection, Scanner scanner) {
        super(collection, scanner);
    }

    @Override
    public void execute(Scanner in) {
        collection.add_if_min(in);
    }
}
