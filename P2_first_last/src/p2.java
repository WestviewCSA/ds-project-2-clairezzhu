import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("p2");
		
		readMap("Test_01");
	}
	
	public static void readMap(String filename) {
		
		try {
			File file = new File(filename);
	        Scanner scanner = new Scanner(file);
	        int rows = scanner.nextInt();
	        int cols = scanner.nextInt();
	        int levels = scanner.nextInt();
	        scanner.nextLine();
	        Map map = new Map(rows, cols, levels);

	        for (int l = 0; l < levels; l++) {
	            for (int r = 0; r < rows; r++) {
	                String row = scanner.nextLine();
	                for (int c = 0; c < cols; c++) {
	                    char el = row.charAt(c);
	                    map.setTile(l, r, c, el);
	                }
	            }
	        }
	        scanner.close();
	        return map;
	        
//			File file = new File(filename);
//			Scanner scanner = new Scanner(file);
//			
//			int numRows  = scanner.nextInt();
//			int numCols  = scanner.nextInt();
//			int numRooms = scanner.nextInt();
//			
//			int rowIndex = 0;
//			int roomIndex = 0;
//			
//			//initalize the tile array
//			Tile[][][] tiles = new Tile[numRows][numCols][numRooms];
//			
//			//process the map!
//			while (scanner.hasNextLine()) {
//				
//				//grab a line (one row of the map)
//				String row = scanner.nextLine();
//				System.out.println(row);
//				
//				//only run if the line is not empty
//				if (row.length()>0) {
//					
//					for (int colIndex = 0; colIndex < numCols && colIndex < row.length(); colIndex++) {
//	                    char el = row.charAt(colIndex);
//	                    
//	                    // Create a new tile for each character
//	                    tiles[rowIndex][colIndex][roomIndex] = new Tile(rowIndex, colIndex, el);
//	                    
//	                    // Print information about the current tile (for debugging)
//	                    System.out.println("Tile at (" + rowIndex + ", " + colIndex + ", " + roomIndex + "): " + el);
//
//	                    // Handle room change if we encounter the '|' character
//	                    if (el == '|') {
//	                        roomIndex++;
//	                        if (roomIndex >= numRooms) {
//	                            roomIndex = numRooms - 1;  // Ensure room index doesn't go out of bounds
//	                        }
//	                    }
//	                }
//	                
//	                rowIndex++;  // Move to the next row
//	            }
//
//	            // After reading all the rows, check if the map dimensions match
//	            if (rowIndex != numRows) {
//	                System.out.println("Warning: The number of rows in the map does not match the expected number of rows.");
//	            }
//	            
//	            // Close the scanner to avoid resource leaks
//	            scanner.close();
//			}
			
			
		}catch (FileNotFoundException e){
			System.out.println(e);
		}

					
					
					//search for each element in the row and make sure it doesn't go out of bounds
//					for(int i = 0; i<numCols && i<row.length(); i++) {
//						char el = row.charAt(i);
//						Tile obj = new Tile(rowIndex, i, el);
//						tiles[rowIndex][i][roomIndex] = obj;
//						
//						//find the (x,y) coordinates of the "W"
//						if(obj.getType() == 'W') {
//							//System.out.println("(" + i + ", " + rowIndex + ")");
//							wX = i;
//							wY = rowIndex;
//						}
//						
//						if(obj.getType() == '$') {
//							$X = i;
//							$Y = rowIndex;
//						}
//						
//						if(obj.getType() == '|') {
//							if(!roomIncremented) {
//								roomIndex++;
//								roomIncremented = true;
//							}
//						}
//						System.out.println(tiles);
	}
	
	private static boolean Queue(Map map) {
        Queue<Tile> queue = new LinkedList<>();
        queue.add(map.start);
        Set<String> visited = new HashSet<>();
        visited.add(map.start.row + "," + map.start.col + "," + map.start.level);

        int[] dr = {-1, 1, 0, 0, 0, 0};
        int[] dc = {0, 0, -1, 1, 0, 0};
        int[] dl = {0, 0, 0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Tile current = queue.poll();
            if (current.row == map.goal.row && current.col == map.goal.col && current.level == map.goal.level) {
                return true;
            }

            for (int i = 0; i < 6; i++) {
                int newRow = current.row + dr[i];
                int newCol = current.col + dc[i];
                int newLevel = current.level + dl[i];
                String key = newRow + "," + newCol + "," + newLevel;

                if (newRow >= 0 && newRow < map.rows && newCol >= 0 && newCol < map.cols && newLevel >= 0 && newLevel < map.levels && map.getTile(newLevel, newRow, newCol) != '#' && !visited.contains(key)) {
                    visited.add(key);
                    queue.add(new Tile(newRow, newCol, newLevel, map.getTile(newLevel, newRow, newCol)));
                }
            }
        }
        return false;
    }
	
	private static boolean Stack(Map map) {
        Stack<Tile> stack = new Stack<>();
        stack.push(map.start);
        Set<String> visited = new HashSet<>();
        visited.add(map.start.row + "," + map.start.col + "," + map.start.level);

        int[] dr = {-1, 1, 0, 0, 0, 0};
        int[] dc = {0, 0, -1, 1, 0, 0};
        int[] dl = {0, 0, 0, 0, -1, 1};

        while (!stack.isEmpty()) {
            Tile current = stack.pop();
            if (current.row == map.goal.row && current.col == map.goal.col && current.level == map.goal.level) {
                return true;
            }

            for (int i = 0; i < 6; i++) {
                int newRow = current.row + dr[i];
                int newCol = current.col + dc[i];
                int newLevel = current.level + dl[i];
                String key = newRow + "," + newCol + "," + newLevel;

                if (newRow >= 0 && newRow < map.rows && newCol >= 0 && newCol < map.cols && newLevel >= 0 && newLevel < map.levels && map.getTile(newLevel, newRow, newCol) != '#' && !visited.contains(key)) {
                    visited.add(key);
                    stack.push(new Tile(newRow, newCol, newLevel, map.getTile(newLevel, newRow, newCol)));
                }
            }
        }
        return false;
    }


	//add the error messages
	class IllegalCommandLineInputsException extends Exception {
	    public IllegalCommandLineInputsException(String message) {
	        super(message);
	    }
	}

	class IllegalMapCharacterException extends Exception {
	    public IllegalMapCharacterException(String message) {
	        super(message);
	    }
	}

	class IncompleteMapException extends Exception {
	    public IncompleteMapException(String message) {
	        super(message);
	    }
	}

	class IncorrectMapFormatException extends Exception {
	    public IncorrectMapFormatException(String message) {
	        super(message);
	    }
	}

}
