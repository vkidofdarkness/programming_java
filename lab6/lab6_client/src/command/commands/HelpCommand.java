package command.commands;

import command.Collection;

public class HelpCommand extends  Command{
    public HelpCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.help();
    }
}
