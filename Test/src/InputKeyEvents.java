import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputKeyEvents implements KeyListener{
	
	public static float speedUp = 1F;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keys = e.getKeyCode();
		
		
		if (StartScreen.tm.running == false) {
			
		}else if (keys == KeyEvent.VK_A) {
			
			if (StartScreen.tm.block.block1X == 0) {
				
			}else if (StartScreen.tm.block.block2X == 0) {
				
			}else if (StartScreen.tm.block.block3X == 0) {
				
			}else if (StartScreen.tm.block.block4X == 0) {
				
			}else if(StartScreen.tm.grid[StartScreen.tm.block.block1X - 1][StartScreen.tm.block.block1Y] == true) {
				
			}else if(StartScreen.tm.grid[StartScreen.tm.block.block2X - 1][StartScreen.tm.block.block2Y] == true) {
				
			}else if(StartScreen.tm.grid[StartScreen.tm.block.block3X - 1][StartScreen.tm.block.block3Y] == true) {
				
			}else if(StartScreen.tm.grid[StartScreen.tm.block.block4X - 1][StartScreen.tm.block.block4Y] == true) {
				
			}else {
				StartScreen.tm.block.block1X -= 1;
				StartScreen.tm.block.block2X -= 1;
				StartScreen.tm.block.block3X -= 1;
				StartScreen.tm.block.block4X -= 1;
				StartScreen.tm.block.update();
			}
			
			
		}else if (keys == KeyEvent.VK_D) {
			
			if (StartScreen.tm.block.block1X == StartScreen.tm.NUMBOFCOLUM - 1) {
				
			}else if (StartScreen.tm.block.block2X == StartScreen.tm.NUMBOFCOLUM - 1) {
				
			}else if (StartScreen.tm.block.block3X == StartScreen.tm.NUMBOFCOLUM - 1) {
				
			}else if (StartScreen.tm.block.block4X == StartScreen.tm.NUMBOFCOLUM - 1) {
				
			}else if(StartScreen.tm.grid[StartScreen.tm.block.block1X + 1][StartScreen.tm.block.block1Y] == true) {
				
			}else if(StartScreen.tm.grid[StartScreen.tm.block.block2X + 1][StartScreen.tm.block.block2Y] == true) {
				
			}else if(StartScreen.tm.grid[StartScreen.tm.block.block3X + 1][StartScreen.tm.block.block3Y] == true) {
				
			}else if(StartScreen.tm.grid[StartScreen.tm.block.block4X + 1][StartScreen.tm.block.block4Y] == true) {
				
			}else {
				StartScreen.tm.block.block1X += 1;
				StartScreen.tm.block.block2X += 1;
				StartScreen.tm.block.block3X += 1;
				StartScreen.tm.block.block4X += 1;
				StartScreen.tm.block.update();
			}
		}
		
		
		if (keys == KeyEvent.VK_S) {
			speedUp = 10F;
			
		}else {
			speedUp = 1F;
		}
		
		
		if (keys == KeyEvent.VK_W) {
			
			
				
				int possibleX1 = StartScreen.tm.block.block1Y -StartScreen.tm.block.block3Y + StartScreen.tm.block.block3X;
				int possibleY1 = -(StartScreen.tm.block.block1X - StartScreen.tm.block.block3X) + StartScreen.tm.block.block3Y;
				int possibleX2 = StartScreen.tm.block.block2Y -StartScreen.tm.block.block3Y + StartScreen.tm.block.block3X;
				int possibleY2 = -(StartScreen.tm.block.block2X - StartScreen.tm.block.block3X) + StartScreen.tm.block.block3Y;
				int possibleX4 = StartScreen.tm.block.block4Y -StartScreen.tm.block.block3Y + StartScreen.tm.block.block3X;
				int possibleY4 = -(StartScreen.tm.block.block4X - StartScreen.tm.block.block3X) + StartScreen.tm.block.block3Y;
				
				if(possibleX1 < 0 || possibleX1 > StartScreen.tm.NUMBOFCOLUM - 1 || possibleY1 > StartScreen.tm.NUMBOFROWS || StartScreen.tm.grid[possibleX1][possibleY1] == true) {
					
				}else if(possibleX2 < 0 || possibleX2 > StartScreen.tm.NUMBOFCOLUM - 1 || possibleY2 > StartScreen.tm.NUMBOFROWS || StartScreen.tm.grid[possibleX2][possibleY2] == true) {
					
				}else if(possibleX4 < 0 || possibleX4 > StartScreen.tm.NUMBOFCOLUM - 1 || possibleY4 > StartScreen.tm.NUMBOFROWS || StartScreen.tm.grid[possibleX4][possibleY4] == true) {
					
				} else {
					StartScreen.tm.block.block1X = possibleX1;
					StartScreen.tm.block.block2X = possibleX2;
					StartScreen.tm.block.block4X = possibleX4;
					StartScreen.tm.block.block1Y = possibleY1;
					StartScreen.tm.block.block2Y = possibleY2;
					StartScreen.tm.block.block4Y = possibleY4;
					StartScreen.tm.block.update();
				}			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keys = e.getKeyCode();
		if (keys == KeyEvent.VK_S) {
			speedUp = 1F;
			
		}else {
			speedUp = 1F;
		}
		
	}

}
