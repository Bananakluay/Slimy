package input.Mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class MouseManager implements MouseListener, MouseMotionListener
{

    private static Mouse mouses[] = new Mouse[5];

    public static int xMouse = 0, yMouse = 0;
    public static int xMove, yMove;
    public static int xDragged, yDragged;

    public MouseManager(){
        init();
    }
    private void init() {
        for(int i=0;i<mouses.length;i++){
            mouses[i] = new Mouse(i, MouseState.IDLE);
        }
    }
    public void update()
    {
        for (Mouse mBtn : mouses)
        {
            mBtn.setState();

            if (mBtn.getState() == MouseState.RELEASED)
                mBtn.queueState(MouseState.IDLE);
            else if (mBtn.getState() == MouseState.PRESSED)
                mBtn.queueState(MouseState.HELD);
        }
    }

    public boolean isHeld(int btn){
        for (Mouse mBtn : mouses){
            if (mBtn.getBtnCode() == btn && mBtn.getState() == MouseState.HELD || mBtn.getState() == MouseState.PRESSED)
                return true;
        }

        return false;
    }

    public boolean onPress(int btn){
        for (Mouse mBtn : mouses){
            if (mBtn.getBtnCode() == btn && mBtn.getState() == MouseState.PRESSED)
                return true;
        }

        return false;
    }

    public boolean onRelease(int btn){
        for (Mouse mBtn : mouses){
            if (mBtn.getBtnCode() == btn && mBtn.getState() == MouseState.RELEASED)
                return true;
        }

        return false;
    }

    public int getMouseX(){
        return xMouse;
    }

    public int getMouseY(){
        return yMouse;
    }

    public int getMouseMoveX(){
        return xMove;
    }

    public int getMouseMoveY(){
        return yMove;
    }

    public int getMouseDraggedX(){
        return xDragged;
    }

    public int getMouseDraggedY(){
        return yDragged;
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e){
        for (Mouse mBtn : mouses){
            if (mBtn.getState() == MouseState.HELD)
                return;

            if (mBtn.getBtnCode() == e.getButton() && mBtn.getState() == MouseState.IDLE){
                mBtn.queueState(MouseState.PRESSED);
                return;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        for (Mouse mBtn : mouses)
        {
            if (mBtn.getBtnCode() == e.getButton())
            {
                mBtn.queueState(MouseState.RELEASED);
            }
        }

        xDragged = 0;
        yDragged = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void mouseDragged(MouseEvent e){
        xDragged = -(xMouse - e.getX());
        yDragged = yMouse - e.getY();
        xMove = -(xMouse - e.getX());
        yMove = yMouse - e.getY();
        xMouse = e.getX();
        yMouse = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e){
        xMove = -(xMouse - e.getX());
        yMove = yMouse - e.getY();
        xMouse = e.getX();
        yMouse = e.getY();
    }

}
