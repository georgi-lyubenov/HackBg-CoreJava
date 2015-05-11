package problems;

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

class BlockingQueueImpl {
	public Node root;

	public BlockingQueueImpl() {
		root = null;
	}

	public synchronized void add(int value) {
		Node node = new Node(value);
		node.setNext(root);
		root = node;
		notifyAll();
	}

	public Node poll() {
		Node node = root;
		Node previous = null;
		while (node.next() != null) {
			synchronized (this) {
				try {
					wait();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			previous = node;
			node = node.next();
			node = previous.next();
			previous.setNext(null);
		}
		return node;
	}

	public void offer(int value) {
		this.add(value);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(root.value);
		while (root.next() != null) {
			s.append(root.next().value);
			root = root.next();
		}
		return s.toString();
	}

}

class ThreadPoll implements Runnable {
	BlockingQueueImpl bq;

	ThreadPoll(BlockingQueueImpl bq) {
		this.bq = bq;
	}

	public void run() {
		System.out.println("poll");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("poll " + bq.poll().getValue());

	}
}

class ThreadOffer implements Runnable {
	BlockingQueueImpl bq;

	ThreadOffer(BlockingQueueImpl bq) {
		this.bq = bq;
	}

	public void run() {
		bq.offer(5);
		System.out.println("add");
		
	}
}

public class BlockingQueue {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueueImpl bq = new BlockingQueueImpl();
		Thread t1 = new Thread(new ThreadPoll(bq));
		Thread t2 = new Thread(new ThreadOffer(bq));
		t1.start();
		t2.start();
	}
}
