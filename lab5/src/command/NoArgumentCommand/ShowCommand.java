package command.NoArgumentCommand;

import util.Collection;

public class ShowCommand extends NoArgumentCommand{
    public ShowCommand(Collection collection) {
        super(collection);
    }
    @Override
    public void execute() {
        collection.show();
    }
}
