package input.Mouse;


public class Mouse{

    private final int btnCode;
    private MouseState state;
    private MouseState queuedState;

    public Mouse(int btn, MouseState state)
    {
        this.btnCode = btn;
        this.queuedState = state;
    }

    public void setState(){this.state = this.queuedState;}

    public void queueState(MouseState state){this.queuedState = state;}

    public int getBtnCode(){return btnCode;}

    public MouseState getState(){return state;}

}
