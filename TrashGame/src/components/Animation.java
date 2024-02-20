package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

import dataStructure.Transform;
@SuppressWarnings("unused")
public class Animation extends Component {

    private HashMap<String, AnimationData> animations;
    private String currentAnimation;
    private float animationTime;
    private int ticks, index;
    public Animation() {
        this.animations = new HashMap<>();
        this.currentAnimation = null;
        this.animationTime = 0f;
    }

    public void addAnimation(String name, float duration, List<BufferedImage> frames) {
        AnimationData data = new AnimationData(duration, frames);
        animations.put(name, data);
    }

    public void play(String animationName) {
        if (animations.containsKey(animationName) && !animationName.equals(currentAnimation)) {
            this.currentAnimation = animationName;
            this.animationTime = 0f;
        }
    }

    public void stop() {
        this.currentAnimation = null;
        this.animationTime = 0f;
    }

    public void reset(){
        this.currentAnimation = null;
        this.animationTime = 0;
        this.index = 0;
    }

    public boolean isAnimating() {
        return currentAnimation != null;
    }

    @Override
    public void update() {
        if(currentAnimation != null){
            if(ticks>=animations.get(currentAnimation).duration){
                ticks = 0;
                index++;
                if(index>=animations.get(currentAnimation).frames.size()){
                    index = 0;
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(
            animations.get(currentAnimation).frames.get(index), 
            (int)entity.getTransform().position.x, 
            (int)entity.getTransform().position.y,
            (int)entity.getTransform().scale.x,
            (int)entity.getTransform().scale.y, 
            null);
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