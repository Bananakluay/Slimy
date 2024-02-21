package input.Keyboard;

public class Key {
    private final int keyCode;
;
    private KeyState state = KeyState.IDLE;
    private KeyState queuedState = KeyState.PRESSED;

    public Key(int keyCode, KeyState status){
        this.keyCode = keyCode;
        this.queuedState = state;
    }

    public void setState(){
        this.state = this.queuedState;
    }

    public void queueState(KeyState state){
        this.queuedState = state;
    }

    public int getKeyCode(){return this.keyCode;}


    public KeyState getState(){return this.state;}
}
