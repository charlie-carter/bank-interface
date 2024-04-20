package model;

public class LogPrinter {

    public LogPrinter() {

    }

    public void printLogSafe() {
        for (Event e : EventLog.getInstance()) {
            System.out.println("\n" + e.toString());
        }
    }

    public void printLogUnsafe() {
        System.out.println("Program closed unsafely \n System Log:");
        for (Event e : EventLog.getInstance()) {
            System.out.println("\n" + e.toString());
        }
        
    }
}
