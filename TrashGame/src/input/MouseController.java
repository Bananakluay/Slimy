package input;

import java.awt.event.MouseEvent;

public interface MouseController {

    public void mouseClicked(MouseEvent e);
    public void mouseDragged(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);

    public void mouseEntered(MouseEvent e);
    public void mouseExited(MouseEvent e);
}
