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

    private float minVelX = -0.75f*Game.SCALE;
    private float maxVelX = 0.75f*Game.SCALE;

    private float jumpForce = 1.5f*Game.SCALE;
    private float downForce = 1.6f*Game.SCALE;




    private Color colorPlayer = Color.white; // for debug
    public Player(Area hitbox) {
        super(hitbox);
    }

    @Override
    public void update(){
        updateVelocity();
        super.update();
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        // g2d.draw((int)hitbox.x, (int)hitbox.y, (int)100, (int)100);
        
        g2d.setColor(colorPlayer);
        g2d.fill(this.hitbox);

        // g2d.setColor(Color.red);
        // g2d.draw(getBoundsX());
        
        // g2d.setColor(Color.blue);
        // g2d.draw(getBoundsY());
        g2d.setColor(Color.red);
        g2d.draw(getFloorHitbox());

    }

    private void updateVelocity(){  
        
        movement();
        jumping();

        velX = clamp(velX, minVelX, maxVelX);
        velY = clamp(velY, -jumpForce, downForce);
    }
    
    private void movement(){
        if(Left && Right || !Left && !Right)
            velX*= 0.5;
        else if(Left && !Right)
            velX--;
        else if(Right && !Left)
            velX++;

        if(velX > 0 && velX < 0.75)
            velX = 0;
        if(velX > -0.75 && velX < 0)    
            velX = 0;
    }

    private void jumping(){
        if(Up && isOnFloor)
            velY -= jumpForce;
    }

    public void setColor(Color color){
        colorPlayer = color;
    }

    public void reset(){
        Left = false;
        Right = false;
        Up = false;
        Down =false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                Up = true; 
                Down = false; // for smooth switch direction
                break;
            case KeyEvent.VK_LEFT: 
            case KeyEvent.VK_A:
                Left = true; 
                Right = false; // for smooth switch direction
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                Down = true; 
                Up =false; // for smooth switch direction
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                Right = true; 
                Left = false;
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
