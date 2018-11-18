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

    private boolean decideStatus(int x, int y)
    {
        boolean currStatus = cells[x][y].getStatus();
        int aliveNeighbours = 0;

        //Check if surrounding neighbour cells are alive
        //directional neighbour cells
        if (x-1 >= 0) 
        {
            aliveNeighbours += cells[x-1][y].getStatus() == true ? 1 : 0;   
        }
        if (x+1 < size) 
        {
            aliveNeighbours += cells[x+1][y].getStatus() == true ? 1 : 0; 
        }
        if (y-1 >= 0) 
        {
            aliveNeighbours += cells[x][y-1].getStatus() == true ? 1 : 0;
        }
        if (y+1 < size) 
        {
            aliveNeighbours += cells[x][y+1].getStatus() == true ? 1 : 0;   
        }
        //diagonal neighbour cells
        if (x-1 >= 0 && y-1 >= 0) 
        {
            aliveNeighbours += cells[x-1][y-1].getStatus() == true ? 1 : 0;
        }
        if (x+1 < size && y-1 >= 0) 
        {
            aliveNeighbours += cells[x+1][y-1].getStatus() == true ? 1 : 0;
        }
        if (x-1 >= 0 && y+1 < size) 
        {
            aliveNeighbours += cells[x-1][y+1].getStatus() == true ? 1 : 0;
        }
        if (x+1 < size && y+1 < size) 
        {
            aliveNeighbours += cells[x+1][y+1].getStatus() == true ? 1 : 0;
        }

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

    public void randomizeCells()
    {
        Random rnd = new Random();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cells[x][y].setStatus(rnd.nextInt(2)+1 == 1 ? true : false);
            }
        }   
    }
    
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