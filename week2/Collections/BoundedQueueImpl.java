class Node {
	public int value;
	public Node next;

	public Node(int value) {
		this.value = value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node next() {
		return next;
	}
}

public class BoundedQueueImpl{
	public Node root;
	public int capacity;
	public int elements = 0;
	public BoundedQueueImpl(int value) {
		capacity = value;
	}

	public void add(int value) {
		Node node = new Node(value);
		node.setNext(root);
		root = node;
		elements ++;
	}

	public Node poll() {
		Node node = root;
		Node previous = null;
		while (node.next() != null) {
			previous = node;
			node = node.next();
		}
		node = previous.next();
		previous.setNext(null);
		return node;
	}
	public void offer(int value){
		this.add(value);
		if (elements > capacity)
			this.poll();
	}
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(root.value);
        while(root.next() != null){
        	s.append(root.next().value);
        	root = root.next();
        }
        return s.toString();
	}
	public static void main(String[] args) {
		BoundedQueueImpl bq = new BoundedQueueImpl(3);
		bq.offer(1);
		bq.offer(2);
		bq.offer(3);
		bq.offer(4);
		bq.offer(5);
		bq.offer(6);
		System.out.print(bq.toString());
	}
}
