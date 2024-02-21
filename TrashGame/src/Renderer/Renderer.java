package Renderer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Entity;

public class Renderer {
    Map<Integer, List<Entity>> layers = new HashMap<>();
 

    public void render(Graphics g){
        int lozIndex = Integer.MAX_VALUE; //0
        int hizIndex = Integer.MIN_VALUE; //-99999999
        // 0 5 3 2 11
        for(Integer i : layers.keySet()){
            if(i<lozIndex) 
                lozIndex = i;
            if(i>hizIndex) 
                hizIndex = i;
        }
        //lo = 0 hi = 11
        int currentZIndex;
        for(currentZIndex = lozIndex ; currentZIndex<=hizIndex;currentZIndex++){
            if(layers.get(currentZIndex) == null){
                currentZIndex++;
                continue;
            }
            for(Entity entity : layers.get(currentZIndex)){
                entity.draw(g);
            }
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

    public void remove(String name, int zIndex) {
		for (int i = 0; i < layers.get(zIndex).size(); i++) {
			Entity entity = layers.get(zIndex).get(i);
			if (entity.getName().equals(name)) {
				layers.get(zIndex).remove(i);
				break; 
			}
		}
	}
}
