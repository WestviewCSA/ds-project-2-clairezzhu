import java.util.Stack;

//new class to manage the stack method - last in, first out
public class StackP2 {
	public Stack<Position> stack;

	public StackP2() {
		stack = new Stack<>();
	}
	
	//adds the Wolverine position to the top of the stack
	public void push(Position pos) { //implement position later
		stack.push(pos);
	}
	
	//remove the Wolverine position from the top of the stack
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
