package Interaction;


import entity.Entity;

public interface Behavior {
    void activateOn(Entity entity);
    void activateOff();
    void activateOneShot(Entity entity);
}
