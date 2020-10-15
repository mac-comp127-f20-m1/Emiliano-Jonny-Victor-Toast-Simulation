package toastsimulation;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;

/**
 * This class is Toaster object.
 * 
 * By Emiliano, Victor and Jonny
 */
public class Toaster extends Rectangle {
    private CanvasWindow canvas; 

    private double xPosition;
    private double yPosition;
    private double width;
    private double height;
    
    public Toaster(double xPosition, double yPosition, double width, double height){
        super(xPosition, yPosition, width, height); 
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height; 
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(this);
    }





}
