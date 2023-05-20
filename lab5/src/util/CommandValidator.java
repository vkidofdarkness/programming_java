package util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * класс для проверки консольных аргументов и шаблонов команд
 */
public class CommandValidator {
    private Invoker invoker;
    public CommandValidator(Invoker invoker) {
        this.invoker = invoker;
    }
    public boolean validate(String commandName, Pattern pattern) {
        Matcher matcher = pattern.matcher(commandName);
        return matcher.matches();
    }

    /**
     * метод сопоставления шаблонов и аргументов, запускает команду через класс invoker
     * @param message
     * @param in
     */
    public void use(String message, Scanner in) {
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

        if (validate(commandName, HELP)) {
            invoker.help();
        } else if (validate(commandName, INFO)) {
            invoker.info();
        } else if (validate(commandName, SHOW)) {
            invoker.show();
        } else if (validate(commandName, ADD)) {
            invoker.add(in);
        } else if (validate(commandName, UPDATE)) {
            try {
                invoker.update(Integer.parseInt(tokens[1]), in);
            } catch (NumberFormatException|ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: Аргумент не интерпретируется в id организации.");
            }
        } else if (validate(commandName, REMOVE_BY_ID)) {
            try {
                invoker.remove_by_id(Integer.parseInt(tokens[1]));
            } catch (NumberFormatException|ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: Аргумент не интерпретируется в id организации.");
            }
        } else if (validate(commandName, CLEAR)) {
            invoker.clear();
        } else if (validate(commandName, SAVE)){
            invoker.save();
        }  else if (validate(commandName, SUM_OF_ANNUAL_TURNOVER)) {
            invoker.sum_of_annual_turnover();
        }  else if (validate(commandName, AVERAGE_OF_ANNUAL_TURNOVER)) {
            invoker.average_of_annual_turnover();
        }  else if (validate(commandName, EXIT)) {
            invoker.exit();
        }  else if (validate(commandName, EXECUTE_SCRIPT)) {
            try {
                invoker.execute_script(tokens[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: Не указан аргумент.");
            }
        }  else if (validate(commandName, ADD_IF_MAX)) {
            invoker.add_if_max(in);
        }   else if (validate(commandName, ADD_IF_MIN)) {
            invoker.add_if_min(in);
        }  else if (validate(commandName, REMOVE_HEAD)) {
            invoker.remove_head();
        }  else if (validate(commandName, FILTER_STARTS_WITH_NAME)) {
            try {
                invoker.filter_starts_with_name(tokens[1]);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: Не указан аргумент.");
            }
        }  else {
            System.out.println("Команда не найдена. Введите 'help', чтобы получить информацию о доступных командах.");
        }
    }
}
