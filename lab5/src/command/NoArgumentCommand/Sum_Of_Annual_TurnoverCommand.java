package command.NoArgumentCommand;

import util.Collection;

public class Sum_Of_Annual_TurnoverCommand extends NoArgumentCommand{
    public Sum_Of_Annual_TurnoverCommand(Collection collection){
        super(collection);
    }

    @Override
    public void execute() {
        collection.sum_of_annual_turnover();
    }
}
