package input.Keyboard;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class KeyManager implements KeyListener
{
    private static Key keys[] = new Key[128];

    public KeyManager(){
        init();
    }

    public void init(){
        for(int i=0;i<keys.length;i++){
            keys[i] = new Key(i,  KeyState.IDLE);
        }
    }

    public void update(){
        for (Key k : keys){   
            k.setState();

            if (k.getState() == KeyState.RELEASED)
                k.queueState(KeyState.IDLE);
            else if (k.getState() == KeyState.PRESSED)
                k.queueState(KeyState.HELD);
        }
    }

    public boolean isHeld(int key){
        for (Key k : keys){
            if (k.getKeyCode() == key && k.getState() == KeyState.HELD)
                return true;
        }

        return false;
    }



    /**
     * @return true if the specified key
     * associated with the key event is
     * being pressed.
     */
    public boolean onPress(int key){
        for (Key k : keys){
            if (k.getKeyCode() == key && k.getState() == KeyState.PRESSED)
                return true;
        }

        return false;
    }


    /**
     * @return true if the specified key
     * associated with the key event is
     * being released.
     */
    public boolean onRelease(int key){
        for (Key k : keys)
        {
            if (k.getKeyCode() == key && k.getState() == KeyState.RELEASED)
                return true;
        }

        return false;
    }


    // Implemented methods

    // Is invoked once key has gone down, and released
    @Override
    public void keyTyped(KeyEvent e) {}

    // Is invoked while key is held
    @Override
    public void keyPressed(KeyEvent e){
        for (Key k : keys){
            if (k.getState() == KeyState.HELD && k.getKeyCode() == e.getKeyCode())
                return;

            if (k.getKeyCode() == e.getKeyCode() && k.getState() == KeyState.IDLE){
                k.queueState(KeyState.PRESSED);
                return;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        for (Key k : keys){
            if (k.getKeyCode() == e.getKeyCode())
                k.queueState(KeyState.RELEASED);
        }
    }

}
