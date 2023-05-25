package command.commands;

import command.Collection;

public class SumOfAnnualTurnoverCommand extends Command{
    public SumOfAnnualTurnoverCommand() {}

    @Override
    public MessageCommand execute(Collection collection) {
        return collection.sum_of_annual_turnover();
    }
}
