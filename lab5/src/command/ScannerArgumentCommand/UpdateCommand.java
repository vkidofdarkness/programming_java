package command.ScannerArgumentCommand;

import util.Collection;

import java.util.Scanner;

public class UpdateCommand extends ScannerCommand {
    public UpdateCommand(Collection collection, Scanner scanner) {
        super(collection, scanner);
    }
    @Override
    public void execute(int id, Scanner in) {
        super.collection.update(id, in);
    }
}
