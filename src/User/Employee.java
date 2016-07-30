package User;

import Abstract.*;
import Core.*;
import Exeptions.*;
import InterFaces.*;
import Lib.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Employee extends EntityUser implements Serializable, IHashMap, Comparable<Employee>, IList {

	private String PW;
	public String JobTitle;
	public String Department;
	private float BasicSalary;
	private float GrossSalary;
	private boolean Gender;
	public String ImagePath;

	/**
	 *
	 */
	public Employee() {
		super();
		this.generatePW();
		this.JobTitle = "Empty";
		this.Department = "Empty";
		this.BasicSalary = 600;
		this.GrossSalary = 1200;
		ImagePath = "user-icon.png";
	}

	/**
	 *
	 * @param Copy
	 */
	public Employee(Employee Copy) {
		this.PW = Copy.PW;
		this.JobTitle = Copy.JobTitle;
		this.Department = Copy.Department;
		this.BasicSalary = Copy.BasicSalary;
		this.GrossSalary = Copy.GrossSalary;
		this.setName(Copy.getName());
		this.setEmail(Copy.getEmail());
		this.setPhoneNumber(Copy.getPhoneNumber());
		this.setAddress(Copy.getAddress());
		this.setID(Copy.getID());
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public String getJobTitle() {
		return JobTitle;
	}

	/**
	 *
	 * @param JobTitle
	 */
	public boolean setJobTitle(String JobTitle) {
		if (JobTitle == null) {
			return true;
		}
		this.JobTitle = JobTitle;
		return false;
	}

	//==========================================================================================================================================================
	public String getImagePath() {
		return ImagePath;
	}

	public void setImagePath(String ImagePath) {
		this.ImagePath = ImagePath;
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public String getDepartment() {
		return Department;
	}

	/**
	 *
	 * @param Department
	 */
	public boolean setDepartment(String Department) {
		if (Department == null) {
			return true;
		}
		this.Department = Department;
		return false;
	}
	//==========================================================================================================================================================

	/**
	 *
	 * @return
	 */
	public String getGender() {
		if (this.Gender == true) {
			return "Male";
		} else {
			return "Female";
		}
	}

	/**
	 *
	 * @param Gender
	 */
	public void setGender(boolean Gender) {
		this.Gender = Gender;
	}

	/**
	 *
	 * @param Gender
	 */
	public void setGender(char Gender) throws CustomException {
		Gender = Character.toLowerCase(Gender);
		if (Gender == 'm') {
			this.Gender = true;
		} else if (Gender == 'f') {
			this.Gender = false;
		} else {
			throw new CustomException("at setGender only a parameter of M , m , F , f can be inserted");
		}
	}
	//==========================================================================================================================================================

	/**
	 *
	 * @param BasicSalary
	 * @return
	 */
	public boolean setBasicSalary(Float BasicSalary) {
		if (BasicSalary < 50000 && BasicSalary > 500) {
			this.BasicSalary = BasicSalary;
			return false;
		} else {
			return true;
		}
	}

	/**
	 *
	 * @param BasicSalary
	 * @return
	 */
	public boolean setBasicSalary(String BasicSalary) // May not Accsept something like 1.0***********************************************************
	{
		if (FileManger.isFloat(BasicSalary)) {
			this.BasicSalary = new Float(BasicSalary);
			return false;
		} else {
			return true;
		}
	}

	/**
	 *
	 * @return
	 */
	public float getBasicSalary() {
		return this.BasicSalary;
	}
	//==========================================================================================================================================================

	/**
	 *
	 * @param GrossSalary
	 * @return
	 */
	public boolean setGrossSalary(Float GrossSalary) {
		if (GrossSalary < 50000 && GrossSalary > 500 && GrossSalary > this.BasicSalary) {
			this.GrossSalary = GrossSalary;
			return false;
		} else {
			return true;
		}
	}

	/**
	 *
	 */
	public boolean setGrossSalary(String GrossSalary) {
		if (FileManger.isFloat(GrossSalary)) {
			this.BasicSalary = new Float(GrossSalary);
			return false;
		} else {
			return true;
		}
	}

	/**
	 *
	 * @return
	 */
	public float getGrossSalary() {
		return this.GrossSalary;
	}
	//==========================================================================================================================================================

	/**
	 *
	 */
	public final void generatePW() {
		Random rand = new Random();
		int max = 999999999, min = 99999;
		this.PW = Integer.toString(rand.nextInt((max - min) + 1) + min) + "Af@";
	}

	/**
	 *
	 * @param PW
	 * @return
	 */
	public boolean setPW(String PW) {
		/*
				 ^                 # start-of-string
				 (?=.*[0-9])       # a digit must occur at least once
				 (?=.*[a-z])       # a lower case letter must occur at least once
				 (?=.*[A-Z])       # an upper case letter must occur at least once
				 (?=.*[@#$%^&+=])  # a special character must occur at least once
				 (?=\S+$)          # no whitespace allowed in the entire string
				 .{8,}             # anything, at least eight places though
				 $                 # end-of-string
		 */
		if (PW == null) {
			return true;
		}
		if (PW.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
			this.PW = PW;
			return false;
		} else {
			return true;
		}
	}

	/**
	 *
	 * @return
	 */
	public String getPW() {
		return this.PW;
	}

//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public boolean LogShiftIn() {
		ArrayList<Shift> Load = FileManger.LoadList(new Shift(this));
		if (Load == null) {
			Load = new ArrayList<Shift>();
		}
		Load.add(new Shift(this));
		return FileManger.Save(Load, new Shift(this));
	}

	/**
	 *
	 * @return
	 */
	public boolean LogShiftOut() {
		ArrayList<Shift> Load = FileManger.LoadList(new Shift(this));
		if (Load == null) {
			return true;
		}
		for (int i = Load.size() - 1; i >= 0; i--) {
			if (Load.get(i).getEmployeeID() == this.getID())//&& (TimeUnit.HOURS.toDays(Load.get(i).getStart_time().CompareToCurrent()) == 0)) 
			{
				Load.get(i).setEnd_time();
				return FileManger.Save(Load, new Shift(this));
			}
		}
		return true;
	}

	public boolean LogIn(String ID, String PW) {
		if (!FileManger.isFloat(ID)) {
			return true;
		}
		return LogIn(new Long(ID), PW);
	}

	/**
	 *
	 * @param ID
	 * @param PW
	 * @return
	 */
	public boolean LogIn(Long ID, String PW) {
		if (PW == null) {
			return true;
		}
		return LogIn(ID, PW, FileManger.LoadHash(this));
	}

	private boolean LogIn() {
		return LogIn(this.getID(), this.PW, FileManger.LoadHash(this));
	}

	private boolean LogIn(Long ID, String PW, HashMap<Long, Employee> list) {
		if (list == null || ID == null || PW == null) {
			return true;
		}
		Employee Copy = list.get(ID);
		if (Copy == null) {
			return true;
		}
		if (!Copy.getPW().equals(PW)) {
			return true;
		}
		this.PW = Copy.PW;
		this.JobTitle = Copy.JobTitle;
		this.Department = Copy.Department;
		this.BasicSalary = Copy.BasicSalary;
		this.GrossSalary = Copy.GrossSalary;
		this.setName(Copy.getName());
		this.setEmail(Copy.getEmail());
		this.setPhoneNumber(Copy.getPhoneNumber());
		this.setAddress(Copy.getAddress());
		this.setID(Copy.getID());
		return false;
	}
//==========================================================================================================================================================

	/**
	 *
	 * @return
	 */
	public float CalculateInsurence() {
		return (float) ((this.BasicSalary * 0.14) + ((this.GrossSalary - this.BasicSalary) * 0.11));
	}

	/**
	 *
	 * @return
	 */
	public float CalculateTaxes() {
		float Salary = this.GrossSalary - CalculateInsurence();
		if (Salary > 1000) {
			Salary -= 1000;
			return (float) (Salary * 0.10);
		}
		return 0;
	}

	/**
	 *
	 * @return
	 */
	public float CalculateNetSalery() {
		return (float) (this.GrossSalary - CalculateInsurence() - CalculateTaxes());
	}
//==========================================================================================================================================================

	@Override
	public int compareTo(Employee o) {
		return (int) (o.getID() - this.getID());
	}

	@Override
	public String toString() {
		return "ID: " + Long.toString(this.getID())
				+ "					Name: " + this.getName()
				+ "					Job Title: " + this.JobTitle
				+ "					Department : " + this.Department
				+ "					Email : " + this.getEmail()
				+ "					Net Salary: " + Float.toString(CalculateNetSalery())
				+ "					Phone No: " + Long.toString(this.getPhoneNumber())
				+ "					Adress : " + this.getAddress();
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	@Override
	public String getFilePath() {
		return "Employee.bin";
	}

	/**
	 *
	 */
	@Override
	public void generateID() {
		HashMap<Long, Employee> list = FileManger.LoadHash(this);
		long x = 0;
		for (Long key : list.keySet()) {
			if (x < key) {
				x = key;
			}
		}
		super.setID(x + 1);
	}

	@Override
	public String[] getList() {
		return new String[]{Long.toString(this.getID()), this.getName(), JobTitle, Department, Long.toString(this.getPhoneNumber()), this.getAddress(), this.getEmail(), Float.toString(BasicSalary), Float.toString(this.GrossSalary)};
	}

	@Override
	public String[] getListHeader() {
		return new String[]{"ID", "Name", "JobTitle", "Department", "Phone Number", "Address", "Email", "Basic Salary", "Gross Salary"};
	}

	@Override
	public String getTitle() {
		return "Employee";
	}
	//==========================================================================================================================================================

	/**
	 *
	 * @return
	 */
	@Override
	public boolean Save() {
		return Save(FileManger.LoadHash(new Employee()));
	}

	/**
	 * @return
	 */
	@Override
	public boolean Delete() {
		return Delete(FileManger.LoadHash(new Employee()));
	}

	/**
	 *
	 * @param Object
	 * @param list
	 * @return
	 */
	private boolean Save(HashMap<Long, Employee> list) {
		if (list == null) {
			list = new HashMap<Long, Employee>();
		}
		list.put(this.getID(), this);
		return FileManger.Save(list, new Employee());
	}

	/**
	 * @param list
	 * @return
	 */
	private boolean Delete(HashMap<Long, Employee> list) {
		if (list == null) {
			list = new HashMap<Long, Employee>();
		}
		if (list.remove(this.getID()) == null) {
			return true;// if object not found
		}
		return FileManger.Save(list, new Product());
	}

	public static HashMap<Long, Employee> loadEmployees() {
		return FileManger.LoadHash(new Employee());
	}

	public static Employee searchEmployee(long ID) {
		return loadEmployees().get(ID);
	}

	public static Employee searchEmployee(String ID) {
		if (FileManger.isNumber(ID)) {
			return searchEmployee(new Long(ID));
		}
		return null;
	}
}
