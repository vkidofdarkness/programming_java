package command;

import command.commands.*;
import classes.Organization;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandValidator {
    public boolean validate(String commandName, Pattern pattern) {
        Matcher matcher = pattern.matcher(commandName);
        return matcher.matches();
    }

    public Command selectCommand(String message, Scanner in) {
        String[] tokens = message.split(" ");
        String commandName = tokens[0];
        Pattern HELP = Pattern.compile("\\s*help\\s*");
        Pattern INFO = Pattern.compile("\\s*info\\s*");
        Pattern SHOW = Pattern.compile("\\s*show\\s*");
        Pattern ADD = Pattern.compile("\\s*add\\s*");
        Pattern UPDATE = Pattern.compile("\\s*update\\s*");
        Pattern REMOVE_BY_ID = Pattern.compile("\\s*remove_by_id\\s*");
        Pattern CLEAR = Pattern.compile("\\s*clear\\s*");
        Pattern SUM_OF_ANNUAL_TURNOVER = Pattern.compile("\\s*sum_of_annual_turnover\\s*");
        Pattern AVERAGE_OF_ANNUAL_TURNOVER = Pattern.compile("\\s*average_of_annual_turnover\\s*");
        Pattern EXIT = Pattern.compile("\\s*exit\\s*");
        Pattern ADD_IF_MAX = Pattern.compile("\\s*add_if_max\\s*");
        Pattern ADD_IF_MIN = Pattern.compile("\\s*add_if_min\\s*");
        Pattern REMOVE_HEAD = Pattern.compile("\\s*remove_head\\s*");
        Pattern EXECUTE_SCRIPT = Pattern.compile("\\s*execute_script\\s*");
        Pattern FILTER_STARTS_WITH_NAME = Pattern.compile("\\s*filter_starts_with_name\\s*");

        Command command = null;
        if (validate(commandName, HELP)) {
            command = new HelpCommand();
        } else if (validate(commandName, CLEAR)) {
            command = new ClearCommand();
        } else if (validate(commandName, INFO)) {
            command = new InfoCommand();
        } else if (validate(commandName, ADD)) {
            command = new AddCommand();
            Organization organization = new Organization();
            organization.addParameters(in);
            command.setOrganization(organization);
        } else if (validate(commandName, SHOW)) {
            command = new ShowCommand();
        } else if (validate(commandName, EXIT)) {
            command = new ExitCommand();
            System.exit(0);
        } else if (validate(commandName, SUM_OF_ANNUAL_TURNOVER)) {
            command = new SumOfAnnualTurnoverCommand();
        } else if (validate(commandName, AVERAGE_OF_ANNUAL_TURNOVER)) {
            command = new AverageOfAnnualTurnoverCommand();
        } else if (validate(commandName, UPDATE)) {
            command = new UpdateCommand();
            try {
                command.setArgument(new Argument(tokens[1]));
            } catch (NumberFormatException|ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: Аргумент не интерпретируется в id организации.");
                command = null;
            }
            if (command != null) {
                Organization organization = new Organization();
                organization.addParameters(in);
                command.setOrganization(organization);
            }
        } else if (validate(commandName, REMOVE_BY_ID)) {
            command = new RemoveByIdCommand();
            try {
                command.setArgument(new Argument(tokens[1]));
            } catch (NumberFormatException|ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: Аргумент не интерпретируется в id организации.");
                command = null;
            }
        } else if (validate(commandName, ADD_IF_MAX)) {
            command = new AddIfMaxCommand();
            Organization organization = new Organization();
            organization.addParameters(in);
            command.setOrganization(organization);
        } else if (validate(commandName, ADD_IF_MIN)) {
            command = new AddIfMinCommand();
            Organization organization = new Organization();
            organization.addParameters(in);
            command.setOrganization(organization);
        } else if (validate(commandName, REMOVE_HEAD)) {
            command = new RemoveHeadCommand();
        } else if (validate(commandName, FILTER_STARTS_WITH_NAME)) {
            command = new FilterStartsWithNameCommand();
            try {
                command.setArgument(new Argument(tokens[1]));
            } catch (NumberFormatException|ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: Аргумент не интерпретируется в id организации.");
                command = null;
            }
        } else if (validate(commandName, EXECUTE_SCRIPT)) {
            command = new ExecuteScriptCommand();
            try {
                command.setArgument(new Argument(tokens[1]));
            } catch (NumberFormatException|ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: Аргумент не интерпретируется в id организации.");
                command = null;
            }
        }
        return command;
    }
}
