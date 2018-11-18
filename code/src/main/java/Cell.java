public class Cell 
{
    private boolean status = false;

    public Cell() 
    {
        
    }

    public Cell(boolean status)
    {
        this.status = status;
    }

    public boolean getStatus() 
    {
        return status;
    }

    public void setStatus(boolean status) 
    {
        this.status = status;
    }
}