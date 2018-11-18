import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel 
{
    private Grid grid;
    private float gridSizeX;
    private float gridSizeY;

    public Display(Grid grid) 
    {
        this.grid = grid;
        
    }

    private void drawGrid(Graphics g)
    {
        gridSizeX = (float)(this.getSize().width) / grid.getSize();
        gridSizeY = ((float)this.getSize().height / grid.getSize()) + 1;

        Cell[][] cells = grid.getCells();

        for (int x = 0; x < grid.getSize(); x++) {
            for (int y = 0; y < grid.getSize(); y++) {
                
                if (cells[x][y].getStatus()) 
                {
                    g.fillRect((int)(x * gridSizeX), (int)(y * gridSizeY), (int)gridSizeX+1, (int)gridSizeY);
                }

            }
        }
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawGrid(g);
    }
}