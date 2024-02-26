package Gui;

import java.util.List;

import main.Game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class GuiLayer {

    private final List<GuiComponent> guiComponents = new ArrayList<>();

    public GuiLayer(){};


    public void update(){
        //check hovering and update
        for(GuiComponent c : getGuiComponents()){
            c.hovering = c.bounds.contains(Game.MI.getMouseX(), Game.MI.getMouseY());
            c.update();
        }


        try{
            for(GuiComponent c : getGuiComponents())
                if(c.hovering && Game.MI.onRelease(MouseEvent.BUTTON1))
                    c.onClick();
        }catch(ConcurrentModificationException ingnore){}
    }
    
    public void render(Graphics g){
            try {
                for(GuiComponent c : getGuiComponents())
                    c.render(g);
            } catch (ConcurrentModificationException ingore) {}
    }


    public void addGuiComponent(GuiComponent c){
        this.guiComponents.add(c);
    }

    public void removeGuiComponent(GuiComponent c){
        this.guiComponents.remove(c);
    }

    public List<GuiComponent> getGuiComponents(){
        return this.guiComponents;
    }

    public void clear(){
        this.guiComponents.clear();
    }

}
