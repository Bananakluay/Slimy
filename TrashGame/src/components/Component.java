package components;

import java.awt.Graphics;

import entity.Entity;
import physics.Collision;

public abstract class Component {
    public int uid = -1;
    private static int ID_COUNTER = 0;
    public Entity entity = null;
    
    public void ready(){};
    public void update(){};
    public void draw(Graphics g){};
    public void onCollision(Collision collision){}
    public void onDestroy(){};
    public void generateId()
    {
        if (this.uid == -1)
            this.uid = ID_COUNTER++;
    }
    public int getID(){
        return uid;
    }
}
