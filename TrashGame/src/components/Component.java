package components;

import java.awt.Graphics;

import entity.Entity;

public abstract class Component {

    public Entity entity = null;

    public void ready(){};
    public void update(){System.out.println(this.getClass() + "is updating");};
    public void draw(Graphics g){};
    //TODO public void onCollision(Collision collision){}
    public void onDestroy(){};
}
