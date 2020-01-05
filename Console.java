public class Console {
	public static void main(String[] args) {
		System.out.println("\033[36mWelcome to Conway's Game of Life");
		
		GameOfLife gol = new GameOfLife();
		gol.playGame(25, 50);
	}
}
