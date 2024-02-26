package Gui;

import static Gui.GuiButtonStatus.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import main.Game;
import utils.Vec2;


public class ToggleGuiButton extends GuiComponent{

    private BufferedImage[] imgs;
    private ClickListener clicker;
    private GuiButtonStatus status = GuiButtonStatus.IDLE;
    private boolean isActive = true;
    public ToggleGuiButton(String name, Vec2 position, Vec2 scale, List<BufferedImage> imgs, ClickListener clicker) {
        super(name, position, scale);
        this.position = position;
        this.scale = scale;
        this.clicker = clicker;
        this.imgs = imgs.toArray(new BufferedImage[0]);
        init();
    }

    public void init(){}
    
    @Override 
    public void update() {
        if(hovering){
            if(Game.MI.isHeld(MouseEvent.BUTTON1)){
                status = PRESSED;
             
            }else if(Game.MI.onRelease(MouseEvent.BUTTON1)){
                if(isActive){
                    isActive = false;
                }else{
                    isActive = true;
                }
            }
            else{
                status = HOVERING;
            }
        }else{
            status = IDLE;
        }
        System.out.println(isActive);
  
        
    }
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(bounds);

        if(isActive){
            switch (status) {
                case IDLE:
                    g.drawImage(imgs[0], (int)position.x, (int)position.y,(int)scale.x, (int)scale.y, null);
                    break;
                case HOVERING:
                    g.drawImage(imgs[1], (int)position.x, (int)position.y,(int)scale.x, (int)scale.y, null);
                    break;
                case PRESSED:
                    g.drawImage(imgs[2], (int)position.x, (int)position.y,(int)scale.x, (int)scale.y, null);
                    break;
                default:
                    break;
            }
        }else{
            switch (status) {
                case IDLE:
                    g.drawImage(imgs[3], (int)position.x, (int)position.y,(int)scale.x, (int)scale.y, null);
                    break;
                case HOVERING:
                    g.drawImage(imgs[4], (int)position.x, (int)position.y,(int)scale.x, (int)scale.y, null);
                    break;
                case PRESSED:
                    g.drawImage(imgs[5], (int)position.x, (int)position.y,(int)scale.x, (int)scale.y, null);
                    break;
                default:
                    break;
            }
        }

    }
    @Override
    public void onClick() {
        if(clicker != null){
            clicker.onClick();
        }
    }
    
}
