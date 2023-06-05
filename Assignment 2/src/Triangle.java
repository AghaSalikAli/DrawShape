import java.awt.*;
import java.util.Arrays;

public class Triangle extends Shape{

    int [] xpoints = new int[3];
    int [] ypoints = new int[3];
    public Triangle () {
        super();
        for (int i = 0; i < super.points.length; i++)
            super.points[i] = new Point(-1, -1);
        Type = "Triangle";
    }
    @Override
    public void draw(Graphics g) {
        for (int i=0; i<3; i++) {
            xpoints[i] = super.points[i].x;
            ypoints[i] = super.points[i].y;
        }
        g.setColor(color);
        g.drawLine(points[0].x, points[0].y, points[1].x, points[1].y);
        g.fillPolygon(xpoints,ypoints,3);
    }

    @Override
    public void undo(Graphics g) {
        g.setColor(Color.white);
        g.drawLine(points[0].x, points[0].y, points[1].x, points[1].y);
        g.fillPolygon(xpoints,ypoints,3);
    }

    @Override
    public String toString() {
        return getType() + " " +
                super.points[0].x + " " + super.points[0].y + " " +
                super.points[1].x + " " + super.points[1].y + " " +
                super.points[2].x + " " + super.points[2].y + " " +
                getColor().getRed() + " " + getColor().getGreen() + " " + getColor().getBlue();
    }
}
