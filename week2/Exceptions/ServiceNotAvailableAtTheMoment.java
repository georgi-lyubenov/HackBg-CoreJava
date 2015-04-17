class Service{
	Service(){
	}
	
	public void useService() throws ServiceNotAvailableAtTheMoment{
		throw new ServiceNotAvailableAtTheMoment();
	}
}

public class ServiceNotAvailableAtTheMoment extends Exception {
	private static final long serialVersionUID = 1L;
	public static String message = "network connection probably went down";
	public ServiceNotAvailableAtTheMoment() {
		super(message);
	}

	public static void main(String[] args) {
		Service s = new Service();
		try{
			s.useService();
		}
		catch( ServiceNotAvailableAtTheMoment se){
			se.printStackTrace();
		}

	}

}
