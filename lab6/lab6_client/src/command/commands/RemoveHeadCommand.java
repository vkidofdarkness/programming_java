package command.commands;

import command.Collection;

public class RemoveHeadCommand extends Command {
    public RemoveHeadCommand() {
    }

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.remove_head();
    }
}