import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClientSocket client = new ClientSocket();
        client.open();
        while (true) {
            client.sendMessage();
            client.getMessage();
        }
    }
}
