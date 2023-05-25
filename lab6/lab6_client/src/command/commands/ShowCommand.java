package command.commands;

import command.Collection;

public class ShowCommand extends Command{
    public ShowCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.show();
    }
}
