package command.commands;

import command.Collection;

public class UpdateCommand extends Command{
    public UpdateCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.update();
    }
}
