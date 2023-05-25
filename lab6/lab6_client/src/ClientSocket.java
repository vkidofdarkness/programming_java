import command.CommandValidator;
import command.commands.Command;
import command.commands.MessageCommand;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class ClientSocket {
    byte[] get;
    byte[] send;
    byte[] len = new byte[6];

    private static Socket clientSocket;
    private static InputStream in;
    private static OutputStream out;

    MessageCommand message;
    Command command;
    Scanner scanner = new Scanner(System.in);
    CommandValidator cv = new CommandValidator();

    public void open() throws IOException {
        int maxAttempts = 3; // Максимальное количество попыток подключения
        int attempt = 0;

        while (attempt < maxAttempts) {
            try {
                clientSocket = new Socket("localhost", 6789);
                in = clientSocket.getInputStream();
                out = clientSocket.getOutputStream();
                System.out.println("Подключение прошло успешно!");
                clientSocket.setSoTimeout(5000);
                break;
            } catch (IOException e) {
                attempt++;
                System.err.println("Не удалось подключиться к серверу. Попытка: " + attempt);
                try {
                    Thread.sleep(1000); // Пауза в 1 секунду
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (attempt == maxAttempts) {
            System.err.println("Не удалось установить соединение с сервером. Попробуйте позже.");
            System.exit(1);
        }
    }


    public void sendMessage() throws IOException {
        try {
            System.out.println("Введите команду: ");
            String input = scanner.nextLine();
            command = cv.selectCommand(input, scanner);
            if (command != null) {
                send = serializeObject(command);
                len = ByteBuffer.allocate(6).putInt(send.length).array();
                out.write(len);
                out.write(send);
                System.out.println("Сообщение отправлено. ");
            } else {
                System.out.println("Команда введена некорректно. ");
                sendMessage();
            }
        } catch (SocketException e) {
            System.out.println("Соединение с сервером разорвано.");
            open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessage() throws IOException, ClassNotFoundException {
        try {
            byte[] arrLengthServer = new byte[6];
            in.read(arrLengthServer);
            get = new byte[ByteBuffer.wrap(arrLengthServer).getInt()];
            in.read(get);
            message = deserializeMessage(get);
            System.out.println(message.getMessage());
        } catch (SocketTimeoutException e) {
            System.out.println("Сервер отключен или не отвечает.");
        }
    }


    public byte[] serializeObject(Object object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream;
        byte[] bytesObject;
        try {
            outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(object);
            out.flush();
            bytesObject = byteArrayOutputStream.toByteArray();
            return bytesObject;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {}
        }
    }

    public MessageCommand deserializeMessage(byte[] arrObj) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrObj);
        ObjectInput input = null;
        try {
            input = new ObjectInputStream(byteArrayInputStream);
            message = (MessageCommand) input.readObject();
            return message;
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {}
        }
    }
}
