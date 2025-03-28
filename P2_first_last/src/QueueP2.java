import java.util.LinkedList;

/*
 * new class to manage the queue method
 * queue will hold position of elements (in linkedlist formatting)
 */
public class QueueP2 {
	//instance variables
	private LinkedList<Position> queue;
	
	
	public QueueP2() {
		queue = new LinkedList<>();
	}
	
	
	//add position of the Wolverine to the end of the queue
	public void enqueue(Position pos) { //implement position later to help us locate the Wolverine
		queue.add(pos);
	}
	
	
	//remove position of the Wolverine from the front of the queue
	public Position dequeue() {
		//works as long queue isn't empty
		if(!isEmpty()) {
			return queue.remove();
		}
		return null;
	}
	
	
	//helps check if queue is empty or not (return true if it is empty)
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
}