public class Tile {
    private int row;
    private int col;
    private String type; // e.g., 'W' for start, '$' for goal, '.' for open path, '#' for wall

    // Constructor
    public Tile(int row, int col, String type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    // Getters and Setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // To check if the tile is passable (e.g., not a wall)
    public boolean isPassable() {
        return !type.equals("#"); // Assuming '#' represents a wall
    }
}