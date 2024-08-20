package GUI.Utils;

import java.awt.Color;

public enum Colors {
    PRIMARY(new Color(0, 153, 102)),
    HOVER(new Color(0, 143, 102)),
    TEXT(new Color(255, 255, 255)),
    BACKGROUND(new Color(240, 240, 240)),
    BORDER(new Color(200, 200, 200));

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

