package simplePhysic;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import gameData.Data.ID;
public class Area extends Rectangle2D.Float{
    
    protected boolean isActive = false;
    protected boolean isMovable = false;
    public ID id = ID.AREA;
    public Area(float x, float y, int width, int height){
        super(x, y, width, height);
    }

    public void draw(Graphics g){
        g.drawRect((int)x, (int)y, (int)width, (int)height);
    }
    public ID getId() {return id;}

    public Area getHitbox(){
        return this;
    }
}
