package command;

import command.commands.MessageCommand;
import classes.Organization;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandValidator {
    public CommandValidator(Collection collection) {
        this.collection = collection;
    }

    Collection collection;
    public boolean validate(String commandName, Pattern pattern) {
        Matcher matcher = pattern.matcher(commandName);
        return matcher.matches();
    }

    public MessageCommand use(String message, Scanner in) throws IOException {
        String[] tokens = message.split(" ");
        String commandName = tokens[0];
        Pattern HELP = Pattern.compile("\\s*help\\s*");
        Pattern INFO = Pattern.compile("\\s*info\\s*");
        Pattern SHOW = Pattern.compile("\\s*show\\s*");
        Pattern ADD = Pattern.compile("\\s*add\\s*");
        Pattern UPDATE = Pattern.compile("\\s*update\\s*");
        Pattern REMOVE_BY_ID = Pattern.compile("\\s*remove_by_id\\s*");
        Pattern CLEAR = Pattern.compile("\\s*clear\\s*");
        Pattern SAVE = Pattern.compile("\\s*save\\s*");
        Pattern SUM_OF_ANNUAL_TURNOVER = Pattern.compile("\\s*sum_of_annual_turnover\\s*");
        Pattern AVERAGE_OF_ANNUAL_TURNOVER = Pattern.compile("\\s*average_of_annual_turnover\\s*");
        Pattern EXIT = Pattern.compile("\\s*exit\\s*");
        Pattern ADD_IF_MAX = Pattern.compile("\\s*add_if_max\\s*");
        Pattern ADD_IF_MIN = Pattern.compile("\\s*add_if_min\\s*");
        Pattern REMOVE_HEAD = Pattern.compile("\\s*remove_head\\s*");
        Pattern EXECUTE_SCRIPT = Pattern.compile("\\s*execute_script\\s*");
        Pattern FILTER_STARTS_WITH_NAME = Pattern.compile("\\s*filter_starts_with_name\\s*");

        MessageCommand messageCommand = null;
        if (validate(commandName, ADD)) {
            Organization organization = new Organization();
            organization.addParameters(in);
            messageCommand = collection.add(organization);
        }  else if (validate(commandName, HELP)) {
            messageCommand = collection.help();
        }  else if (validate(commandName, SHOW)) {
            messageCommand = collection.show();
        }  else if (validate(commandName, REMOVE_BY_ID)) {
            messageCommand = collection.remove_by_id(Integer.parseInt(tokens[1]));
        }  else if (validate(commandName, INFO)) {
            messageCommand = collection.info();
        }  else if (validate(commandName, UPDATE)) {
            Organization organization = new Organization();
            organization.addParameters(in);
            messageCommand = collection.update(Integer.parseInt(tokens[1]), organization);
        }  else if (validate(commandName, CLEAR)) {
            messageCommand = collection.clear();
        }  else if (validate(commandName, SAVE)) {
            messageCommand = collection.save();
        }  else if (validate(commandName, SUM_OF_ANNUAL_TURNOVER)) {
            messageCommand = collection.sum_of_annual_turnover();
        }  else if (validate(commandName, AVERAGE_OF_ANNUAL_TURNOVER)) {
            messageCommand = collection.average_of_annual_turnover();
        }  else if (validate(commandName, EXIT)) {
            messageCommand = collection.exit();
        }  else if (validate(commandName, ADD_IF_MAX)) {
            Organization organization = new Organization();
            organization.addParameters(in);
            messageCommand = collection.add_if_max(organization);
        }  else if (validate(commandName, ADD_IF_MIN)) {
            Organization organization = new Organization();
            organization.addParameters(in);
            messageCommand = collection.add_if_min(organization);
        }  else if (validate(commandName, REMOVE_HEAD)) {
            messageCommand = collection.remove_head();
        }  else if (validate(commandName, EXECUTE_SCRIPT)) {
            messageCommand = collection.execute_script(tokens[1]);
        }  else if (validate(commandName, FILTER_STARTS_WITH_NAME)) {
            messageCommand = collection.filter_starts_with_name(tokens[1]);
        }  else {
            messageCommand = new MessageCommand("Команда введена некорректно. ");
        }
        return messageCommand;
    }
}
