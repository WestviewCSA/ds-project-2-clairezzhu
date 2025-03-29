import java.util.LinkedList;

public class MazeQueue {
    private LinkedList<Tile> queue;

    // Constructor
    public MazeQueue() {
        queue = new LinkedList<>();
    }

    // Enqueue a tile to the queue
    public void enqueue(Tile tile) {
        queue.addLast(tile);
    }

    // Dequeue a tile from the queue
    public Tile dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}