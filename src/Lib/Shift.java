package Lib;

import Core.*;
import InterFaces.*;
import User.*;
import java.io.*;

/**
 *
 * @author Marc Wafik
 */
public class Shift implements Serializable, IArrayList, Comparable<Shift> {

	private final long EmployeeID;
	private final Date Start_time;  // time in minuts
	private Date End_time;   // time in minuts

	/**
	 *
	 * @param Employee
	 */
	public Shift(Employee Employee) {
		this.EmployeeID = Employee.getID();
		this.Start_time = new Date();
		this.End_time = new Date();
	}
	//==========================================================================================================================================================

	/**
	 *
	 * @return
	 */
	public long getEmployeeID() {
		return this.EmployeeID;
	}

	/**
	 *
	 * @return
	 */
	public Date getStart_time() {
		return this.Start_time;
	}

	/**
	 *
	 * @return
	 */
	public Date getEnd_time() {
		return this.End_time;
	}

	/**
	 *
	 * @return
	 */
	public Employee getEmployee() {
		return (Employee) FileManger.LoadHash(new Employee()).get(this.EmployeeID);
	}

	//==========================================================================================================================================================
	/**
	 *
	 */
	public void setEnd_time() {
		this.End_time.SetDateToCurent();
	}

	/**
	 *
	 * @return
	 */
	public long calculateTimeInHour() {
		if (this.Start_time.compareTo(this.End_time) < 0) {
			return 0;
		}
		return this.Start_time.compareTo(this.End_time);
	}

	//==========================================================================================================================================================
	@Override
	public int compareTo(Shift o) {
		return (int) (o.EmployeeID - this.getEmployeeID());
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String getFilePath() {
		return "Shift.bin";
	}

	@Override
	public String toString() {
		return "ID : " + EmployeeID
				+ "					Shift length : " + Long.toString(calculateTimeInHour())
				+ "h					Entry Date : " + this.Start_time.toString();
	}
}
