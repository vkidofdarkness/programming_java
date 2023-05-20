package util;

/**
 * интерфейс для работы с командами без консольного ввода
 */
public interface Command {
    void execute();

    void execute(int id);

    void execute(String str);
}
