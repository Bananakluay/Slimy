package ui;

import static utils.Constants.Game.SCALE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dataStructure.AssetPool;
import gui.ClickListener;
import gui.GuiButton;
import gui.GuiText;
import level.LevelManager;
import scene.SceneManager;
import scene.Scenes;
import utils.Vec2;

public class SelectLevelButton extends GuiButton implements ClickListener {

    int levelNumber;
    Font font;
    private int highestReachedLevel = LevelManager.loadLastPlayedLevel();

    public SelectLevelButton(String name, Vec2 position, Vec2 scale, int levelNumber) {
        super(
                name,
                position,
                scale,
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/SelectLevelButton.png", 17, 16),
                null);
        this.levelNumber = levelNumber;
        this.font = AssetPool.getFont("TrashGame/res/assets/fonts/m3x6.ttf", (int) (16 * SCALE * 3));
    }

    @Override
    public void render(Graphics g) {
        if (isLock()) {
            switch (status) {
                case IDLE:
                    g.drawImage(imgs[3], (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
                    break;
                case HOVERING:
                    g.drawImage(imgs[4], (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
                    break;
                case PRESSED:
                    g.drawImage(imgs[5], (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
                    break;
                default:
                    break;
            }
        } else {
            switch (status) {
                case IDLE:
                    g.drawImage(imgs[0], (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
                    GuiText.drawString(g, String.format("%02d", levelNumber),
                            new Vec2(position.x + 26 * SCALE, position.y + 19 * SCALE), Color.WHITE, font);
                    break;
                case HOVERING:
                    g.drawImage(imgs[1], (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
                    GuiText.drawString(g, String.format("%02d", levelNumber),
                            new Vec2(position.x + 26 * SCALE, position.y + 19 * SCALE), Color.WHITE, font);
                    break;
                case PRESSED:
                    g.drawImage(imgs[2], (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
                    GuiText.drawString(g, String.format("%02d", levelNumber),
                            new Vec2(position.x + 26 * SCALE, position.y + 21 * SCALE), Color.WHITE, font);
                    break;
                default:
                    break;
            }

        }
        // Graphics2D g2d = (Graphics2D) g;
        // g2d.draw(bounds);
    }

    @Override
    public void onClick() {
        System.out.println("Level " + levelNumber);
        if (!isLock()) {
            LevelManager.setLevel(levelNumber - 1);
            SceneManager.changeScene(Scenes.LEVEL_SCENE);
        }

    }

    private boolean isLock() {
        return levelNumber > highestReachedLevel;
    }

}
