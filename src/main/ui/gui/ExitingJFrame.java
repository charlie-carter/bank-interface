package ui.gui;

import model.LogPrinter;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ExitingJFrame extends JFrame implements WindowListener {

    private LogPrinter lp = new LogPrinter();

    public ExitingJFrame(String title) {
        super(title);
        addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        lp.printLogUnsafe();
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkExit(0);
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
