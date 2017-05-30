package HTMLeditor.actions;


import HTMLeditor.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by SergeyND on 24.05.2017.
 */
public class UndoAction extends AbstractAction {
    private View view;

    public UndoAction(View view) {
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        view.undo();
    }
}
