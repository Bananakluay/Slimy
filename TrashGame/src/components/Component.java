package components;

import java.awt.Graphics;

import entity.Entity;
import physics.Collision;

public abstract class Component {

    public Entity entity = null;

    public void ready(){};
    public void update(){};
    public void draw(Graphics g){};
    public void onCollision(Collision collision){}
    public void onDestroy(){};
}
