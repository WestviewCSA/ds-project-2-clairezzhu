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
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			int rowIndex = 0;
			scanner.nextLine(); // Consume the leftover newline after reading integers

            // Read the maze
            char[][] maze = new char[numRows][numCols];
            int startRow = -1, startCol = -1;
            int endRow = -1, endCol = -1;

            for (int i = 0; i < numRows; i++) {
                String row = scanner.nextLine().trim();
                for (int j = 0; j < numCols; j++) {
                    maze[i][j] = row.charAt(j);
                    if (maze[i][j] == 'W') {
                        startRow = i;
                        startCol = j;
                    } else if (maze[i][j] == '$') {
                        endRow = i;
                        endCol = j;
                    }
                }
            }
//            
//			//process the map!
//			while(scanner.hasNextLine()) {
//				//grab a line (one row of the map)
//				String row = scanner.nextLine();
//				System.out.println(row);
//				
//				if(row.length() > 0) {
//					for(int i = 0; i<numCols && i<row.length(); i++) {
//						char el = row.charAt(i);
//						Tile obj = new Tile(rowIndex, i, el);
//					}
//				}
//			}
		}catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

}
