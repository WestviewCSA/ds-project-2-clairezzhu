public class Position {
	public int room;
	public int row;
	public int col;
	public String character; 
	public Position previous;

	public Position() {
		row = -1;
		col = -1;
		room = -1;
	}
	public Position(int room, int row, int col, String c) {
		this.row = row;
		this.col = col;
		this.room = room;
		character = c;
		
	}
	
	public Position(int room, int row, int col, String c, Position p) {
		this.room = room;
		this.row = row;
		this.col = col;
		character = c;
		previous = p;
	}
	
	public String toString() {
		return "{"	+ character + ", " + row + ", " + col + ", " + room + "}";
	}
	
	public boolean isValid() {
		return row != -1; //valid row
	}
	public boolean isVisited(int row, int col, String[][] map, boolean visited[][]) { 
		//check if position exists and has ./$/|
		//return true if location hasn't been visited
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length 
        		&& (map[row][col].equals(".") || map[row][col].equals("$") || map[row][col].equals("|")) && !visited[row][col]; 
    }
	
	//queue enqueue
	public void enqueueNeighbors(MazeQueue queue, String[][] map, boolean[][] visited) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //north, south, west, east

        for (int[] dir : directions) {
            int newRow = row + dir[0]; //change row
            int newCol = col + dir[1]; //change col

            if (isVisited(newRow, newCol, map, visited)) {
            	//store neighbors
                Position newPos = new Position(room, newRow, newCol, map[newRow][newCol], this);
                queue.enqueue(newPos);
                visited[newRow][newCol] = true;

            }
        }
    }
	
	//stack push
	public void pushNeighbors(MazeStack stack, String[][] map, boolean[][] visited) {
		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //north, south, west, east

        for (int[] dir : directions) {
            int newRow = row + dir[0]; //change row
            int newCol = col + dir[1]; //change col

            if (isVisited(newRow, newCol, map, visited)) {
            	//store neighbors
                Position newPos = new Position(room, newRow, newCol, map[newRow][newCol], this);
                stack.push(newPos);
                visited[newRow][newCol] = true;

            }
        }
	}

}