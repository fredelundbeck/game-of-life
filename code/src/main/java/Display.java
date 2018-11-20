import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel 
{
    private final int FADE_AMOUNT = 45;

    private Grid grid;
    private float gridSizeX;
    private float gridSizeY;

    private int[][] colors;
    private boolean[][] wasTouched;
    private boolean colorDeadCells = true;

    public Display(Grid grid) 
    {
        this.grid = grid;
        colors = new int[grid.getSize()][grid.getSize()];
        wasTouched = new boolean[grid.getSize()][grid.getSize()];
        setBackground(Color.WHITE);
        initColors();
    }

    public void colorDeadCells(boolean colorDeadCells)
    {
        this.colorDeadCells = colorDeadCells;
    }

    private void initColors()
    {
        for (int x = 0; x < grid.getSize(); x++) {
            for (int y = 0; y < grid.getSize(); y++) {
                colors[x][y] = 255; 
            }           
        }
    }

    private void drawGrid(Graphics g)
    {
        gridSizeX = (float)this.getSize().width / grid.getSize();
        gridSizeY = ((float)this.getSize().height / grid.getSize());

        Cell[][] cells = grid.getCells();

        for (int x = 0; x < grid.getSize(); x++) {
            for (int y = 0; y < grid.getSize(); y++) {
                
                if (cells[x][y].getStatus()) 
                {
                    g.setColor(Color.BLACK);
                    g.fillRect((int)(x * gridSizeX), (int)(y * gridSizeY), (int)gridSizeX+1, (int)gridSizeY+1);

                    if (colorDeadCells) 
                    {
                        wasTouched[x][y] = true;
                        colors[x][y] = 45;
                    }
                }
                else 
                {
                    if (colors[x][y] < 255 && colorDeadCells) 
                    {
                        int red = 0;
                        int green = 0;
                        int blue = 0;

                        colors[x][y] = colors[x][y] + FADE_AMOUNT < 255 ? (colors[x][y] + FADE_AMOUNT) : 255;

                        red = colors[x][y] / 4;
                        green = colors[x][y];
                        blue = colors[x][y] / 2; 

                        g.setColor(new Color(red, green, blue));
                        g.fillRect((int)(x * gridSizeX), (int)(y * gridSizeY), (int)gridSizeX+1, (int)gridSizeY+1);
                    }
                }
            }
        }
        g.dispose();
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawGrid(g);
    }
}