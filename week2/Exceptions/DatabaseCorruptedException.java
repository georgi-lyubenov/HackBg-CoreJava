class User{
	public String username;
	User(String name){
		username = name;
	}
	public String getUser(){
		return username;
	}
	public void detectUsername() throws DatabaseCorruptedException{
		 if (getUser() == ""){
			 throw new DatabaseCorruptedException();
		 }
	}
}
public class DatabaseCorruptedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public static String myMessage = "You need a username";
	public DatabaseCorruptedException(){
		super(myMessage);
	}

	public static void main(String[] args) {
		User user = new User("");
		try{
			user.detectUsername();
		}
		catch (DatabaseCorruptedException d){
			d.printStackTrace();
		}
	}	

}
