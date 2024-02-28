package renderer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Entity;

public class Renderer {

    public Map<Integer, List<Entity>> layers;
    public Map<Integer, List<Entity>> entitiesToRemove = new HashMap<>();
    public Map<Integer, List<Entity>> entitiesToAdd = new HashMap<>();

    public Renderer() {
        layers = new HashMap<>();
    }

    public void render(Graphics g) {
        int lozIndex = Integer.MAX_VALUE;
        int hizIndex = Integer.MIN_VALUE;

        for (Integer i : layers.keySet()) {
            if (i < lozIndex)
                lozIndex = i;
            if (i > hizIndex)
                hizIndex = i;
        }

        int zIndex = lozIndex;
        while (zIndex <= hizIndex) {
            List<Entity> entitiesToRender = layers.get(zIndex);
            if (entitiesToRender != null) {
                for (Entity entity : entitiesToRender) {
                    entity.draw(g);
                }
            }
            zIndex++;
        }

        processQueuedChanges();

    }

    public void submit(Entity entity) {
        layers.computeIfAbsent(entity.zIndex, k -> new ArrayList<>());
        entitiesToAdd.computeIfAbsent(entity.zIndex, k -> new ArrayList<>());

        entitiesToAdd.get(entity.zIndex).add(entity);
    }

    public void submitAll(List<Entity> entities) {
        for (Entity entity : entities) {
            submit(entity);
        }
    }

    public void remove(Entity entity, int zIndex) {
        entitiesToRemove.computeIfAbsent(zIndex, k -> new ArrayList<>());
        entitiesToRemove.get(zIndex).add(entity);
    }

    public int size(int zIndex) {
        return layers.get(zIndex) != null ? layers.get(zIndex).size() : 0;
    }

    public void clear() {
        layers.clear();
        entitiesToAdd.clear();
        entitiesToRemove.clear();
        System.out.println(layers.size() + " " + entitiesToAdd.size() + " " + entitiesToRemove.size());
    }

    public void processQueuedChanges() {

        for (Integer zIndex : entitiesToAdd.keySet()) {
            if (!layers.containsKey(zIndex)) {
                layers.put(zIndex, new ArrayList<>());
            }
            layers.get(zIndex).addAll(entitiesToAdd.get(zIndex));
        }
        entitiesToAdd.clear();
        for (Integer zIndex : entitiesToRemove.keySet()) {
            if (layers.containsKey(zIndex)) {
                layers.get(zIndex).removeAll(entitiesToRemove.get(zIndex));
            }
        }
        entitiesToRemove.clear();
    }

}
