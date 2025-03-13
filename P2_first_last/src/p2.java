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
			
			//figure out how many rows, cols, and rooms there are for the map
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			int rowIndex = 0;
			scanner.nextLine(); // Consume the leftover newline after reading integers
			
			Tile[][][] tiles = new Tile[numRows][numCols][numRooms];
			
			int wX = -1;
			int wY = -1;
			
			int $X = -1;
			int $Y = -1;

			//process the map!
			while(scanner.hasNextLine()) {
				//grab a line (one row of the map)
				String row = scanner.nextLine();
				System.out.println(row);
				
				//there is data in the row
				if(row.length() > 0) {
					//search for each element in the row and make sure it doesn't go out of bounds
					for(int i = 0; i<numCols && i<row.length(); i++) {
						char el = row.charAt(i);
						Tile obj = new Tile(rowIndex, i, el);
						
						//find the (x,y) coordinates of the "W"
						if(obj.getType() == 'W') {
							//System.out.println("(" + i + ", " + rowIndex + ")");
							wX = i;
							wY = rowIndex;
						}
						
						if(obj.getType() == '$') {
							$X = i;
							$Y = rowIndex;
						}
					}
					rowIndex++;
				}
			}
		}catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

}
