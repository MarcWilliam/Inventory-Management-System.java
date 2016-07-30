package InventorySystem;

import InventorySystem.Date;
import java.util.*;

/**
 * @author marcw
 */
public class InventorySystem {

	public static void main(String[] args) {
		while (true) {
			checkFiles();
			Employee User = login();
			if (User.JobTitle.equals("Cashier")) {
				Cashier(User.toSaveString());
			}
			if (User.JobTitle.equals("Manger")) {
				Manger(User.toSaveString());
			}
			if (User.JobTitle.equals("Supervisor")) {
				Supervisor(User.toSaveString());
			}
			if (User.JobTitle.equals("Accountant")) {
				Accountant(User.toSaveString());
			} else {
				Employee(User.toSaveString());
			}
		}
	}

	/**
	 * check if files exist or no and ask if user wants to load from backup
	 */
	public static void checkFiles() {
		String FilePath[] = {Employee.getFilePath(), Product.getFilePath(), Order.getFilePath(), Invoice.getFilePath(), Shift.getFilePath()};
		Scanner Scan = new Scanner(System.in);
		boolean Flag = false;
		for (int i = 0; i < FilePath.length; i++) {
			Flag = FileManger.CheckFiles(FilePath[i]);
		}
		if (Flag) {
			System.out.println("Some Files are missing");
			for (int i = 0; i < FilePath.length; i++) {
				FileManger.CreatFiles(FilePath[i]);
			}
		}

	}

	/**
	 * Print a Separator on the Screen
	 */
	public static void PrintSeparator() {
		System.out.println("  __    __   __   __   __   __   __   __    __");
		System.out.println(" _\\/_  _\\/_ _\\/_ _\\/_ _\\/_ _\\/_ _\\/_ _\\/_  _\\/_");
		System.out.println(" \\/\\/  \\/\\/ \\/\\/ \\/\\/ \\/\\/ \\/\\/ \\/\\/ \\/\\/  \\/\\/");
	}

	/**
	 * Asks the user for his ID and PW and checks them
	 *
	 * @return an object employee upon a successful login
	 */
	public static Employee login() {
		Scanner Scan = new Scanner(System.in);
		PrintSeparator();
		System.out.println("====  Login ====");
		System.out.print("ID : ");
		String Empinfo;
		int ID = ScanInt();
		Employee User;
		while ((Empinfo = FileManger.Search(Integer.toString(ID), Employee.getFilePath(), 1)) == null) {
			System.out.println("Wrong ID !!!");
			System.out.print("ID : ");
			ID = ScanInt();
		}

		User = new Employee(Empinfo);
		while (true) {
			System.out.print("PW : ");
			String PW = Scan.nextLine();
			if (User.LogIn(PW)) {
				return User;
			}
			System.out.println("Wrong PW !!!!");
		}
	}

	/**
	 * Scan the next integer and check is it is a proper integer if not ill continue again in a loop
	 *
	 * @return the scanned integer
	 */
	public static int ScanInt() {
		Scanner Scan = new Scanner(System.in);
		while (true) {
			if (Scan.hasNextInt()) {
				return Scan.nextInt();
			}
			System.out.println("Enter a valid Number : ");
			Scan.next();
		}
	}

	/**
	 * Scan the next float and check is it is a proper float if not ill continue again in a loop
	 *
	 * @return the scanned float
	 */
	public static float ScanFloat() {
		Scanner Scan = new Scanner(System.in);
		while (true) {
			if (Scan.hasNextFloat()) {
				return Scan.nextFloat();
			}
			System.out.println("Enter a valid Number : ");
			Scan.next();
		}
	}

	/**
	 * Ask the user to fill a product
	 *
	 * @return the filled object
	 */
	public static Product FillProduct() {
		Scanner Scan = new Scanner(System.in);
		Product temp = new Product();
		temp.GenerateID();
		temp.ExpireDate.SetDateToCurent();
		temp.ProductionDate.SetDateToCurent();
		System.out.print("Name :");
		temp.Name = Scan.nextLine();
		System.out.print("Category :");
		temp.Category = Scan.nextLine();
		System.out.print("Brand :");
		temp.Brand = Scan.nextLine();
		System.out.print("Quantity :");
		temp.TotalQuantity = ScanInt();
		temp.QuantityLeft = temp.TotalQuantity;
		System.out.print("Price/Unit :");
		temp.PricePerUnit = ScanFloat();
		System.out.print("Expire Date :");
		while (temp.ExpireDate.SetDate(Scan.nextLine())) {
			System.out.println("Wrong Date Format ");
		}
		System.out.print("Production Date :");
		while (temp.ProductionDate.SetDate(Scan.nextLine())) {
			System.out.println("Wrong Date Format ");
		}
		System.out.print("Add a Suppliers to the list ?      Y or N   : ");
		String In = Scan.nextLine();
		while (!(In.equals("Y") || In.equals("Yes") || In.equals("yes") || In.equals("y") || In.equals("N") || In.equals("No") || In.equals("no") || In.equals("n"))) {
			System.out.println("Please Enter Y or N");
			In = Scan.nextLine();
		}
		if (In.equals("Y") || In.equals("Yes") || In.equals("yes") || In.equals("y")) {
			temp.addToSupplierList(ScanInt());
		}

		return temp;
	}

	/**
	 * Ask the user to fill a Employee
	 *
	 * @return the filled object
	 */
	public static Employee FillEmployee() {
		Scanner scan = new Scanner(System.in);
		Employee temp = new Employee();
		temp.GenerateID();
		System.out.print("Name :");
		temp.Name = scan.nextLine();
		System.out.print("Password :");
		temp.PW = scan.nextLine();
		System.out.print("Email :");
		while (temp.setEmail(scan.nextLine())) {
			System.out.print("Enter a valid Email :");
		}
		System.out.print("there are 4 main categories Manger , Cashier , Accountant , Supervisor any thins else will be considered as regular employee");
		System.out.print("JobTitle :");
		temp.JobTitle = scan.nextLine();
		System.out.print("Department :");
		temp.Department = scan.nextLine();
		System.out.print("Basic Salary :");
		temp.BasicSalary = ScanFloat();
		System.out.print("Gross Salary :");
		temp.GrossSalary = ScanFloat();
		System.out.print("Phone Number :");
		temp.PhoneNumber = ScanInt();
		return temp;
	}

	/**
	 * Ask the user to fill a product
	 *
	 * @return the filled object
	 */
	public static Supplier FillSupplier() {
		Scanner scan = new Scanner(System.in);
		Supplier temp = new Supplier();
		temp.GenerateID();
		System.out.print("Name :");
		temp.Name = scan.nextLine();
		System.out.print("Address :");
		temp.Address = scan.nextLine();
		System.out.print("Website :");
		temp.Website = scan.nextLine();
		System.out.print("Email :");
		while (temp.setEmail((scan.nextLine())) == true) {
			System.out.println("Enter a valid Email :");
		}
		System.out.print("Phone Number :");
		temp.PhoneNum = ScanInt();
		System.out.print("Bank Account :");
		temp.BankAcc = ScanInt();
		return temp;
	}

	/**
	 * Ask the user to fill an Invoice
	 *
	 * @return the filled object
	 */
	public static Invoice FillInvoice() {
		Scanner scan = new Scanner(System.in);
		Product temp = new Product();
		Invoice temp2 = new Invoice();
		temp2.SoldDate.SetDateToCurent();
		temp2.GenerateID();

		String In = "Y";
		while (In.equals("Y") || In.equals("Yes") || In.equals("yes") || In.equals("y")) {

			System.out.println("Enter Product ID fallowed by the Quantity :");
			String StrTemp;
			if ((StrTemp = FileManger.Search(Integer.toString(ScanInt()), temp.getFilePath(), 1)) != null) {
				temp = new Product(StrTemp);
				temp2.addToProductsList(temp, ScanInt());
			} else {
				System.out.print("No sutch Product exists");
			}

			System.out.print("Add an other Product?      Y or N : ");
			In = scan.nextLine();
		}

		return temp2;
	}

	/**
	 * Ask the user to fill an Order
	 *
	 * @return the filled object
	 */
	public static Order FillOrder() {
		Scanner scan = new Scanner(System.in);
		Order temp = new Order();
		temp.GenerateID();
		temp.DeliveryDate.SetDateToCurent();
		temp.OrderDate.SetDateToCurent();
		System.out.print("Quantity :");
		temp.Quantity = ScanInt();
		System.out.print("Price Per Unit :");
		temp.PricePerUnit = ScanFloat();
		System.out.print("Product Id :");
		temp.ProductID = ScanInt();
		System.out.print("Supplier Id :");
		temp.SupplierID = ScanInt();
		return temp;
	}

	public static void EditProduct(Product Product) {
		Scanner Scan = new Scanner(System.in);
		System.out.print("Name :");
		Product.Name = Scan.nextLine();
		System.out.print("Category :");
		Product.Category = Scan.nextLine();
		System.out.print("Brand :");
		Product.Brand = Scan.nextLine();
		System.out.print("Quantity :");
		Product.TotalQuantity = ScanInt();
		Product.QuantityLeft = Product.TotalQuantity;
		System.out.print("Price/Unit :");
		Product.PricePerUnit = ScanFloat();
		System.out.print("Expire Date :");
		while (Product.ExpireDate.SetDate(Scan.nextLine())) {
			System.out.println("Wrong Date Format ");
		}
		System.out.print("Production Date :");
		while (Product.ProductionDate.SetDate(Scan.nextLine())) {
			System.out.println("Wrong Date Format ");
		}
		System.out.print("Add a Suppliers to the list ?      Y or N   : ");
		String In = Scan.nextLine();
		while (!(In.equals("Y") || In.equals("Yes") || In.equals("yes") || In.equals("y") || In.equals("N") || In.equals("No") || In.equals("no") || In.equals("n"))) {
			System.out.println("Please Enter Y or N");
			In = Scan.nextLine();
		}
		if (In.equals("Y") || In.equals("Yes") || In.equals("yes") || In.equals("y")) {
			Product.addToSupplierList(ScanInt());
		}

	}

	public static void EditEmployee(Employee Employee) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Name :");
		Employee.Name = scan.nextLine();
		System.out.print("Password :");
		Employee.PW = scan.nextLine();
		System.out.print("Email :");
		while (Employee.setEmail(scan.nextLine())) {
			System.out.print("Enter a valid Email :");
		}
		System.out.print("JobTitle :");
		Employee.JobTitle = scan.nextLine();
		System.out.print("Department :");
		Employee.Department = scan.nextLine();
		System.out.print("Basic Salary :");
		Employee.BasicSalary = ScanFloat();
		System.out.print("Gross Salary :");
		Employee.GrossSalary = ScanFloat();
		System.out.print("Phone Number :");
		Employee.PhoneNumber = ScanInt();
	}

	public static void EditSupplier(Supplier Supplier) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Name :");
		Supplier.Name = scan.nextLine();
		System.out.print("Address :");
		Supplier.Address = scan.nextLine();
		System.out.print("Website :");
		Supplier.Website = scan.nextLine();
		System.out.print("Email :");
		while (Supplier.setEmail((scan.nextLine())) == true) {
			System.out.println("Enter a valid Email :");
		}
		System.out.print("Phone Number :");
		Supplier.PhoneNum = ScanInt();
		System.out.print("Bank Account :");
		Supplier.BankAcc = ScanInt();
	}

	public static void Editnvoice(Invoice temp2) {
		Scanner scan = new Scanner(System.in);
		Product temp = new Product();

		String In = "Y";
		while (In.equals("Y") || In.equals("Yes") || In.equals("yes") || In.equals("y")) {

			System.out.println("Enter Product ID fallowed by the Quantity :");
			String StrTemp;
			if ((StrTemp = FileManger.Search(Integer.toString(ScanInt()), temp.getFilePath(), 1)) != null) {
				temp = new Product(StrTemp);
				temp2.addToProductsList(temp, ScanInt());
			} else {
				System.out.print("No sutch Product exists");
			}

			System.out.print("Add an other Product?      Y or N : ");
			In = scan.nextLine();
		}
	}

	public static void EditOrder(Order temp) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Quantity :");
		temp.Quantity = ScanInt();
		System.out.print("Price Per Unit :");
		temp.PricePerUnit = ScanFloat();
		System.out.print("Product Id :");
		temp.ProductID = ScanInt();
		System.out.print("Supplier Id :");
		temp.SupplierID = ScanInt();
	}

	/**
	 * The log in menu as a Manger
	 *
	 * @param UserInfo takes a String that exists in the text file to create a User
	 */
	public static void Manger(String UserInfo) {
		while (true) {
			Scanner Scan = new Scanner(System.in);
			Manger User = new Manger(UserInfo);
			PrintSeparator();
			System.out.println("======  Manger ======");
			System.out.println("[1] Add new Employee");
			System.out.println("[2] Delete Employee");
			System.out.println("[3] Edit Employee's Info");  // ill open an other menu
			System.out.println("[4] Start Shift");
			System.out.println("[5] End Shift");
			System.out.println("[6] Log out");
			int Menu = ScanInt();
			while (Menu > 6 || Menu < 0) {
				Menu = ScanInt();
				System.out.println("Enter a valid number");
			}
			switch (Menu) {
				case 1:
					Employee AddEmp = FillEmployee();
					FileManger.Append(AddEmp.toSaveString(), AddEmp.getFilePath());
					break;
				case 2:
					System.out.print("Enter the Employee ID : ");
					if (User.DeleteEmployee(ScanInt())) {
						System.out.println("No sutch Employee exist");
					}
					break;
				case 3:
					System.out.print("Enter the Employee ID : ");// ***************************************************
					String info;
					if ((info = FileManger.Search(Integer.toString(ScanInt()), Employee.getFilePath(), 1)) == null) {
						System.out.println("No sutch Employee exist  !!!");
					} else {
						Employee tempo = new Employee(info);
						EditEmployee(tempo);
						if (User.EditEmployee(tempo)) {
							System.out.println("Failed to modify !!!");
						}
					}
					break;
				case 4:
					if (User.LogShiftIn()) {
						System.out.println("Failed to log Shift");
					}
					break;
				case 5:
					if (User.LogShiftOut()) {
						System.out.println("Failed to log Shift");
					}
					break;
				case 6:
					return;
			}
		}
	}

	/**
	 * The log in menu as a Employee
	 *
	 * @param UserInfo takes a String that exists in the text file to create a User
	 */
	public static void Employee(String UserInfo) {
		while (true) {
			Scanner Scan = new Scanner(System.in);
			Employee User = new Employee(UserInfo);
			PrintSeparator();
			System.out.println("======  Employee ======");
			System.out.println("[1] Start Shift");
			System.out.println("[2] End Shift");
			System.out.println("[3] Log out");
			int Menu = ScanInt();
			while (Menu > 3 || Menu < 0) {
				Menu = ScanInt();
				System.out.println("Enter a valid number");
			}
			switch (Menu) {
				case 1:
					if (User.LogShiftIn()) {
						System.out.println("Failed to log Shift");
					}
					break;
				case 2:
					if (User.LogShiftOut()) {
						System.out.println("Failed to log Shift");
					}
					break;
				case 3:
					return;
			}
		}
	}

	/**
	 * The log in menu as a Cashier
	 *
	 * @param UserInfo takes a String that exists in the text file to create a User
	 */
	public static void Cashier(String UserInfo) {
		while (true) {
			Scanner Scan = new Scanner(System.in);
			Cashier User = new Cashier(UserInfo);
			PrintSeparator();
			System.out.println("======  Cashier ======");
			System.out.println("[1] Issue Invoice");
			System.out.println("[2] Remove an Invoice");
			System.out.println("[3] Start Shift");
			System.out.println("[4] End Shift");
			System.out.println("[5] Log out");
			int Menu = ScanInt();
			while (Menu > 5 || Menu < 0) {
				Menu = ScanInt();
				System.out.print("Enter a valid number :");
			}
			switch (Menu) {

				case 1:
					if (User.addInvoice(FillInvoice())) {
						break;
					}
				case 2:
					System.out.print("Enter the Invoice ID : ");
					if (User.deleteInvoice(ScanInt())) {
						System.out.println("No sutch Invoice exist");
					}
					break;
				case 3:
					if (User.LogShiftIn()) {
						System.out.println("Failed to log Shift");
					}
					break;
				case 4:
					if (User.LogShiftOut()) {
						System.out.println("Failed to log Shift");
					}
					break;
				case 5:
					return;
			}
		}
	}

	/**
	 * The log in menu as a Accountant
	 *
	 * @param UserInfo takes a String that exists in the text file to create a User
	 */
	public static void Accountant(String UserInfo) {
		while (true) {
			Scanner Scan = new Scanner(System.in);
			Accountant User = new Accountant(UserInfo);
			PrintSeparator();
			System.out.println("======  Acountant ======");
			System.out.println("[1] issue Invoice Taxes Report");
			System.out.println("[2] issue Employees Salery Report");
			System.out.println("[3] Edit Employes Salary");
			System.out.println("[4] Start Shift");
			System.out.println("[5] End Shift");
			System.out.println("[6] Log out");
			int Menu = ScanInt();
			while (Menu > 6 || Menu < 0) {
				Menu = ScanInt();
				System.out.print("Enter a valid number :");
			}
			switch (Menu) {
				case 1:
					Date Start = new Date(),
					 End = new Date();
					System.out.print("From the following Date : ");
					while (Start.SetDate(Scan.nextLine())) {
						System.out.println("Wrong Date Format ");
					}
					System.out.print("To the following Date : ");
					while (End.SetDate(Scan.nextLine())) {
						System.out.println("Wrong Date Format ");
					}
					System.out.println(User.issueInvoiceTaxesReport(Start, End));
					break;
				case 2:
					System.out.println(User.issueEmployeesSaleryReport());
					break;
				case 3:
					System.out.print("Enter the Employee ID : ");
					String info;
					if ((info = FileManger.Search(Integer.toString(ScanInt()), Employee.getFilePath(), 1)) == null) {
						System.out.println("No sutch employee exist");
					} else {
						Employee temp = new Employee(info);
						System.out.print("Net Salary : ");
						temp.BasicSalary = ScanFloat();
						System.out.print("Gross Salary : ");
						temp.GrossSalary = ScanFloat();
						FileManger.Update(info, temp.toSaveString(), temp.getFilePath());
					}
					break;
				case 4:
					if (User.LogShiftIn()) {
						System.out.println("Failed to log Shift");
					}
					break;
				case 5:
					if (User.LogShiftOut()) {
						System.out.println("Failed to log Shift");
					}
					break;
				case 6:
					return;
			}
		}
	}

	/**
	 * The log in menu as a Supervisor
	 *
	 * @param UserInfo takes a String that exists in the text file to create a User
	 */
	public static void Supervisor(String UserInfo) {
		while (true) {
			Scanner Scan = new Scanner(System.in);
			Supervisor User = new Supervisor(UserInfo);
			PrintSeparator();
			System.out.println("======  Supervisor ======");
			System.out.println("[1] Add Products");
			System.out.println("[2] Delete Products");
			System.out.println("[3] Update Products");
			System.out.println("[4] Add Orders");
			System.out.println("[5] Delete Orders");
			System.out.println("[6] Update Orders");
			System.out.println("[7] Add Supliers");
			System.out.println("[8] Delete Supliers");
			System.out.println("[9] Update Supliers");
			System.out.println("[10] List Deplited Products");
			System.out.println("[11] List Expired Products");
			System.out.println("[12] List Products");
			System.out.println("[13] List Orders");
			System.out.println("[14] List Suppliers");
			System.out.println("[15] Start Shift");
			System.out.println("[16] End Shift");
			System.out.println("[17] Log out");
			int Menu = ScanInt();
			while (Menu > 6 || Menu < 0) {
				Menu = ScanInt();
				System.out.print("Enter a valid number : ");
			}
			switch (Menu) {
				case 1:
					Product AddPro = FillProduct();
					User.addProduct(AddPro);
					break;
				case 2:
					System.out.print("Enter the Product's ID : ");
					if (User.deleteProduct(ScanInt())) {
						System.out.println("No sutch Product exist");
					}
					break;
				case 3:
					System.out.print("Enter the Product ID : ");
					String tempo;
					if ((tempo = (FileManger.Search(Integer.toString(ScanInt()), Product.getFilePath(), 1))) == null) {
						System.out.println("No sutch Invoice exist");
					} else {
						Product Pro = new Product(tempo);
						EditProduct(Pro);
						User.updateProduct(Pro);
					}
					break;
				case 4:
					Order AddOrder = FillOrder();
					User.addOrder(AddOrder);
					break;
				case 5:
					System.out.print("Enter the Order's ID : ");
					if (User.deleteOrder(ScanInt())) {
						System.out.println("No sutch Order exist");
					}
					break;
				case 6:
					System.out.print("Enter the Product ID : ");
					String tempo2;
					if ((tempo2 = (FileManger.Search(Integer.toString(ScanInt()), Order.getFilePath(), 1))) == null) {
						System.out.println("No sutch Order exist");
					} else {
						Order Pro = new Order(tempo2);
						EditOrder(Pro);
						User.updateOrder(Pro);
					}
					break;
				case 7:
					Supplier AddSuplier = FillSupplier();
					User.addSupplier(AddSuplier);
					break;
				case 8:
					System.out.print("Enter the Supplier's ID : ");
					if (User.deleteSupplier(ScanInt())) {
						System.out.println("No sutch Supplier exist");
					}
					break;
				case 9:
					System.out.print("Enter the Supplier ID : ");
					String tempo3;
					if ((tempo3 = (FileManger.Search(Integer.toString(ScanInt()), Supplier.getFilePath(), 1))) == null) {
						System.out.println("No sutch Supplier exist");
					} else {
						Supplier Pro = new Supplier(tempo3);
						EditSupplier(Pro);
						User.updateSupplier(Pro);
					}
					break;
				case 10:
					System.out.println("Any product that is going to expire in the next X days will be listed");
					ArrayList<Product> arrpro = User.lowQuantityProduct(ScanInt());
					for (int i = 0; i < arrpro.size(); i++) {
						Product tempx = arrpro.get(i);
						System.out.println(tempx.toString());
					}
					break;
				case 11:
					System.out.println("Any product with quantity lower than the next imput ill be listed");
					ArrayList<Product> arrpro2 = User.lowQuantityProduct(ScanInt());
					for (int i = 0; i < arrpro2.size(); i++) {
						Product tempx = arrpro2.get(i);
						System.out.println(tempx.toString());
					}

					break;
				case 12:
					System.out.println(User.listProducts());
					break;
				case 13:
					System.out.println(User.listOrders());
					break;
				case 14:
					System.out.println(User.listSuppliers());
					break;
				case 15:
					if (User.LogShiftIn()) {
						System.out.println("Failed to log Shift");
					}
					break;
				case 16:
					if (User.LogShiftOut()) {
						System.out.println("Failed to log Shift");
					}
					break;
				case 17:
					return;
			}
		}
	}

}
