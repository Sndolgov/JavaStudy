package Arcanoid;

/**
 * Created by SergeyND on 08.02.2017.
 */
public abstract class BasicObject
{
    protected double x, y, length, height;
    int [][] matrix;

    public BasicObject(double y, double x, int height, int length)
    {
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
        matrix = new int [height][length];
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getLength()
    {
        return length;
    }

    public double getHeight()
    {
        return height;
    }

    public int[][] getMatrix()
    {
        return matrix;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setMatrix(int[][] matrix)
    {
        this.matrix = matrix;
    }

    public void draw (Field field, char ch){
        for (int i=(int)Math.round(y); i<Math.round(y+height); i++){
            for (int n = (int)Math.round(x); n<Math.round(x+length); n++){
                field.getMatrix()[i][n]=ch;
            }
        }
    }
    public abstract void move();


}
