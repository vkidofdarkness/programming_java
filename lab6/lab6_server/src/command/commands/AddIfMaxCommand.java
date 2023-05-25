package command.commands;

import command.Collection;

public class AddIfMaxCommand extends Command{
    public AddIfMaxCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.add_if_max(getOrganization());
    }
}
