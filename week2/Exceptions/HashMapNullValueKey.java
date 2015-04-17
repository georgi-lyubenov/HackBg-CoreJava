import java.util.*;

class myHashMap extends HashMap<Object, Object>{
	private static final long serialVersionUID = 1L;
	public boolean flag = false;
	Map<Object, Object> hm = new HashMap<Object, Object>();
	myHashMap(){	
	}
	myHashMap(boolean f){
		flag = f;
	}
	public void setFlag(boolean f){
		flag = f;
	}
	public void throwNullKeyException() throws HashMapNullValueKey{
		throw new HashMapNullValueKey();
	}
	public Object put(Object key, Object value){
		if (key == null){
			if (flag)
				hm.put(key, value);
			else{
				try{
					throwNullKeyException();
				}
				catch(HashMapNullValueKey h){
					h.printStackTrace();
				}
			}
		}
		hm.put(key, value);
		return hm; 
	}
	public Object get(Object key){
		if (key == null){
			if (flag){
				hm.get(key);
			}
			else{
				try{
					throwNullKeyException();
				}
				catch(HashMapNullValueKey h){
					h.printStackTrace();
				}
			}
		}
		hm.get(key);
		return hm;
	}
}



public class HashMapNullValueKey extends Exception {
	private static final long serialVersionUID = 1L;
	public static String message = "The key can't be null";
	public HashMapNullValueKey() {
		super(message);
	}


	public static void main(String[] args) {
		myHashMap a = new myHashMap(true);
		a.put(null, 1);
		System.out.print(a.get(null));
		a.setFlag(false);
		a.get(null);
		
	}

}
