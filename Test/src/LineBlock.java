
public class LineBlock {
	
	public static int x = 0;
	public static int y = 0;
	public static int rotation = 0;
	public int block1X = 3;
	public int block1Y = 0;
	public int block2X = 4;
	public int block2Y = 0;
	public int block3X = 5;
	public int block3Y = 0;
	public int block4X = 6;
	public int block4Y = 0;
	public static boolean moving = true;
	public static int linesKilled = 0;
	public static int linesToNextLevel = 10;
	public static int scoreMultiplyer = 5;
	public static boolean wasLineBreak = false;
	
	

	public final int XADJUST = 22;
	public final int YADJUST = XADJUST;
	
	int actual1X = (block1X * XADJUST) + 30;
	int actual2X = (block2X * XADJUST) + 30;
	int actual3X = (block3X * XADJUST) + 30;
	int actual4X = (block4X * XADJUST) + 30;
	
	int actual1Y = (block1Y * YADJUST) + 20;
	int actual2Y = (block2Y * YADJUST) + 20;
	int actual3Y = (block3Y * YADJUST) + 20;
	int actual4Y = (block4Y * YADJUST) + 20;
	
	
	public LineBlock(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move() {
		
		if(block1Y == StartScreen.tm.NUMBOFROWS - 1){
			death();
			StartScreen.tm.didDie = true;
		}else if(block2Y == StartScreen.tm.NUMBOFROWS - 1) {
			death();
			StartScreen.tm.didDie = true;
		}else if(block3Y == StartScreen.tm.NUMBOFROWS - 1) {
			death();
			StartScreen.tm.didDie = true;
		}else if(block4Y == StartScreen.tm.NUMBOFROWS - 1) {
			death();
			StartScreen.tm.didDie = true;
		}else if (StartScreen.tm.grid[block1X][block1Y + 1] == true){
			death();
			StartScreen.tm.didDie = true;
		}else if (StartScreen.tm.grid[block2X][block2Y + 1] == true) {
			death();
			StartScreen.tm.didDie = true;
		}else if (StartScreen.tm.grid[block3X][block3Y + 1] == true) {
			death();
			StartScreen.tm.didDie = true;
		}else if (StartScreen.tm.grid[block4X][block4Y + 1] == true) {
			death();
			StartScreen.tm.didDie = true;
		}else {
			
		}
		
		
		if (moving == true) {
			block1Y += 1;
			block2Y += 1;
			block3Y += 1;
			block4Y += 1;
		}
		
		update();	
	}
	
	public void update() {
		actual1X = (block1X * XADJUST) + 30;
		actual2X = (block2X * XADJUST) + 30;
		actual3X = (block3X * XADJUST) + 30;
		actual4X = (block4X * XADJUST) + 30;
		
		actual1Y = (block1Y * YADJUST) + 20;
		actual2Y = (block2Y * YADJUST) + 20;
		actual3Y = (block3Y * YADJUST) + 20;
		actual4Y = (block4Y * YADJUST) + 20;	
		
	}

	public void death() {
		StartScreen.tm.spawning = true;
		StartScreen.tm.blocksPlaced += 4;
		try {
		StartScreen.tm.grid[block1X][block1Y] = true;
		StartScreen.tm.grid[block2X][block2Y] = true;
		StartScreen.tm.grid[block3X][block3Y] = true;
		StartScreen.tm.grid[block4X][block4Y] = true;
		StartScreen.tm.remember(actual1X,actual1Y,actual2X,actual2Y,actual3X,actual3Y,actual4X,actual4Y);
		}catch(java.lang.ArrayIndexOutOfBoundsException e){
		}
		
		
		int brokenRow = 20;
		
		for(int i = 0; i < StartScreen.tm.NUMBOFCOLUM; i++) {
			if(StartScreen.tm.grid[i][0] == true) {
//				StartScreen.tm.running = false;
				StartScreen.tm.running = false;
			}
			
		}
		
		
		for(int i = 0; i < StartScreen.tm.NUMBOFROWS; i++) {
			for(int j = 0; j < StartScreen.tm.NUMBOFCOLUM; j++) {
				if(StartScreen.tm.grid[j][i] == true) {
					
				}else {
					break;
				}
				
				if (j == StartScreen.tm.NUMBOFCOLUM - 1) {
					brokenRow = i;
					StartScreen.tm.blocksRemoved += 11;
					StartScreen.tm.rearange(brokenRow);
					linesKilled += 1;
					wasLineBreak = true;
				}
			}
		}
		
		if(wasLineBreak == true) {
			StartScreen.tm.score += scoreMultiplyer * linesKilled;
			StartScreen.tm.lineNumb += linesKilled;
			linesToNextLevel -= linesKilled;
			linesKilled = 0;
			wasLineBreak = false;
			if (linesToNextLevel < 0){
				linesToNextLevel = 10;
				StartScreen.tm.score += 100;
				scoreMultiplyer = scoreMultiplyer * 2;
				StartScreen.tm.level += 1;
				StartScreen.tm.speed = StartScreen.tm.speed * 1.5F;
			}
		}
	}
	
	
	
	
}
