package command.OneArgumentCommand;

import util.Collection;

import java.io.IOException;

public class SaveCommand extends OneArgumentCommand {
    public SaveCommand(Collection collection) {
        super(collection);
    }
    @Override
    public void execute(String filename) {
        try {
            collection.save(filename);
        } catch (IOException e) {
            System.out.println("Что-то пошло не так.");
        }
    }
}
