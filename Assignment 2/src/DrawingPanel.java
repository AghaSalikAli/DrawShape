import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	// PROPERTIES
	private final int DEFAULT_WIDTH = 600;
	private final int DEFAULT_HEIGHT = 600;

	private int x1, y1, x2, y2;

	private Graphics g;
	private Shape shape;
	private int clicks;
	private int[] xp = new int[3];
	private int[] yp = new int[3];
	private int shapetodraw;
	private Stack undoStack;
	private Queue redoQueue;

	// CONSTRUCTOR
	public DrawingPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		undoStack = new Stack();
		redoQueue = new Queue();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		setFocusable(true);
	}

	// METHOD
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	private void setUpDrawingGraphics() {
		g = getGraphics();
		if (shape != null) {
			shape.draw(g);
			undoStack.push(shape);
			redoQueue.clear();
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (shapetodraw == 49) {
				x1 = e.getX();
				y1 = e.getY();
				shape = new Rectangle();
			} else if (shapetodraw == 50) {
				x1 = e.getX();
				y1 = e.getY();
				shape = new Circle();
			} else if (shapetodraw == 51) {
				xp[clicks] = e.getX();
				yp[clicks] = e.getY();
				clicks++;

				if (clicks % 2 == 0) {
					g = getGraphics();
					g.setColor(Color.BLACK);
					g.drawLine(xp[0], yp[0], xp[1], yp[1]);
				}
				if (clicks % 3 == 0) {
					shape = new Triangle();
					for (int i = 0; i < 3; i++) {
						x1 = xp[i];
						y1 = yp[i];
						shape.setPoints(x1, y1);
					}
					setUpDrawingGraphics();
					clicks = 0;
				}
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			if (!undoStack.isEmpty()) {
				Shape s1 = undoStack.pop();
				redoQueue.enqueue(s1);
				g = getGraphics();
				s1.undo(g);
				for (int x = undoStack.size() - 1; x >= 0; x--) {
					undoStack.get(x).draw(g);
				}
			}
			System.out.println("right pressed");
		} else if (SwingUtilities.isMiddleMouseButton(e)) {
			if (!redoQueue.isEmpty()) {
				shape = redoQueue.removeLast();
				g = getGraphics();
				shape.draw(g);
				undoStack.push(shape);
			}
			System.out.println("middle pressed");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e) && (shapetodraw == 49 || shapetodraw == 50)) {
			x2 = e.getX();
			y2 = e.getY();
			shape.setWidth(x1, x2);
			shape.setHeight(y1, y2);
			shape.setStartpoint(x1,y1,x2,y2);
			setUpDrawingGraphics();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		shapetodraw = e.getKeyChar();
		g = getGraphics();
		String s;
		if (shapetodraw == 49)
			s = "Rectangle";
		else if (shapetodraw == 50)
			s = "Circle";
		else if (shapetodraw == 51)
			s = "Triangle";
		else
			return;

		Shape.drawname(g, s);
		System.out.println("key pressed: " + (shapetodraw - 48));
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}


	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}


	public void saveToFile(String fileName) {
		try {
			FileWriter myWriter = new FileWriter(fileName);
			for (int x = undoStack.size() - 1; x >= 0; x--) {
				shape = undoStack.get(x);
				String s = shape.toString();
				myWriter.append(s).append("\n");
			}
			myWriter.close();
			System.out.println("Successfully saved progress!");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void readFromfile(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			{
				String[] curr;
				curr = sc.nextLine().split(" ");

				if (curr[0].equals("Triangle")) {
					shape = new Triangle();
					shape.points[0].x = Integer.parseInt(curr[1]);
					shape.points[0].y = Integer.parseInt(curr[2]);
					shape.points[1].x = Integer.parseInt(curr[3]);
					shape.points[1].y = Integer.parseInt(curr[4]);
					shape.points[2].x = Integer.parseInt(curr[5]);
					shape.points[2].y = Integer.parseInt(curr[6]);
					int r1 = Integer.parseInt(curr[7]);
					int g1 = Integer.parseInt(curr[8]);
					int b1 = Integer.parseInt(curr[9]);
					shape.setColor(new Color(r1, g1, b1));
					setUpDrawingGraphics();

				} else if (curr[0].equals("Circle")) {
					shape = new Circle();
					shape.startpoint.x = Integer.parseInt(curr[1]);
					shape.startpoint.y = Integer.parseInt(curr[2]);
					shape.width = Integer.parseInt(curr[3]);
					shape.height = Integer.parseInt(curr[4]);
					int r1 = Integer.parseInt(curr[5]);
					int g1 = Integer.parseInt(curr[6]);
					int b1 = Integer.parseInt(curr[7]);
					shape.setColor(new Color(r1, g1, b1));
					setUpDrawingGraphics();

				} else if (curr[0].equals("Rectangle")) {
					shape = new Rectangle();
					shape.startpoint.x = Integer.parseInt(curr[1]);
					shape.startpoint.y = Integer.parseInt(curr[2]);
					shape.width = Integer.parseInt(curr[3]);
					shape.height = Integer.parseInt(curr[4]);
					int r1 = Integer.parseInt(curr[5]);
					int g1 = Integer.parseInt(curr[6]);
					int b1 = Integer.parseInt(curr[7]);
					shape.setColor(new Color(r1, g1, b1));
					setUpDrawingGraphics();
				}
			}
		}
		System.out.println("Current progress!");
	}
}