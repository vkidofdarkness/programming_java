package command.commands;

public class MessageCommand extends Command{
    private String message;

    public MessageCommand() {
        this.message = "Пустое сообщение.";
    }

    public String getMessage() {
        return message;
    }

    public MessageCommand(String message) {
        this.message = message;
    }
}
