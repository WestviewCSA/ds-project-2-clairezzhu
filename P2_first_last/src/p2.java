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
			
			int numRows  = scanner.nextInt();
			int numCols  = scanner.nextInt();
			int numRooms = scanner.nextInt();
			
			int rowIndex = 0;
			int roomIndex = 0;
			int colIndex = 0;
			
			Tile[][][] tiles = new Tile[numRows][numCols][numRooms];
			
			//process the map!
			while (scanner.hasNextLine()) {
				
				//grab a line (one row of the map)
				String row = scanner.nextLine();
				System.out.println(row);
				
				if (row.length()>0) {
					
					for(colIndex = 0; colIndex < numCols && colIndex < row.length(); colIndex++) {
						
						char el = row.charAt(colIndex);
						Tile obj = new Tile(rowIndex, colIndex, el);
						tiles[rowIndex][colIndex][roomIndex] = new Tile(rowIndex, colIndex, el);
						
						System.out.println("Tile at (" + tiles[rowIndex][colIndex][roomIndex].getRow()
								+ ", " + tiles[rowIndex][colIndex][roomIndex].getCol() + ", "
								+ tiles[rowIndex][colIndex][roomIndex].getType() + ")");
						
					}
					
					rowIndex++;
				}
			}
			
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

}
