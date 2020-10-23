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
    private Toaster toaster;
    private CanvasWindow canvas;
    private static final int CANVAS_WIDTH = 1200;
    private static final int CANVAS_HEIGHT = 1200;

    private double animationParameter;
    private boolean touchingSlit;
    private boolean inSlit;
    private boolean isToasting;
    private TextField timeInput;
    private GraphicsText timeInputDirections1;
    private GraphicsText timeInputDirections2;
    private Integer toastTime;
    private Button setTimerButton;
    private Ellipse bagelHoleShape;
    private GraphicsText ratings;
    private Image secretImage;
    private int sumTime;


    private final double BREAD_RADIUS = 350;  
    private Bagel bagel2;
; 
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

        secretImage = new Image("Design.png");
        
        setTimerButton = new Button("Set Time");
        
        canvas = new CanvasWindow("TOAST!", CANVAS_WIDTH, CANVAS_HEIGHT);

        timeInputDirections1 = new GraphicsText();
        timeInputDirections2 = new GraphicsText();

        ratings= new GraphicsText();
        ratings.setCenter(2, CANVAS_HEIGHT*.2);
        ratings.setFontSize(CANVAS_HEIGHT*.04);
        
        timeInput = new TextField();



        createBread();
        createToaster();
        createToastControls();

        isAnimating = true;
        toaster.addToCanvas(canvas);
        canvas.add(slitBoundary1);
        bagel.addToCanvas(canvas);
        bagel.addBagelHoleCanvas(canvas);

        overLappingBagel(); 
        
        canvas.add(lever); 
        
        this.bagelShape = bagel.getShape(); 
        this.bagelHoleShape = bagel.getHoleShape();
        touchingSlit = false;
        inSlit = false;
        isToasting = true;

        animateBagel1(); 
        animateLever();

        
        canvas.animate(() ->
        {    
            insertBagelIntoSlit();
            toastBread();
        }); 
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
        setTimerButton.onClick(()->toastTime = Utils.stringToMillliseconds(timeInput.getText()));
        sumTime += toastTime;
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
                bagelHoleShape.setCenter(bagelShape.getCenter());
                inSlit = true;
                touchingSlit = false;
            } else{
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
                    if (bagelShape.getY() >= 200) {
                        bagelShape.setPosition(
                            bagelShape.getX() + event.getDelta().getX(),
                            bagelShape.getY() -5);

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
        } else{
            return false;
        }
    }

    public void toastBread(){
        if(isLeverDownWithBread() && isToasting && toastTime/1000 != 127){
            if(toastTime/1000<=2){
                Color toastColor = new Color(245,222,179);
                bagelShape.setFillColor(toastColor);
                ratings.setText("Like biting into an icecube!");
            }
            else if(toastTime/1000<=4){
                Color toastColor = new Color(255,204,51);
                bagelShape.setFillColor(toastColor);
                ratings.setText("I'd slap some cream cheese on that!");
            }
            else if(toastTime/1000<=6){
                Color toastColor = new Color(153,102,0);
                bagelShape.setFillColor(toastColor);
                ratings.setFontSize(CANVAS_HEIGHT*.01);
                ratings.setText("Wow I wish the line between technology and reality was even more blurred so I can reach in and eat that bagel!");
            }
            else if(toastTime/1000>=7){
                bagelShape.setFillColor(Color.BLACK);
                ratings.setText("Dude I wouldn't feed that to my worst enemy.");
            }
            canvas.pause(toastTime);
            isToasting = false;
            lever.setY(699);
            bagel.getShape().setY(341);
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
                lever.setY(699);
                bagel.getShape().setY(341);
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
        bagel = new Bagel(303, 303);

 
    }
    public void createToaster(){
        toaster = new Toaster(canvas);
    }
}