package problems;
class Container{
	public long counter = 0;
	public boolean flag = true;
	public void increment(){
		//System.out.println("counter: " + counter);
		counter++;
	}
	public long getCounter(){
		return counter;
	}
	public boolean getFlag(){
		return flag;
	}
	public void setFlag(boolean flag){
		this.flag = flag;
	}
}
class Incr1 implements Runnable{
	public Incr1(Container c) {
		this.c = c;
	}
	Container c;

	public void run() {
		for (int i=0;i< 2_000_000;i++){
			synchronized(c){
				while (c.getFlag() == true){
					try {
						c.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			c.increment();
			c.setFlag(true);
			c.notify();
			}
		}
	}
}
class Incr2 implements Runnable{
	public Incr2(Container c) {
		this.c = c;
	}
	Container c;
	public void run(){
		for (int i=0;i< 2_000_000;i++){
			synchronized(c){
				while (c.getFlag() == false){
					try {
						c.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			c.increment();
			c.setFlag(false);
			c.notify();
			}
		}	
	}
}
public class RaceCondition{
	public static void main(String[] args) throws InterruptedException {
		Container c = new Container();
		Thread t1 = new Thread(new Incr1(c));
		Thread t2 = new Thread(new Incr2(c));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(c.getCounter());

	}

}



