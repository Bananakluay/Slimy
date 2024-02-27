package Interaction;


import entity.Entity;

public interface Behavior {
    void activateOn(Entity entity);
    void activateOff();

    void activate(Entity entity); //test for arrow
}
