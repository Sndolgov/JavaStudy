package task3008;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by SergeyND on 12.04.2017.
 */
public class ServerView {
    private static JFrame frame = new JFrame("Сервер");
    private static JTextField textField = new JTextField(50);
    private static JTextArea messages = new JTextArea(10, 50);

    ServerController controller;

    public static String getData(){
        return new SimpleDateFormat("H:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static void setTextArea(String text){
        messages.append(getData()+" "+text+"\n");
    }

    public ServerView(ServerController controller) {
        this.controller = controller;
        initView();
    }

    private void initView(){
        textField.setEditable(false);// запрещаем редактирование поля
        messages.setEditable(false);


        textField.setText("Номер порта: ");

        frame.getContentPane().add(textField, BorderLayout.NORTH); // указывает где разместить поле
        frame.getContentPane().add(new JScrollPane(messages), BorderLayout.WEST);

        frame.pack(); // автоматически задает размер окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрытие программы после закрытия окна
        frame.setVisible(true); // делает окно видимым
    }

    public static int serverNumber() {
        while (true) {
            String port = JOptionPane.showInputDialog(
                    frame,
                    "Введите порт сервера:",
                    "Конфигурация сервера",
                    JOptionPane.QUESTION_MESSAGE);
            try {
                return Integer.parseInt(port.trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog( // выводит информационное диалоговое окно
                        frame,
                        "Был введен некорректный порт сервера. Попробуйте еще раз.",
                        "Конфигурация сервера",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void serverStart() {
        JOptionPane.showMessageDialog(
                frame,
                "Сервер запущен",
                "Конфигурация сервера",
                JOptionPane.INFORMATION_MESSAGE);
        setTextArea("Сервер запущен");
    }
    public static void portNumber(int port){
        textField.setText(textField.getText()+port);
    }


    public static void serverError(String s) {
        JOptionPane.showMessageDialog(
                frame,
                "Ошибка сервера: "+s,
                "Конфигурация сервера",
                JOptionPane.ERROR_MESSAGE);
    }


    }
