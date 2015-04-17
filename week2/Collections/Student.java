import java.util.*;

public class Student implements Comparable<Student>, Comparator<Student> {
	private String name;
	private int grade;
	Student(){};	
	Student(String n, int gr) {
		name = n;
		grade = gr;
	}

	public String getName(){
		return name;
	}

	public int getGrade(){
		return grade;
	}
	
	public int compareTo(Student st){
		return (this.name).compareTo(st.name);
	}
	public int compare(Student st1, Student st2){
	    return st1.getGrade() - st2.getGrade();
	}
	
	public static void main(String[] args) {
		List<Student> l = new LinkedList<Student>();
		l.add(new Student("Ivan", 6));
		l.add(new Student("Georgi", 5));
		l.add(new Student("Nikolay", 4));
		l.add(new Student("Biser", 5));
		Collections.sort(l, new Student());
		System.out.print("");
	    for(Student st: l)
	    	System.out.print(st.getName() + ": " + st.getGrade() + ", ");
	}

}
