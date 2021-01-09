import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;


public class EndScreen extends Canvas implements Runnable{

	public  Graphics2D g;
	public  BufferStrategy buf;
	boolean running = true;
	
	
	public void EndScreen(BufferStrategy buf) {
		this.buf = buf;
		Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}
	
	
	
	public void run() {
		while(running) {
			buf = getBufferStrategy();
			if(buf == null) {
				createBufferStrategy(3);
				continue;
			}
			g = (Graphics2D) buf.getDrawGraphics();
			
			
			render(g);
			g.dispose();
			buf.show();
			
			System.out.println("hi");
			
		}
	}

	private void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 100, 100);
		
	}
}
