package command.commands;

import command.Collection;

public class ExecuteScriptCommand extends Command{
    public ExecuteScriptCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.execute_script();
    }
}
