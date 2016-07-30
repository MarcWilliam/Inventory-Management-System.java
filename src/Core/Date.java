package Core;

import java.io.*;
import java.util.concurrent.*;

/**
 * a class that represents number of days from epoch time 1/1/1970
 *
 * @author Marc Wafik
 */
public final class Date implements Serializable, Comparable<Date> {

	/**
	 * the total amount of days from 1/1/1970 Epoch time
	 */
	private long Minutes;

	/**
	 *
	 */
	public Date() {
		this.Minutes = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
	}

	//==========================================================================================================================================================
	/**
	 * calculates the total amount of days from 1/1/1970 Epoch time
	 *
	 * @param SDate take a parameter of a string value in format of DD/MM/YYYY or D/M/YYYY
	 * @return true if wrong format or false if correct
	 */
	public boolean SetDate(String SDate) {
		if (SDate == null) {
			return true;
		}
		String[] parts = SDate.split("/");  // splits the string to 3 parts  after eatch /
		if (parts.length != 3) {
			return true;
		}
		return SetDate(Long.valueOf(parts[0]), Long.valueOf(parts[1]), Long.valueOf(parts[2]));
	}

	public boolean SetDate(long day, long month, long year) {
		int DaysInEatchMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		if (isLeapYear(year)) {
			DaysInEatchMonth[2] = 29;
		}

		if (year < 1970 || year > 2100 || month < 1 || month > 12 || day < 1 || day > DaysInEatchMonth[(int) month]) {
			return true;
		}

		this.Minutes = TimeUnit.DAYS.toMinutes(((year - 1970) * 365) + day + CountLeapYears(1970, year)) - 1;

		for (int i = 1; i < month; i++) {
			this.Minutes += TimeUnit.DAYS.toMinutes(DaysInEatchMonth[i]);
		}
		return false;
	}

	/**
	 *
	 */
	public void SetDateToCurent() {
		this.Minutes = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
	}

	//==========================================================================================================================================================
	/**
	 * compares the stored Date value with the current time 86,400,000 is 60 sec * 60 min *24 H * 1000 millisecond
	 *
	 * @return a negative value if expired ( in days ) and a positive value if still usable
	 */
	public boolean CompareToCurrent() {
		return this.toString().equals(new Date().toString());
	}

	/**
	 *
	 * @return number of days from epoch time 1/1/1970
	 */
	public long GetDate() {
		return this.Minutes;
	}

	/**
	 * @return an array with index 0 is days , index 1 is month , index 2 is year
	 */
	public long[] toDayMonthYear() {
		long DaysInEatchMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		long month = 0;
		long day = TimeUnit.MINUTES.toDays(this.Minutes);
		long year = (long) (day / 365.25) + 1970;
		day += -(year - 1970) * 365 - CountLeapYears(1970L, year) + 1;
		if (isLeapYear(year)) {
			DaysInEatchMonth[2] = 29;
		}

		for (int i = 0; i < 12; i++) {
			day -= DaysInEatchMonth[i];
			if (day <= 0) {
				day += DaysInEatchMonth[i];
				break;
			}
			month++;
		}

		long DateArr[] = {day, month, year};
		return DateArr;
	}

	public long getMonth() {
		return toDayMonthYear()[1];
	}

	public long getDay() {
		return toDayMonthYear()[0];
	}

	public long getYear() {
		return toDayMonthYear()[2];
	}

	public long getMaxDaysInMonth() {
		long DaysInEatchMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		long DateArr[] = toDayMonthYear();

		if (isLeapYear(DateArr[2])) {
			DaysInEatchMonth[2] = 29;
		}

		return DaysInEatchMonth[(int) DateArr[1]];
	}

	public long getValue() {
		return this.Minutes;
	}
	//==========================================================================================================================================================

	/**
	 * @return the date in format of DD/MM/YYYY in a string
	 */
	@Override
	public String toString() {
		long DateArr[] = toDayMonthYear();
		return Long.toString(DateArr[0]) + '/' + Long.toString(DateArr[1]) + '/' + Long.toString(DateArr[2]);

	}

	@Override
	public int compareTo(Date date) {
		return (int) (date.Minutes - this.Minutes);
	}

	public static boolean isLeapYear(long Year) {
		return (Year % 4 == 0) && (Year % 100 != 0) || (Year % 400 == 0);
	}

	public static long CountLeapYears(long From, Long To) {
		long Counter = 0;
		for (long i = From + 1; i < To; i++) {
			if (isLeapYear(i)) {
				Counter++;
			}
		}
		return Counter;
	}

	public static long[] FromYearsTo(long From, long To) {
		if (From > To) {
			long[] ret = new long[1];
			ret[0] = 0L;
			return ret;
		}
		int value = (int) (To - From);
		long[] ret = new long[value];
		for (int i = 1; i <= value; i++) {
			ret[value - i] = From + i;
		}
		return ret;
	}

	public static long[] DaysInEachMonth(long month) {
		if (month < 1 || month > 12) {
			return new long[0];
		}
		long DaysInEatchMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		long[] ret = new long[(int) DaysInEatchMonth[(int) month]];
		for (int i = 0; i < DaysInEatchMonth[(int) month]; i++) {
			ret[i] = i + 1;
		}
		return ret;
	}

	public static long[] MonthInEachYear() {
		long DaysInEatchMonth[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		return DaysInEatchMonth;
	}
}
