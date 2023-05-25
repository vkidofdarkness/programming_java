package command.commands;

import command.Collection;

public class AddIfMinCommand extends Command{
    public AddIfMinCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.add_if_min();
    }
}
