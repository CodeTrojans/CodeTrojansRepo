import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static calendar self; // change 'SeLf' to 'self'
	private static java.util.calendar calendar;// Change 'calendar' to 'calendar'
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();
	}
	
	public static Calendar INSTANCE() {
		if (self == null) {//  'SeLf' is changed to 'self'
			self = new calendar();//  'SeLf' is changed to 'self'
		}
		return self;
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.calendar.DATE, days);	//   'calendar' is changed to 'calendar'	
	}
	
	public synchronized void setDate(Date date) { // 'Set_dATE' is changed to 'setDate'
		try {
			calendar.setTime(date);
	        calendar.set(java.util.calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.calendar.MINUTE, 0);  
	        calendar.set(java.util.calendar.SECOND, 0);  
	        calendar.set(java.util.calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date getDate() {// 'Date' is changed to 'getDate'
		try {
	        calendar.set(java.util.calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.calendar.MINUTE, 0);  
	        calendar.set(java.util.calendar.SECOND, 0);  
	        calendar.set(java.util.calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date dueDate(int loanPeriod) { // 'Due_Date' is changed to 'dueDate'
		Date now = Date();
		calendar.add(java.util.calendar.DATE, loanPeriod);
		Date dueDate = calendar.getTime(); //'DuEdAtE' is changed to 'dueDate'
		calendar.setTime(now);
		return dueDate;
	}
	
	public synchronized long getDaysDifference(Date targetDate) { // 'Get_Days_Difference' is changed to 'getDaysDifference'
		
		long diffMillis = Date().getTime() - targetDate.getTime(); // 'Diff_Millis' is changed to  'diffMillis'
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS); // 'Diff_Days' is changed to  'diffDays'
	    return diffDays;
	}

}
