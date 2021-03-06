package HTMLeditor.listeners;


import HTMLeditor.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by SergeyND on 22.05.2017.
 */
public class FrameListener extends WindowAdapter {
    private View view;

    public FrameListener(View view) {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        view.exit();
    }
}
