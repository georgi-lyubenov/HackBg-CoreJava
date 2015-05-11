package test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface ClassInfo {
	String author() default "";
	int revision() default 1;
	boolean checked() default true;
	Class<?>[] related();

}

@ClassInfo(author = "georgi", checked = false, related = {})
public class MyClass {

	public MyClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		MyClass c = new MyClass();
		Object[] obj = c.getClass().getFields();
		for (Object item : obj) {
			System.out.println(item);
		}
		ClassInfo annotation = c.getClass().getAnnotation(ClassInfo.class);
		System.out.println(annotation.author());
		//System.out.println(c.getClass().getAnnotation(ClassInfo.class));

		
	}

}
