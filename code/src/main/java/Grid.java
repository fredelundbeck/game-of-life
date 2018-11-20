import java.util.Random;

public class Grid 
{
    private int size;
    private Cell[][] cells;  

    public Grid(int size) 
    {
        this.size = size;
        initGrid();
    }

    public int getSize()
    {
        return size;
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public void updateGrid()
    {
        Cell[][] updatedGrid = new Cell[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                updatedGrid[x][y] = new Cell(decideStatus(x, y));
            }
        }
        cells = updatedGrid;
    }

    private int repCord(int n)
    {
        if (n >= size) 
        {
            return 0;
        }
        if (n < 0) 
        {
            return size-1;
        }
        return n;
    }

    /**
     * This method checks the {@code cells[x][y]} cell and counts it's neighbours and based on Conway's game of life rules
     * , then decides whether or not that cell should live or die.
     * @param x coordinate of current cell to check neighbours from
     * @param y coordinate of current cell to check neighbours from
     * @return returns a {@code boolean} value on whether or not current cell should live or die
     */
    private boolean decideStatus(int x, int y)
    {
        boolean currStatus = cells[x][y].getStatus();
        int aliveNeighbours = 0;
        //Check cell neighbours, add 1 to total aliveNeighbours if alive
        aliveNeighbours += cells[repCord(x-1)][y].getStatus() == true ? 1 : 0;   
        aliveNeighbours += cells[repCord(x+1)][y].getStatus() == true ? 1 : 0; 
        aliveNeighbours += cells[x][repCord(y-1)].getStatus() == true ? 1 : 0;
        aliveNeighbours += cells[x][repCord(y+1)].getStatus() == true ? 1 : 0;   
        aliveNeighbours += cells[repCord(x-1)][repCord(y-1)].getStatus() == true ? 1 : 0;
        aliveNeighbours += cells[repCord(x+1)][repCord(y-1)].getStatus() == true ? 1 : 0;
        aliveNeighbours += cells[repCord(x-1)][repCord(y+1)].getStatus() == true ? 1 : 0;
        aliveNeighbours += cells[repCord(x+1)][repCord(y+1)].getStatus() == true ? 1 : 0;
        
        //decide based on Conways game of life rules: https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Rules
        if (currStatus == true) 
        {
            if (aliveNeighbours < 2) 
            {
                return false;
            }
            if (aliveNeighbours < 4) 
            {
                return true;
            } 
            return false;
        }
        else
        {
            if (aliveNeighbours == 3) 
            {
                return true;    
            }
            return false;
        }

    }

    /**
     * This method is used for randomizing whether or not the cells are alive, before the simulation begins.
     */
    public void randomizeCells()
    {
        Random rnd = new Random();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cells[x][y].setStatus(rnd.nextInt(2)+1 == 1 ? true : false);
            }
        }   
    }
    
    /**
     * Declares and initializes the {@code cells[][]}. The size of both dimensions are equal to {@code size}
     */
    private void initGrid()
    {
        cells = new Cell[size][size];  

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                cells[x][y] = new Cell();
            }
        }       
    }
}