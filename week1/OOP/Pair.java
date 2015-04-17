
public final class Pair {
	public final int member1;
	public final int  member2;
	
	public Pair(int x, int y) {
		member1 = x;
		member2 = y;
	}

	public int getFirst(){
		return member1;
	}
	
	public int getSecond(){
		return member2;
	}
	
	public String toString(){
		return String.format("The pair contains %d and %d", member1, member2);
	}
	
	@Override
	public boolean equals(Object other){
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Pair))return false;
	    Pair otherPair = (Pair)other;
	    return member1 == otherPair.member1 && member2 == otherPair.member2;
	}
	public static void main(String[] args) {
		Pair p = new Pair(2,3);
		System.out.println(p.getFirst());
		System.out.println(p.toString());
		Pair a = new Pair(3,3);
		System.out.println(p.equals(a));

	}

}
