package InventorySystem;

import Interfaces.*;
import InventorySystem.Date;
import java.io.*;
import java.util.*;

/**
 * @author marcw
 */
public class Accountant extends Employee implements IAcountant {

	Accountant() {
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

	Accountant(String LoadFromString) {
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

	/**
	 *
	 * @param From
	 * @param To
	 * @return
	 */
	@Override
	public String issueInvoiceTaxesReport(Date From, Date To) {
		try {
			Invoice temp = new Invoice();
			BufferedReader Update = new BufferedReader(new FileReader(temp.getFilePath()));
			String raw, ret = "";
			float Total = 0, Taxes = 0;
			while ((raw = Update.readLine()) != null) {

				temp = new Invoice(raw);
				if ((temp.SoldDate.Day > From.Day) && (temp.SoldDate.Day < To.Day)) {
					Total += temp.calculateTotal();
					Taxes += temp.calculateTotal() / ((temp.currentTax / 100) + 1);
					ret += "Curent Tax : " + Integer.toString(temp.currentTax)
							+ "						Issue Date: " + temp.SoldDate.toString()
							+ "						Total (taxed): " + Float.toString(temp.calculateTotal())
							+ "						Taxes: " + Float.toString(temp.calculateTotal() / ((temp.currentTax / 100) + 1));
				}
			}
			ret += "						Total (taxed): " + Total + "						Taxes: " + Taxes;
			return ret;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String issueEmployeesSaleryReport() {
		final String FilePath[] = {"Employee.txt", "Cashier.txt", "Acountant.txt", "Supervisor.txt", "Manger.txt"};
		String input = "";
		BufferedReader Update;
		float Total_Taxes = 0, Total_Insurence = 0;
		try {
			for (int i = 0; i < FilePath.length; i++) {
				Update = new BufferedReader(new FileReader(FilePath[i]));
				String raw;

				while ((raw = Update.readLine()) != null) {
					Employee temp = new Employee(raw);
					Total_Taxes += temp.CalculateTaxes();
					Total_Insurence += temp.CalculateInsurence();
					input += "ID: " + Integer.toString(temp.ID)
							+ "Name : " + temp.Name + "					"
							+ "Department : " + temp.Department + "					"
							+ "Net Salary : " + Float.toString(temp.CalculateNetSalery()) + "					"
							+ "BasicSalary : " + Float.toString(temp.BasicSalary) + "					"
							+ "GrossSalary : " + Float.toString(temp.GrossSalary) + "					"
							+ "Taxes : " + Float.toString(temp.CalculateTaxes()) + "					"
							+ "Insurence : " + Float.toString(temp.CalculateInsurence()) + "					"
							+ System.lineSeparator();
				}
			}
		} catch (FileNotFoundException ex) {
			return "File not Found";
		} catch (IOException ex) {
			return "File is corrupt";
		}
		return input + "Total Taxes : " + Float.toString(Total_Taxes) + "									" + "Total Insurence : " + Float.toString(Total_Insurence) + "					";
	}

	/**
	 *
	 * @param ID
	 * @param BasicSalary
	 * @param GrossSalary
	 * @param Filepath
	 * @return
	 */
	@Override
	public boolean EditEmployeeSalary(int ID, float BasicSalary, float GrossSalary, String Filepath) {
		String tempString = SearchEmployeeByID(ID);
		Employee tempEmployee = new Employee(tempString);
		tempEmployee.BasicSalary = BasicSalary;
		tempEmployee.GrossSalary = GrossSalary;
		return FileManger.Update(tempString, tempEmployee.toSaveString(), Filepath);
	}

	private String SearchEmployeeByID(int ID) {
		final String FilePath[] = {"Employee.txt", "Cashier.txt", "Acountant.txt", "Supervisor.txt", "Manger.txt"};
		Employee Employee;
		if (ID > 0 && ID < 100000000) // Employee
		{
			return FileManger.Search(Integer.toString(ID), FilePath[0], 1);
		}
		if (ID > 100000000 && ID < 200000000) // Manger
		{
			return FileManger.Search(Integer.toString(ID), FilePath[4], 1);
		}
		if (ID > 200000000 && ID < 300000000) // Supervisor
		{
			return FileManger.Search(Integer.toString(ID), FilePath[3], 1);
		}
		if (ID > 300000000 && ID < 400000000)// accountant
		{
			return FileManger.Search(Integer.toString(ID), FilePath[1], 1);
		}
		if (ID >= 400000000 && ID < 500000000)// cashier
		{
			return FileManger.Search(Integer.toString(ID), FilePath[1], 1);
		} else {
			return null;
		}
	}
}
