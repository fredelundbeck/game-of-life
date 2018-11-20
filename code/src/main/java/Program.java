import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public final class Program {
    
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    private static Grid grid;
    private static JFrame frame;
    private static Display display;

    static Timer timer;
    
    public static void main(String[] args) 
    {
        initGUI();  
    }

    private static void initGUI()
    {
        timer = new Timer(); 
        grid = new Grid(200);
        grid.randomizeCells();

        javax.swing.SwingUtilities.invokeLater(() -> {

            frame = new JFrame("Game of life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
            frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
            frame.setResizable(false);
            frame.setVisible(true);

            display = new Display(grid);
            frame.add(display);

            timer.schedule(new TimerTask() {
                public void run() {
                    grid.updateGrid();
                    display.repaint();
                }
            }, 0, 50);
        });
    }
}
