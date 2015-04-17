import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
public class Main {
	boolean expr(String arg){
		Stack<Character> s = new Stack<Character>();
		char[] inputString = arg.toCharArray();
		int i=0;
		int len = inputString.length - 1;
		while (i <= len){
			if (inputString[i] == '('){
				s.push(inputString[i]);
				i++;
			}
			else{
				if (!s.empty()){
					s.pop();
				}
				else return false;
				i++;
			}
		}
		if (s.empty()){
			return true;
		}
		return false;
	}
	
	static void reverse(Collection<Integer> collection){
		Stack<Integer> s = new Stack<Integer>();
		Iterator<Integer> iterator = collection.iterator();
		while (iterator.hasNext()) {
			s.push(iterator.next());
		}
		collection.clear();
		while(!s.empty()){
			int temp = s.pop();
			collection.add(temp);
		}
	}
	
	public void print(Collection<Integer> collection){
		Iterator<Integer> iterator = collection.iterator();
		while (iterator.hasNext()){
			System.out.print(iterator.next());
		}
	}
		
	public void rotate(Collection<Integer> collection, int rotateStep ){
		if (rotateStep > 0){
			Integer[] arr = collection.toArray(new Integer[collection.size()]);
			collection.clear();
			final int length = arr.length;
		    final int[] rotated = new int[length];
		    for (int i = 0; i < length; i++)
		        rotated[(i + rotateStep) % length] = arr[i];	
		    for (int i = 0;i < rotated.length; i++)
		    	collection.add(rotated[i]);
		}
		else {
			Deque d = new LinkedList();
			Iterator it = collection.iterator();
			while(it.hasNext())
				d.add(it);
			collection.clear();
			for (int i = 0; i < collection.size() - Math.abs(rotateStep); i++){
				collection.add((Integer)d.getLast());
				d.removeLast();
			}
			for (int i = 0; i < Math.abs(rotateStep); i++){
				collection.add((Integer)d.getFirst());
			}
		}
	}

	public int firstUnique(int[] arr){
		OnOfCollectionImpl c = new OnOfCollectionImpl();
		for (int i = 0; i < arr.length; i++){
			c.add(arr[i]);
		}
		return c.first.inf;
	}
		
	public Set<Integer> duplElem(Set<Integer>... args){
		Set<Integer> result = new HashSet<Integer>(args[0]);
		for (int i = 0; i < args.length; i++)
			result.retainAll(args[i]);
		return result;
	}

	public static String converter(Map<String, Integer> hm){
		String result = "";
		Set set = hm.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()) {
	        Map.Entry me = (Map.Entry)i.next();
	        result += me.getKey() + ":" + me.getValue() + ", ";
	    }
	    return result;
	}
	public static Map<String, Integer> Occurences(String text){
		Map<String, Integer> map = new LinkedHashMap<>();
	    for (String w : text.split(" ")) {
	        Integer n = map.get(w);
	        n = (n == null) ? 1 : ++n;
	        map.put(w, n);
	    }
	    return map;
	}
	
	public static void main(String[] args) {
		//String testString = "()())()";
		Main object = new Main();
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		//object.reverse(s);
		//object.rotate(s, 1);
		//object.print(s);
		//System.out.print(object.expr(testString));
		//int[] ints = {1,2,3,4,5,5,4,3,1};
		//System.out.println(object.firstUnique(ints));
	    //Set<Integer> A = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5));
	    //Set<Integer> B = new HashSet<Integer>(Arrays.asList(4, 5, 6));
	    //Set<Integer> C = new HashSet<Integer>(Arrays.asList(5, 6, 7, 8));
	    //System.out.print(object.duplElem(A, B, C));
	    Map<String, Integer> hm = new HashMap<String, Integer>();
	    hm.put("one", 1);
	    hm.put("two", 2);
	    hm.put("three", 3);
	    //System.out.println(Main.converter(hm)); //
	    //String occurencesTest = "Ninjas are all over the place! We are all going to die!";
	    //Map<String, Integer> result = Main.Occurences(occurencesTest);
	    //System.out.println(result.toString());
	    
	}

}
