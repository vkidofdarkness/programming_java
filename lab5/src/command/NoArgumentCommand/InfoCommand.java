package command.NoArgumentCommand;

import util.Collection;

public class InfoCommand extends NoArgumentCommand{
    public InfoCommand(Collection collection) {
        super(collection);
    }
    @Override
    public void execute() {
        collection.info();
    }
}
