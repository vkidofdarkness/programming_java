package command.NoArgumentCommand;

import util.Collection;

public class Average_Of_Annual_TurnoverCommand extends NoArgumentCommand {
    public Average_Of_Annual_TurnoverCommand(Collection collection){
            super(collection);
        }

    @Override
    public void execute() {
         collection.average_of_annual_turnover();
        }
}

