package command.ScannerArgumentCommand;

import util.Collection;
import util.CommandWithScanner;

import java.util.Scanner;

/**
 * абстрактный класс для команд с вводом
 */
public abstract class ScannerCommand  implements CommandWithScanner {
    Collection collection;
    Scanner scanner;
    public ScannerCommand(Collection collection, Scanner scanner) {
        this.collection = collection;
        this.scanner = scanner;
    }
    public void execute(Scanner in) {}

    public void execute(int id, Scanner in) {}


}
