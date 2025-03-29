import java.util.LinkedList;


public class QueueP2{

	private LinkedList<Position> queue;
	
	public QueueP2(){
		queue = new LinkedList<>();
	}
	
	public void enqueue(Position pos){
		queue.add(pos);
	}
	
	public Position dequeue(){
		if(!isEmpty()){
			return queue.remove();
		}
		return null;
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
}