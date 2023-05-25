package command.commands;

import command.Collection;

public class AverageOfAnnualTurnoverCommand extends Command{
    public AverageOfAnnualTurnoverCommand() {};

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.average_of_annual_turnover();
    }
}
