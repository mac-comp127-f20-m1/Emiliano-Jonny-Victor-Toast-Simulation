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
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;
public class ToasterSimulation {
    private Bagel bagel;
    private Ellipse bagelShape;
    private Ellipse bagelHoleShape;
    private final double BREAD_RADIUS = 350;  
    private Bagel bagel2;

    private Toaster toaster;
    private boolean isToasting;

    private CanvasWindow canvas;
    private static final int CANVAS_WIDTH = 1200;
    private static final int CANVAS_HEIGHT = 1200;

    private double animationParameter;
    // slits for monitoring if bagel enter toaster 
    private boolean touchingSlit;
    private boolean inSlit;
    private Rectangle slitBoundary1;
    // textfield boxes / images for canvas 
    private TextField timeInput;
    private GraphicsText timeInputDirections1;
    private GraphicsText timeInputDirections2;
    private GraphicsText ratings;
    private Image secretImage;

    // monitoring time inside toaster 
    private int sumTime;
    private Integer toastTime;
    private Button setTimerButton;
    
    private boolean isAnimating;
    private boolean isDragable;
    
    private Rectangle lever; 


    private boolean flag1 = true;
    private boolean flag2 = true;
    


    public ToasterSimulation() {
        graphicToasterInterSections(); 

        this.toastTime = 0;
        graphicObjectsForToaster(); 
        textGraphics();
        createBread();
        createToaster();
        createToastControls();
        addAllToCanvas();
        overLappingBagel(); 
        this.bagelShape = bagel.getShape(); 
        this.bagelHoleShape = bagel.getHoleShape();
        booleanForAnimations(); 
        animateBagel1(); 
        animateLever();

        animateToaster();
        
    }

    public void animateToaster(){
        canvas.animate(() ->
        {    
            insertBagelIntoSlit();
            toastBread();
        }); 
    }

    public void graphicObjectsForToaster(){
        secretImage = new Image("Design.png");
        
        setTimerButton = new Button("Set Time");
        
        canvas = new CanvasWindow("TOAST!", CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    public void textGraphics(){
        timeInputDirections1 = new GraphicsText();
        timeInputDirections2 = new GraphicsText();

        ratings= new GraphicsText();
        ratings.setCenter(2, CANVAS_HEIGHT*.2);
        ratings.setFontSize(CANVAS_HEIGHT*.04);
        
        timeInput = new TextField();
    }
    public void graphicToasterInterSections(){
        slitBoundary1 = new Rectangle(CANVAS_WIDTH * 0.326, CANVAS_HEIGHT * 0.4058, CANVAS_WIDTH * 0.333334, CANVAS_HEIGHT * 0.0183);
        slitBoundary1.setStrokeColor(Color.DARK_GRAY);

        lever = new Rectangle(CANVAS_WIDTH * 0.2125, CANVAS_HEIGHT * 0.5833,CANVAS_WIDTH * 0.05, CANVAS_HEIGHT * 0.025);
        lever.setFillColor(Color.DARK_GRAY);

    }
    public void booleanForAnimations(){
        isAnimating = true;
        touchingSlit = false;
        inSlit = false;
        isToasting = true;
    }
    public void addAllToCanvas(){
        toaster.addToCanvas(canvas);
        canvas.add(slitBoundary1);
        bagel.addToCanvas(canvas);
        bagel.addBagelHoleCanvas(canvas);
        canvas.add(lever); 
    }

    public void createToastControls(){
        setTimerButton.setPosition(CANVAS_WIDTH*.5,CANVAS_HEIGHT*.62);
        timeInput.setPosition(CANVAS_WIDTH*.5,CANVAS_HEIGHT*.6);
        timeInputDirections1.setPosition(CANVAS_WIDTH*.75,CANVAS_HEIGHT*.6);
        timeInputDirections1.setText("type in the second of toasting then");
        timeInputDirections2.setPosition(CANVAS_WIDTH*.72,CANVAS_HEIGHT*.61);
        timeInputDirections2.setText("press the button and pull the lever for some toast action!");
        canvas.add(setTimerButton);
        canvas.add(timeInput);
        canvas.add(timeInputDirections1);
        canvas.add(timeInputDirections2);
        setTimerButton.onClick(()->{
            toastTime = 1000*Integer.parseInt(timeInput.getText());
            sumTime += toastTime;
        });

        
    }
    // public void breadEnteredAgain(){
    //     if(){

    //     }
    // }

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
                bagel.getShape().setPosition(CANVAS_WIDTH * 0.369,CANVAS_HEIGHT * 0.284);
                bagelHoleShape.setCenter(bagelShape.getCenter());
                inSlit = true;
                touchingSlit = false;
            } else{
                return;
            }
        });
    }

    public void checkIfBagelIsOverSlit() {
        if (bagel.getShape().getX()> CANVAS_WIDTH * 0.3266 && 
            bagel.getShape().getX()+bagel.getRadius()<CANVAS_WIDTH * 0.66 &&
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
                if(isBreadObject(event.getPosition()) && inSlit == false){
                    if (bagelShape.getY() >= CANVAS_HEIGHT * .166666667) {
                        bagelShape.setPosition(
                            bagelShape.getX() + event.getDelta().getX(),
                            bagelShape.getY() -3);

                        bagelHoleShape.setCenter(bagelShape.getCenter());
                        animateMethod();
                    } else {
                        bagelShape.setPosition(
                            bagelShape.getX() + event.getDelta().getX(),
                            bagelShape.getY() + event.getDelta().getY());

                        bagelHoleShape.setCenter(bagelShape.getCenter());
                        animateMethod();
                    }                 
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
        } else{
        canvas.onMouseUp(event ->
            flag1 = true);
        }
    }

    public void animateMethod2() {
        if(flag2 == false){
        canvas.onMouseDown(event ->
        flag2 = false);
        } else{
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
                    bagelHoleShape.setCenter(bagelShape.getCenter());
                    animateMethod();
                    if (lever.getY() <= CANVAS_HEIGHT * 0.5825){
                        lever.setY(CANVAS_HEIGHT * 0.5825);
                    } else if (lever.getY() >= CANVAS_HEIGHT * 0.716667) {
                        lever.setY(CANVAS_HEIGHT * 0.716667);
                    }
                } else if (isLeverObject(event.getPosition())) {
                    lever.setPosition(
                        lever.getX(),
                        lever.getY() + event.getDelta().getY());
                    animateMethod();
                    if (lever.getY() <= CANVAS_HEIGHT * 0.5825){
                        lever.setY(CANVAS_HEIGHT * 0.5825);
                    } else if (lever.getY() >= CANVAS_HEIGHT * 0.716667) {
                        lever.setY(CANVAS_HEIGHT * 0.716667);
                    }
                }
            }
        );
        animateMethod();
    }

    public boolean isLeverDownWithBread(){
        if(inSlit && lever.getY() >= CANVAS_HEIGHT * 0.716667){
            return true;
        } else{
            return false;
        }
    }

    public void toastBread(){
        if(isLeverDownWithBread() && isToasting && toastTime/1000 != 127){
            if(sumTime/1000<=2){
                Color toastColor = new Color(245,222,179);
                bagelShape.setFillColor(toastColor);
                ratings.setText("Like biting into an icecube!");
            }
            else if(sumTime/1000<=4){
                Color toastColor = new Color(255,204,51);
                bagelShape.setFillColor(toastColor);
                ratings.setText("I'd slap some cream cheese on that!");
            }
            else if(sumTime/1000<=6){
                Color toastColor = new Color(153,102,0);
                bagelShape.setFillColor(toastColor);
                ratings.setFontSize(CANVAS_HEIGHT*.01);
                ratings.setText("Wow I wish the line between technology and reality was even more blurred so I can reach in and eat that bagel!");
            }
            else if(sumTime/1000>=7){
                bagelShape.setFillColor(Color.BLACK);
                ratings.setText("Dude I wouldn't feed that to my worst enemy.");
            }
            canvas.pause(toastTime);
            isToasting = false;
            lever.setY(CANVAS_HEIGHT * 0.5825);
            bagel.getShape().setY(CANVAS_HEIGHT * 0.2841);
            bagelHoleShape.setCenter(bagelShape.getCenter());
            canvas.add(ratings);
            inSlit = false;
            touchingSlit = false;
            isToasting = true;

        } else if (isLeverDownWithBread() && isToasting && toastTime/1000==127){
            bagelShape.setFillColor(Color.WHITE);
            ratings.setText("The Paul Cantrell");
            canvas.pause(1000);
            
            isToasting = false;
            lever.setY(CANVAS_HEIGHT * 0.5825);
            bagel.getShape().setY(CANVAS_HEIGHT * 0.2841);
            bagelHoleShape.setCenter(bagelShape.getCenter());
            secretImage.setCenter(bagelShape.getCenter());
            secretImage.setY(bagelShape.getY()-10);
            canvas.add(secretImage);
            canvas.add(ratings);
            inSlit = false;
            touchingSlit = false;
            isToasting = true;
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
        bagel = new Bagel(CANVAS_WIDTH * 0.2525, CANVAS_HEIGHT * 0.2525);

 
    }
    public void createToaster(){
        toaster = new Toaster(canvas);
    }
}