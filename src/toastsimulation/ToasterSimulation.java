package toastsimulation;

import edu.macalester.graphics.CanvasWindow;
/**
 * This class is the main toaster simulation.
 * 
 * By Emiliano, Victor and Jonny
 */
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;


public class ToasterSimulation {
    private Bagel bagel; 
    private Toaster toaster; 

    private CanvasWindow canvas; 
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;

    private double x; 
    private double y;
    private double width = CANVAS_WIDTH;
    private double height = CANVAS_HEIGHT; 
    private GraphicsText cripsyness; 
    private GraphicsText timeLeft; 
    private GraphicsGroup group;
    private double animationParameter;
    // private Bagel bagel; 

    private boolean flag = true; 

    public ToasterSimulation(){
        canvas = new CanvasWindow("TOAST!", CANVAS_WIDTH, CANVAS_HEIGHT);
        createBread();
        createToaster();

        // Button toastButton = new Button("Toast Your Bread"); 
        // canvas.add(toastButton);   
        // toaster.addToCanvas(canvas);
        // bagel.addToCanvas(canvas);

        canvas.onDrag(event ->
            setAnimationParameter(
                getAnimationParameter() +
                event.getDelta().getX() / width));

        canvas.onMouseDown(event ->
        flag = false);

        canvas.onMouseUp(event ->
        flag = true);

        
        

    }

    public double getAnimationParameter() {
        return animationParameter;
    }

    public void animateMethod() {
        canvas.onMouseDown(event ->
        flag = false);
        
    }
    public void setAnimationParameter(double animationParameter) {
        this.animationParameter = animationParameter;
    }
    
    public static void main(String[] args){
        ToasterSimulation simulation = new ToasterSimulation();

    }

    public void createBread(){
        bagel = new Bagel(50, 50, CANVAS_WIDTH, CANVAS_HEIGHT); 

    }
    public void createToaster(){
        toaster = new Toaster(300, 300, width * 0.2 , height * 0.23); 
    }
}
