package inputImage;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;
    private int ticks;
    private int delay;
    private int currentFrame = 0;
    private boolean stopped = true;

    public Animation(BufferedImage[] frames, int delay){
        this.frames = frames;
        this.delay = delay;
    }

    public void start() {
        if (!stopped) 
            return;
        if (frames.length == 0) 
            return;
        stopped = false;
    }

    public void stop() {
        if (frames.length == 0) 
            return;    
        stopped = true;
    }

    public void restart() {
        if (frames.length == 0) 
            return;   
        stopped = false;
        currentFrame = 0;
    }

    public void reset() {
        this.stopped = true;
        this.ticks= 0;
        this.currentFrame = 0;
    }
    public void update() {
        if (!stopped) {
            ticks++;

            if (ticks > delay) {
                ticks = 0;
                currentFrame++;
                if(currentFrame>=frames.length){
                    currentFrame = 0;
                }
            }
        }

    }

}
