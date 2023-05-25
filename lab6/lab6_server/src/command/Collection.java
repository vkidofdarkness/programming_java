package command;

import command.commands.MessageCommand;
import classes.Organization;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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

    public MessageCommand help() {
        return new MessageCommand("Доступные команды:\n" +
                "help: вывести справку по доступным командам\n" +
                "info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear: очистить коллекцию\n" +
                "execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме\n" +
                "exit: завершить программу (без сохранения в файл)\n" +
                "remove_head  : вывести первый элемент коллекции и удалить его\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "sum_of_annual_turnover : вывести сумму значений поля annualTurnover для всех элементов коллекции\n" +
                "average_of_annual_turnover : вывести среднее значение поля annualTurnover для всех элементов коллекции\n" +
                "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки");
    }

    public MessageCommand info() {
        return new MessageCommand("Тип коллекции: " + this.collection.getClass() + "\n" +
        "Размер коллекции: " + this.collection.size() + "\n" +
        "Дата инициализации: " + this.creationTime + "\n");
    }

    public MessageCommand show() {
        return new MessageCommand(collection.stream().map(Organization::toString).collect(Collectors.joining("\n")));
    }

    public MessageCommand add(Organization organization) {
        organization.setId();
        organization.setCreationDate();
        checkId(organization);
        collection.add(organization);
        System.out.println("Новый элемент успешно добавлен в коллекцию. ");
        sortCollection();
        return new MessageCommand("Элемент успешно добавлен в коллекцию.");
    }

    public MessageCommand update(int id, Organization newOrganization) {
        for (Organization organization : collection) {
            if (organization.getId() == id) {
                organization.setParameters(newOrganization);
                System.out.println("Элемент с id = " + id + " успешно обновлён. ");
                sortCollection();
                return new MessageCommand("Элемент успешно обновлён");
            }
        }
        return new MessageCommand("Элемент с id = " + id + " не найден." );
    }
    public MessageCommand remove_by_id(int id) {
            for (Organization organization: collection) {
            if (organization.getId() == id) {
                collection.remove(organization);
                sortCollection();
                System.out.println("Элемент с id = " + id + " успешно удалён. ");
                return new MessageCommand("Элемент успешно удалён. ");
            }
        }
        return new MessageCommand("Элемент с id = " + id + " не найден.");
    }
    public MessageCommand remove_head() {
        System.out.println(collection.poll());
        System.out.println("Первый элемент коллекции удалён.");
        return new MessageCommand("Элемент успешно удалён");
    }
    public MessageCommand clear() {
        collection.clear();
        sortCollection();
        return new MessageCommand("Коллекция успешно очищена. ");
    }
    public MessageCommand exit() throws IOException {
        save();
        return new MessageCommand("Коллекция сохранена. ");
    }
    public MessageCommand sum_of_annual_turnover() {
        double sum = 0;
        if (collection.size() != 0) {
            for (Organization organization: collection) {
                sum += organization.getAnnualTurnover();
            }
            return new MessageCommand("Сумма годовых оборотов организаций: " + sum);
        } else {
            return new MessageCommand("Коллекция пуста.");
        }
    }
    public MessageCommand average_of_annual_turnover() {
        double sum = 0;
        int count = 0;
        if (collection.size() != 0) {
            for (Organization organization : collection) {
                sum += organization.getAnnualTurnover();
                count += 1;
            }
            return new MessageCommand("Среднее значение годовых оборотов организаций: " + sum/count);
        } else {
            return new MessageCommand("Коллекция пуста.");
        }
    }
    public MessageCommand save() throws IOException {
        String filename = System.getenv("aboba");
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write("");
        fileWriter.flush();
        fileWriter.close();
        if (collection.size() != 0) {
            for (Organization organization: collection) {
                saver.serializeOrganization(organization, filename);
            }
            return new MessageCommand("Коллекция сохранена.");
        } else {
            return new MessageCommand("Коллекция пуста.");
        }
    }
    public MessageCommand execute_script(String filename) {
        String message = "";
        try {
            String path = filename;
            Scanner file = new Scanner(new FileReader(path));
            CommandValidator validator = new CommandValidator(this);
            String line;
            while ((line = file.nextLine()) != null) {
                message = message + validator.use(line, file).getMessage() + "\n";
            }

        } catch (FileNotFoundException e) {
            message = message + "Файл не найден.";
        } catch (NoSuchElementException e) {
            message = message + "Скрипт закончился.";
        } catch (StackOverflowError | IOException e) {
            return new MessageCommand("Скрипт зациклился. Выполнен принудительный выход из программы. ");
        }
        return new MessageCommand(message);
    }

    public MessageCommand filter_starts_with_name(String name) {
        return new MessageCommand(collection.stream().filter(x->x.getName().startsWith(name)).map(Organization::toString).collect(Collectors.joining("\n")));
    }
    public MessageCommand add_if_max(Organization organization) {
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
        return new MessageCommand("Элемент успешно добавлен в коллекцию.");
    }

        public MessageCommand add_if_min(Organization organization) {
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
            return new MessageCommand("Элемент успешно добавлен в коллекцию.");
        }

    /**
     * method for sorting the collection
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
                organization.setId();
                checkId(organization);
            }
        }
    }
}
