import java.util.Calendar;
import java.util.Date;

public class Time {
	private String currentTime;
	private String currentDate;

	Time() {
	}
	public void argumentsChecker(String time, String date)throws IllegalArgumentException{
		if (time != ""){
			int hours = Character.getNumericValue(time.charAt(0)) * 10 + 
					Character.getNumericValue(time.charAt(1));
			int minutes = Character.getNumericValue(time.charAt(3)) * 10 + 
					Character.getNumericValue(time.charAt(4));
			int seconds = Character.getNumericValue(time.charAt(6)) * 10 + 
					Character.getNumericValue(time.charAt(7));
			if (hours > 23 || hours < 0 || minutes > 59 || minutes < 0 || seconds > 59 || seconds < 0)
				throw new IllegalArgumentException();
			
		}
		if (date != ""){
			int day = Character.getNumericValue(date.charAt(0)) * 10 + 
					Character.getNumericValue(date.charAt(1));
			int month = Character.getNumericValue(date.charAt(3)) * 10 + 
					Character.getNumericValue(date.charAt(4));
			if (day > 31 || day < 0 || month > 12 || month < 0)
				throw new IllegalArgumentException();
		}
	}
	public Time(String time, String date) throws IllegalArgumentException{
		currentTime = time;
		currentDate = date;
		argumentsChecker(time, date);
	}
	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime)throws IllegalArgumentException{
		this.currentTime = currentTime;
		argumentsChecker(currentTime, "");

	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate)throws IllegalArgumentException {
		this.currentDate = currentDate;
		argumentsChecker(currentDate, "");
	}

	public String toString() {
		return String.format("%s, %s", getCurrentTime(), getCurrentDate());
	}

	public String now() {
		Date date = Calendar.getInstance().getTime();
		return date.toString();
	}

	public static void main(String[] args) {
		try{
		Time t = new Time("06:50:23", "06:05:1993");
		//t.setCurrentTime("22:30:00");
		System.out.println("memorized time and year are: " + t.toString());
		System.out.println("The time at the current moment is: " + t.now());
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}

	}

}

