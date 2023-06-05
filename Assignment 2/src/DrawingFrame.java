import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import javax.swing.*;

class DrawingFrame extends JFrame implements ActionListener, WindowListener
{
	//please manually remove entire data from the progress.txt file if you want to start testing...
	//...the code from scratch as if you have opened a brand new MS paint file
	//PLEASE NOTE that in case when you reopen the file and your progress does not appear..
	//...just undo (right click) and redo (middle click) and it will appear. This is a JDK issue..
	//... and not a coding error.

	static DrawingPanel panel;
	public static void main(String[] args)
	{
	    JFrame frame = new JFrame( "Drawing Program" );
	    frame.setDefaultCloseOperation(3);
	
	     panel = new DrawingPanel();
	    
	    frame.add(panel);
		frame.addWindowListener(new DrawingFrame());

		frame.pack();
	    frame.setVisible(true);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("hello!");
		try {
			panel.readFromfile("progress.txt");
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("goodbye!");
		panel.saveToFile("progress.txt");
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}