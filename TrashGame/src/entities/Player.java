package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import input.Controller;
import main.Game;
import simplePhysics.Area;
import simplePhysics.RigidBody;
@SuppressWarnings("unused")
public class Player extends RigidBody implements Controller{

    protected float _acc = 0.25f*Game.SCALE, _dcc = 0.25f*Game.SCALE;
    private boolean Left, Right, Up, Down;
    private float jumpForce = 2.5f*Game.SCALE;
    private float maxSpeed = 0.75f*Game.SCALE;

    public Player(Area hitbox) {
        super(hitbox);
    }

    public void update(){
        move();
        super.update();
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        // g2d.draw((int)hitbox.x, (int)hitbox.y, (int)100, (int)100);
        
        g2d.setColor(Color.white);
        g2d.fill(this.hitbox);

        g2d.setColor(Color.red);
        g2d.draw(getBoundsX());
        
        g2d.setColor(Color.blue);
        g2d.draw(getBoundsY());
        

    }
    @Override
    protected void move(){
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

        objectCollision();

        velX = clamp(velX, -maxSpeed, maxSpeed);
        velY = clamp(velY, -maxSpeed, maxSpeed);

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
