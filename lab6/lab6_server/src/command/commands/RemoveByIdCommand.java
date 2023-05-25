package command.commands;

import command.Collection;

public class RemoveByIdCommand extends Command{
    public RemoveByIdCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.remove_by_id(Integer.parseInt(getArgument().getValue()));
    }
}
