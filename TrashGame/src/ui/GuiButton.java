package ui;

import org.joml.Vector2i;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GuiButton extends GuiComponent {

    private BufferedImage image;
    private final ClickListener clicker;

    public GuiButton(String name, Vector2i position, Vector2i scale, String imagePath, ClickListener clicker) {
        super(name, position, null); // Setting scale to null temporarily

        try {
            this.image = ImageIO.read(new File(imagePath));
            this.scale = new Vector2i(this.image.getWidth(), this.image.getHeight()); // Set scale based on image
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + imagePath, e); // Throw a more informative exception
        }

        this.clicker = clicker;
    }

    @Override
    public void update() {
        // Add logic for button updates if needed
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, position.x, position.y, null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}