

public class QueBlock {
	
	public int block1X = 3;
	public int block1Y = 0;
	public int block2X = 4;
	public int block2Y = 0;
	public int block3X = 5;
	public int block3Y = 0;
	public int block4X = 6;
	public int block4Y = 0;
	public int xMove = 227;
	public int yMove = 40;
	

	public final int XADJUST = 22;
	public final int YADJUST = XADJUST;
	
	int actual1X = (block1X * XADJUST) + xMove;
	int actual2X = (block2X * XADJUST) + xMove;
	int actual3X = (block3X * XADJUST) + xMove;
	int actual4X = (block4X * XADJUST) + xMove;
	
	int actual1Y = (block1Y * YADJUST) + yMove;
	int actual2Y = (block2Y * YADJUST) + yMove;
	int actual3Y = (block3Y * YADJUST) + yMove;
	int actual4Y = (block4Y * YADJUST) + yMove;
	
	
	public QueBlock(int x1, int x2, int x3, int x4, int y1, int y2, int y3, int y4) {
		
		block1X = x1;
		block1Y = y1;
		block2X = x2;
		block2Y = y2;
		block3X = x3;
		block3Y = y3;
		block4X = x4;
		block4Y = y4;
		
		
		
		
	}

	
	public void update() {
		actual1X = (block1X * XADJUST) + xMove;
		actual2X = (block2X * XADJUST) + xMove;
		actual3X = (block3X * XADJUST) + xMove;
		actual4X = (block4X * XADJUST) + xMove;
		
		actual1Y = (block1Y * YADJUST) + yMove;
		actual2Y = (block2Y * YADJUST) + yMove;
		actual3Y = (block3Y * YADJUST) + yMove;
		actual4Y = (block4Y * YADJUST) + yMove;	
		
	}
}
