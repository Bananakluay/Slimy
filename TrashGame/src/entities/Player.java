package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import input.Controller;
import main.Game;
import simplePhysic.Area;
import simplePhysic.RigidBody;
@SuppressWarnings("unused")
public class Player extends RigidBody implements Controller{

    protected float _acc = 0.1f * Game.SCALE, _dcc = 0.1f * Game.SCALE;;
    private boolean Left, Right, Up, Down;
    private float jumpForce = 2.5f*Game.SCALE;
    private float maxSpeed = 0.1f * Game.SCALE;

    public Player(Area hitbox) {
        super(hitbox);
    }

    public void update(){
        super.update();
        move();
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        // g2d.draw((int)hitbox.x, (int)hitbox.y, (int)100, (int)100);
        
        g2d.setColor(Color.red);
        g2d.fill(getBoundsX());
        
        g2d.setColor(Color.blue);
        g2d.fill(getBoundsY());
        
        
        g2d.setColor(Color.white);
        g2d.draw(this.hitbox);

    }
    private void move(){

        
        // if(Left && Right || !Left && !Right) velX *= 0.8;
        // else if(Left && !Right) velX --;
        // else if(Right && !Left) velX ++;   
        // if(velX > 0 && velX < 0.75) velX = 0;
        // if(velX > -0.75 && velX < 0) velX = 0;
        // System.out.println();

        if(Left) velX -= _acc;
        else if(Right) velX += _acc;
        else if(!Left && !Right){
            if(velX > 0)
                    velX -= _dcc;
            else if(velX < 0) 
                    velX += _dcc;
            // if(velX >= -0.1 && velX < 0) velX = 0;
            // if(velX > 0 && velX >= 0.1) velX = 0;
        // }
        // System.out.println(velX);
        objectCollision();
        if(Up && isColliding)
            velY = -6;
        velY += 0.2;
    
        
        hitbox.x += velX;
        hitbox.y += velY;
        
        velX = clamp(velX, -maxSpeed,  maxSpeed);
        // velY = clamp(velY, -jumpForce, 1*Game.SCALE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                Up = true; break;
            case KeyEvent.VK_LEFT: 
            case KeyEvent.VK_A:
                Left = true; break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                Down = true; break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                Right = true; break;
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
