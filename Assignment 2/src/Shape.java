import java.awt.*;
import java.util.Random;

public abstract class Shape {
    Color color;
    Point startpoint;
    String Type;
    int width;
    int height;
    Point[] points = new Point[3];
    Random random = new Random();

    public Shape() {
        color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public abstract void draw(Graphics g);
    public abstract void undo (Graphics g);
    public String toString () {
        return getType() + " " + startpoint.x + " " + startpoint.y + " " + getWidth() + " " + getHeight()
                + " " + getColor().getRed() + " " + getColor().getGreen() + " " + getColor().getBlue();
    }
    public void setWidth(int x1, int x2) {
        int x = Math.abs(x1 - x2);
        width = Math.max(x, 5);
    }

    public void setHeight(int y1, int y2) {
        int y = Math.abs(y1 - y2);
        height = Math.max(y, 5);
    }

    public Color getColor() {
        return color;
    }

    public String getType() {
        return Type;
    }

    public Point getStartpoint() {
        return startpoint;
    }

    public void setPoints(int x, int y) {
        if (points[0].x == -1)
            points[0] = new Point(x, y);
        else if (points[1].x == -1)
            points[1] = new Point(x, y);
        else if (points[2].x == -1)
            points[2] = new Point(x, y);
    }

    public void setColor (Color c) {
        color = c;
    }

    public void setStartpoint(int x1, int y1, int x2, int y2) {
        //used for circle and rectangle ONLY
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static void drawname(Graphics g, String s) {
        if (s.equals("Rectangle")) {
            g.setColor(Color.white);
            g.setFont(new Font("Comic Sans", Font.PLAIN, 10));
            g.drawString("Circle", 10,580);
            g.drawString("Triangle", 10,580);
            g.setColor(Color.BLACK);
            g.drawString("Rectangle", 10,580);
        }
        else if (s.equals("Circle")) {
            g.setColor(Color.white);
            g.setFont(new Font("Comic Sans", Font.PLAIN, 10));
            g.drawString("Rectangle", 10,580);
            g.drawString("Triangle", 10,580);
            g.setColor(Color.BLACK);
            g.drawString("Circle", 10,580);
        }
        else if (s.equals("Triangle")) {
            g.setColor(Color.white);
            g.setFont(new Font("Comic Sans", Font.PLAIN, 10));
            g.drawString("Circle", 10,580);
            g.drawString("Rectangle", 10,580);
            g.setColor(Color.BLACK);
            g.drawString("Triangle", 10,580);
        }
    }
}
