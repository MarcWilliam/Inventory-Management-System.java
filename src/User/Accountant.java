package User;

import Core.Date;
import Core.*;
import InterFaces.*;
import Lib.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Accountant extends Employee implements Serializable, IHashMap {

	public Accountant() {
		super();
	}

	public Accountant(Employee Emp) {
		super(Emp);
	}

//==========================================================================================================================================================
	/**
	 *
	 * @param From
	 * @param To
	 * @return
	 */
	public String[][] InvoiceTaxesReport(Date From, Date To) {

		float Total = 0, Taxes = 0;
		HashMap<Long, Invoice> list = loadInvoices();
		ArrayList<String[]> ret = new ArrayList<>();
		for (Long key : list.keySet()) {
			Invoice temp = list.get(key);

			if ((temp.getSoldDate().GetDate() > From.GetDate()) && (temp.getSoldDate().GetDate() < To.GetDate())) // checks if it's in the date range
			{
				// calculates the total of eatch colum
				Total += temp.calculateTotal();
				Taxes += temp.calculateTotal() / ((temp.getCurrentTax() / 100) + 1);
				ret.add(new String[]{Long.toString(temp.getID()),
					Long.toString(temp.getCurrentTax()),
					temp.getSoldDate().toString(),
					Float.toString(temp.calculateTotal()),
					Float.toString(temp.calculateTotal() / ((temp.getCurrentTax() / 100) + 1))});
			}
		}
		// and the total row
		ret.add(new String[]{"Total", "", "", Float.toString(Total), Float.toString(Taxes)});
		return ret.toArray(new String[ret.size()][ret.get(0).length]);
	}

	public String[] InvoiceTaxesReportHeader() {
		return new String[]{"ID", "Curent Tax", "Issue Date", "Total (taxed)", "Taxes"};
	}

	/**
	 *
	 * @return
	 */
	public String[][] EmployeesSaleryReport() {
		HashMap<Long, Employee> list = loadEmployees();
		ArrayList<String[]> ret = new ArrayList<>();
		float Total_Taxes = 0, Total_Insurence = 0, Total_GrossSalary = 0, Total_NetSalary = 0, Total_BasicSalary = 0;

		for (Long key : list.keySet()) {
// calculates the total of eatch colum
			Employee temp = list.get(key);
			Total_NetSalary += temp.CalculateNetSalery();
			Total_BasicSalary += temp.getBasicSalary();
			Total_GrossSalary += temp.getGrossSalary();
			Total_Taxes += temp.CalculateTaxes();
			Total_Insurence += temp.CalculateInsurence();
//add the result row by row
			ret.add(new String[]{Long.toString(temp.getID()),
				temp.getName(),
				temp.getJobTitle(),
				temp.getDepartment(),
				Float.toString(temp.CalculateNetSalery()),
				Float.toString(temp.getBasicSalary()),
				Float.toString(temp.getGrossSalary()),
				Float.toString(temp.CalculateTaxes()),
				Float.toString(temp.CalculateInsurence())});
		}
		// and the total row
		ret.add(new String[]{"Total", "", "", "",
			Float.toString(Total_NetSalary),
			Float.toString(Total_BasicSalary),
			Float.toString(Total_GrossSalary),
			Float.toString(Total_Taxes),
			Float.toString(Total_Insurence)});
		return ret.toArray(new String[ret.size()][ret.get(0).length]);
	}

	public String[] EmployeesSaleryReportHeader() {
		return new String[]{"ID", "Name", "JobTitle", "Department", "Net Salary", "BasicSalary", "GrossSalary", "Taxes", "Insurence"};
	}

	/**
	 *
	 * @return
	 */
	public Product MostSoldProduct() {
		Product x = new Product();
		HashMap<Long, Product> list = loadProducts();
		for (Long key : list.keySet()) {
			if (x.getQuantitySold() < list.get(key).getQuantitySold()) {
				x = list.get(key);
			}
		}
		return x;
	}

	public ArrayList<Point> MonthProfitGraph() {
		// creat an empty arraylist of point and fill empty values from 0~month (Max 31)
		int size = (int) new Date().getMaxDaysInMonth();
		ArrayList<Point> ret = new ArrayList(size + 1);
		for (int i = 0; i < size + 1; i++) {
			ret.add(i, new Point(i, 0));
		}

		HashMap<Long, Invoice> list = loadInvoices();

		for (Long key : list.keySet()) {
			Invoice temp = list.get(key);

			if ((temp.getSoldDate().getMonth() == new Date().getMonth())) // checks if it's in the date range
			{
				int inde2use = (int) temp.getSoldDate().getDay();
				ret.get(inde2use).y += (int) temp.calculateTotal();
			}
		}
		return ret;
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @param ID
	 * @param BasicSalary
	 * @param GrossSalary
	 * @return
	 */
	public boolean EditEmployeeSalary(long ID, float BasicSalary, float GrossSalary) {
		return EditEmployeeSalary(ID, BasicSalary, GrossSalary, loadEmployees());
	}

	/**
	 *
	 * @param emp
	 * @param BasicSalary
	 * @param GrossSalary
	 * @return
	 */
	public boolean EditEmployeeSalary(Employee emp, float BasicSalary, float GrossSalary) {

		return EditEmployeeSalary(emp, BasicSalary, GrossSalary, loadEmployees());
	}

	/**
	 *
	 * @param ID
	 * @param BasicSalary
	 * @param GrossSalary
	 * @param list
	 * @return
	 */
	private boolean EditEmployeeSalary(long ID, float BasicSalary, float GrossSalary, HashMap<Long, Employee> list) {
		list.get(ID).setBasicSalary(BasicSalary);
		list.get(ID).setGrossSalary(GrossSalary);
		return FileManger.Save(list, new Employee());
	}

	/**
	 *
	 * @param emp
	 * @param BasicSalary
	 * @param GrossSalary
	 * @param list
	 * @return
	 */
	private boolean EditEmployeeSalary(Employee emp, float BasicSalary, float GrossSalary, HashMap<Long, Employee> list) {
		boolean x = list.get(emp.getID()).setBasicSalary(BasicSalary) && list.get(emp.getID()).setGrossSalary(GrossSalary);
		return FileManger.Save(list, new Employee());
	}

//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public static HashMap<Long, Invoice> loadInvoices() {
		return FileManger.LoadHash(new Invoice());
	}

	/**
	 *
	 * @return
	 */
	public static HashMap<Long, Employee> loadEmployees() {
		return FileManger.LoadHash(new Employee());
	}

	/**
	 *
	 * @return
	 */
	public static HashMap<Long, Product> loadProducts() {
		return FileManger.LoadHash(new Product());
	}

}
