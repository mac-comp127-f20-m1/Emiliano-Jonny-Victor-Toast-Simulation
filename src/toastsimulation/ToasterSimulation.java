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
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;
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

    private GraphicsGroup group;
    private double animationParameter;
    private boolean touchingSlit;
    private boolean inSlit;
    private boolean isToasting;
    private TextField timeInput;
    private GraphicsText timeInputDirections1;
    private GraphicsText timeInputDirections2;
    private Integer toastTime;


    private final double BREAD_RADIUS = 350;  
    private Bagel bagel2;
    private double centerX; 
    private double centerY; 
    private Rectangle slitBoundary1;
    private Rectangle slitBoundary2;
    private boolean isAnimating;
    private boolean isDragable;
    private double dy = .1; 
    
    private Rectangle lever; 


    private boolean flag1 = true;
    private boolean flag2 = true;
    private Ellipse bagelShape;
    public ToasterSimulation() {
        slitBoundary1 = new Rectangle(392, 487, 400, 22);
        slitBoundary1.setStrokeColor(Color.DARK_GRAY);

        slitBoundary2 = new Rectangle(392, 541, 400, 22);
        slitBoundary2.setStrokeColor(Color.DARK_GRAY);

        lever = new Rectangle(255,700,60,30);
        lever.setFillColor(Color.DARK_GRAY);

        this.toastTime = 0;
        
        
        
        canvas = new CanvasWindow("TOAST!", CANVAS_WIDTH, CANVAS_HEIGHT);

        timeInputDirections1 = new GraphicsText();
        timeInputDirections2 = new GraphicsText();

        
        timeInput = new TextField();



        createBread();
        createToaster();
        createToastControls();

        isAnimating = true;
        toaster.addToCanvas(canvas);
        canvas.add(slitBoundary1);
        bagel.addToCanvas(canvas);
        // bagel2.addToCanvas(canvas);
        overLappingBagel(); 
        
       
        // canvas.add(slitBoundary2); 
        canvas.add(lever); 
        

        // bagel2.addToCanvas(canvas);
        this.bagelShape = bagel.getShape(); 
        touchingSlit = false;
        inSlit = false;
        isToasting = true;

        animateBagel1(); 
        animateLever();

        
        canvas.animate(() ->
        {
            insertBagelIntoSlit();
            toastBread();
            // checkBounds();
            // if(inSlit == false){
            //     ;
            // }else if(bagel.getShape().getY() > 800){
            //    bagel.getShape().moveBy(0,30 * dy);
            //    if(bagel.getShape().getY() > 800){
            //     bagel.getShape().setFillColor(Color.BLACK);
            //     canvas.pause(3000);
            //     bagel.getShape().moveBy(0, -50);
            //     }
            // }
            
        });
        
        // removeBreadFromCanvas();
        
    }

    public void createToastControls(){
        timeInput.setPosition(CANVAS_WIDTH*.5,CANVAS_HEIGHT*.6);
        timeInputDirections1.setPosition(CANVAS_WIDTH*.75,CANVAS_HEIGHT*.6);
        timeInputDirections1.setText("type in the second of toasting then");
        timeInputDirections2.setPosition(CANVAS_WIDTH*.72,CANVAS_HEIGHT*.61);
        timeInputDirections2.setText("press enter and pull the lever for some toast action!");
        canvas.add(timeInput);
        canvas.add(timeInputDirections1);
        canvas.add(timeInputDirections2);

    }

    public void overLappingBagel(){
        toaster.removeMiddleBody();
        toaster.addMiddleBody();
        toaster.removeBottomBody();
        toaster.addBottomBody();
    }

    public void insertBagelIntoSlit(){
        checkIfBagelIsOverSlit();
        canvas.onMouseUp(event -> {
            if(touchingSlit == true){
                bagel.getShape().setPosition(443,341);
                inSlit = true;
                touchingSlit = false;
            }
            else{
                return;
            }
        });
    }

    public void checkIfBagelIsOverSlit() {
        if (bagel.getShape().getX()>392 && 
            bagel.getShape().getX()+bagel.getRadius()<792 &&
            canvas.getElementAt(bagel.getShape().getX()+bagel.getRadius(), 
            bagel.getShape().getY()+(2*bagel.getRadius())) 
            instanceof Rectangle) {

            touchingSlit = true;
            canvas.pause(300);
        }
        
    }

    public void animateBagel1(){
        canvas.onDrag(
            event ->{
                if(isBreadObject(event.getPosition()) && inSlit == false  ){
                    bagelShape.setPosition(
                    bagelShape.getX() + event.getDelta().getX(),
                    bagelShape.getY() + event.getDelta().getY());
                    animateMethod();
                }

            }
        );
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

    public void animateLever(){
        canvas.onDrag(
            event -> {
                Double change = event.getDelta().getY();
                if (isLeverObject(event.getPosition()) && inSlit == true) {
                    lever.setPosition(
                        lever.getX(),
                        lever.getY() + change);
                    bagelShape.setPosition(
                        bagelShape.getX(),
                        bagelShape.getY() + change);
                    animateMethod();
                    if (lever.getY() <= 699){
                        lever.setY(699);
                    } else if (lever.getY() >= 860) {
                        lever.setY(860);
                    }
                } else if (isLeverObject(event.getPosition())) {
                    lever.setPosition(
                        lever.getX(),
                        lever.getY() + event.getDelta().getY());
                    animateMethod();
                    if (lever.getY() <= 699){
                        lever.setY(699);
                    } else if (lever.getY() >= 860) {
                        lever.setY(860);
                    }
                }
            }
        );
        animateMethod();
    }

    public boolean isLeverDownWithBread(){
        if(inSlit && lever.getY() >= 860){
            return true;
        }
        else{
            return false;
        }
    }

    public void toastBread(){
        if(canvas.getKeysPressed().contains(Key.RETURN_OR_ENTER)){
            toastTime = 1000*Integer.parseInt(timeInput.getText());
            System.out.println(1000*Integer.parseInt(timeInput.getText()));
        }

        if(isLeverDownWithBread() && isToasting){
            bagel.getShape().setFillColor(Color.BLACK);
            // canvas.add(timeInput);
            // while(!timeInput.getText().equalsIgnoreCase(" ")){
            //     ;
            // }
            // timeInput.onChange(event -> canvas.pause(1000*Integer.parseInt(timeInput.getText())));
            
            canvas.pause(toastTime);
            isToasting = false;
            lever.setY(699);
            bagel.getShape().setY(341);
        }
    }

    public boolean isBreadObject(Point point){
        if(canvas.getElementAt(point) instanceof Ellipse){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean isLeverObject(Point point){
        if(canvas.getElementAt(point) instanceof Rectangle){
            return true;
        }
        else{
            return false;
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
        bagel2 = new Bagel(303, 600, CANVAS_WIDTH, CANVAS_HEIGHT);
 
    }
    public void createToaster(){
        toaster = new Toaster(canvas);
    }
}