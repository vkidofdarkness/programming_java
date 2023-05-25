package command.commands;

import command.Collection;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.exit();
    }
}