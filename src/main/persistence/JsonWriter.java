package persistence;

import model.Client;
import model.Event;
import model.EventLog;
import org.json.JSONObject;
import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
// Some methods taken from phase 2 example file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of client to file
    public void write(Client c) {
        JSONObject json = c.toJson();
        saveToFile(json.toString(TAB));
        EventLog.getInstance().logEvent(new Event("Data Saved to File"));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
