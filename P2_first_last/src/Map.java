//import java.io.*;
//import java.util.*;
//
//public class Map {
//    private char[][] grid;
//    private int rows, cols;
//    private Tile start, goal;
//    
//    public Map(String filename, boolean isCoordinateFormat) throws IOException {
//        loadMap(filename, isCoordinateFormat);
//    }
//
//    private void loadMap(String filename, boolean isCoordinateFormat) throws IOException {
//        Scanner scanner = new Scanner(new File(filename));
//        rows = scanner.nextInt();
//        cols = scanner.nextInt();
//        int numMaps = scanner.nextInt();  // Assuming only one map for now
//        scanner.nextLine(); 
//
//        grid = new char[rows][cols];
//
//        if (isCoordinateFormat) {
//            for (char[] row : grid) Arrays.fill(row, '.');
//            while (scanner.hasNext()) {
//                String[] parts = scanner.nextLine().split(" ");
//                if (parts.length != 4) continue;
//                char type = parts[0].charAt(0);
//                int r = Integer.parseInt(parts[1]);
//                int c = Integer.parseInt(parts[2]);
//
//                grid[r][c] = type;
//                if (type == 'W') start = new Tile(r, c);
//                if (type == '$') goal = new Tile(r, c);
//            }
//        } else {
//            for (int r = 0; r < rows; r++) {
//                String line = scanner.nextLine();
//                for (int c = 0; c < cols; c++) {
//                    grid[r][c] = line.charAt(c);
//                    if (grid[r][c] == 'W') start = new Tile(r, c);
//                    if (grid[r][c] == '$') goal = new Tile(r, c);
//                }
//            }
//        }
//        scanner.close();
//    }
//
//    public void printMap() {
//        for (char[] row : grid) {
//            System.out.println(new String(row));
//        }
//    }
//
//    public char[][] getGrid() { return grid; }
//    public Tile getStart() { return start; }
//    public Tile getGoal() { return goal; }
//    public int getRows() { return rows; }
//    public int getCols() { return cols; }
//}