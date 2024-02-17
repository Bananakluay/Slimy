package Scene;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Entity;

public class Renderer {
    Map<Integer, List<Entity>> entities = new HashMap<>();
    public Renderer(){

    }

    public void render(Graphics g){
        int lozIndex = Integer.MAX_VALUE;
        int hizIndex = Integer.MIN_VALUE;
        for(Integer i : entities.keySet()){
            if(i<lozIndex) lozIndex = i;
            if(i>hizIndex) hizIndex = i;
        }
        int currentZIndex;
        for(currentZIndex = lozIndex ; currentZIndex<=hizIndex;currentZIndex++){
            if(entities.get(currentZIndex) == null){
                currentZIndex++;
                continue;
            }
            for(Entity entity : entities.get(currentZIndex)){
                entity.draw(g);
            }
        }

    }

    public void submit(Entity entity){
        entities.computeIfAbsent(entity.zIndex, k-> new ArrayList<>());
        entities.get(entity.zIndex).add(entity);
    }
}
