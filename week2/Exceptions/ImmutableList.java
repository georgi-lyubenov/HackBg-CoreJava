import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class MyException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public static String myMessage = "This is an immutable list. You cant change it's content";
	public MyException(){
		super(myMessage);
	}
}

public class ImmutableList extends ArrayList<Integer> {
	private static final long serialVersionUID = 1L;
	public static List<Object> myList= new ArrayList<Object>();

	public ImmutableList(Collection<Integer> collection) {
		myList.addAll(collection);
	}
	public Integer get(int index){
		int result = 0;
		Object[] arr = new Object[myList.size()];
		arr = myList.toArray();
		for (int i = 0; i < arr.length; i ++){
			if (i == index)
				result = (Integer) arr[i];
		}
		return result;
	}
	public void add(int x) throws MyException{
		throw new MyException();
	}
	public void delete(int x) throws MyException{
		throw new MyException();
	}
	@SafeVarargs
	public static List<Object> asList(Integer... arguments){
		Object[] arr = new Object[myList.size()];
		arr = myList.toArray();
		for (int i = 0; i < arguments.length; i++){
			arr[i] = arguments[i];
		}
		return Arrays.asList(arr);
	}
	public void print(){
		Iterator<Object> it = myList.iterator();
		while (it.hasNext()){
			System.out.print(it.next());
		}
	}

	public static void main(String[] args) {
		Collection<Integer> c =  Arrays.asList(1,2,3,4,5,5,4,3,1);
		ImmutableList l = new ImmutableList(c);
		/*
		try{
			l.delete(2);
		}catch(MyException e){
			e.printStackTrace();
		}
		*/
		//l.print();
		//System.out.print(l.get(2));
		List<Object> testList = ImmutableList.asList(1,2,3);
		Iterator<Object> it = testList.iterator();
		while (it.hasNext()){
			System.out.print(it.next());
		}
	}

}
