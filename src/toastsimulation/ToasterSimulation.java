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
/** This program creates a toaster simulation that toasts a bagel based of of a specified input for time given by the user.
 *  The program utilizes animate methods, canvas events, mouse events, boolean variables, lambda expressions and other java
 *  tools to create an effective and easy to use computer program.
 */
public class ToasterSimulation {

    /**Implements the bagel class into ToasterSimulation */
    private Bagel bagel;
    private Ellipse bagelShape;
    private Ellipse bagelHoleShape;
    private final double BREAD_RADIUS = 350;
    private Bagel bagel2;

    private Toaster toaster;
    private boolean isToasting;

    /**Creates canvas */
    private CanvasWindow canvas;
    private static final int CANVAS_WIDTH = 1200;
    private static final int CANVAS_HEIGHT = 1200;


    /** slits for monitoring if bagel enter toaster */
    private boolean touchingSlit;
    private boolean inSlit;
    private Rectangle slitBoundary1;
    /**textfield boxes / images for canvas*/
    private TextField timeInput;
    private GraphicsText timeInputDirections1;
    private GraphicsText timeInputDirections2;
    private GraphicsText ratings;
    private Image secretImage;
    private Image background;

    /** monitoring time inside toaster*/
    private int sumTime;
    private Integer toastTime;
    private Button setTimerButton;

    private boolean isAnimating;
    private boolean isDragable;

    private Rectangle lever;

    //Frankly I have no idea wtf these do
    private boolean flag1 = true;
    private boolean flag2 = true;

    /**Toaster simulation runs on a series of methods that create objects on the canvas, use boolean expressions to set certain events into action, and lambda expressions 
     * to manage animations */
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

    /**The animateToaster method sets the animations that pertain to the toaster, taking the bread and toasting, into action */
    public void animateToaster() {
        canvas.animate(() -> {
            insertBagelIntoSlit();
            toastBread();
        });
    }

    /**Creates the visuals that appear on the UI for the toaster */
    public void graphicObjectsForToaster() {
        secretImage = new Image("Design.png");

        setTimerButton = new Button("Set Time");

        canvas = new CanvasWindow("TOAST!", CANVAS_WIDTH, CANVAS_HEIGHT);
    }
    /**Creates the commentary that shows up on the canvvas after a bagel has been toasted and adds the direction to the canvas */
    public void textGraphics() {
        timeInputDirections1 = new GraphicsText();
        timeInputDirections2 = new GraphicsText();

        ratings = new GraphicsText();
        ratings.setCenter(2, CANVAS_HEIGHT * .2);
        ratings.setFontSize(CANVAS_HEIGHT * .04);

        timeInput = new TextField();
    }

    /**Sets the bounds on the canvas that indicate where the bread insertions on the totoaster are located,
     * also speciies a location and other componesnts for the lever
     */
    public void graphicToasterInterSections() {
        slitBoundary1 = new Rectangle(CANVAS_WIDTH * 0.326, CANVAS_HEIGHT * 0.4058, CANVAS_WIDTH * 0.333334,
            CANVAS_HEIGHT * 0.0183);
        slitBoundary1.setStrokeColor(Color.DARK_GRAY);

        lever = new Rectangle(CANVAS_WIDTH * 0.2125, CANVAS_HEIGHT * 0.5833, CANVAS_WIDTH * 0.05,
            CANVAS_HEIGHT * 0.025);
        lever.setFillColor(Color.DARK_GRAY);

    }
    /**These are the states that in which these booleans should be in when an animation is occuring, these boolean values are
     * cruicial when it comes to how our objects interact with one another when a bagel is occuring or a object is moving around the canvas
     */
    public void booleanForAnimations() {
        isAnimating = true;
        touchingSlit = false;
        inSlit = false;
        isToasting = true;
    }

    /**Adds the objects that are visible to the user to the canvas */
    public void addAllToCanvas() {
        Image background = new Image("countertop.jpg");
        canvas.add(background);
        toaster.addToCanvas(canvas);
        canvas.add(slitBoundary1);
        bagel.addToCanvas(canvas);
        bagel.addBagelHoleCanvas(canvas);
        canvas.add(lever);
    }

    /**Specifies components and specifics for objects, buttons, and canvas objects that pertain the toasters controls */
    public void createToastControls() {
        setTimerButton.setPosition(CANVAS_WIDTH * .5, CANVAS_HEIGHT * .62);
        timeInput.setPosition(CANVAS_WIDTH * .5, CANVAS_HEIGHT * .6);
        timeInputDirections1.setPosition(CANVAS_WIDTH * .75, CANVAS_HEIGHT * .6);
        timeInputDirections1.setText("type in the second of toasting then");
        timeInputDirections2.setPosition(CANVAS_WIDTH * .72, CANVAS_HEIGHT * .61);
        timeInputDirections2.setText("press the button and pull the lever for some toast action!");
        canvas.add(setTimerButton);
        canvas.add(timeInput);
        canvas.add(timeInputDirections1);
        canvas.add(timeInputDirections2);
        setTimerButton.onClick(() -> {
            toastTime = 1000 * Integer.parseInt(timeInput.getText());
            sumTime += toastTime;
        });


    }
    // public void breadEnteredAgain(){
    // if(){

    // }
    // }

    /**Allows for the way the toaster is presented on the canvas to remain consistent and look realistic when the bagel is inserted */
    public void overLappingBagel() {
        toaster.removeMiddleBody();
        toaster.addMiddleBody();
        toaster.removeBottomBody();
        toaster.addBottomBody();
    }

    /**Method handles the state of specific boolean objects when the insetBagelIntoSlit is called, specifies the position for the bagel 'inside' the toaster */
    public void insertBagelIntoSlit() {
        checkIfBagelIsOverSlit();
        canvas.onMouseUp(event -> {
            if (touchingSlit == true) {
                bagel.getShape().setPosition(CANVAS_WIDTH * 0.369, CANVAS_HEIGHT * 0.284);
                bagelHoleShape.setCenter(bagelShape.getCenter());
                inSlit = true;
                touchingSlit = false;
            } else {
                return;
            }
        });
    }

    /**Method checks multiple positional components of the bagel, if the bagels position falls within the specifications of the if statement it sets the 'touchingSlit' method
     * to true which is used later in the program to set other methods into action if it is equal to trues
      */
    public void checkIfBagelIsOverSlit() {
        if (bagel.getShape().getX() > CANVAS_WIDTH * 0.3266 &&
            bagel.getShape().getX() + bagel.getRadius() < CANVAS_WIDTH * 0.66 &&
            canvas.getElementAt(bagel.getShape().getX() + bagel.getRadius(),
            bagel.getShape().getY() + (2 * bagel.getRadius())) instanceof Rectangle) {
                touchingSlit = true;
                canvas.pause(300);
        }

    }
    //Check me on accuracy
    /**Allows the user to drag the bagel at certain positions on the canvas */
    public void animateBagel1() {
        canvas.onDrag(
            event -> {
                if (isBreadObject(event.getPosition()) && inSlit == false) {
                    if (bagelShape.getY() >= 199) {
                        bagelShape.setPosition(
                            bagelShape.getX() + event.getDelta().getX(),
                            bagelShape.getY() - 3);

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
            });
    }

    //Check me on accuracy
    public void animateMethod() {
        if (flag1 == false) {
            canvas.onMouseDown(event -> flag1 = false);
        } else {
            canvas.onMouseUp(event -> flag1 = true);
        }
    }

    //Check me on accuracy
    public void animateMethod2() {
        if (flag2 == false) {
            canvas.onMouseDown(event -> flag2 = false);
        } else {
            canvas.onMouseUp(event -> flag2 = true);
        }
    }

    /**Allows the user to drag the lever under certain conditions */
    public void animateLever() {
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
                    if (lever.getY() <= CANVAS_HEIGHT * 0.5825) {
                        lever.setY(CANVAS_HEIGHT * 0.5825);
                    } else if (lever.getY() >= CANVAS_HEIGHT * 0.716667) {
                        lever.setY(CANVAS_HEIGHT * 0.716667);
                    }
                } else if (isLeverObject(event.getPosition())) {
                    lever.setPosition(
                        lever.getX(),
                        lever.getY() + event.getDelta().getY());
                    animateMethod();
                    if (lever.getY() <= CANVAS_HEIGHT * 0.5825) {
                        lever.setY(CANVAS_HEIGHT * 0.5825);
                    } else if (lever.getY() >= CANVAS_HEIGHT * 0.716667) {
                        lever.setY(CANVAS_HEIGHT * 0.716667);
                    }
                }
            });
        animateMethod();
    }
    /**Boolean variable that checks if the lever is in 'down' position with the bread inaserted, this ultimately is used to set the toastBread into action */
    public boolean isLeverDownWithBread() {
        if (inSlit && lever.getY() >= CANVAS_HEIGHT * 0.716667) {
            return true;
        } else {
            return false;
        }
    }

    /**Specifies what happens when the toaster magically accepts the bread and returns the mysteriously beter tasting hard form of it */
    public void toastBread() {
        if (isLeverDownWithBread() && isToasting && toastTime / 1000 != 127) {
            if (sumTime / 1000 <= 2) {
                Color toastColor = new Color(245, 222, 179);
                bagelShape.setFillColor(toastColor);
                ratings.setText("Like biting into an icecube!");
            } else if (sumTime / 1000 <= 4) {
                Color toastColor = new Color(255, 204, 51);
                bagelShape.setFillColor(toastColor);
                ratings.setText("I'd slap some cream cheese on that!");
            } else if (sumTime / 1000 <= 6) {
                Color toastColor = new Color(153, 102, 0);
                bagelShape.setFillColor(toastColor);
                ratings.setFontSize(CANVAS_HEIGHT * .01);
                ratings.setText(
                    "Wow I wish the line between technology and reality was even more blurred so I can reach in and eat that bagel!");
            } else if (sumTime / 1000 >= 7) {
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

        } else if (isLeverDownWithBread() && isToasting && toastTime / 1000 == 127) {
            bagelShape.setFillColor(Color.WHITE);
            ratings.setText("The Paul Cantrell");
            canvas.pause(1000);

            isToasting = false;
            lever.setY(CANVAS_HEIGHT * 0.5825);
            bagel.getShape().setY(CANVAS_HEIGHT * 0.2841);
            bagelHoleShape.setCenter(bagelShape.getCenter());
            secretImage.setCenter(bagelShape.getCenter());
            secretImage.setY(bagelShape.getY() - 10);
            canvas.add(secretImage);
            canvas.add(ratings);
            inSlit = false;
            touchingSlit = false;
            isToasting = true;
        }
    }

    /**Checks if the object that is clicked is a bread object, used to implemet its drag event */
    public boolean isBreadObject(Point point) {
        if (canvas.getElementAt(point) instanceof Ellipse) {
            return true;
        } else {
            return false;
        }
    }

    /**Checks if the object that is clicked is a lever object, used to implemet its drag event */
    public boolean isLeverObject(Point point) {
        if (canvas.getElementAt(point) instanceof Rectangle) {
            return true;
        } else {
            return false;
        }
    }

    /**Main method that is used to run the toaster simulation program */
    public static void main(String[] args) {
        ToasterSimulation simulation = new ToasterSimulation();
    }

    public void createBread() {
        bagel = new Bagel(CANVAS_WIDTH * 0.2525, CANVAS_HEIGHT * 0.2525);
    }

    public void createToaster() {
        toaster = new Toaster(canvas);
    }
}