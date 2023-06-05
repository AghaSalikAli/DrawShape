import java.awt.*;

public class Rectangle extends Shape{

    public Rectangle () {
        super();
        Type = "Rectangle";
        startpoint = new Point(-5,-5);
    }
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getStartpoint().x, getStartpoint().y, getWidth(), getHeight());
    }

    @Override
    public void undo(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(getStartpoint().x, getStartpoint().y, getWidth(), getHeight());
    }

    public void setStartpoint(int x1, int y1, int x2, int y2) {
        if ((x1<x2) && (y1<y2))
            startpoint = new Point(x1,y1);
        else if ((x1<x2) && (y1>y2))
            startpoint = new Point(x1,y2);
        else if ((x1>x2) && (y1>y2))
            startpoint = new Point(x2,y2);
        else
            startpoint = new Point(x2,y1);
    }

}
