package command.commands;

import command.Collection;

public class FilterStartsWithNameCommand extends Command {
    public FilterStartsWithNameCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.filter_starts_with_name();
    }
}
