package command.OneArgumentCommand;

import util.Collection;

public class Execute_ScriptCommand extends OneArgumentCommand{
    public Execute_ScriptCommand(Collection collection) {
        super(collection);
    }

    @Override
    public void execute(String filename) {
        this.collection.execute_script(filename);
    }
}
