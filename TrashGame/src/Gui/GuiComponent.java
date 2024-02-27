package gui;

import java.awt.Graphics;
import java.awt.Rectangle;

import utils.Vec2;

public abstract class GuiComponent {
    protected String name;
    protected boolean hovering;
    protected Vec2 position;
    protected Vec2 scale;


    protected Rectangle bounds;
    
    public GuiComponent(String name, Vec2 position, Vec2 scale){
        this.name = name;
        this.position =position;
        this.scale = scale;

        this.bounds = new Rectangle((int)position.x, (int)position.y, (int)scale.x, (int)scale.y);
    }

    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void onClick();
}
