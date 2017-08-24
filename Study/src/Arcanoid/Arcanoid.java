package Arcanoid;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SergeyND on 08.02.2017.
 */
public class Arcanoid
{

    private Stand stand;
    private Ball ball;
    private List<Bricks> bricks = new ArrayList<>();
    static Arcanoid game;
    private int length, height;
    private char [][] matrix;
    private Field field;

    public Arcanoid(int height, int length)
    {
        this.length = length;
        this.height = height;
        matrix = new char[height][length];
    }

    public Stand getStand()
    {
        return stand;
    }

    public Ball getBall()
    {
        return ball;
    }

    public List<Bricks> getBricks()
    {
        return bricks;
    }

    public static Arcanoid getGame()
    {
        return game;
    }

    public void setStand(Stand stand)
    {
        this.stand = stand;
    }

    public void setBall(Ball ball)
    {
        this.ball = ball;
    }

    public void setBricks(List<Bricks> bricks)
    {
        this.bricks = bricks;
    }

    public static void setGame(Arcanoid game)
    {
        Arcanoid.game = game;
    }

    public int getLengt()
    {
        return length;
    }

    public int getHight()
    {
        return height;
    }

    public void setLengt(int lengt)
    {
        this.length = lengt;
    }

    public void setHight(int hight)
    {
        this.height = hight;
    }

    public char[][] getMatrix()
    {
        return matrix;
    }

    public void setMatrix(char[][] matrix)
    {
        this.matrix = matrix;
    }

    public Field getField()
    {
        return field;
    }

    public void setField(Field field)
    {
        this.field = field;
    }

    public int getLength()
    {
        return length;
    }

    public int getHeight()
    {
        return height;
    }



    public void move(){
        ball.move();
        stand.move();
    }


    public void print(){
        field.printField();
    }
    public void draw(){
        field.drawBoards();
        stand.draw(field,'H');
        ball.draw(field, 'O');
        for (Bricks br: bricks)
            br.draw(field, 'X');
        System.out.println();
        System.out.println();

    }

    public void run(){
        field = new Field(height, length);
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (!ball.isGameOver())
        {
            System.out.println(ball.getDirection());
           // System.out.println(ball.getDx());
         //   System.out.println(ball.getDy());

            if (keyboardObserver.hasKeyEvents())
            {
                ball.start();
                KeyEvent event = keyboardObserver.getEventFromTop();

                //Если "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    stand.moveLeft();
                    //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    stand.moveRight();
                    //Если "пробел" - запускаем шарик
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                   // ball.start();
                    stand.stop();
            }

            draw();
            print();
            move();



        }
    }

    public static void main (String [] args){
        game = new Arcanoid(30,60);
        game.stand= new Stand(28,26);
        game.ball=new Ball(27,30,4);

        game.bricks.add(new Bricks(5,5));
        game.bricks.add(new Bricks(8,10));
        game.bricks.add(new Bricks(5,15));
        game.bricks.add(new Bricks(8,20));
        game.bricks.add(new Bricks(5,25));
        game.bricks.add(new Bricks(8,30));
        game.bricks.add(new Bricks(5,35));
        game.bricks.add(new Bricks(8,40));
        game.bricks.add(new Bricks(5,45));
        game.bricks.add(new Bricks(8,50));





        game.run();
    }


}
