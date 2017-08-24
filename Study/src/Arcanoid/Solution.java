package Arcanoid;

import javax.swing.*;
import java.awt.*;


/**
 * Created by SergeyND on 16.02.2017.
 */
public class Solution
{
    public static void main(String[] args){

       new ClientGui();
    }

    public static class ClientGui{
        private JFrame frame = new JFrame("Чат");
        private JTextField textField = new JTextField(50); //однострочное поле
        private JTextArea messages = new JTextArea(10, 50); // многострочное поле
        private JTextArea users = new JTextArea(10, 10);



        public ClientGui() {
            initView();
        }

        private void initView()
        {
            textField.setEditable(false);// запрещаем редактирование поля
            messages.setEditable(false);
            users.setEditable(false);

            frame.getContentPane().add(textField, BorderLayout.NORTH); // указывает где разместить поле
            frame.getContentPane().add(new JScrollPane(messages), BorderLayout.WEST);
            frame.getContentPane().add(new JScrollPane(users), BorderLayout.EAST);
            frame.pack(); // автоматически задает размер окна
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрытие программы после закрытия окна
            frame.setVisible(true); // делает окно видимым
        }
    }

}