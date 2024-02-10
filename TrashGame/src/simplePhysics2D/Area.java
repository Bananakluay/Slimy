package simplePhysics2D;

import java.awt.geom.Rectangle2D;
public class Area extends Rectangle2D.Float{
    
    protected boolean isActive = false;

    public Area(float x, float y, int width, int height){
        super(x, y, width, height);
    }

}
