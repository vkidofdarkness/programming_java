package command;

import command.commands.MessageCommand;

public interface Collection {
    MessageCommand help();
    MessageCommand info();
    MessageCommand show();
    MessageCommand sum_of_annual_turnover();
    MessageCommand clear();
    MessageCommand execute_script();
    MessageCommand exit();
    MessageCommand filter_starts_with_name();
    MessageCommand remove_by_id();
    MessageCommand add();
    MessageCommand remove_head();
    MessageCommand update();
    MessageCommand add_if_max();
    MessageCommand add_if_min();
    MessageCommand average_of_annual_turnover();

}
