package util;

import classes.Organization;
import exceptions.RecursiveScriptExecuteException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * класс, содержащий коллекцию и все методы для работы с ней
 */
public class Collection {
    public Collection(PriorityQueue<Organization> collection) {
        this.collection = collection;
        sortCollection();
    }
    private PriorityQueue<Organization> collection;
    private long[] idArray = new long[100];
    private final java.util.Date creationTime = new Date();
    private Scanner console = new Scanner(System.in);
    private final List<String> scriptStack = new ArrayList<>();

    Saver saver = new Saver();

    public void help() {
        System.out.println("Доступные команды:\n" +
                "help: вывести справку по доступным командам\n" +
                "info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear: очистить коллекцию\n" +
                "save: сохранить коллекцию в файл\n" +
                "execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме\n" +
                "exit: завершить программу (без сохранения в файл)\n" +
                "remove_head  : вывести первый элемент коллекции и удалить его\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "sum_of_annual_turnover : вывести сумму значений поля annualTurnover для всех элементов коллекции\n" +
                "average_of_annual_turnover : вывести среднее значение поля annualTurnover для всех элементов коллекции\n" +
                "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки");
    }
    public void info() {
        System.out.println("Тип коллекции: " + this.collection.getClass());
        System.out.println("Размер коллекции: " + this.collection.size());
        System.out.println("Дата инициализации: " + this.creationTime + "\n");
    }

    public void show() {
        if (collection.size() != 0) {
            for (Organization organization : collection) {
                System.out.println(organization.toString());
            }
        } else {
            System.out.println("Коллекция пуста.");
        }
    }

    public void add(Scanner sc) {
        Organization organization = new Organization();
        organization.addParameters(sc);
        checkId(organization);
        collection.add(organization);
        System.out.println("Элемент успешно добавлен в коллекцию. ");
        sortCollection();
    }

    public void update(int id, Scanner in) {
        boolean found = false;
        for (Organization organization: collection) {
            if (organization.getId() == id) {
                found = true;
                organization.addParameters(in);
                System.out.println("Элемент успешно обновлен. ");
                sortCollection();
            }
        }
        if (!found) {
            System.out.println("Элемент с данным id не найден. ");
        }
    }

    public void remove_by_id(int id) {
        boolean found = false;
        for (Organization organization : collection) {
            if (organization.getId() == id) {
                found = true;
                collection.remove(organization);
                System.out.println("Элемент успешно удалён. ");
                sortCollection();
            }
        }
        if (!found) {
            System.out.println("Элемент с данным id не найден.");
        }
    }

    public void clear() {
        collection.clear();
        System.out.println("Коллекция успешно очищена. ");
        sortCollection();
    }

    public void exit() {
        System.out.println("Программа завершена. ");
        System.exit(0);
    }

    public void sum_of_annual_turnover() {
        double sum = 0;
        if (collection.size() != 0) {
            for (Organization organization : collection) {
                sum += organization.getAnnualTurnover();
            }
            System.out.println("Сумма годовых оборотов всех организаций: " + sum);
        } else {
            System.out.println("Коллекция пуста.");
        }
    }

    public void average_of_annual_turnover() {
        double sum = 0;
        int count = 0;
        if (collection.size() != 0) {
            for (Organization organization : collection) {
                sum += organization.getAnnualTurnover();
                count += 1;
            }
            System.out.println("Средний годовой оборот: " + (sum / count));
        } else {
            System.out.println("Коллекция пуста.");
        }
    }

    public void save(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write("");
        fileWriter.flush();
        fileWriter.close();
        if (collection.size() != 0) {
            for (Organization organization: collection) {
                saver.serializeOrganization(organization, filename);
            }
        }
        else {
            System.out.println("Коллекция пуста.");
        }
        System.out.println("Коллекция сохранена.");
    }

    public void execute_script(String filename) {
        try {
            Scanner file = new Scanner(new FileReader(filename));
            Invoker user = new Invoker(filename);
            user.setCommands(this, file);
            CommandValidator validator = new CommandValidator(user);
            String line;
            if (scriptStack.contains(filename)) throw new RecursiveScriptExecuteException();
            scriptStack.add(filename);
            while (file.hasNextLine()) {
                System.out.println("Запущен скрипт: " + filename + "\n");
                line = file.nextLine();
                System.out.println("Прочитана команда: " + line);
                validator.use(line, file);
            }
            scriptStack.remove(scriptStack.size() - 1);
        } catch (FileNotFoundException e) {
            System.out.println("Файл со скриптом не найден.");
        } catch (NoSuchElementException e) {
            System.out.println("Файл со скриптом пустой.");
        } catch (RecursiveScriptExecuteException e) {
            System.out.println("Произошла бесконечная рекурсия. Скрипт принудительно завершён.");
        }
    }

    public void filter_starts_with_name(String name) {
        ArrayList <Organization> list = new ArrayList();
        for (Organization organization : collection) {
            if (organization.getName().startsWith(name)) {
                list.add(organization);
            }
        }
        if (!list.isEmpty()) {
            System.out.println(list.stream().map(Organization::toString).collect(Collectors.joining("\n")));
        } else {
            System.out.println("Элементы, начинающиеся с заданной подстроки не найдены.");
        }
    }

    public void add_if_max(Scanner sc) {
        Organization organization = new Organization();
        organization.addParameters(sc);
        checkId(organization);
        try {
            Organization max = collection.peek();
            for (Organization org : collection) {
                if (org.compareTo(max) > 0) {
                    max = org;
                }
            }
            if (organization.compareTo(max) > 0) {
                collection.add(organization);
                System.out.println("Новый элемент успешно добавлен в коллекцию. ");
                sortCollection();
            } else {
                System.out.println("Добавляемый элемент меньше максимального. ");
            }
        } catch (NoSuchElementException e) {
            collection.add(organization);
        }
    }

    public void add_if_min(Scanner sc) {
        Organization organization = new Organization();
        organization.addParameters(sc);
        checkId(organization);
        try {
            Organization min = collection.peek();
            for (Organization org : collection) {
                if (org.compareTo(min) < 0) {
                    min = org;
                }
            }
            if (organization.compareTo(min) < 0) {
                collection.add(organization);
                System.out.println("Новый элемент успешно добавлен в коллекцию. ");
                sortCollection();
            } else {
                System.out.println("Добавляемый элемент больше минимального");
            }
        } catch (NoSuchElementException e) {
            collection.add(organization);
        }
    }

    public void remove_head() {
        if (collection.size() !=0 ) {
            System.out.println(collection.poll());
            System.out.println("Первый элемент коллекции удалён.");
        }  else {
            System.out.println("Коллекция пуста.");
        }
    }

    /**
     * метод для сортировки коллекции
     */
    public void sortCollection() {
        ArrayList<Organization> ArrayCollection = new ArrayList<>(collection);
        Collections.sort(ArrayCollection);
        collection.clear();
        int i = 0;
        for (Organization organization: ArrayCollection) {
            collection.add(organization);
            idArray[i] = organization.getId();
            i++;
        }
    }

    public void checkId(Organization organization) {
        for (int i = 0; i<idArray.length; i++) {
            if (organization.getId()==idArray[i]) {
                System.out.println("id организации уже есть в коллекции. Присвоен новый id");
                organization.setId();
                checkId(organization);
            }
        }
    }
}
