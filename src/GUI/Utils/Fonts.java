package GUI.Utils;

import java.awt.Font;

public enum Fonts {
	
    BUTTONS(new Font("Arial", Font.PLAIN, 25));

    private final Font font;

    Fonts(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}

