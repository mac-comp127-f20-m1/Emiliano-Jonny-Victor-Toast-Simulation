package toastsimulation;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
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
    private static final int CANVAS_WIDTH = 1200;
    private static final int CANVAS_HEIGHT = 1200;

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

    public ToasterSimulation() {
        canvas = new CanvasWindow("TOAST!", CANVAS_WIDTH, CANVAS_HEIGHT);
        createBread();
        createToaster();

        toaster.addToCanvas(canvas);
        bagel.addToCanvas(canvas);


        Ellipse bagelShape = bagel.getShape();
        canvas.onDrag(
            event -> 
        bagelShape.setPosition(
            bagelShape.getX() + event.getDelta().getX(),
            bagelShape.getY() + event.getDelta().getY()));
       
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
        bagel = new Bagel(303, 303, CANVAS_WIDTH, CANVAS_HEIGHT); 
    }
    public void createToaster(){
        toaster = new Toaster(canvas); 
    }
}
