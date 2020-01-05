import java.util.Arrays;
import java.util.Random;

public class Board {
	private final char LIVE = '*';
	private final char DEAD = ' ';
	
	private char[][] map;
	private char[][] cache; //repeat checker
	private int yLen;
	private int xLen;
	private boolean onRepeat;
	
	public Board(int height, int width) {
		this.map = new char[height][width];
		this.cache = new char[height][width];
		this.yLen = height;
		this.xLen = width;
		this.onRepeat = false;
	}
	
	public char[][] getMap() {
		return this.map;
	}
	
	public int getYLength() {
		return this.yLen;
	}
	
	public int getXLength() {
		return this.xLen;
	}
	
	public boolean isOnRepeat() { 
		return this.onRepeat;
	}
	
	public void generateBoard() {
		Random generator = new Random();
		for(int y = 0; y < this.yLen; y++) {
			for(int x = 0; x < this.xLen; x++) {
				int randomNumber = generator.nextInt(10);
				if(randomNumber > 6) {
					this.map[y][x] = this.LIVE; //live cell 
				}
				else {
					this.map[y][x] = this.DEAD; //dead cell
				}
			}
		}
	}
	
	public boolean isBoardDead() {
		for(int y = 0; y < this.yLen; y++) {
			for(int x = 0; x < this.xLen; x++) {
				if(Character.compare(this.map[y][x], this.LIVE) == 0) {
					return false;
				}
			}
		}
		
		return true; 
	}
	
	public void printBoard() {
		for(int y = 0; y < this.yLen; y++) {
			for(int x = 0; x < this.xLen; x++) {
				System.out.print(this.map[y][x] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public void playRound() {
		char[][] temp = new char[this.yLen][this.xLen];
		for(int y = 0; y < this.yLen; y++) {
			for(int x = 0; x < this.xLen; x++) {
				temp[y][x] = this.determineCell(y, x);
			}
		}
		
		if(Arrays.deepEquals(this.map, temp) || Arrays.deepEquals(this.cache, temp)) { //compares previous and the one before that
			this.onRepeat = true;
		}
		
		this.cache = this.map;
		this.map = temp;
	}
	
	private char determineCell(int y, int x) {
		if(Character.compare(this.map[y][x], this.LIVE) == 0) {
			//check if live cell can stay alive
			if(this.liveNeighborsCount(y, x) == 2 || this.liveNeighborsCount(y, x) == 3) {
				return this.LIVE;
			}
			else {
				return this.DEAD;
			}
		}
		else {
			//check if dead cell has enough neighbors to come back to life
			if(this.liveNeighborsCount(y, x) == 3) {
				return this.LIVE;
			}
			else {
				return this.DEAD;
			}
		}
	}
	
	private int liveNeighborsCount(int y, int x) {
		return this.northWestCell(y, x) + this.northCell(y, x) + this.northEastCell(y, x) + this.westCell(y, x) + this.eastCell(y, x) + this.southWestCell(y, x) + this.southCell(y, x) + this.southEastCell(y, x);
	}
	
	private boolean isCellOutOfBounds(int y, int x) {
		if(0 <= y && 0 <= x && this.yLen > y && this.xLen > x) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private int northWestCell(int y, int x) {
		if(!this.isCellOutOfBounds(y-1, x-1) && Character.compare(this.map[y-1][x-1], this.LIVE) == 0) {
			return 1;
		}
		else {
			return 0; //if out of bounds or has no neighbors
		}
	}
	
	private int northCell(int y, int x) {
		if(!this.isCellOutOfBounds(y-1, x) && Character.compare(this.map[y-1][x], this.LIVE) == 0) {
			return 1;
		}
		else {
			return 0; //if out of bounds or has no neighbors
		}
	}
	
	private int northEastCell(int y, int x) {
		if(!this.isCellOutOfBounds(y-1, x+1) && Character.compare(this.map[y-1][x+1], this.LIVE) == 0) {
			return 1;
		}
		else {
			return 0; //if out of bounds or has no neighbors
		}
	}
	
	private int westCell(int y, int x) {
		if(!this.isCellOutOfBounds(y, x-1) && Character.compare(this.map[y][x-1], this.LIVE) == 0) {
			return 1;
		}
		else {
			return 0; //if out of bounds or has no neighbors
		}
	}
	
	private int eastCell(int y, int x) {
		if(!this.isCellOutOfBounds(y, x+1) && Character.compare(this.map[y][x+1], this.LIVE) == 0) {
			return 1;
		}
		else {
			return 0; //if out of bounds or has no neighbors
		}
	}
	
	private int southWestCell(int y, int x) {
		if(!this.isCellOutOfBounds(y+1, x-1) && Character.compare(this.map[y+1][x-1], this.LIVE) == 0) {
			return 1;
		}
		else {
			return 0; //if out of bounds or has no neighbors
		}
	}
	
	private int southCell(int y, int x) {
		if(!this.isCellOutOfBounds(y+1, x) && Character.compare(this.map[y+1][x], this.LIVE) == 0) {
			return 1;
		}
		else {
			return 0; //if out of bounds or has no neighbors
		}
	}
	
	private int southEastCell(int y, int x) {
		if(!this.isCellOutOfBounds(y+1, x+1) && Character.compare(this.map[y+1][x+1], this.LIVE) == 0) {
			return 1;
		}
		else {
			return 0; //if out of bounds or has no neighbors
		}
	}
}
