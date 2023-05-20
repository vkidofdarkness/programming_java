package command.NoArgumentCommand;

import util.Collection;

public class ClearCommand extends NoArgumentCommand{
    public ClearCommand(Collection collection) {
        super(collection);
    }
    @Override
    public void execute() {
        collection.clear();
    }
}
