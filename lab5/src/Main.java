import util.Collection;
import util.CommandValidator;
import util.Invoker;
import util.Saver;
import classes.Organization;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * main class of program
 * @autor Zheleznov Nikita Sergeevich P3119
 * @version 1.0
 */
public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PINK = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        try {
            System.out.println(ANSI_BLUE + "Введите название переменной окружения, в которой содержится путь с файлом для чтения: " + ANSI_RESET);
            Scanner envName = new Scanner(System.in);
            String filename = System.getenv(envName.nextLine());
            Saver saver = new Saver();
            PriorityQueue<Organization> organization = new PriorityQueue<>();
            saver.deserializeOrganization(filename, organization);
            Scanner console = new Scanner(System.in);
            Collection collection = new Collection(organization);
            collection.sortCollection();
            Invoker user = new Invoker(filename);
            CommandValidator validator = new CommandValidator(user);
            user.setCommands(collection, console);
            System.out.println(ANSI_BLUE + "\nДобро пожаловать! Введите в консоль команду 'help', чтобы получить информацию о существующих командах." + ANSI_RESET);
            while (true) {
                System.out.println(ANSI_PINK + "\nВведите команду: " + ANSI_RESET);
                String message = console.nextLine();
                validator.use(message, console);
            }
        } catch (NullPointerException e) {
            System.out.println("Переменная окружения не найдена.");
        }
    }
}
