package util;

import java.util.Scanner;

/**
 * интерфейс для работы с командами с консольным вводом
 */
public interface CommandWithScanner{
    void execute(Scanner in);

    void execute(int id, Scanner in);
}
