import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

public class TetrisMain extends Canvas implements Runnable{
	
	public  Boolean running = true;
	
	public  final int WIDTH = 400, HEIGHT = 540;
	public  final int NUMBOFROWS = 20, NUMBOFCOLUM = 11;
	
	public  int score = 0;
	public  int lineNumb = 0;
	public  int level = 1;
	public  boolean spawning = true;
	public  int blockType = 1;
	public  int spawnX = 50;
	public  int spawnY = 50;
	
	public  Boolean didDie = false;
	
	public  int queBlockX = 100;
	public  int queBlockY = 100;
	
	public  LineBlock block;
	public  int x1, x2, x3, x4, y1, y2, y3, y4;
	public  float time = 0;
	public  int oldTimeInt = 0;
	public  int blocksRemoved = 0;
	public QueBlock nextBlock1;
	public QueBlock nextBlock2;
	public QueBlock nextBlock3;
	public QueBlock nextBlock4;
	public Color nextBlock1Color = Color.BLUE;
	public Color nextBlock2Color = Color.BLUE;
	public Color nextBlock3Color = Color.BLUE;
	public Color nextBlock4Color = Color.BLUE;
	public Color BlockColor = Color.BLUE;
	
	public  float speed = (float) 1.5;
	public  Boolean[][] grid = new Boolean[NUMBOFCOLUM][NUMBOFROWS];
	public  int[] queBlockXY  = new int[8];
	
	public  int blocksPlaced = 0;
	public  ArrayList blocksPlacedArrayX = new ArrayList();
	public  ArrayList blocksPlacedArrayY = new ArrayList();
	public  ArrayList blocksPlacedArrayColor = new ArrayList();
	public  Graphics2D g;
	public  BufferStrategy buf;
	
	public float last_time = System.nanoTime();

	public boolean test = true;


	public void start() {		
		
		for(int i = 0; i < NUMBOFCOLUM; i++) {
			for(int j = 0; j < NUMBOFROWS; j++) {
				grid[i][j] = false;
			}
		}
		
		block = new LineBlock(spawnX, spawnY);
		BlockColor = Color.CYAN;
		
		blockRandomiser();
		nextBlock1 = new QueBlock(queBlockXY[0], queBlockXY[1], queBlockXY[2], queBlockXY[3], queBlockXY[4], queBlockXY[5], queBlockXY[6], queBlockXY[7]);
		nextBlock1.yMove = 45;
		nextBlock1Color = nextBlock4Color;
		blockRandomiser();
		nextBlock2 = new QueBlock(queBlockXY[0], queBlockXY[1], queBlockXY[2], queBlockXY[3], queBlockXY[4], queBlockXY[5], queBlockXY[6], queBlockXY[7]);
		nextBlock2.yMove = 133;
		nextBlock2Color = nextBlock4Color;
		blockRandomiser();
		nextBlock3 = new QueBlock(queBlockXY[0], queBlockXY[1], queBlockXY[2], queBlockXY[3], queBlockXY[4], queBlockXY[5], queBlockXY[6], queBlockXY[7]);
		nextBlock3.yMove = 221;
		nextBlock3Color = nextBlock4Color;
		blockRandomiser();
		nextBlock4 = new QueBlock(queBlockXY[0], queBlockXY[1], queBlockXY[2], queBlockXY[3], queBlockXY[4], queBlockXY[5], queBlockXY[6], queBlockXY[7]);
		nextBlock4.yMove = 309;
		
		Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
		
	}


	@Override
	public void run() {
		while(running){
			
			float realtime = System.nanoTime();
		    float delta = ((realtime - last_time) / 100000);
		    last_time = realtime;
			
			if(bufferRender() == true) {
				continue;
			}

			
			time += 0.0001 * speed * InputKeyEvents.speedUp * delta;
				
			if ( (int)time != oldTimeInt) {
				block.move();
				if (didDie == true){
					newBlock();
					didDie = false;
					
				}
			}
			oldTimeInt = (int)time;
			
			render(g);
			g.dispose();
			buf.show();
		}
		
	}


	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("level " + Integer.toString(level) + "      Score " + Integer.toString(score) + "      Line Number " + Integer.toString(lineNumb), 40, HEIGHT - 50);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(30, 20, 22 * 11, HEIGHT - 100);
		g.setColor(Color.BLUE);
		
		buildBlock(block.actual1X, block.actual1Y, BlockColor);
		buildBlock(block.actual2X, block.actual2Y, BlockColor);
		buildBlock(block.actual3X, block.actual3Y, BlockColor);
		buildBlock(block.actual4X, block.actual4Y, BlockColor);
		
		nextBlock1.update();
		buildBlock(nextBlock1.actual1X, nextBlock1.actual1Y, nextBlock1Color);
		buildBlock(nextBlock1.actual2X, nextBlock1.actual2Y, nextBlock1Color);
		buildBlock(nextBlock1.actual3X, nextBlock1.actual3Y, nextBlock1Color);
		buildBlock(nextBlock1.actual4X, nextBlock1.actual4Y, nextBlock1Color);
		nextBlock2.update();
		buildBlock(nextBlock2.actual1X, nextBlock2.actual1Y, nextBlock2Color);
		buildBlock(nextBlock2.actual2X, nextBlock2.actual2Y, nextBlock2Color);
		buildBlock(nextBlock2.actual3X, nextBlock2.actual3Y, nextBlock2Color);
		buildBlock(nextBlock2.actual4X, nextBlock2.actual4Y, nextBlock2Color);
		nextBlock3.update();
		buildBlock(nextBlock3.actual1X, nextBlock3.actual1Y, nextBlock3Color);
		buildBlock(nextBlock3.actual2X, nextBlock3.actual2Y, nextBlock3Color);
		buildBlock(nextBlock3.actual3X, nextBlock3.actual3Y, nextBlock3Color);
		buildBlock(nextBlock3.actual4X, nextBlock3.actual4Y, nextBlock3Color);
		nextBlock4.update();
		buildBlock(nextBlock4.actual1X, nextBlock4.actual1Y, nextBlock4Color);
		buildBlock(nextBlock4.actual2X, nextBlock4.actual2Y, nextBlock4Color);
		buildBlock(nextBlock4.actual3X, nextBlock4.actual3Y, nextBlock4Color);
		buildBlock(nextBlock4.actual4X, nextBlock4.actual4Y, nextBlock4Color);
	
		
		for (int i = 0; i < blocksPlacedArrayX.size(); i++) {
			
			buildBlock((int)blocksPlacedArrayX.get(i), (int)blocksPlacedArrayY.get(i), (Color) blocksPlacedArrayColor.get(i));
			
			
		}
		
		
	}
	//do you remember 
	public  void remember(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		
		blocksPlacedArrayX.add(x1);
		blocksPlacedArrayX.add(x2);
		blocksPlacedArrayX.add(x3);
		blocksPlacedArrayX.add(x4);
		
		blocksPlacedArrayY.add(y1);
		blocksPlacedArrayY.add(y2);
		blocksPlacedArrayY.add(y3);
		blocksPlacedArrayY.add(y4);
		
		blocksPlacedArrayColor.add(BlockColor);
		blocksPlacedArrayColor.add(BlockColor);
		blocksPlacedArrayColor.add(BlockColor);
		blocksPlacedArrayColor.add(BlockColor);
	}
	
	public  void rearange(int tetrisRow) {
		
		for ( int i = 0; i < blocksPlacedArrayY.size(); i++) {
			
			if((int)blocksPlacedArrayY.get(i) == tetrisRow * block.YADJUST + 20) {
				blocksPlacedArrayY.remove(i);
				blocksPlacedArrayX.remove(i);
				blocksPlacedArrayColor.remove(i);
				i -= 1;
			}else if((int)blocksPlacedArrayY.get(i) < tetrisRow * block.YADJUST + 20){

				int b = (int) blocksPlacedArrayY.get(i) + 22;
				blocksPlacedArrayY.remove(i);
				blocksPlacedArrayY.add(i, b);
			}
		}
		
		for ( int i = 0; i < NUMBOFCOLUM; i++) {
			for (int j = tetrisRow; j > 0; j-- ) {
				grid[i][j] = grid[i][j-1];
			}
		}
	}
	
		
	
	public void newBlock() {
		block.block1X = nextBlock1.block1X;
		block.block2X = nextBlock1.block2X;
		block.block3X = nextBlock1.block3X;
		block.block4X = nextBlock1.block4X;
		
		block.block1Y = nextBlock1.block1Y;
		block.block2Y = nextBlock1.block2Y;
		block.block3Y = nextBlock1.block3Y;
		block.block4Y = nextBlock1.block4Y;
		
		BlockColor = nextBlock1Color;
		
		nextBlock1.block1X = nextBlock2.block1X;
		nextBlock1.block2X = nextBlock2.block2X;
		nextBlock1.block3X = nextBlock2.block3X;
		nextBlock1.block4X = nextBlock2.block4X;
		
		nextBlock1.block1Y = nextBlock2.block1Y;
		nextBlock1.block2Y = nextBlock2.block2Y;
		nextBlock1.block3Y = nextBlock2.block3Y;
		nextBlock1.block4Y = nextBlock2.block4Y;
		
		nextBlock1Color = nextBlock2Color;
		
		nextBlock2.block1X = nextBlock3.block1X;
		nextBlock2.block2X = nextBlock3.block2X;
		nextBlock2.block3X = nextBlock3.block3X;
		nextBlock2.block4X = nextBlock3.block4X;
		
		nextBlock2.block1Y = nextBlock3.block1Y;
		nextBlock2.block2Y = nextBlock3.block2Y;
		nextBlock2.block3Y = nextBlock3.block3Y;
		nextBlock2.block4Y = nextBlock3.block4Y;
		
		nextBlock2Color = nextBlock3Color;
		
		nextBlock3.block1X = nextBlock4.block1X;
		nextBlock3.block2X = nextBlock4.block2X;
		nextBlock3.block3X = nextBlock4.block3X;
		nextBlock3.block4X = nextBlock4.block4X;
		
		nextBlock3.block1Y = nextBlock4.block1Y;
		nextBlock3.block2Y = nextBlock4.block2Y;
		nextBlock3.block3Y = nextBlock4.block3Y;
		nextBlock3.block4Y = nextBlock4.block4Y;
		
		nextBlock3Color = nextBlock4Color;
		
		blockRandomiser();
		
		nextBlock4.block1X = queBlockXY[0];
		nextBlock4.block2X = queBlockXY[1];
		nextBlock4.block3X = queBlockXY[2];
		nextBlock4.block4X = queBlockXY[3];
		
		nextBlock4.block1Y = queBlockXY[4];
		nextBlock4.block2Y = queBlockXY[5];
		nextBlock4.block3Y = queBlockXY[6];
		nextBlock4.block4Y = queBlockXY[7];
		
		block.update();
		
		
	}
	
	
	public  void blockRandomiser() {
		
		Random r = new Random();
		int c = (r.nextInt(7) + 1);
		switch(c) {
		case 1://backwards L
			queBlockXY[0] = 4;
			queBlockXY[1] = 6;
			queBlockXY[2] = 5;
			queBlockXY[3] = 4;
			
			queBlockXY[4] = 1;
			queBlockXY[5] = 1;
			queBlockXY[6] = 1;
			queBlockXY[7] = 0;
			
			nextBlock4Color = Color.ORANGE;
			break;
		case 2:// L
			queBlockXY[0] = 4;
			queBlockXY[1] = 6;
			queBlockXY[2] = 5;
			queBlockXY[3] = 6;
			
			queBlockXY[4] = 1;
			queBlockXY[5] = 1;
			queBlockXY[6] = 1;
			queBlockXY[7] = 0;
			nextBlock4Color = Color.BLUE;
			break;
		case 3://Square
			queBlockXY[0] = 5;
			queBlockXY[1] = 6;
			queBlockXY[2] = 5;
			queBlockXY[3] = 6;
			
			queBlockXY[4] = 1;
			queBlockXY[5] = 1;
			queBlockXY[6] = 0;
			queBlockXY[7] = 0;
			nextBlock4Color = Color.YELLOW;
			break;
		case 4://right zig
			queBlockXY[0] = 4;
			queBlockXY[1] = 5;
			queBlockXY[2] = 5;
			queBlockXY[3] = 6;
			
			queBlockXY[4] = 1;
			queBlockXY[5] = 1;
			queBlockXY[6] = 0;
			queBlockXY[7] = 0;
			nextBlock4Color = Color.RED;
			break;
		case 5://T\
			queBlockXY[0] = 4;
			queBlockXY[1] = 5;
			queBlockXY[2] = 5;
			queBlockXY[3] = 6;
			
			queBlockXY[4] = 1;
			queBlockXY[5] = 0;
			queBlockXY[6] = 1;
			queBlockXY[7] = 1;
			nextBlock4Color = Color.MAGENTA;
			break;
		case 6://left zag
			queBlockXY[0] = 4;
			queBlockXY[1] = 5;
			queBlockXY[2] = 5;
			queBlockXY[3] = 6;
			
			queBlockXY[4] = 0;
			queBlockXY[5] = 0;
			queBlockXY[6] = 1;
			queBlockXY[7] = 1;
			nextBlock4Color = Color.GREEN;
			break;
		case 7://line
			queBlockXY[0] = 3;
			queBlockXY[1] = 4;
			queBlockXY[2] = 5;
			queBlockXY[3] = 6;
			
			queBlockXY[4] = 0;
			queBlockXY[5] = 0;
			queBlockXY[6] = 0;
			queBlockXY[7] = 0;
			nextBlock4Color = Color.CYAN;
			break;
		}
	}
	public  void reset() {
		score = 0;
		lineNumb = 0;
		level = 1;
		spawning = true;
		blockType = 1;
		spawnX = 50;
		spawnY = 50;
		
		didDie = false;
		
		queBlockX = 100;
		queBlockY = 100;

		time = 0;
		oldTimeInt = 0;
		blocksRemoved = 0;
		
		speed = (float) 1.5;
		grid = new Boolean[NUMBOFCOLUM][NUMBOFROWS];
		queBlockXY  = new int[8];
		
		blocksPlaced = 0;
		blocksPlacedArrayX = new ArrayList();
		blocksPlacedArrayY = new ArrayList();
	}
	
	public boolean bufferRender() {
		buf = getBufferStrategy();
		if(buf == null) {
			createBufferStrategy(3);
			return true;
		}
		g = (Graphics2D) buf.getDrawGraphics();
		return false;
	}
	
	public void buildBlock(int x, int y, Color color) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 22, 22);
		
		g.setColor(color);
		g.fillRect(x + 2, y + 2, 18, 18);
		
	}
	
	
	

}
