package ui.gui;

import model.Client;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class ExitPage extends JFrame {
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/client.json";

    private Client client;

    public ExitPage(Client client) {
        super("Bank of UBC");
        this.client = client;
        jsonWriter = new JsonWriter(JSON_STORE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        initializeGraphics();
        setVisible(true);
    }

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
                System.exit(0);
            }
        });

        exitPanel.add(save);
        exitPanel.add(discard);
        add(exitPanel);
    }

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
        }
    }

    private void removePanel() {
        this.setVisible(false);
        this.dispose();
    }
}
