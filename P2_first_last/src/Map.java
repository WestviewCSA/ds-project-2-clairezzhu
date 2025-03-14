
public class Map {
	private Tile[][] map;
	
	char[][] maze;
	int rows, cols;
	Tile start, goal;
	
	//initalize the values for the maps
	public Map(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.maze = new char[rows][cols];
	 }
	
	public void setTile(int row, int col, char val) {
		maze[row][col] = val;
		
		if(val == 'W') {
			start = new Tile(row, col, 'W');
		}
		
		if(val == '$') {
			goal = new Tile(row, col, '$');
		}
	}
	
	public char getTile(int row, int col) {
		return maze[row][col];
	}
}
