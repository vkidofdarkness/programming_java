package command.NoArgumentCommand;

import util.Collection;
import util.Command;

/**
 * абстрактный класс для команд без аргументов
 */
public abstract class NoArgumentCommand implements Command {
    Collection collection;
    public NoArgumentCommand(Collection collection) {
        this.collection = collection;
    }
    public void execute() {};

    public void execute(int id) {
        System.out.println("Данная команда не предполагает наличие аргументов.");
        System.out.println("Данная команда будет выполнена по умолчанию, без использования аргумента.");
        execute();
    };

    public void execute(String str) {};
}
