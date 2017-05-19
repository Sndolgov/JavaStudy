package Arcanoid;

/**
 * Created by SergeyND on 08.02.2017.
 */
public class Ball extends BasicObject
{
    private int speed;
    private int direction;
    private double dx;
    private double dy;
    private boolean isFrozen;
    private boolean gameOver = false;



    public Ball(int y, int x, int speed)
    {
        super(y, x, 1, 1);
        this.speed=speed;
        direction=(int)Math.round(Math.random()*90+40);
       // direction=150;
        isFrozen=true;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getDirection()
    {
        return direction;
    }

    public double getDx()
    {
        return dx;
    }

    public double getDy()
    {
        return dy;
    }

    public boolean isFrozen()
    {
        return isFrozen;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    public void setDx(double dx)
    {
        this.dx = dx;
    }

    public void setDy(double dy)
    {
        this.dy = dy;
    }

    public void setFrozen(boolean frozen)
    {
        isFrozen = frozen;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }


    public void step (){
      //  System.out.println("step Direction: " + direction);
        double angel = Math.toRadians(direction);
        dx = Math.cos(angel);
        dy = -Math.sin(angel);
        if (checkStep()==true)
        {
            setDirection();
        }
    }
    public void start(){
        isFrozen=false;
    }

    public void dirCounterclockwise(){
     //   direction -= (int) Math.round(Math.random() * 30) - 90;
        direction=direction+(70 + (int)Math.round(Math.random() * 40));
     /*   if (direction<0)
            direction+=360;*/
        step();
    }
    public void dirClockwise(){
        //   direction -= (int) Math.round(Math.random() * 30) - 90;
       direction=direction-(70 + (int)Math.round(Math.random() * 40));
        if (direction<0)
            direction+=360;
        step();
    }
    public boolean checkStep(){
        boolean check = false;
        if (x+dx<=1||x+dx>=Arcanoid.game.getField().getLength()-1||y+dy<=1||Math.round(y+dy)>=Arcanoid.game.getField().getHeight()-1){
            return true;
        }
        int stX = (int)Arcanoid.game.getStand().getX();
        int stLnth= (int)Arcanoid.game.getStand().getLength();
        int stY = (int)Arcanoid.game.getStand().getY();
        for (int i =stX; i<stX+stLnth; i++)
        {
            if (Math.round(x + dx) == i && Math.round(y + dy) == stY)
            {
                return true;
            }
        }
        for (Bricks br: Arcanoid.game.getBricks())
        {
            if ((x+dx>=br.getX()&&x+dx<=(br.getX()+br.getLength()))&& ((Math.round(y+dy)>=br.getY())&&(Math.round(y+dy)<=(br.getY()+br.getHeight()-1)))){
                return true;
            }
        }
            return check;

    }

    public void setDirection(){
        if (x+dx<=1){
            if (direction<=180)
            {
                dirClockwise();
            }
            else if (direction>180){
                dirCounterclockwise();
            }
        }
        else if (x+dx>=Arcanoid.game.getField().getLength()-1){
            if (direction<=90){
                dirCounterclockwise();
            }
            else if (direction>=270){
                dirClockwise();
            }
        }
        if (y+dy<=1){
            if (direction<=90){
                dirClockwise();
            }
            else if (direction>90){
                dirCounterclockwise();
            }

        }
        else if (Math.round(y+dy)>=Arcanoid.game.getField().getHeight()-1)
        {
            gameOver = true;
            dx = 0;
            dy = 0;
            System.out.println("GAME OVER");
        }

            int stX = (int)Arcanoid.game.getStand().getX();
            int stLnth= (int)Arcanoid.game.getStand().getLength();
            int stY = (int)Arcanoid.game.getStand().getY();
        for (int i =stX; i<stX+stLnth; i++){
                if (Math.round(x+dx)==i && Math.round(y+dy)==stY){
                    if (direction<=270){
                        dirClockwise();
                    }
                    else {
                        dirCounterclockwise();
                    }
                    step();
                }

        }
        for (Bricks br: Arcanoid.game.getBricks())
        {
            if ((x+dx>=br.getX()&&x+dx<=(br.getX()+br.getLength()))&& ((Math.round(y+dy)>=br.getY())&&(Math.round(y+dy)<=(br.getY()+br.getHeight()-1)))){
                if (direction<=90||(direction<=270&&direction>=180))
                    dirClockwise();
                else dirCounterclockwise();
                step();
                Arcanoid.game.getBricks().remove(br);
                return;
            }
        }
        direction=direction%360;
    }

    @Override
    public void move()
    {
        if (!isFrozen())
        {
            step();
            x+=dx;
            y+=dy;
        }
        try
        {
            Thread.sleep(500/speed);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


}
