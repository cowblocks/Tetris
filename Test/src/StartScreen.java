import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class StartScreen extends Canvas{

	private static final int WIDTH = 400, HEIGHT = 540;
	
	public static JFrame frame;
	public static TetrisMain tm;
	public static EndScreen lm;
	static boolean runningWorld = true;
	public  Graphics2D g;
	public  BufferStrategy buf;
	
	public static void main (String[] args) {
		
		frame = new JFrame("Tetris");
		frame.setSize(WIDTH, HEIGHT);;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		startTetris();
		
		while(runningWorld) {
//		while(tm.running == true) {
//		}
//		System.out.println("hi");
		lm = new EndScreen();
		lm.setBounds(0,0,WIDTH,HEIGHT);
		frame.add(lm);
		lm.EndScreen(tm.buf);
		while(lm.running == true) {}
		}
	}

	
	public static void startTetris(){
		
		tm = new TetrisMain();
		tm.setBounds(0,0,WIDTH,HEIGHT);
		frame.add(tm);
		tm.addKeyListener(new InputKeyEvents());
		frame.setVisible(true);
		tm.start();
		
	}
}
