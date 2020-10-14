package toastsimulation;
/**
 * This class is the main toaster simulation.
 * 
 * By Emiliano, Victor and Jonny
 */
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;


public class ToasterSimulation {
    
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;

    private double x; 
    private double y;
    private GraphicsText cripsyness; 
    private GraphicsText timeLeft; 
    private GraphicsGroup group;

    public ToasterSimulation(){


    }
    
    public static void main(String[] args){
        ToasterSimulation simulation = new ToasterSimulation();
        // simulation.run();
    }
}
