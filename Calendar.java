import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; // change 'SeLf' to 'self'
	private static java.util.Calendar calendar;// Change 'CaLeNdAr' to 'calendar'
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();// 'CaLeNdAr' is changed to 'calendar'
	}
	
	public static Calendar INSTANCE() {
		if (self == null) {//  'SeLf' is changed to 'self'
			self = new Calendar();//  'SeLf' is changed to 'self'
		}
		return self;
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);	//   'CaLeNdAr' is changed to 'calendar'	
	}
	
	public synchronized void Set_dATE(Date date) { // 'Set_dATE' is changed to 'setDate'
		try {
			CaLeNdAr.setTime(date);
	        CaLeNdAr.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        CaLeNdAr.set(java.util.Calendar.MINUTE, 0);  
	        CaLeNdAr.set(java.util.Calendar.SECOND, 0);  
	        CaLeNdAr.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
	        CaLeNdAr.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        CaLeNdAr.set(java.util.Calendar.MINUTE, 0);  
	        CaLeNdAr.set(java.util.Calendar.SECOND, 0);  
	        CaLeNdAr.set(java.util.Calendar.MILLISECOND, 0);
			return CaLeNdAr.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date Due_Date(int loanPeriod) {
		Date NoW = Date();
		CaLeNdAr.add(java.util.Calendar.DATE, loanPeriod);
		Date DuEdAtE = CaLeNdAr.getTime();
		CaLeNdAr.setTime(NoW);
		return DuEdAtE;
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long Diff_Millis = Date().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
