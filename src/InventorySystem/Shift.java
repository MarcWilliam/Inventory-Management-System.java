/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventorySystem;

import Interfaces.*;
import InventorySystem.Date;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Shift implements IShift {

	int EmployeeID;
	String EmployeeName;
	long Start_time;  // time in minuts
	long End_time;   // time in minuts

	public Shift() {
		this.EmployeeID = 0;
		this.EmployeeName = "Emplty";
		this.Start_time = System.currentTimeMillis() / 60000;
		this.End_time = System.currentTimeMillis() / 60000;
	}

	public Shift(String LoadFromString) {
		LoadFromString = LoadFromString.trim();
		StringTokenizer st = new StringTokenizer(LoadFromString, "#$#");
		this.EmployeeID = Integer.valueOf(st.nextToken());
		this.EmployeeName = st.nextToken();
		this.Start_time = Long.valueOf(st.nextToken());
		this.End_time = Long.valueOf(st.nextToken());
	}

	static public String getFilePath() {
		return "Shift.txt";
	}

	@Override
	public String toString() {
		return "ID : " + EmployeeID
				+ "					Name : " + this.EmployeeName
				+ "					Shift length : " + Long.toString(calculateTimeInHour())
				+ "h					Entry Date : " + entryDate();
	}

	@Override
	public String toSaveString() {
		return "#$#" + EmployeeID + "#$#"
				+ EmployeeName + "#$#"
				+ Start_time + "#$#"
				+ End_time + "#$#";
	}

	@Override
	public void setEnd_time() {
		this.End_time = System.currentTimeMillis() / 60000;
	}

	@Override
	public long calculateTimeInHour() {
		if (this.End_time == 0 || this.Start_time == 0) {
			return 0;
		}
		return this.End_time - this.Start_time;
	}

	@Override
	public String entryDate() {
		Date entry = new Date();
		entry.Day = (int) (this.Start_time / 60 / 24);
		return entry.toString();
	}

}
