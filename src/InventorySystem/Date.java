package InventorySystem;

import Interfaces.*;

/**
 * @author Marc
 */
public class Date implements IDate {

	/**
	 * the total amount of days from 1/1/1970 Epoch time
	 */
	public int Day;

	public Date() {
		this.Day = (int) System.currentTimeMillis() / 86400000;
	}

	public Date(String SDate) {
		this.Day = 0;
		boolean x = SetDate(SDate);
	}

	/**
	 * take a parameter of a string value in format of DD/MM/YYYY or D/M/YYYY calculates the total amount of days from 1/1/1970 Epoch time
	 *
	 * @param SDate
	 * @return true if wrong format or false if correct
	 */
	@Override
	public boolean SetDate(String SDate) // will add exeptions
	{
		final int DaysInEatchMonth[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		String[] parts = SDate.split("/");  // splits the string to 3 parts  after eatch /
		if (parts.length > 3 || parts.length < 3) {
			return true;
		}
		if ((Integer.valueOf(parts[2]) < 1970) || (Integer.valueOf(parts[2]) > 2100)
				|| (Integer.valueOf(parts[1]) < 1) || (Integer.valueOf(parts[1]) > 12)
				|| (Integer.valueOf(parts[0]) < 1) || (Integer.valueOf(parts[0]) > DaysInEatchMonth[Integer.valueOf(parts[1])])) {
			return true; //checks if the date is in correct format and return true if in wrong format
		}
		final int MonthPreCalc[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};  //number of days till a specific month index 0 = jan
		this.Day = this.Day + ((Integer.valueOf(parts[2]) - 1970) * 365) // value of year
				+ (MonthPreCalc[Integer.valueOf(parts[1]) - 1]) // value of month
				+ (Integer.valueOf(parts[0])); // value of days
		return false;
		// Year = (year -1970 *365 ) to get the day value of year
		// + month = monthvaluearray[month-1]  --> -1 is for index
		// + day = day
	}

	/**
	 * compares the stored Date value with the current time 86,400,000 is 60 sec * 60 min *24 H * 1000 milisec
	 *
	 * @return a negative value if expired ( in days ) and a positive value if still usable
	 */
	@Override
	public int CompareToCurrentDate() {
		return (int) (System.currentTimeMillis() / 86400000) - this.Day;
	}

	@Override
	public void SetDateToCurent() {
		this.Day = ((int) System.currentTimeMillis() / 86400000);
	}

	/**
	 * @return the date in format of DD/MM/YYYY in a string
	 */
	@Override
	public String toString() {
		int DateArr[] = toIntgersDayMonthYear();
		return Integer.toString(DateArr[0]) + '/' + Integer.toString(DateArr[1]) + '/' + Integer.toString(DateArr[2]);

	}

	/**
	 * @return an array with index 0 is days , index 1 is month , index 2 is year
	 */
	@Override
	public int[] toIntgersDayMonthYear() {
		int DateArr[] = new int[3];// 0 is days , 1 is month , 2 is year
		DateArr[0] = this.Day;
		DateArr[2] = DateArr[0] / 365;
		DateArr[0] -= 365 * DateArr[2];
		DateArr[2] += 1970;

		final int MonthPreCalc[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

		for (int i = 11; DateArr[0] > 31; i--) {
			if (DateArr[0] - MonthPreCalc[i] > 0) {
				DateArr[0] -= MonthPreCalc[i];
				DateArr[1] = i + 1;
				return DateArr;
			}
		}

		DateArr[1] = 1;
		return DateArr;
	}
}
