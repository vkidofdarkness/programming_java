package command.NoArgumentCommand;

import util.Collection;

public class HelpCommand extends NoArgumentCommand {
    public HelpCommand(Collection collection) {
        super(collection);
    }
    @Override
    public void execute() {
        collection.help();
    }
}
