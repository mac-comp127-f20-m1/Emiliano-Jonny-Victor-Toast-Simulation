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
    private Image toasterBottemBody;

    
    public Toaster(CanvasWindow canvas){
<<<<<<< HEAD
        this.toasterTopBody = new Image(300, 300, "toasterTopBody.png");
        this.toasterMidBody = new Image(300, 496, "toasterMidBody.png");
        this.toasterBottemBody = new Image(300, 546, "toasterBottemBody.png");
=======
        this.toasterTopBody = new Image(300, 450, "res/Images/toasterTopBody.png");
        // this.toasterMidBody = new Image(300, 496, "toasterMidBody.png");
        // this.toasterBottemBody = new Image(300, 546, "toasterBottemBody.png");
>>>>>>> b5fa208d50cd90b558b923eb4d39d4ba33556573

        this.canvas = canvas;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(toasterTopBody);
        canvas.add(toasterMidBody);
        canvas.add(toasterBottemBody);
    }





}
