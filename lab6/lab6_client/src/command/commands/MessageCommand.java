package command.commands;

public class MessageCommand extends Command{
    private String message;

    public MessageCommand() {this.message = "Пустое сообщение.";}

    public MessageCommand(String message) {this.message = message;}

    public String getMessage() {return  message;}
}
