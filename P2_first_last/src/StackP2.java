import java.util.Stack;


public class StackP2{
    
	public Stack<Position> stack; //stack to store the optimal path

	public StackP2(){
		stack = new Stack<>();
	}
	
	public void push(Position pos){
		stack.push(pos);
	}
	
	public Position pop(){
		if (!isEmpty()) {
			return stack.pop();
		}
		return null;
	}
	
	public boolean isEmpty(){
		return stack.isEmpty();
	}
	
	
}