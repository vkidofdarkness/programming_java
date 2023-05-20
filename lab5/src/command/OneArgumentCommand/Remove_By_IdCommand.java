package command.OneArgumentCommand;

import util.Collection;

public class Remove_By_IdCommand extends OneArgumentCommand{
    public Remove_By_IdCommand(Collection collection) {
        super(collection);
    }
    @Override
    public void execute(int id) {
        super.collection.remove_by_id(id);
    }
}

