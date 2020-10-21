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
    private boolean touchingSlit;
    private boolean inSlit;

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


        canvas = new CanvasWindow("TOAST!", CANVAS_WIDTH, CANVAS_HEIGHT);
        
        createBread();
        createToaster();

        isAnimating = true;
        toaster.addToCanvas(canvas);
        canvas.add(slitBoundary1);
        bagel.addToCanvas(canvas);
        overLappingBagel(); 
        
       
        // canvas.add(slitBoundary2); 
        canvas.add(lever); 
        

        // bagel2.addToCanvas(canvas);
        this.bagelShape = bagel.getShape(); 
        touchingSlit = false;
        inSlit = false;

        animateBagel1(); 
        animateLever();
        insertBagelIntoSlit();
        
        canvas.animate(() ->
        {
            insertBagelIntoSlit();
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
                if(isBreadObject(event.getPosition())){
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
                    } else if (lever.getY() >= 850) {
                        lever.setY(850);
                    }
                } else if (isLeverObject(event.getPosition())) {
                    lever.setPosition(
                        lever.getX(),
                        lever.getY() + event.getDelta().getY());
                    animateMethod();
                    if (lever.getY() <= 699){
                        lever.setY(699);
                    } else if (lever.getY() >= 850) {
                        lever.setY(850);
                    }
                }
            }
        );
        animateMethod();
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
        // bagel2 = new Bagel(303, 600, CANVAS_WIDTH, CANVAS_HEIGHT);
    }
    public void createToaster(){
        toaster = new Toaster(canvas);
    }
}