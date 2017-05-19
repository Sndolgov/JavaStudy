package task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by SergeyND on 04.04.2017.
 */
public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();


    protected static class Handler extends Thread {
        private Socket socket;


        protected Handler(Socket socket) {
            this.socket = socket;
        }

        String serverHandshake(Connection connection) throws IOException, ClassNotFoundException { // запрашиваем имя юзера и добавляем в список
            boolean accepted = false;
            String name = null;
            while (!accepted) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    name = message.getData();
                    if (!name.isEmpty() && connectionMap.get(name) == null) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        accepted = true;
                    }
                }
            }
            return name;
        }

        void sendListOfUsers(Connection connection, String userName) throws IOException { // сообщает юзеру кто еще в чате
            for (String name : connectionMap.keySet()) {
                if (!name.equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED, name));
            }
        }

        void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException { // выводит сообщение юзера
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    Message showMessage = new Message(MessageType.TEXT, userName + ": " + message.getData());
                    sendBroadcastMessage(showMessage);
                } else new ConsoleHelper().writeMessage("Ошибка! Недопустимый формат сообщения.");
            }
        }

        @Override
        public void run() {
            String userName = null;
            try (Connection connection = new Connection(socket)){
                ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
                    userName = serverHandshake(connection);

                    sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));

                    sendListOfUsers(connection, userName);

                    serverMainLoop(connection, userName);

            } catch (IOException |ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            }
            finally {
                if (userName!=null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                    ConsoleHelper.writeMessage(String.format("Соединение с удаленным адресом (%s) закрыто.", socket.getRemoteSocketAddress()));
            }
        }
    }


    public static void sendBroadcastMessage(Message message) { // отправляет всем участника сообщение
        for (String clientName : connectionMap.keySet())
            try {
                connectionMap.get(clientName).send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Сообщение не было отправлено клиенту: " + clientName);
            }
    }

    public static void main(String[] args) {
        ConsoleHelper helper = new ConsoleHelper();
        helper.writeMessage("Введите номер порта:");
        try (ServerSocket serverSocket = new ServerSocket(helper.readInt());
        ) {
            System.out.println("Сервер запущен");
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка сервера");
        }
    }
}
