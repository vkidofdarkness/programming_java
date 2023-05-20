package classes;

import util.AdditionFunctions;

import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * класс, описывающий элементы объекта Organization
 */
public class Organization implements Comparable <Organization> {
    public Organization() {
        this.id = (int) (Math.random()*10000+1);
    }
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float annualTurnover; //Значение поля должно быть больше 0, Поле не может быть null
    private OrganizationType type; //Поле может быть null
    private Address officialAddress; //Поле не может быть null


    public void setId() {
        this.id = (int)(Math.random()*10000+1);
    }
    public void setName(Scanner console) {
        System.out.println("Введите название организации: ");
        String strName = console.nextLine();
        if (strName.isEmpty() | strName == null) {
            System.out.println("Некорректный ввод. Получен пустой аргумент. Попробуйте снова.");
            setName(console);
        }
        this.name = strName;
    }

    public void setCoordinates(Scanner console) {
        System.out.println("Введите координаты организации ");
        Coordinates coordinates = new Coordinates();
        coordinates.setX(console);
        coordinates.setY(console);
        this.coordinates = coordinates;
    }

    public void setCreationDate() {this.creationDate = new Date();}

    public void setAnnualTurnover(Scanner console) {
        System.out.println("Введите годовой оборот организации: ");
        try {
            String strAnnualTurnover = console.nextLine();
            Float floatAnnualTurnover = Float.parseFloat(strAnnualTurnover);
            if (floatAnnualTurnover <= 0) {
                System.out.println("Некорректный  ввод. Значение поля не может быть меньше 0. Попробуйте снова.");
                setAnnualTurnover(console);
            }
            this.annualTurnover = floatAnnualTurnover;
        } catch (NullPointerException e){
            System.out.println("Некорректный ввод. Получен пустой аргумент. Попробуйте снова");
            setAnnualTurnover(console);
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. В аргументе содержатся нечисловые символы. Попробуйте снова.");
            setAnnualTurnover(console);
        }
    }

    public void setType(Scanner console) {
        System.out.println("Введите тип организации: ");
        System.out.println("Значение поля может быть: \n"+ OrganizationType.nameList());
        String strType = console.nextLine();
        try {
            this.type = AdditionFunctions.compareType(strType.toLowerCase(Locale.ROOT));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            setType(console);
        }
    }
    public void setAddress(Scanner console) {
        System.out.println("Введите адрес организации: ");
        Address address = new Address();
        address.setZipCode(console);
        this.officialAddress = address;
    }

    public void addParameters(Scanner console) {
        setName(console);
        setCoordinates(console);
        setCreationDate();
        setAnnualTurnover(console);
        setType(console);
        setAddress(console);
    }

    public boolean checkParameters() {
        if ((getName() == null) | (getName().equals("")) | (getCoordinates() == null) | (getCreationDate() == null)
                | (getAnnualTurnover() == null) | (getAddress() == null)) {
            return false;
        } else if ((getCoordinates().getX() == null)) {
            return false;
        } else if (getCoordinates().getX() > 630 | getCoordinates().getY() <= -203)  {
            return false;
        }
        if (getAddress() != null) {
            if ((getAddress().getZipCode() == null)) {
                return false;
            }
        }
        return true;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Float getAnnualTurnover() {
        return annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getAddress() {
        return officialAddress;
    }



    @Override
    public String toString() {
        if (getType() != null) {
            return "Organization{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coordinates=" + coordinates.toString() +
                    ", creationDate=" + creationDate +
                    ", annualTurnover=" + annualTurnover +
                    ", type=" + type +
                    ", address=" + officialAddress.toString() + "}";
        } else {
            return "Organization{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coordinates=" + coordinates.toString() +
                    ", creationDate=" + creationDate +
                    ", annualTurnover=" + annualTurnover +
                    ", type=" + null +
                    ", address=" + officialAddress.toString() + "}";
        }
    }

    @Override
    public int compareTo(Organization o) {
        return 0;
    }
}
