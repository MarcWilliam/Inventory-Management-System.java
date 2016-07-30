package User;

import Core.*;
import InterFaces.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Manger extends Employee implements Serializable, IHashMap {

	public Manger() {
		super();
	}

	public Manger(Employee Employee) {
		super(Employee);
	}
//==========================================================================================================================================================

	/**
	 *
	 * @return
	 */
	public static HashMap<Long, Employee> loadEmployees() {
		return FileManger.LoadHash(new Employee());
	}

	/**
	 *
	 * @param Emp
	 * @return
	 */
	public boolean updateEmployee(Employee Emp) {
		return Emp.Save();
	}

	/**
	 *
	 * @param ID
	 * @return
	 */
	public boolean deleteEmployee(Employee Emp) {
		return Emp.Delete();
	}

	/**
	 *
	 * @param ID
	 * @return
	 */
	public String[][] ListEmployee() {
		return FileManger.ListObj(FileManger.LoadHash(new Employee()));
	}

	public boolean BackUpfiles(String FilePath) {
		return FileManger.CreatBackUp(FilePath);
	}
}
