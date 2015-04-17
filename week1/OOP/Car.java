
public class Car {
	
	public int mileage = 0;
	public int hp = 0;
	Car(){
	}
	public Car(int mileAge, int horsePower) {
		mileage = mileAge;
		hp = horsePower;
	}
	public int get_mileage(){
		return mileage;
	}
	public int get_hp(){
		return hp;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public static void main(String[] args) {
		Car c = new Car(100, 150);
		System.out.println(c.get_mileage());
		Audi a = new Audi(50, 100);
		a.setEngine(1700);
		System.out.print(a.engine);
		System.out.print(a.hp);

	}
}

class Audi extends Car{
	public int engine;
	public Audi() {
		super();
	}
	public Audi(int mileAge, int horsePower) {
		super(mileAge, horsePower);
	}
	public int getEngine() {
		return engine;
	}
	public void setEngine(int engine) {
		this.engine = engine;
	}	
}


