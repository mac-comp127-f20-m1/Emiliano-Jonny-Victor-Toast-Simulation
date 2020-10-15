package toastsimulation;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class TimeBoxes extends Rectangle{
    private TimeBoxes timebox;

    public TimeBoxes(TimeBoxes timebox, double xPosition, double yPosition, double width, double height){
        super(xPosition, yPosition, width, height);
        setStrokeWidth(Math.rint((width + height) / 40 + 1) * 0.5);
        setActive(false);
        this.timebox = timebox;
    }

     public void setActive(boolean active) {
        setFillColor(active
            ? new Color(0x3ba634)
            : new Color(0xD9D9D9));
    }
    public TimeBoxes getForecast() {
        return timebox;
    }
}
