package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import input.Controller;
import inputImage.Ani;
import simplePhysic.Area;
import simplePhysic.RigidBody;
@SuppressWarnings("unused")
public class Player extends RigidBody implements Controller{

    private boolean Left, Right, Up, Down;
    private Ani animation = new Ani();
    private int x,y;
    
    public Player(Area hitbox) {
        super(hitbox);
    }

    public void update(){
        super.update();
        move();
    }
    public void changeX(int value){
        this.x += value;
    }

    public void changeY(int value){
        this.y += value;
    }

    public void draw(Graphics g, BufferedImage[] aniLeft, BufferedImage[] aniRight ){
        
        if(!Left && ! Right){
            g.drawImage(aniLeft[animation.aniIndex()], 100+x, 100+y, null);
        }
        if(Left){
            g.drawImage(aniLeft[animation.aniIndex()], 100+x, 100+y, null);
        }
        if(Right){
            
            g.drawImage(aniRight[animation.aniIndex()], 100+x, 100+y, null);

        }
    
    
    
    }





    private void move(){

        hitbox.x += velX;
        hitbox.y += velY;
        
        if(Left) velX -= _acc;
        else if(Right) velX += _acc;
        else if(!Left && !Right){
            if(velX > 0)
                if(velX - _dcc <= 0) velX = 0;
                else velX -= _dcc;
            else if(velX < 0) 
                if(velX + _dcc >= 0) velX = 0;
                else velX += _dcc;
            velX = 0;
            
        }
        
        if(Up) velY -= _acc;
        else if(Down) velY += _acc;
        else if(!Up && !Down){
            if(velY > 0) velY -= _dcc;
            else if(velY < 0) velY += _dcc;
            velY = 0;
        }
        ObjectCollision();
        AreasCollision();
        velX = clamp(velX, -maxSpeed, maxSpeed);
        velY = clamp(velY, -maxSpeed, maxSpeed);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                Up = true;
                changeY(-5);
                break;
            case KeyEvent.VK_LEFT: 
            case KeyEvent.VK_A:
                Left = true; 
                changeX(-5);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                Down = true; 
                changeY(5);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                Right = true; 
                changeX(5);
                break;
            default: break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                Up = false; break;
            case KeyEvent.VK_LEFT: 
            case KeyEvent.VK_A:
                Left = false; break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                Down = false; break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                Right = false; break;
            default: break;
        }  
    }
    
}
