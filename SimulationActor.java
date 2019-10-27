import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*; 
import java.awt.image.*; 

/**
 * Write a description of class SimulationActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimulationActor extends Actor
{
    protected Point2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;

    protected GreenfootImage originalImage;
    
    public SimulationActor()
    {
        this.position = null;
        this.velocity = new Vector2D(0.0,0.0);
        this.acceleration = new Vector2D(0.0,0.0);
        originalImage = null;
    }
    
    public SimulationActor(Point2D position, Vector2D velocity, Vector2D acceleration)
    {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        originalImage = null;
    }
    
    public void act() 
    {
        eulerIntegration();
    }    
    
    protected SimulationWorld getSimulationWorld()
    {
        return (SimulationWorld) getWorld();
    }
    
    protected void eulerIntegration()
    {
        // This is for scaling the sprites later on, somehow, greenfoot draw sprites with scale
        if (originalImage == null && getImage() != null)
        {
            saveOriginalImage();
        }
        
        // Get time step duration
        double dt = getSimulationWorld().getTimeStepDuration();
        
        // Update position
        Vector2D positionVariation = Vector2D.multiply(velocity, dt);
        position.add(positionVariation);
        
        // Update velocity
        Vector2D velocityVariation = Vector2D.multiply(acceleration, dt);
        velocity = Vector2D.add(velocity, velocityVariation);
        
        // Set new actor position
        setLocation(position);
    }

    public void saveOriginalImage()
    {
        BufferedImage img = getImage().getAwtImage();
        originalImage = new GreenfootImage(img.getWidth(), img.getHeight());

        Graphics g = originalImage.getAwtImage().getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();        
    }
    
    public void scaleImage(double zoomRatio)
    {
        if (originalImage != null)
        {
            int imageWidth = originalImage.getWidth();
            int imageHeight = originalImage.getHeight();

            GreenfootImage scaledImage = new GreenfootImage(imageWidth, imageHeight);
            BufferedImage gBufImg = scaledImage.getAwtImage();
            Graphics2D graphics = (Graphics2D)gBufImg.getGraphics();
            graphics.drawImage(originalImage.getAwtImage(), null, 0, 0);
            graphics.dispose();
            scaledImage.scale((int)Math.max(imageWidth*zoomRatio, 0.0) + 1, (int) Math.max(imageHeight*zoomRatio, 0.0) + 1);
            setImage(scaledImage);
        }
    }
    
    public Point2D getPosition()
    {
        return position;
    }
    
    public Vector2D getVelocity()
    {
        return velocity;
    }

    public Vector2D getAcceleration()
    {
        return acceleration;
    }

    public void setPosition(Point2D newValue)
    {
        position = newValue;
        setLocation(position);
    }
    
    public void setVelocity(Vector2D newValue)
    {
        velocity = newValue;
    }

    public void setAcceleration(Vector2D newValue)
    {
        acceleration = newValue;
    }

    public void setLocation(Point2D worldLocation)
    {
        Point2D windowLocation = worldToWindow(worldLocation);
        setLocation((int) windowLocation.getX(), (int) windowLocation.getY());
    }
    
    protected double worldToWindow(double windowCoordinates)
    {        
        return getSimulationWorld().worldToWindow(windowCoordinates);
    }

    protected Point2D worldToWindow(Point2D windowCoordinates)
    {        
        return getSimulationWorld().worldToWindow(windowCoordinates);
    }

    protected Vector2D worldToWindow(Vector2D windowCoordinates)
    {
        return getSimulationWorld().worldToWindow(windowCoordinates);
    }
    
    protected double windowToWorld(double windowCoordinates)
    {
        return getSimulationWorld().windowToWorld(windowCoordinates);
    }

    protected Point2D windowToWorld(Point2D windowCoordinates)
    {
        return getSimulationWorld().windowToWorld(windowCoordinates);
    }

    protected Vector2D windowToWorld(Vector2D windowCoordinates)
    {
        return getSimulationWorld().windowToWorld(windowCoordinates);
    }
    
    public int getRadius()
    {
        if (getImage() != null)
        {
            return getImage().getHeight() / 2;
        }
        else
        {
            return 0;
        }
    }
    

}
