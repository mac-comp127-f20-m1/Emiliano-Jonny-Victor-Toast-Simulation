package toastsimulation;

import java.awt.Color;

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
    private Ellipse breadBoundary; 
    private Bagel bagel2;

    private boolean flag1 = true;
    private boolean flag2 = true;
    private Ellipse bagelShape; 

    public ToasterSimulation() {
        canvas = new CanvasWindow("TOAST!", CANVAS_WIDTH, CANVAS_HEIGHT);
        createBread();
        createToaster();

        toaster.addToCanvas(canvas);
        bagel.addToCanvas(canvas);
        // bagel2.addToCanvas(canvas);
        this.bagelShape = bagel.getShape(); 
        toasterBoundary();
        canvas.animate(() ->
        {
            checkBounds();
        });
        animateBagel1(); 

    }
    public void checkBounds() {
        if (bagel.getX() > 415 && bagel.getX() < 765 && bagel.getY() > 290 && bagel.getY() < 640) {
            canvas.remove(bagel.getShape());
        }
    }



    public void toasterBoundary(){
        Ellipse breadBoundary = new Ellipse(415,290,350,350);
        // breadBoundary.setFillColor(Color.WHITE); 
        canvas.add(breadBoundary); 
    }
    public void animateBagel1(){
        canvas.onDrag(
            event -> 
                bagelShape.setPosition(
                bagelShape.getX() + event.getDelta().getX(),
                bagelShape.getY() + event.getDelta().getY()));
       animateMethod();
    }

    public void animateBagel2(){
        Ellipse bagelShape2 = bagel2.getShape();
        canvas.onDrag(
            event -> 
                bagelShape2.setPosition(
                bagelShape2.getX() + event.getDelta().getX(),
                bagelShape2.getY() + event.getDelta().getY()));
        animateMethod();
    }

    public double getAnimationParameter() {
        return animationParameter;
    }

    public void animateMethod() {
        if(flag1 == false){
        canvas.onMouseDown(event ->
        flag1 = false);
        }else{
        canvas.onMouseUp(event ->
            flag1 = true);
        }
    }
    public void animateMethod2() {
        if(flag2 == false){
        canvas.onMouseDown(event ->
        flag2 = false);
        }else{
        canvas.onMouseUp(event ->
            flag2 = true);
        }
    }
    







    public void setAnimationParameter(double animationParameter) {
        this.animationParameter = animationParameter;
    }
    
    public static void main(String[] args){
        ToasterSimulation simulation = new ToasterSimulation();

    }

    public void createBread(){
        bagel = new Bagel(303, 303, CANVAS_WIDTH, CANVAS_HEIGHT); 
        // bagel2 = new Bagel(303, 600, CANVAS_WIDTH, CANVAS_HEIGHT); 
    }
    public void createToaster(){
        toaster = new Toaster(canvas); 
    }
}
