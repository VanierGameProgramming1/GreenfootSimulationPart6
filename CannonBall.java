import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
* Write a description of class CannonBall here.
* 
* @author (your name) 
* @version (a version number or a date)
*/
public class CannonBall extends SimulationActor
{
    protected static final double GRAVITY = -9.8;
    protected boolean onPlatform;
    protected int lastPositionX;
    
    
    public CannonBall()
    {
        super(null, new Vector2D(0.0, 0.0), new Vector2D(0.0, GRAVITY));
        onPlatform = false;
        lastPositionX = 0;
    }
    
    public void act() 
    {
        super.act();
        landOnPlatform();
        rollBall();    
    }    
    
    public void landOnPlatform()
    {
        Platform p = (Platform) getOneObjectAtOffset(0, getRadius() + 1, Platform.class);
        
        if (p != null)
        {
            // Update position to lie on the platform
            setLocation(getX(), p.getY() - p.getHeight() / 2 - getRadius());
            
            // Update the velocity to stop falling
            velocity.setY(Math.max(velocity.getY(), 0.0));

            onPlatform = true;
        }
        else
        {
            onPlatform = false;
        }
        
    }
    
    public void rollBall()
    {
        if (onPlatform == true)
        {
            double circumference = 2 * Math.PI * getRadius();
            int dx = getX() - lastPositionX;
            double angleVariation = dx * 360 / circumference;
            
            setRotation(getRotation() + (int) angleVariation);
        }
        
        // Prepare the last position X for the next time step
        lastPositionX = getX();
    }
    
}
















