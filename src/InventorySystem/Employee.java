package InventorySystem;

import Interfaces.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * @author Marc
 */
public class Employee extends Entity implements IEntity {

	public String Name;
	public String PW;
	public String Email;
	public String JobTitle;
	public String Department;
	public float BasicSalary;
	public float GrossSalary;
	public int PhoneNumber;

	Employee() {
		this.ID = 0;
		this.Name = "Empty";
		this.PW = "Empty";
		this.Email = "Empty";
		this.JobTitle = "Empty";
		this.Department = "Empty";
		this.BasicSalary = 0;
		this.GrossSalary = 0;
		this.PhoneNumber = 0;
	}

	Employee(String LoadFromString) {
		LoadFromString = LoadFromString.trim();
		StringTokenizer st = new StringTokenizer(LoadFromString, "#$#");
		this.ID = Integer.valueOf(st.nextToken());
		this.Name = st.nextToken();
		this.PW = st.nextToken();
		this.Email = st.nextToken();
		this.JobTitle = st.nextToken();
		this.Department = st.nextToken();
		this.BasicSalary = Float.valueOf(st.nextToken());
		this.GrossSalary = Float.valueOf(st.nextToken());
		this.PhoneNumber = Integer.valueOf(st.nextToken());
	}

	public boolean GenerateID() {
		return super.GenerateID(getFilePath());
	}

	static public String getFilePath() {
		return "Employee.txt";
	}

	public boolean LogShiftIn() {
		Shift Temp = new Shift();
		Temp.EmployeeID = this.ID;
		Temp.EmployeeName = this.Name;
		return FileManger.Append(Temp.toSaveString(), Temp.getFilePath());
	}

	public boolean LogShiftOut() {
		try {
			Shift Temp = new Shift();
			BufferedReader search = new BufferedReader(new FileReader(Temp.getFilePath()));
			String line;
			while ((line = search.readLine()) != null) {
				Temp = new Shift(line);
				if (Temp.EmployeeID == this.ID && (Temp.Start_time / 1440) == (System.currentTimeMillis() / 86400000)) {
					Temp.setEnd_time();
					return false;
				}
			}
		} catch (Exception e) {
			return true;
		}
		return true;
	}

	public boolean LogIn(String PW) {
		if (this.PW.equals(PW)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "ID: " + Integer.toString(this.ID)
				+ "					Name: " + this.Name
				+ "					Job Title: " + this.JobTitle
				+ "					Department : " + this.Department
				+ "					Email : " + this.Email
				+ "					Net Salary: " + Float.toString(CalculateNetSalery())
				+ "					Phone No: " + Integer.toString(this.PhoneNumber);
	}

	public String toSaveString() {
		return "#$#"
				+ Integer.toString(this.ID) + "#$#"
				+ this.Name + "#$#"
				+ this.PW + "#$#"
				+ this.Email + "#$#"
				+ this.JobTitle + "#$#"
				+ this.Department + "#$#"
				+ Float.toString(this.BasicSalary) + "#$#"
				+ Float.toString(this.GrossSalary) + "#$#"
				+ Integer.toString(this.PhoneNumber) + "#$#";
	}

	public boolean setEmail(String email) // abdelrahmen
	{//^ ==> begin of the line				// - ==> something to somethiong				//+@ mean @ must exist				//$ end of the line
		Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
		Matcher m = p.matcher(email);
		if (m.matches()) {
			this.Email = email;
			return false;
		} else {
			return true;
		}
	}

	public String getEmail() {
		return this.Email;
	}

	public float CalculateInsurence() {
		return (float) ((this.BasicSalary * 0.14) + ((this.GrossSalary - this.BasicSalary) * 0.11));
	}

	public float CalculateTaxes() {
		float Salary = this.GrossSalary - CalculateInsurence() - 1000;
		if (Salary < 10000) // for any one below 10k$ get 10% taxes
		{
			return (float) (Salary * 0.10);
		}
		return (float) (Salary * 0.20); // for any one higer 10k$ get 20% taxes
	}

	public float CalculateNetSalery() {
		return (float) (this.GrossSalary - CalculateInsurence() - CalculateTaxes());
	}

	public static String RandEncry(String StringE) {
		Random random = new Random();
		String temp = new String();
		char RandNumb = (char) (random.nextInt(50 - 2 + 1) + 50);
		temp += RandNumb;
		for (int i = 0; i < StringE.length(); i++) {
			temp += (char) (StringE.charAt(i) + RandNumb);
		}
		return temp;
	}

	public static String DeRandEncry(String StringE) {
		String temp = new String();
		char RandNumb = StringE.charAt(0);
		for (int i = 0; i < StringE.length(); i++) {
			temp += (char) (StringE.charAt(i) - RandNumb);
		}
		return temp;
	}

}
