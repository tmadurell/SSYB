package Fakehalla.Game.Entity;

import Fakehalla.Game.Entity.Animations.ShotAnimation;
import Fakehalla.Game.Utils.Vector2D;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.LinkedList;

public class Shot extends Entity implements Updatable{
    private double shotSpeed;
    private boolean hit;
    private ShotAnimation shotAnimation;
    //private final static String bulletFileName = "src/resources/bullet.png";

    public Shot(Point2D startPosition,Direction playerDirection, double shotWidth,double shotHeight,double playerWidth, double playerHeight, String bulletFileName)
    {
        super(startPosition,playerDirection,shotWidth,shotHeight);
        this.shotSpeed = playerWidth-1;
        this.hit = true;
        this.shotAnimation = new ShotAnimation(bulletFileName+"bullet.png");
        setVelocity(new Vector2D(new Point2D(shotSpeed,0)));
        chooseDirection();
        chooseStartPosition(playerWidth,playerHeight);
        setDefaultTexture(new Texture(bulletFileName+"bullet.png"));
        this.setDefaultTexture(shotAnimation.getTexture(this.getDirection()));
    }

    public Shot(Point2D startPosition, Direction direction, double shotWidth, double shotHeight, double speed,double xVelocity ,String bulletFileName,double sizeLimit)
    {
        super(startPosition,direction,shotWidth,shotHeight);
        this.shotSpeed = -1*speed*(shotWidth / sizeLimit);
        this.setDefaultTexture(new Texture(bulletFileName));
        this.hit = true;
        setVelocity(new Vector2D(new Point2D(shotSpeed,xVelocity)));
    }

    @Override
    public void update(long currentTime, double dt, double gameWidth, double gameHeight, Vector2D gravity, ArrayList<Updatable> objToInteract, LinkedList<Block> gameObj)
    {
        //this.getVelocity().add(gravity);
        this.getVelocity().multiply(dt);
        this.setPosition(this.getPosition().add(this.getVelocity().getDirection()));
    }

    @Override
    public boolean inBounds(double widthLimit,double heightLimit, double stepY)
    {
        return this.getBody().getX() <= widthLimit && this.getBody().getX() + this.getBody().getWidth() >= 0;
    }

    public boolean isHit() { return this.hit; }

    public void setHit(boolean hit) { this.hit = hit; }

    private void chooseDirection()
    {
        if(this.getDirection() == Direction.LEFT)
        {
            this.shotSpeed *= -1;
            setVelocity(new Vector2D(new Point2D(this.getVelocity().getDirection().getX()*-1,this.getVelocity().getDirection().getY())));
            this.setDirection(Direction.LEFT);
        }
        else
        {
            this.setDirection(Direction.RIGHT);
        }
    }

    private void chooseStartPosition(double playerWidth, double playerHeight)
    {
        double shotToPlayer = 0.25;
        if(this.getDirection() == Direction.LEFT)
        {
            this.setPosition(new Point2D(this.getPosition().getX() - playerWidth - 1, this.getPosition().getY() + playerHeight* shotToPlayer));
        }
        else
        {
            this.setPosition(new Point2D(this.getPosition().getX() + playerWidth + 1, this.getPosition().getY()+ playerHeight* shotToPlayer));
        }
    }

}
