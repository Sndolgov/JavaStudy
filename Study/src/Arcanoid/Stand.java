package Arcanoid;

/**
 * Created by SergeyND on 08.02.2017.
 */
public class Stand extends BasicObject
{
    private int speed;
    private int direction;
    public Stand(int y, int x)
    {
        super(y, x, 1, 9);
        speed=2;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    public void moveLeft(){
     //   System.out.println(length);
        direction=-1;
    }
    public void moveRight(){
     //   System.out.println("length: "+length);
        direction=1;
    }
    public void stop(){
        direction=0;
    }

    @Override
    public void move()
    {
        if (direction==-1)
        {
            int a = speed;
            if (speed>x-1)
                speed=(int)x-1;
            if (x-speed > 0)
                x -= speed;
            speed=a;
        }
        else if (direction==1)
        {
            int a = speed;
            if (speed>Arcanoid.game.getField().getLength()-x-length-1)
                speed=(int)(Arcanoid.game.getField().getLength()-x-length-1);
            if (x + length + speed < Arcanoid.game.getField().getLength())
                x += speed;
            speed=a;
        }
        else if (direction==0){

        }
    }
}
