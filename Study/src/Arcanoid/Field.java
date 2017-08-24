package Arcanoid;

/**
 * Created by SergeyND on 08.02.2017.
 */
public class Field
{
    private int length, height;
    private char [][] matrix;

    public Field(int height, int length)
    {
        this.length = length;
        this.height = height;
        matrix = new char [height][length];
    }

    public int getLength()
    {
        return length;
    }

    public int getHeight()
    {
        return height;
    }

    public char[][] getMatrix()
    {
        return matrix;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setMatrix(char[][] matrix)
    {
        this.matrix = matrix;
    }
    public void printField(){
        for (int i = 0; i<height; i++){
            for (int n = 0; n<length; n++)
            {
                System.out.print(matrix[i][n]);
            }
            System.out.println();

        }
    }

    public void drawBoards(){
        for (int i = 0; i<height; i++){
            for (int n = 0; n<length; n++){
                if (i==0 || i==height-1)
                    matrix[i][n]='-';
                else if (n==0||n==length-1)
                    matrix[i][n]='|';
                else matrix[i][n]='.';
            }
        }
    }
}
