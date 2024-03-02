package gui;

import static gui.GuiButtonStatus.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import main.Game;
import utils.Vec2;

public class GuiButton extends GuiComponent {

    private ClickListener clicker;
    protected BufferedImage[] imgs;
    protected GuiButtonStatus status = GuiButtonStatus.IDLE;

    public GuiButton(String name, Vec2 position, Vec2 scale, List<BufferedImage> imgs, ClickListener clicker) {
        super(name, position, scale);
        this.position = position;
        this.scale = scale;
        this.clicker = clicker;
        this.imgs = imgs.toArray(new BufferedImage[0]);
        init();
    }

    public void init() {
    }

    @Override
    public void update() {
        if (hovering) {
            if (Game.MI.isHeld(MouseEvent.BUTTON1)) {
                status = PRESSED;
            } else {
                status = HOVERING;
            }
        } else {
            status = IDLE;
        }
    }

    @Override
    public void render(Graphics g) {
        switch (status) {
            case IDLE:
                g.drawImage(imgs[0], (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
                break;
            case HOVERING:
                g.drawImage(imgs[1], (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
                break;
            case PRESSED:
                if (imgs.length >= 3) {
                    g.drawImage(imgs[2], (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
                } else {
                    g.drawImage(imgs[1], (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick() {
        if (clicker != null) {
            clicker.onClick();
        }
    }
}
