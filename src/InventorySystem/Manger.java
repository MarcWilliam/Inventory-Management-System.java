package InventorySystem;

import Interfaces.*;
import java.io.*;
import java.util.*;

/**
 * @author marcw
 */
public class Manger extends Employee implements IManger {

	Manger() {
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

	Manger(String LoadFromString) {
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

	@Override
	public boolean GenerateID() {
		return super.GenerateID(getFilePath());
	}

	@Override
	public boolean AddEmployee(Employee Employee) {
		return FileManger.Append(Employee.toSaveString(), Employee.getFilePath());
	}

	/**
	 *
	 * @param ID
	 * @return
	 */
	@Override
	public boolean DeleteEmployee(int ID) {
		return FileManger.Delete(FileManger.Search(Integer.toString(ID), getFilePath(), 1), getFilePath());
	}

	@Override
	public boolean EditEmployee(Employee Employee) {
		return FileManger.Update(FileManger.Search(Integer.toString(Employee.ID), Employee.getFilePath(), 1), Employee.toSaveString(), Employee.getFilePath());
	}

	@Override
	public String ListEmployee() {
		BufferedReader Update;
		try {
			Update = new BufferedReader(new FileReader(getFilePath()));
			String raw;
			String input = "";
			while ((raw = Update.readLine()) != null) {
				Employee temp = new Employee(raw);
				input += temp.toString() + System.lineSeparator();
			}
			return input;
		} catch (FileNotFoundException ex) {
			return "File not Found";
		} catch (IOException ex) {
			return "File is corrupt";
		}
	}

}
