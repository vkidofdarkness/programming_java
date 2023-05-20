package classes;

import java.util.Scanner;

public class Coordinates {
    private Long x; //Максимальное значение поля: 630, Поле не может быть null
    private double y; //Минимальное значение поля -202, Поле не может быть null

    public void setX(Scanner console) {
        try{
            System.out.print("Введите координату X =< 630: ");
            String strX = console.nextLine();
            x = Long.parseLong(strX);
            if (x>630){
                System.out.println("Некорректный ввод. Поле не может быть больше 630. Попробуйте снова.");
                this.setX(console);
            }
        } catch (NullPointerException|NumberFormatException e){
            System.out.println("Некорректный ввод. Получен пустой аргумент. Попробуйте снова.");
            this.setX(console);
        }}
    public void setY(Scanner console) {
        try{
            System.out.print("Введите координату Y > -203: ");
            String strY = console.nextLine();
            this.y = Double.parseDouble(strY);
            if (y<-203){
                System.out.println("Некорректный ввод. Поле не может быть меньше или равно -203. Попробуйте снова.");
                this.setY(console);
            }
        } catch (NullPointerException|NumberFormatException e){
            System.out.println("Некорректный ввод. Получен пустой аргумент. Попробуйте снова.");
            this.setY(console);
        }}

    public Long getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
