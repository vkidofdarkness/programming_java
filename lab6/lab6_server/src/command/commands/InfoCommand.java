package command.commands;

import command.Collection;

public class InfoCommand extends Command {
    public InfoCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.info();
    }
}
