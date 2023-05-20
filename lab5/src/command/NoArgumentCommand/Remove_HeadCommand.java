package command.NoArgumentCommand;

import util.Collection;

public class Remove_HeadCommand extends NoArgumentCommand{
    public Remove_HeadCommand(Collection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        collection.remove_head();
    }
}
