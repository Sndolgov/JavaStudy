package HTMLeditor.actions;


import HTMLeditor.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by SergeyND on 24.05.2017.
 */
public class RedoAction extends AbstractAction {
    private View view;

    public RedoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.redo();
    }
}
