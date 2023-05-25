import command.Collection;
import command.CommandValidator;
import command.commands.Command;
import command.commands.MessageCommand;
import command.Saver;
import classes.Organization;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private SocketChannel clientSocket;
    private ServerSocketChannel server;

    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private static final String LOG_FILE = "server.log";
    ByteBuffer get;
    ByteBuffer send;
    ByteBuffer len = ByteBuffer.allocate(6);
    MessageCommand message;
    Command command;
    String filename = System.getenv("aboba");
    Saver saver = new Saver();
    PriorityQueue<Organization> priorityQueue = new PriorityQueue<Organization>();
    Collection collection;
    {
        saver.deserializeOrganization(filename, priorityQueue);
    collection = new Collection(priorityQueue);
    }
    CommandValidator cv = new CommandValidator(collection);
    Scanner scanner = new Scanner(System.in);

    public void startServer() {
        try {
            this.open();
            while (true) {
                if (clientSocket == null || !clientSocket.isOpen()) {
                    logger.info("Ждем нового клиента...");
                    clientSocket = server.accept();
                    logger.info("Подключен новый клиент.");
                }
                this.getMessage();
                this.sendMessage();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при запуске сервера", e);
        }
    }
    public void startConsole() {
        try {
            while (true) {
                String input = scanner.nextLine();
                System.out.println(cv.use(input, scanner));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void open() throws IOException {
        logger.info("Сервер запущен. Ожидание подключения клиента.");
        server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(6789));
        server.configureBlocking(true);
        clientSocket = server.accept();
        SocketAddress clientAddress = clientSocket.getRemoteAddress();
        logger.info("Подключен клиент: " + clientAddress);
    }
    public void getMessage() {
        try {
            clientSocket.read(len);
            len.flip();
            int length = len.getInt();
            get = ByteBuffer.allocate(length);
            clientSocket.read(get);
            command = deserializeMessage(get);
            logger.info("Получено сообщение.");
            message = command.execute(collection);
            command = null;
            len.clear();
            get.clear();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при получении сообщения от клиента", e.getMessage());
            try {
                clientSocket.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Ошибка при закрытии соединения с клиентом", ex.getMessage());
            }
        }
    }
    public void sendMessage() {
        try {
            if (clientSocket.isOpen()) {
                send = serializeObject(message);
                len = ByteBuffer.allocate(6).putInt(send.array().length);
                len.flip();
                clientSocket.write(len);
                clientSocket.write(send);
                len.clear();
                send.clear();
            } else {
                logger.warning("Соединение с клиентом разорвано.");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при отправке данных", e);
        }
    }

    public Command deserializeMessage(ByteBuffer get) throws IOException{
        ByteArrayInputStream bis = new ByteArrayInputStream(get.array());
        get.clear();
        ObjectInput in = null;
        in = new ObjectInputStream(bis);
        try {
            command = (Command) in.readObject();
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Проблема с десериализацией", e);
        }
        return command;
    }

    public ByteBuffer serializeObject(Object o) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        byte[] bytes;
        out = new ObjectOutputStream(bos);
        out.writeObject(o);
        out.flush();
        bytes = bos.toByteArray();
        return ByteBuffer.wrap(bytes);
    }
}
