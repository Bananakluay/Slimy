package entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import components.Component;
import dataStructure.Transform;
import utils.Vec2;

public class Entity {

    private String name;
    public int uid = -1;
    protected EntityType type = EntityType.NONE;
    public static int ID_COUNTER = 0;
    protected Transform transform;

    private List<Component> components;
    
    public int zIndex = 0;
    
    public Entity(String name, Transform transform, int zIndex){
        this.name = name;
        this.transform = transform;
        this.components = new ArrayList<>();
        this.zIndex = zIndex;
        generateId();
    }
    
    public void update(){
        for(Component c : components)
            c.update();
    }
    public void draw(Graphics g){
        for(Component c : components)
            c.draw(g);
    }
    
    public void ready(){
        for(Component c : components)
            c.ready();
    }

    public void onDestroy(){
        for(Component c : components)
            c.onDestroy();
    };

    
    
    public void addComponent(Component c){
        this.components.add(c);
        c.entity = this;
        c.generateId();
    }

    public <T extends Component> void removeCompnent(Class<T> componentClass){
        for(int i=0;i<components.size();i++){
            Component c = components.get(i);
            if(componentClass.isAssignableFrom(c.getClass())){
                components.remove(i);
                return;
            }
        }
    }
    
    public <T extends Component> boolean hasComponent(Class<T> componentClass){
        for(Component c : components)
            if(componentClass.isAssignableFrom(c.getClass()))
                return true;
        return false;
    }

    public <T extends Component> T getComponent(Class<T> componentClass){
        for(Component c : components){
            if(componentClass.isAssignableFrom(c.getClass())){
                try{
                    return componentClass.cast(c); 
                }catch (ClassCastException e){
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }
        return null;
    }

    public void generateId()
    {
        if (this.uid == -1)
            this.uid = ID_COUNTER++;
    }
    
    public int getId(){return uid;}

    public int getZindex(){return zIndex;}

    public List<Component> getAllComponents() {return components;}

    public String getName() {return name;}

    public Transform getTransform() {return transform;}

    public Vec2 getPosition(){return transform.position;}
    
    public Vec2 getScale(){return transform.scale;}

    public void setType(EntityType type){this.type = type;}
    public EntityType getType(){return this.type;}


    
}
