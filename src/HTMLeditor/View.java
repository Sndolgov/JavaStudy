package HTMLeditor;



import HTMLeditor.listeners.FrameListener;
import HTMLeditor.listeners.TabbedPaneChangeListener;
import HTMLeditor.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SergeyND on 22.05.2017.
 */
public class View extends JFrame implements ActionListener {

    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);


    public View() throws HeadlessException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {

            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
        /*    case "Открыть страницу...":
                openURL();
                break;*/
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        initGui();
        FrameListener listener = new FrameListener(this);
        this.addWindowListener(listener);
        this.setVisible(true);


    }

    public void exit() {
        controller.exit();
    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        //setJMenuBar(menuBar);
        getContentPane().add(menuBar, BorderLayout.NORTH);

    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));
        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));
        tabbedPane.setPreferredSize(new Dimension(500, 500));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {
        int tab = tabbedPane.getSelectedIndex();
        if (tab == 0)
            controller.setPlainText(plainTextPane.getText());
        if (tab == 1)
            plainTextPane.setText(controller.getPlainText());
        resetUndo();

    }

    public boolean canUndo() {
        return undoManager.canUndo();

    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {

        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0 ? true : false;
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(this, "Редактор текста\nРазработчик: Sndolgov", "О программе", JOptionPane.INFORMATION_MESSAGE);
    }

 /*   public void openURL(){
        controller.resetDocument();
        resetUndo();
        selectHtmlTab();

        JFrame frame = new JFrame();
        JTextField   url;
        final String unavailable = "Адрес недоступен";
        frame.setTitle("Загрузка текста из интернета");
     //   frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel pnlURL = new JPanel();
        pnlURL.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlURL.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        url = new JTextField("http://yandex.ru", 35);
        url.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Переход по адресу
                String newAddress = url.getText();
                try {
                    htmlTextPane.setPage(newAddress);
                } catch (Exception b) {
                    JOptionPane.showMessageDialog(frame,
                            unavailable);
                }
            }
        });

        pnlURL.add(new JLabel("Адрес:"));
        pnlURL.add(url);
        frame.add(pnlURL);
        htmlTextPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                // Проверка типа события
                if ( e.getEventType() != HyperlinkEvent.EventType.ACTIVATED)
                    return;
                // Переходим по адресу
                try {
                    htmlTextPane.setPage(e.getURL());
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(frame,
                            unavailable);
                }
            }
        });


      //  htmlTextPane.setText(url.getText());





        frame.setSize(400,100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }*/

}


