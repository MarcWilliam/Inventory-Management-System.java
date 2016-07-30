package InventorySystem;

import Interfaces.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Supervisor extends Employee implements ISupervisor {

	Supervisor() {
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

	Supervisor(String LoadFromString) {
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
	public boolean addProduct(Product Product) {
		return FileManger.Append(Product.toSaveString(), Product.getFilePath());
	}

	@Override
	public boolean updateProduct(Product Product) {
		return FileManger.Update(FileManger.Search(Integer.toString(Product.ID), Product.getFilePath(), 1), Product.toSaveString(), Product.getFilePath());
	}

	@Override
	public boolean deleteProduct(int ID) {
		Product Product = new Product();
		return FileManger.Delete(FileManger.Search(Integer.toString(ID), Product.getFilePath(), 1), Product.getFilePath());
	}

	@Override
	public boolean deleteProduct(String Name) {
		Product Product = new Product();
		return FileManger.Delete(FileManger.Search(Name, Product.getFilePath(), 2), Product.getFilePath());
	}

	@Override
	public boolean addOrder(Order Order) {
		return FileManger.Append(Order.toSaveString(), Order.getFilePath());
	}

	@Override
	public boolean deleteOrder(int ID) {
		Order Order = new Order();
		return FileManger.Delete(FileManger.Search(Integer.toString(ID), Order.getFilePath(), 1), Order.getFilePath());
	}

	@Override
	public boolean updateOrder(Order Order) {
		return FileManger.Update(FileManger.Search(Integer.toString(Order.ID), Order.getFilePath(), 1), Order.toSaveString(), Order.getFilePath());
	}

	@Override
	public boolean addSupplier(Supplier Supplier) {
		return FileManger.Append(Supplier.toSaveString(), Supplier.getFilePath());
	}

	@Override
	public boolean deleteSupplier(int ID) {
		Supplier Supplier = new Supplier();
		return FileManger.Delete(FileManger.Search(Integer.toString(ID), Supplier.getFilePath(), 1), Supplier.getFilePath());
	}

	@Override
	public boolean deleteSupplier(String Name) {
		Supplier Supplier = new Supplier();
		return FileManger.Delete(FileManger.Search(Name, Supplier.getFilePath(), 2), Supplier.getFilePath());
	}

	@Override
	public boolean updateSupplier(Supplier Supplier) {
		return FileManger.Update(FileManger.Search(Integer.toString(Supplier.ID), Supplier.getFilePath(), 1), Supplier.toSaveString(), Supplier.getFilePath());
	}

	@Override
	public ArrayList<Product> lowQuantityProduct(int LowQuantity) {
		try {
			Product temp = new Product();
			ArrayList<Product> ret = new ArrayList();
			BufferedReader Update;
			Update = new BufferedReader(new FileReader(temp.getFilePath()));
			String raw;
			while ((raw = Update.readLine()) != null) {
				temp = new Product(raw);
				if (temp.QuantityLeft < LowQuantity) {
					ret.add(temp);
				}
			}
			return ret;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public ArrayList<Product> listSoonToExpProduct(int daysToExp) {
		try {
			Product temp = new Product();
			ArrayList<Product> ret = new ArrayList();
			BufferedReader Update;
			Update = new BufferedReader(new FileReader(temp.getFilePath()));
			String raw;
			while ((raw = Update.readLine()) != null) {
				temp = new Product(raw);
				if (temp.ExpireDate.CompareToCurrentDate() < daysToExp) {
					ret.add(temp);
				}
			}
			return ret;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public String listProducts() {
		BufferedReader Update;
		try {
			Product temp = new Product();
			Update = new BufferedReader(new FileReader(temp.getFilePath()));
			String raw;
			String input = "";
			while ((raw = Update.readLine()) != null) {
				temp = new Product(raw);
				input += temp.toString() + System.lineSeparator();
			}
			return input;
		} catch (FileNotFoundException ex) {
			return "File not Found";
		} catch (IOException ex) {
			return "File is corrupt";
		}
	}

	@Override
	public String listSuppliers() {
		BufferedReader Update;
		try {
			Supplier temp = new Supplier();
			Update = new BufferedReader(new FileReader(temp.getFilePath()));
			String raw;
			String input = "";
			while ((raw = Update.readLine()) != null) {
				temp = new Supplier(raw);
				input += temp.toString() + System.lineSeparator();
			}
			return input;
		} catch (FileNotFoundException ex) {
			return "File not Found";
		} catch (IOException ex) {
			return "File is corrupt";
		}
	}

	@Override
	public String listOrders() {
		BufferedReader Update;
		try {
			Order temp = new Order();
			Update = new BufferedReader(new FileReader(temp.getFilePath()));
			String raw;
			String input = "";
			while ((raw = Update.readLine()) != null) {
				temp = new Order(raw);
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
