package task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by SergeyND on 12.04.2017.
 */
public class ServerController extends Server{
    ServerView view = new ServerView(this);
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class HandlerView extends Handler{
        private Socket socket;

        protected HandlerView(Socket socket) {
            super(socket);
            this.socket=socket;
        }

       /* @Override
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
        }*/

        @Override
        public void run() {
            String userName = null;
            try (Connection connection = new Connection(socket)){
             //   ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
                ServerView.setTextArea("Установлено новое соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
                userName = serverHandshake(connection);

                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));

                sendListOfUsers(connection, userName);

                serverMainLoop(connection, userName);

            } catch (IOException |ClassNotFoundException e) {
               // ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
                ServerView.setTextArea("Произошла ошибка при обмене данными с удаленным адресом");
            }
            finally {
                if (userName!=null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
             //   ConsoleHelper.writeMessage(String.format("Соединение с удаленным адресом (%s) закрыто.", socket.getRemoteSocketAddress()));
                ServerView.setTextArea(String.format("Соединение с удаленным адресом (%s) закрыто.", socket.getRemoteSocketAddress()));
            }
        }
    }



    public static void main(String[] args) {
        new ServerController();
        int server = ServerView.serverNumber();
        try (ServerSocket serverSocket = new ServerSocket(server);){
            ServerView.serverStart();
            ServerView.portNumber(server);
            while (true) {
                Socket socket = serverSocket.accept();
                new HandlerView(socket).run();
            }
        } catch (IOException e) {
            ServerView.serverError(e.getMessage());
        }
    }
}


