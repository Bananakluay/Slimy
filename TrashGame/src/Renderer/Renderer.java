package renderer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Entity;

public class Renderer {

    public Map<Integer, List<Entity>> layers = new HashMap<>();
 
    public void render(Graphics g){
        int lozIndex = Integer.MAX_VALUE; //0
        int hizIndex = Integer.MIN_VALUE; 
        // 0 5 3 2 11
        for(Integer i : layers.keySet()){
            if(i<lozIndex) 
                lozIndex = i;
            if(i>hizIndex) 
                hizIndex = i;
        }
    
        int zIndex = lozIndex;
        while (zIndex <= hizIndex) {
            if(layers.get(zIndex) == null){
                zIndex++;
                continue;
            }
            for(Entity entity : layers.get(zIndex)){
                entity.draw(g);
            }
            zIndex++;
        }

    }

    // add to layer
    public void submit(Entity entity){
        layers.computeIfAbsent(entity.zIndex, k-> new ArrayList<>());
        layers.get(entity.zIndex).add(entity);
    }

    public void submitAll(List<Entity> entities){
        for (Entity entity : entities) {
            submit(entity);
        }
    }

    public void remove(Entity entity, int zIndex) {
		layers.get(zIndex).remove(entity);
	}

    public int size(int zIndex){
        return layers.get(zIndex).size();
    }

    public void clear(){
        layers.clear();
    }

 
}

