package simplePhysics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import gameData.Data.ID;
public class Area extends Rectangle2D.Float{
    
    

    public ID id = ID.AREA;
    public Area(float x, float y, float width, float height){
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
