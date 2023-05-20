package command.OneArgumentCommand;


import util.Collection;

public class Filter_Starts_With_NameCommand extends OneArgumentCommand{
    public Filter_Starts_With_NameCommand(Collection collection) {
        super(collection);
    }

    @Override
    public void execute(String name) {
        collection.filter_starts_with_name(name);
    }
}

