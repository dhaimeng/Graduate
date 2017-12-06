package process;

public class Time{
	
	int year;
	int month;
	int hour;
	int day;
	double minute;
	//2015-01-27T20:09:32.720
	public Time(String YMDM) {
		// TODO Auto-generated constructor stub
		year=Integer.parseInt(YMDM.substring(0,4));
		month=Integer.parseInt(YMDM.substring(5,7));
		day=Integer.parseInt(YMDM.substring(8,10));
		hour=Integer.parseInt(YMDM.substring(11,13));
		minute=Integer.parseInt(YMDM.substring(14,16));
	}

	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public int getHour() {
		return hour;
	}

	public double getMinute() {
		return minute;
	}
	
}
