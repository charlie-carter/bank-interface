package ui.gui;

import model.Client;
import model.LogPrinter;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// This is the final page the client sees as the exit the application,
// it presents them the option of saving or discarding their data.
public class ExitPage extends ExitingJFrame {
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/client.json";

    private Client client;
    private LogPrinter lp;

    public ExitPage(Client client) {
        super("Bank of UBC");
        this.client = client;
        jsonWriter = new JsonWriter(JSON_STORE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        initializeGraphics();
        setVisible(true);
        lp = new LogPrinter();
    }

    //MODIFIES: this
    //EFFECTS: generates options for the user to either save or discard their data
    private void initializeGraphics() {
        JPanel exitPanel = new JPanel();
        exitPanel.setBorder(new EmptyBorder(5,5,5,5));
        exitPanel.setLayout(new GridLayout(2,1));
        JButton save = new JButton("Save Data");
        JButton discard = new JButton("Discard Data");
        save.addActionListener(new SaveData());
        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePanel();
                lp.printLogSafe();
                System.exit(0);
            }
        });

        exitPanel.add(save);
        exitPanel.add(discard);
        add(exitPanel);
    }

    //MODIFIES: this, data files
    //EFFECTS: saves the userdata to file, closes application
    private class SaveData implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                jsonWriter.open();
                jsonWriter.write(client);
                jsonWriter.close();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
            removePanel();
            lp.printLogSafe();
            System.exit(0);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes this panel from the screen
    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }
}
