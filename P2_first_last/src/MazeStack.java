import java.util.ArrayList;

public class MazeStack {
    private ArrayList<Tile> stack;

    // Constructor
    public MazeStack() {
        stack = new ArrayList<>();
    }

    // Push a tile onto the stack
    public void push(Tile tile) {
        stack.add(tile);
    }

    // Pop a tile from the stack
    public Tile pop() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}