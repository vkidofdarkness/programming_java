package command.commands;

import command.Collection;

public class AddCommand extends Command{
    public AddCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.add(getOrganization());
    }
}
