public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
        //Thread serverThread = new Thread(server::startServer);
        //Thread consoleThread = new Thread(server::startConsole);
        //serverThread.start();
        //consoleThread.start();

    }
}
