public class Map {
    private Tile[][] tiles;
    private int rows;
    private int cols;

    public Map(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.tiles = new Tile[rows][cols];
    }

    public void setTile(int row, int col, String type) {
        tiles[row][col] = new Tile(row, col, type);
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
} 