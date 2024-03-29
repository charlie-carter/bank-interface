package ui.gui;

import java.awt.*;

public class FontBook {
    private Font titleFont = new Font("Helvetica", Font.BOLD,28);
    private Font subFont = new Font("Helvetica", Font.BOLD,22);
    private Font headerOne = new Font("Helvetica", Font.BOLD,18);
    private Font headerTwo = new Font("Helvetica", Font.BOLD,14);

    public FontBook() {

    }

    public Font getTitleFont() {
        return titleFont;
    }

    public Font getSubFont() {
        return subFont;
    }

    public Font getHeaderOne() {
        return headerOne;
    }

    public Font getHeaderTwo() {
        return headerTwo;
    }
}
