package command.commands;

import java.io.Serializable;

public class Argument implements Serializable {
    private String value = "";

    public Argument(String value) { this.value = value; }

    public String getValue() {return value;}
}
