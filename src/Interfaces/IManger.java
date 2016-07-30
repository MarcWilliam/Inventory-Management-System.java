package Interfaces;

import InventorySystem.*;

public interface IManger {

	boolean AddEmployee(Employee Employee);

	boolean DeleteEmployee(int ID);

	boolean EditEmployee(Employee Employee);

	String ListEmployee();
}
