package command.NoArgumentCommand;

import util.Collection;

public class ExitCommand extends NoArgumentCommand{
    public ExitCommand(Collection collection) {
        super(collection);
    }
    @Override
    public void execute() {
        collection.exit();
    }
}
