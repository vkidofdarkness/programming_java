package command.commands;

import command.Collection;


public class ClearCommand extends Command{

    public ClearCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {return collection.clear();}
}
