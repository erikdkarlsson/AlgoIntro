
public class Rectangle
{
    private int length = 1;
    private int width = 1;
    
    public void setLength(int l)
    {
        if (l < 0 || l >= 20) throw new IllegalArgumentException("Längden ska vara mellan (0-20)");
        this.length = l;
        
    }
    public void setWidth(int w)
    {
        if (w < 0 || w >= 20) throw new IllegalArgumentException("Bredden ska vara mellan (0-20)");
        this.width = w;
    }
    
    public int getWidth()
    {
        return width;
    }
    public int getLength()
    {
        return length;
    }
    public int circumference()
    {
        return 2*this.length+2*this.width;
    }
    public int area()
    {
        return this.length*this.width;
    }
}
