public class LinkedStack extends DLinkedListImpl{
	public Link last;
	public LinkedStack() {
		first = null;
		last = null;
	}
	public void push(int x){
		insertLast(x);
	}
	public void pop(){
		deleteLast();
	}
	public int peek(){
		return last.iData;
	}
	public boolean empty(){
		return isEmpty();
	}
	public int length(){
		int count = 0;
		while(!empty()){
			count ++;
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
		LinkedStack ls = new LinkedStack();
		ls.push(1);
		ls.push(2);
		ls.push(3);
		//ls.pop();
		//System.out.print(ls.peek());
		//System.out.print(ls.length());
		//System.out.print(ls.isEmpty());
		ls.print();
	}

}
