package command.commands;

import command.Collection;

import java.io.IOException;

public class ExitCommand extends Command{
    public ExitCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        try {
            return collection.exit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
