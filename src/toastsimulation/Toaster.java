package toastsimulation;

import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;

/**
 * This class is Toaster object.
 * 
 * By Emiliano, Victor and Jonny
 */
public class Toaster{
    private CanvasWindow canvas; 
    private Image toasterBody;

    
    public Toaster(CanvasWindow canvas){
        this.toasterBody = new Image(300, 450, "toasterBody.png");
        this.canvas = canvas;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(toasterBody);
    }





}
