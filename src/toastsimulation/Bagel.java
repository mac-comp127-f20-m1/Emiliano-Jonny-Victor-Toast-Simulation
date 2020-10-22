package toastsimulation;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

import java.awt.Color;

import javax.sound.midi.MidiFileFormat;

public class Bagel {
    private double centerX;
    private double centerY;
    private double maxX;
    private double maxY;
    private Ellipse middleBagel; 

    private Ellipse bagelBread;
    private final double BALL_RADIUS = 150; 
    public static final Color breadColor = new Color(255,255,153);

    public Bagel(
        double centerX,
        double centerY,
        double maxX,
        double maxY
        ){
        this.centerX = centerX;
        this.centerY = centerY;
        this.maxX = maxX;
        this.maxY = maxY; 
        Ellipse bagelBread = new Ellipse(centerX - BALL_RADIUS, centerY - BALL_RADIUS, BALL_RADIUS * 2, BALL_RADIUS * 2);
        Ellipse middleBagel = new Ellipse(centerX*.25 - BALL_RADIUS, centerY*.25 - BALL_RADIUS, BALL_RADIUS * 2 /4, BALL_RADIUS * 2/4);
        this.bagelBread = bagelBread;
        this.middleBagel = middleBagel;
        bagelBread.setStrokeColor(breadColor);
        bagelBread.setFillColor(breadColor);
        middleBagel.setStrokeColor(Color.BLACK);
        middleBagel.setFillColor(Color.black);
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(bagelBread);
    }

    public void addMiddleToCanvas(CanvasWindow canvas) {
        canvas.add(middleBagel);
    }

    /**
     * Removes the bagel shape from the given canvas.
     *  @param canvas represents canvas window 
     */
    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(bagelBread);
    }
    public double getX() {
        return centerX;
    }
    public double getY() {
        return centerY;
    }
    public double getRadius(){
        return BALL_RADIUS; 
    }

    public Point getCenter(){
        return bagelBread.getCenter(); 
    }
    public Ellipse getShape(){
        return bagelBread; 
    }


}
