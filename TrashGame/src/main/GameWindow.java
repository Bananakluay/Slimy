package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
    public GameWindow(GamePanel gamePanel){
        add(gamePanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        
    }
}
