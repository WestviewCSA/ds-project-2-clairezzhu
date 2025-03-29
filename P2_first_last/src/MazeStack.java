import java.util.Stack;


public class MazeStack {
    
	public Stack<Position> stack; //stack to store the optimal path

	public MazeStack() {
		stack = new Stack<>();
	}
	
	public void push(Position pos) {
		stack.push(pos);
	}
	
	public Position pop() {
		if (!isEmpty()) {
			return stack.pop();
		}
		return null;
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	
}