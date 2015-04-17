class Elem {
	public int inf;
	public Elem next;
	public Elem previous;

	public Elem(int inf_) {
		inf = inf_;
	}
}

public class OnOfCollectionImpl implements OnOfCollection {
	public Elem first;
	public Elem last
;	public OnOfCollectionImpl() {
		first = null;
		last = null;
	}

	public void add(int x) {
		if (hasItem(x) == false){
			Elem newLink = new Elem(x);
			if (isEmpty()) {
				first = newLink;
			} else {
				last.next = newLink;
				newLink.previous = last;
			}
			last = newLink;
		}
		else
			deleteKey(x);
	}
	public boolean hasItem(int element){
		Elem current = first;
		if (current == null)
			return false;
		else{
			while(current.inf != element){
				current = current.next;
				if (current == null)
					return false;
			}
			return true;
		}
	}

	public boolean isEmpty() {
		return first == null;
	}
	public void deleteKey(int key){
		Elem current = first;
		while (current.inf != key) {
			current = current.next;
			if (current == null){
				break;
			}
		}
		if (current == first) {
			first = current.next;
		} else {
			current.previous.next = current.next;
		}

		if (current == last) {
			last = current.previous;
		} else {
			current.next.previous = current.previous;
		}
	}

	public String print() {
		String str = "";
		Elem current = first;
		while (current != null) {
			str += current.inf;
			current = current.next;
		}
		return str;
	}

	public static void main(String[] args) {
		OnOfCollectionImpl c = new OnOfCollectionImpl();
		c.add(1);
		c.add(2);
		c.add(3);
		c.add(4);
		c.add(2);
		c.add(4);
		System.out.print(c.print());
		//System.out.print(c.isEmpty());
	}

}
