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
public class Player extends RigidBody implements Controller {

    // protected float _acc = 0.25f*Game.SCALE, _dcc = 0.25f*Game.SCALE;
    protected boolean Left, Right, Up, Down, isJumping = false;

    private float accelerate = 0.33f * Game.SCALE;
    private float deceleration = 0.00125f * Game.SCALE;   //0.03125f

    private float maxVelX = 0.3f * Game.SCALE;
    private float minVelX = -0.3f * Game.SCALE;
    
    private float jumpForce = 0.5f*Game.SCALE;
    private float downForce = 0.5f * Game.SCALE;
    private boolean isCurrentPlayer;

    private float jumpDuration = 0f;
    private float maxJumpDuration = 30f;
    private Color colorPlayer = Color.white; // for debug

    public Player(Area hitbox) {
        super(hitbox);
    }

    @Override
    public void update() {
        if (isCurrentPlayer)
            updateVelocity();
        super.update();
        System.out.println(velX);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // g2d.draw((int)hitbox.x, (int)hitbox.y, (int)100, (int)100);

        g2d.setColor(colorPlayer);
        g2d.fill(this.hitbox);

        g2d.setColor(Color.red);
        g2d.draw(getBoundsX());

        // g2d.setColor(Color.blue);
        // g2d.draw(getBoundsY());

        g2d.setColor(Color.red);
        g2d.draw(getFloorHitbox());

    }

    private void updateVelocity() {
        movement();
        jumping();
        velX = clamp(velX, minVelX, maxVelX);
        velY = clamp(velY, -jumpForce, downForce);
    }

    private void movement() {
        if (Left && Right || !Left && !Right)
            velX *= deceleration;
            // velX *= deceleration;
        else if (Left && !Right)
            velX -= accelerate;
        else if (Right && !Left)
            velX += accelerate;

        // if (velX > 0 && velX < 0.75)
        //     velX = 0;
        // if (velX > -0.75 && velX < 0)
        //     velX = 0;
    }

    private void jumping() {
        if (Up && isOnFloor) {
            velY -= jumpForce; // Initial jump impulse on key press
            jumpDuration = 0; // Reset jump duration tracker
            isJumping = true; // Set jumping flag
        } else if (isJumping) {
            jumpDuration++; // Track jump duration
            if (jumpDuration < maxJumpDuration && Up) {
                // Apply additional upward force while holding jump key
                velY -= jumpForce * 0.5f; // Adjust intensity as needed
            }
        }
    }

    public void setColor(Color color) {
        colorPlayer = color;
    }

    public void reset() {
        Left = false;
        Right = false;
        Up = false;
        Down = false;
        isCurrentPlayer = false;
    }

    public void setIsCurrentPlayer() {
        isCurrentPlayer = true;
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
                Up = false; // for smooth switch direction
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                Right = true;
                Left = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                Up = false;
                isJumping = false;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                Left = false;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                Down = false;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                Right = false;
                break;
            default:
                break;
        }
    }

}
