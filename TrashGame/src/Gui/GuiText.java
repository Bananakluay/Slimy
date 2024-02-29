package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import utils.Vec2;

public class GuiText {

    public static void drawString(Graphics g, String text, Vec2 position, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);
        int x = (int) position.x;
        int y = (int) position.y;

        // set to Center
        FontMetrics fm = g.getFontMetrics(font);
        x = x - fm.stringWidth(text) / 2;
        y = (y - fm.getHeight() / 2) + fm.getAscent();

        g.drawString(text, x, y);

        // g.drawRect(fm.stringWidth(text) / 2, fm.getHeight() / 2, fm.stringWidth(text), fm.getHeight());
    }

}
