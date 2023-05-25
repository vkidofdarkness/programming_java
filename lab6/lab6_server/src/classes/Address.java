package classes;

import java.io.Serializable;
import java.util.Scanner;

public class Address implements Serializable {
    private String zipCode; //Поле не может быть null

    public void setZipCode(Scanner console) {
        System.out.println("Введите почтовый индекс: ");
        try{
            String strZipCode = console.nextLine();
            this.zipCode = strZipCode;
        } catch (NullPointerException e) {
            System.out.println("Некорректный ввод. Получен пустой аргумент. Попробуйте снова.");
            setZipCode(console);
        }
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode +
                "'}";
    }
}
