import java.util.Calendar;
import java.util.Date;

public class Time {
	private String currentTime;
	private String currentDate;

	Time() {
	}

	public Time(String time, String date) {
		currentTime = time;
		currentDate = date;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String toString() {
		return String.format("%s, %s", getCurrentTime(), getCurrentDate());
	}

	public String now() {
		Date date = Calendar.getInstance().getTime();
		return date.toString();
	}

	public static void main(String[] args) {
		Time t = new Time("6:50:23", "6:05:1993");
		t.setCurrentTime("10:30:00");
		System.out.println("memorized time and year are: " + t.toString());
		System.out.println("The time at the current moment is: " + t.now());
	}

}
