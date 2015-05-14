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
	public int elements = 0;
	public int capacity;
	public boolean available = true;

	public BlockingQueueImpl(int value) {
		capacity = value;
	}

	public synchronized void add(int value) {
		while (elements > capacity) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Node node = new Node(value);
		node.setNext(root);
		root = node;
		elements++;
		notifyAll();
	}

	public synchronized Node poll() {
		Node node = root;
		Node previous = null;
		while (elements < 0 ) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		while(node.next() != null){
			previous = node;
			node = node.next();
			node = previous.next();
			previous.setNext(null);
		}
		elements --;
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
		while (true) {
			// System.out.println("poll ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Consumer poll " + bq.poll().getValue());
			System.out.println("");
		}
	}
}

class ThreadOffer implements Runnable {
	BlockingQueueImpl bq;

	ThreadOffer(BlockingQueueImpl bq) {
		this.bq = bq;
	}

	public void run() {
		int i = 1;
		while (true) {
			bq.offer(i);
			System.out.println("Producer add  " + i);
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class BlockingQueue {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueueImpl bq = new BlockingQueueImpl(1);
		Thread t1 = new Thread(new ThreadPoll(bq));
		Thread t2 = new Thread(new ThreadOffer(bq));
		t1.start();
		t2.start();
	}
}
