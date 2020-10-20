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
    private Image toasterTopBody;
    private Image toasterMidBody;
    private Image toasterBottomBody;

    
    public Toaster(CanvasWindow canvas){
        this.toasterTopBody = new Image(300, 450, "toasterTopBody.png");
        this.toasterMidBody = new Image(300, 496, "toasterMidBody.png");
        this.toasterBottomBody = new Image(300, 546, "toasterBottomBody.png");
        this.canvas = canvas;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(toasterTopBody);
        canvas.add(toasterMidBody);
        canvas.add(toasterBottomBody);
    }

    public void removeMiddleBody(){
        canvas.remove(toasterMidBody); 
    }
    public void addMiddleBody(){
        canvas.add(toasterMidBody);
    }
    public void removeBottomBody(){
        canvas.remove(toasterBottomBody);
    }
    public void addBottomBody(){
        canvas.add(toasterBottomBody);
    }
}
