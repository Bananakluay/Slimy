package main;


import javax.swing.JFrame;

public class Window extends JFrame{
    private static Window window = null;
    private Window(GamePanel gamePanel){
        this.add(gamePanel);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setFocusable(true);

    }

    public static Window get(GamePanel gamePanel){
        if(window == null)
            window = new Window(gamePanel);
        return window;
    }
}
