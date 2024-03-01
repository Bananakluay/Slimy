package components;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

import dataStructure.Transform;
import Prefabs.Player.Player;
import Prefabs.Trap.BombButton;

@SuppressWarnings("unused")
public class Animation extends Component {

    private HashMap<String, AnimationData> animations;
    private String currentAnimation;
    private float animationTime;
    private int ticks, index;
    private float scale = 1f;

    private float width, height;
    private int offsetX = 0, offsetY = 0;

    private boolean playOneTime = false;

    public Animation() {

        this.animations = new HashMap<>();
        this.currentAnimation = null;
        this.animationTime = 0f;

    }

    public void addAnimation(String name, float duration, List<BufferedImage> frames, boolean playOneTime) {
        AnimationData data = new AnimationData(duration, frames);
        animations.put(name, data);
        this.playOneTime = playOneTime;
    }

    public void play(String animationName) {
        if (animations.containsKey(animationName) && !animationName.equals(currentAnimation)) {
            this.currentAnimation = animationName;
            this.animationTime = 0f;
            this.index = 0;
        }
    }

    public void stop() {
        this.currentAnimation = null;
        this.animationTime = 0f;
    }

    public void reset() {
        this.currentAnimation = null;
        this.animationTime = 0;
        this.index = 0;
    }

    public void restart() {
        this.animationTime = 0;
        this.index = 0;
    }

    public boolean isAnimating() {
        return currentAnimation != null;
    }

    public void setOffset(int x, int y) {
        this.offsetX = x;
        this.offsetY = y;
    }

    @Override
    public void update() {
        if (currentAnimation != null) {
            ticks++;
            if (ticks >= animations.get(currentAnimation).duration) {
                ticks = 0;
                index++;
                if (index >= animations.get(currentAnimation).frames.size()) {
                    if (playOneTime) { // Check if playOnce is set and reset animation
                        System.out.println("playOneTime");
                        currentAnimation = null;
                    } else {
                        index = 0;
                    }
                }
            }
        }
    }

    public void draw(Graphics g, Transform transform) {
        g.drawImage(
                animations.get(currentAnimation).frames.get(index),
                (int) transform.position.x,
                (int) transform.position.y,
                (int) transform.scale.x,
                (int) transform.scale.y,
                null);
    }

    @Override
    public void draw(Graphics g) {
        if (!isAnimating())
            return;
        int x = (int) (entity.getTransform().position.x + offsetX * scale);
        int y = (int) (entity.getTransform().position.y + offsetY * scale);
        int w = (int) (width * scale);
        int h = (int) (height * scale);
        if (this.entity instanceof BombButton) {
            BombButton b = (BombButton) this.entity;
        
            // Rest of the code inside the if block remains the same
            // System.out.println(currentAnimation);
            // System.out.println("Drawing bomb button" + currentAnimation + " " + index + " "
            // + animations.get(currentAnimation).frames.size() + " ");
            System.out.println(
                    "Drawing bomb button" + x / TILES_SIZE + " " + y / TILES_SIZE + " " + w + " " + h + " " + scale);
        }
        
        if (this.entity instanceof Player) {
            Player player = (Player) this.entity;
            if (player.getDirection().x < 0) {
                g.drawImage(
                        animations.get(currentAnimation).frames.get(index),
                        x + w,
                        y,
                        -w,
                        h,
                        null);
            } else {
                g.drawImage(
                        animations.get(currentAnimation).frames.get(index),
                        x, y, w, h,
                        null);
            }
        } else {
            g.drawImage(
                    animations.get(currentAnimation).frames.get(index),
                    x, y, w, h,
                    null);
            System.out.println("Drawing " + currentAnimation + " " + index + " " + animations.get(currentAnimation).frames.size() + " ");
        }

    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    private class AnimationData {
        float duration;
        List<BufferedImage> frames;

        public AnimationData(float duration, List<BufferedImage> frames) {
            this.duration = duration;
            this.frames = frames;
        }
    }
}