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
    protected boolean Left, Right, Up, Down, jump = false;

    private float accelerate = 0.33f * Game.SCALE;
    private long pressStartTime = 0;
    private boolean isTiming = false;
    private boolean isCoyote = false;
    private long Start_count_coyote = 0;
    private boolean Coyote_jump = false;
    private float coyote_scale = 0.37f * Game.SCALE;
    private float Hold_scale_L = 0.000383f * Game.SCALE;
    private float Hold_scale_H = 0.00033f * Game.SCALE;

    private float minVelX = -0.7f * Game.SCALE;
    private float maxVelX = 0.7f * Game.SCALE;
    private float jumpacc = 0.37f * Game.SCALE;
    private float jumpForce = 1.5f * Game.SCALE;
    private float downForce = 1.6f * Game.SCALE;
    private int count = 0;
    private boolean isCurrentPlayer;

    private Color colorPlayer = Color.white; // for debug

    public Player(Area hitbox) {
        super(hitbox);
    }

    @Override
    public void update() {
        // velx = 0
        if (isCurrentPlayer)
            updateVelocity();
        // velX = -12
        super.update();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

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

    private void updateVelocity() {
        checkfloor();
        movement();
        jumping();
        Timingjump();
        velX = clamp(velX, minVelX, maxVelX);
        velY = clamp(velY, -jumpForce, downForce);
    }

    private void movement() {
        if (Left && Right || !Left && !Right)
            velX *= 0.5;
        else if (Left && !Right)
            velX -= accelerate;
        else if (Right && !Left)
            velX += accelerate;

        if (velX > 0 && velX < 0.75)
            velX = 0;
        if (velX > -0.75 && velX < 0)
            velX = 0;
    }

    private void jumping() {
        Coyote();
        if (Up && isOnFloor) {
            velY -= jumpacc;
            jump = true;
        }
        if (Coyote_jump && Up) {
            velY -= jumpForce * coyote_scale;
            jump = true;
            Coyote_jump = false;
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

    public void Timingjump() {
        if (isTiming) {
            long elapsedTime = System.currentTimeMillis() - pressStartTime;
            // Do something with elapsedTime:
            // System.out.println("Time since key press: " + elapsedTime + " milliseconds");
            count++;
            if (jump = true) {
                if (count == 2) {
                    if (elapsedTime < 50) {
                        velY -= jumpacc * (50 * Hold_scale_L);
                        count = 0;
                    } else {
                        velY -= jumpacc * (elapsedTime * Hold_scale_H);
                        count = 0;
                    }
                }
            }
            if (elapsedTime > 205)
                isTiming = false;
        }
    }

    public void Coyote() {
        if (isCoyote && !jump) {
            long coyoteTime = System.currentTimeMillis() - Start_count_coyote;
            // Do something with elapsedTime:
            // System.out.println("Time since key press: " + elapsedTime + " milliseconds");

            if (coyoteTime < 100 && Up) {
                Coyote_jump = true;

            }
        }
    }

    private void checkfloor() {
        if (!Up && !isOnFloor && !isCoyote && !jump) {
            isCoyote = true;
            Start_count_coyote = System.currentTimeMillis();
        }
        if (isOnFloor) {
            isCoyote = false;
            jump = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                Up = true;
                Down = false; // for smooth switch direction
                pressStartTime = System.currentTimeMillis();
                isTiming = true;
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
                isTiming = false;
                pressStartTime = 0;
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
