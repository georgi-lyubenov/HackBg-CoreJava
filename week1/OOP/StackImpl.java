class MyException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public static String myMessage = "This element is already in the stack";
	public MyException(){
		super(myMessage);
	}
}
public class StackImpl implements Stack{
	private static final int MAX = 100;
	private int[] stack;
	private int last;
	
	public StackImpl() {
		stack = new int[MAX];
		last = -1;
	}
	public boolean unique(int element){
		for (int i = 0; i < stack.length; i ++){
			if (stack[i] == element)
				return false;
		}
		return true;
	}
	public void push(int x) throws MyException {
		if (unique(x))
			stack[++last] = x;
		else{
			throw new MyException();
		}
	}
	public void pop(){
		last--;
	}
	public int peek() {
		return stack[last];
	}
	public boolean empty() {
		return last == -1;
	}
	public boolean isFull() {
	    return (last == MAX - 1);
	}
	public int length(){
		int count = 0;
		while (!empty()){
			count++;
			pop();
		}
		return count;
	}
	public void print(){
		while(!empty()){
			System.out.print(peek());
			pop();
		}
	}
	     
	public static void main(String[] args) {
		StackImpl s = new StackImpl(); 
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(2);
		s.push(4);
		s.push(5);
		s.push(6);
		s.pop();
		//System.out.print(s.length());
		s.print();
		//System.out.println(s.empty());
		//System.out.println(s.isFull());
		
	}

}
