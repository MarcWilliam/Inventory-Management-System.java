package Interfaces;

import InventorySystem.*;

public interface IAcountant {

	boolean GenerateID();

	String issueInvoiceTaxesReport(Date From, Date To);

	String issueEmployeesSaleryReport();

	boolean EditEmployeeSalary(int ID, float BasicSalary, float GrossSalary, String Filepath);
}
