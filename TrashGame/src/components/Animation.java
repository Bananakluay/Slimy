package components;

import java.util.HashMap;
@SuppressWarnings("unused")
public class Animation extends Component {

    private HashMap<String, AnimationData> animations;
    private String currentAnimation;
    private float animationTime;

    public Animation() {
        this.animations = new HashMap<>();
        this.currentAnimation = null;
        this.animationTime = 0f;
    }

    public void addAnimation(String name, float duration, int[] frames) {
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

    public boolean isAnimating() {
        return currentAnimation != null;
    }

    @Override
    public void update() {
        // if (currentAnimation != null) {
        //     animationTime += deltaTime;
        //     AnimationData data = animations.get(currentAnimation);

        //     // Calculate current frame based on animation duration and time
        //     int frameIndex = (int) Math.floor(animationTime / data.duration * (data.frames.length - 1));

        //     // Apply the current frame to the game object (replace with your rendering logic)
        //     gameObject.setTexture(textures[data.frames[frameIndex]]);
        // }
    }

    private class AnimationData {
        float duration;
        int[] frames;

        public AnimationData(float duration, int[] frames) {
            this.duration = duration;
            this.frames = frames;
        }
    }
}