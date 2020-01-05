public class GameOfLife {
	private Board newBoard;
	private int turns;
	
	public void playGame(int height, int width) {
		this.newBoard = new Board(height, width);
		this.turns = 0;
		
		this.newBoard.generateBoard();
		this.newBoard.printBoard();
		
		
		while(!this.newBoard.isBoardDead() && !this.newBoard.isOnRepeat()) {
			this.newBoard.playRound();
			this.turns++;
			
			System.out.println("\033[32mTurn number " + turns);
			this.newBoard.printBoard();
			
			try {
				Thread.sleep(500);
			} 
			catch (InterruptedException e) {
				System.out.println("Unable to sleep for 1000 ms");
			}
		}
		
		this.printGameReport();
	}
	
	public void printGameReport() {
		if(this.newBoard.isOnRepeat()) {
			System.out.println("\033[34mGame of life ran for " + this.turns + " turns before final sustainable ecosystem");
		}
		else {
			System.out.println("\033[31mGame of life ran for " + this.turns + " turns before dead ecosystem");
		}
	}
}
