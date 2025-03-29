import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class p2{
	
	public static String[][][] map = null;
	public static boolean needStack = false;
	public static boolean needQueue = false;
	public static boolean needOptimal = false;
	public static boolean needTime = false;
	public static boolean needIncoordinate = false;
	public static boolean needOutcoordinate = false;
	public static boolean needAllCoins = false;

	
	public static long time = 0;
	public static int num = 0;


	public static void main(String[] args) throws IllegalCommandLineInputsException, IllegalMapCharacterException, IncompleteMapException, IncorrectMapFormatException {
		
		//check what argument is inputted
		String inputFile = null;
		for ( String arg : args ) {
			if ( arg.equals("-stack") ) {
				needStack = true;
			} 
			else if ( arg.equals("-queue") ) {
				needQueue = true;
			}
			else if ( arg.equals("-opt") ) {
				needOptimal = true;
			}
			else if ( arg.equals("-time") ) {
				needTime = true;
			}
			else if ( arg.equals("-incoordinate") ) {
				needIncoordinate = true;
			}
			else if ( arg.equals("-outcoordinate") ) {
				needOutcoordinate = true;
			}
			else if ( arg.equals("-findall")) {
				needAllCoins = true;
			}
			else if ( arg.equals("-help") ) {
				printHelpMessage();
				System.exit(0);
			}
			else if ( arg.substring(0).equals("-") ) {
				throw new IllegalCommandLineInputsException();
			} else {
				inputFile = arg;
			}
		}
	
		//check if stack/queue/opt all == true/false
		if ((needStack == false && needQueue == needOptimal) || 
				(needStack == true && (needQueue == true || needOptimal == true))) {
			System.out.println("Choose either stack, queue, or opt");
			System.exit(-1);
		}
		
		if ( inputFile != null ) {
			readFile( inputFile );
			run();
		} else {
			System.err.println( "Invalid input file");
		}
	
		System.out.println();
		System.out.print("Total $ found: " + num);
		if(needTime) {
			System.out.println("Total Runtime: " + time/1.0e9 + " seconds"); //runtime

		}

	}

	public static void run() {
		
		long startTime = System.nanoTime(); //start timer
		
		//call stack/queue method for each map
		Position prevPosition = new Position();
	    for (int roomId = 0; roomId < map.length; roomId++) {
			if ( needStack ) {
				prevPosition = findPathByStack(roomId, prevPosition); //find path by stack
			}
			if ( needQueue || needOptimal ) {
				prevPosition = findPathByQueue(roomId, prevPosition); //find path by queue
			}
			if ( !prevPosition.isValid()) { //check if it's room #1
				break;
			}
	    }
		
		long endTime = System.nanoTime();
		time += endTime - startTime; //end timer
	}
	
	public static void printMap(String[][][] map) {
		//print each character in map
		for (String[][] a : map) {
        	for (String[] b : a) {
        		for (String c : b) {
        			System.out.print(c);
  
        		}

        		System.out.println();
        	}

        }
	}
	
	public static void readFile(String f) throws IllegalMapCharacterException, IncompleteMapException, IncorrectMapFormatException {
		if ( needIncoordinate ) {
			readCoordinate(f); //coordinate input
		} else {
			readMap(f); //text input
		}
		System.out.println("The input map: " );
		printMap(map);
	}
	public static void readCoordinate(String f) {
		try {
			System.out.println("Reading text file " + f + " ...done");
		    File file = new File(f);
		    Scanner scanner = new Scanner(file); 
	        
	        //row of numbers at the top of each map
		    int rows = 0, cols = 0, rooms = 0;
		    try { 
		    	//first row on each map
		    	String line = scanner.nextLine();
		    	String[] chars = line.split(" ");
		    	rows = Integer.valueOf(chars[0]);
                cols = Integer.valueOf(chars[1]);
                rooms = Integer.valueOf(chars[2]);
		    } catch (InputMismatchException e) {
		    	System.err.println("IncorrectMapFormatException");
		    }
      

            map = new String[rooms][rows][cols];
            for (int room = 0; room < rooms; room++) {
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        map[room][row][col] = ".";
                    }
                }
            }
           
            while ( scanner.hasNextLine()) {
            	String line = scanner.nextLine();
            	
                String[] chars = line.split(" "); 
                
                if ( chars.length != 4 ) {
            		throw new IncorrectMapFormatException();
            	}
                
                int row = Integer.valueOf(chars[1]);
                int col = Integer.valueOf(chars[2]);
                int room = Integer.valueOf(chars[3]);
                map[room][row][col] = chars[0];

            }
            
            //print input map
            printMap(map);
       

            scanner.close();

            IllegalMapCharacterException.checkMapCharacters(map); //check for illegal characters
            IncompleteMapException.checkMapCompleteness(map); //check if map is complete
            IncorrectMapFormatException.checkMapFormat(map); //check map format

            

	    } catch (FileNotFoundException e) {
	        System.err.println(e.getMessage());
	    } catch (IllegalMapCharacterException e) {
	        System.err.println(e.getMessage());
	    } catch (IncompleteMapException e) {
	        System.err.println(e.getMessage());
	    } catch (IncorrectMapFormatException e) {
	        System.err.println(e.getMessage());
	    }
	}
	
	public static void readMap(String f) throws IllegalMapCharacterException, IncompleteMapException, IncorrectMapFormatException { //be able to print multiple maps
						
		try {
			System.out.println("Reading text file " + f + " ...done");
		    File file = new File(f);
		    Scanner scanner = new Scanner(file); 
	        
	        //row of numbers at the top of each map
		    int rows = 0, cols = 0, rooms = 0;
		    try { 
		    	//first row on each map
		    	rows = scanner.nextInt();
                cols = scanner.nextInt();
                rooms = scanner.nextInt();
		    } catch (InputMismatchException e) {
		    	System.err.println("IncorrectMapFormatException");
		    }
      
            scanner.nextLine(); 

            map = new String[rooms][rows][cols];
           

            for (int room = 0; room < rooms; room++) {
                for (int row = 0; row < rows; row++) {
                    String line = scanner.nextLine();
                    for (int col = 0; col < cols; col++) {
                        map[room][row][col] = String.valueOf(line.charAt(col)); //find character at the location
                    }
                }
            }  

            scanner.close();

            IllegalMapCharacterException.checkMapCharacters(map); //check for illegal characters
            IncompleteMapException.checkMapCompleteness(map); //check if map is complete
            IncorrectMapFormatException.checkMapFormat(map); //check map format

            

	    } catch (FileNotFoundException e) {
	        System.err.println(e.getMessage());
	    } catch (IllegalMapCharacterException e) {
	        System.err.println(e.getMessage());
	    } catch (IncompleteMapException e) {
	        System.err.println(e.getMessage());
	    } catch (IncorrectMapFormatException e) {
	        System.err.println(e.getMessage());
	    }
	}
	
	public static Position findW(String[][] room, int roomId) {
	    int rows = room.length;
	    int cols = room[0].length;
	    for (int r = 0; r < rows; r++) {
	        for (int c = 0; c < cols; c++) {
	            if (room[r][c].equals("W")) {
	                return new Position(roomId, r, c, "W"); //first Position
	            }
	        }
	    }
	    return null; //return null if 'W' is not found in the room
	}

	public static Position findPathByQueue(int roomId, Position prevPosition) { //bfs
	    MazeQueue queue = new MazeQueue();
	    String[][] room = map[roomId];
	    boolean[][] visited = new boolean[room.length][room[0].length];
	
	    //find starting position
	    Position start = findW(room, roomId);
	   	if (start == null) {
	   		System.out.println("W not found");
	   		return prevPosition;
	   	}
	    if ( prevPosition.isValid()  ) {
	    	start.previous = prevPosition; //set new prevPosition
	    }
	    
	   	queue.enqueue(start); 

	 	visited[start.row][start.col] = true;
		   
	    //traverse until coin is found
	    while (!queue.isEmpty()) { 
	    		       
	   		Position current = queue.dequeue();

	        //check if current position is the coin
	        if (current.character.equals("$")) {
	        	num++;
	            tracePath(start, current);
	            if(!needAllCoins) { //only find closest $
	        	   break;
	            }
	           //else continue tracing if there's multiple $
	        }
	        else if (current.character.equals("|")) {
	        	prevPosition = current;
	        	break;
	        }
	    		        
	        //enqueue the neighbors of the current position onto the queue
            current.enqueueNeighbors(queue, room, visited);
   	    }
	    return prevPosition;
	}
	
	public static Position findPathByStack(int roomId, Position prevPosition) { //dfs
		MazeStack stack = new MazeStack();
	    String[][] room = map[roomId];
	    boolean[][] visited = new boolean[room.length][room[0].length];
	
	    //find starting position
	    Position start = findW(room, roomId);
	   	if (start == null) {
	   		System.out.println("W not found");
	   		return prevPosition;
	   	}
	    if ( prevPosition.isValid()  ) {
	    	start.previous = prevPosition;
	    }
	    
	   	stack.push(start); 

	 	visited[start.row][start.col] = true;
		   
	    //traverse until coin is found
	    while (!stack.isEmpty()) { 
	    		       
	   		Position current = stack.pop();

	        //check if current position is the coin
	        if (current.character.equals("$")) {
	        	num++;
	            tracePath(start, current);
	            if(!needAllCoins) { //only find closest $
	        	   break;
	            }
	           //continue tracing if there's multiple $
	        }
	        else if (current.character.equals("|")) {
	        	prevPosition = current;
	        	break;
	        }
	    		        
	        //enqueue the neighbors of the current position onto the queue
            current.pushNeighbors(stack, room, visited);
   	    }
	    return prevPosition;
	}
	
	
	private static void tracePath(Position start, Position end) {
	    List<Position> path = new ArrayList<>();

	    Position current = end;
	    // while (!current.equals(start)) {
	    while ( current != null && current.isValid() ) {
	        path.add(current);
	        current = current.previous; //trace back previous positions
	    }
	    
	    System.out.println();
	    System.out.println("Traced path #" + num + ":");
	    
	    if(needOutcoordinate) {
	    	//print traced path in reverse order
		    for (int i = path.size() - 1; i >= 0; i--) {
		        System.out.println(path.get(i).toString());
		    }
	    } else {
		    //change character to +
		    for (Position curPath : path) {
		    	map[curPath.room][curPath.row][curPath.col]= "+"; 
		    }
		    printMap(map);
	    }
	   
	    
	}
	
	public static void printHelpMessage() { //when help arg is called
	    System.out.println("options:");
	    System.out.println("-stack\t\tuse the stack-based approach for maze solving");
	    System.out.println("-queue\t\tuse the queue-based approach for maze solving");
	    System.out.println("-opt\t\tfind the shortest path using an optimal algorithm");
	    System.out.println("-time\t\tprint the runtime of the program");
	    System.out.println("-incoordinate\tread the input file in the coordinate-based system");
	    System.out.println("-outcoordinate\twrite the output file in the coordinate-based system");
	    System.out.println("-findall\tfind all the coins in the maze");
	    System.out.println("-help\t\tdisplay this help message and exit");
	}

	public static class IllegalCommandLineInputsException extends Exception {
        public IllegalCommandLineInputsException() {
            super("Illegal command line inputs.");
        }

        public static void checkCommandLineArgs(String[] args) throws IllegalCommandLineInputsException {
            if (args.length <= 1) {
                throw new IllegalCommandLineInputsException();
            }
        }
    }

    // Static class IllegalMapCharacterException
    public static class IllegalMapCharacterException extends Exception {
        public IllegalMapCharacterException() {
            super("Illegal map character detected.");
        }

        public static void checkMapCharacters(String[][][] map) throws IllegalMapCharacterException {
            for (String[][] room : map) {
                for (String[] row : room) {
                    for (String obj : row) {
                        if (!obj.matches("[.@W$| ]")) { // Improved readability using regex
                            throw new IllegalMapCharacterException();
                        }
                    }
                }
            }
        }
    }

    // Static class IncompleteMapException
    public static class IncompleteMapException extends Exception {
        public IncompleteMapException() {
            super("Incomplete map detected.");
        }

        public static void checkMapCompleteness(String[][][] map) throws IncompleteMapException {
            for (String[][] room : map) {
                for (String[] row : room) {
                    for (String obj : row) {
                        if (obj == null) {
                            throw new IncompleteMapException();
                        }
                    }
                }
            }
        }
    }

    // Static class IncorrectMapFormatException
    public static class IncorrectMapFormatException extends Exception {
        public IncorrectMapFormatException() {
            super("Incorrect map format.");
        }

        public static void checkMapFormat(String[][][] map) throws IncorrectMapFormatException {
            if (map == null || map.length == 0 || map[0].length == 0 || map[0][0].length == 0) {
                throw new IncorrectMapFormatException();
            }
        }
    }



}