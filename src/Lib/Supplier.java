package Lib;

import Abstract.*;
import Core.*;
import InterFaces.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Marc Wafik
 */
public class Supplier extends EntityUser implements Serializable, IHashMap, Comparable<Supplier>, IList {

	private String Website;
	private long BankAcc;
	private ArrayList<Long> ProductsIDs;			 //{*}[unique]
	private ArrayList<Long> OrdersIDs;					//{*}[unique]

	/**
	 *
	 */
	public Supplier() {
		super();
		this.Website = "https://www.example.com";
		this.BankAcc = 0;
		this.ProductsIDs = new ArrayList();
		this.OrdersIDs = new ArrayList();
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public String getWebsite() {
		return this.Website;
	}

	/**
	 * Check if the website is in the correct google.com or https://google.com or https://www.google.com and assign the value
	 *
	 * @param Website
	 * @return is the input Wrong ?
	 */
	public boolean setWebsite(String Website) {//^ ==> begin of the line				// - ==> something to somethiong				//+@ mean @ must exist				//$ end of the line
		if (Website == null) {
			return true;
		}
		if (Website.matches("@^(http\\:\\/\\/|https\\:\\/\\/)?([a-z0-9][a-z0-9\\-]*\\.)+[a-z0-9][a-z0-9\\-]*$@i")) {
			this.Website = Website;
			return false;
		} else {
			return true;
		}
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public long getBankAcc() {
		return this.BankAcc;
	}

	/**
	 *
	 * @param BankAcc
	 * @return
	 */
	public boolean setBankAcc(long BankAcc) {
		if (BankAcc < 10000) {
			return true;
		}
		this.BankAcc = BankAcc;
		return false;
	}

	/**
	 *
	 * @param BankAcc
	 * @return
	 */
	public boolean setBankAcc(String BankAcc) {
		if (!FileManger.isNumber(BankAcc)) {
			return true;
		}
		return setBankAcc(new Long(BankAcc));
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public ArrayList<Long> getProductsIDs() {
		return this.ProductsIDs;
	}

	/**
	 *
	 * @return
	 */
	public HashMap<Long, Product> getProducts() {
		HashMap<Long, Product> temp = FileManger.LoadHash(new Product());
		HashMap<Long, Product> ret = new HashMap();
		for (Long ProductsIDIterator : this.ProductsIDs) {
			ret.put(ProductsIDIterator, temp.get(ProductsIDIterator));
		}
		return ret;
	}

	/**
	 *
	 * @param Product
	 */
	public boolean addToProductsList(Product Product) {
		if (Product == null) {
			return true;
		}
		if (!(this.ProductsIDs.contains(Product.getID()))) {
			this.ProductsIDs.add(Product.getID());
		}
		return false;
	}

	/**
	 *
	 * @param Product
	 */
	public boolean deleteFromProductsList(Product Product) {
		if (Product == null) {
			return true;
		}
		this.ProductsIDs.remove(Product.getID());
		return false;
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public ArrayList<Long> getOrdersIDs() {
		return this.OrdersIDs;
	}

	/**
	 *
	 * @return
	 */
	public HashMap<Long, Order> getOrders() {
		HashMap<Long, Order> temp = FileManger.LoadHash(new Order());
		HashMap<Long, Order> ret = new HashMap();
		for (Long OrdersIDIterator : this.OrdersIDs) {
			ret.put(OrdersIDIterator, temp.get(OrdersIDIterator));
		}
		return ret;
	}

	/**
	 *
	 * @param Order
	 */
	public boolean addToOrderList(Order Order) {
		if (Order == null) {
			return true;
		}
		if (!(this.OrdersIDs.contains(Order.getID()))) {
			this.OrdersIDs.add(Order.getID());
		}
		return false;
	}

	/**
	 *
	 * @param Order
	 */
	public boolean deleteFromOrderList(Order Order) {
		if (Order == null) {
			return true;
		}
		this.OrdersIDs.remove(Order.getID());
		return false;
	}

	//==========================================================================================================================================================
	@Override
	public void generateID() {
		HashMap<Long, Supplier> list = FileManger.LoadHash(this);
		long x = 0;
		for (Long key : list.keySet()) {
			if (x < key) {
				x = key;
			}
		}
		super.setID(x + 1);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String getFilePath() {
		return "Supplier.bin";
	}

	@Override
	public String toString() {
		return "ID : " + Long.toString(this.getID())
				+ "			Name : " + this.getName()
				+ "				Address : " + this.getAddress()
				+ "				WebSite : " + this.Website
				+ "				E-mail : " + this.getEmail()
				+ "				Phone No : " + Long.toString(this.getPhoneNumber())
				+ "				Bank Acc : " + Long.toString(this.BankAcc);
	}

	@Override
	public int compareTo(Supplier o) {
		return (int) (o.getID() - this.getID());
	}

	@Override
	public String[] getList() {
		return new String[]{Long.toString(this.getID()), this.getName(), Long.toString(this.getPhoneNumber()), this.Website, Long.toString(this.BankAcc), this.getAddress(), this.getEmail()};
	}

	@Override
	public String[] getListHeader() {
		return new String[]{"ID", "Name", "Phone Number", "Website", "Bank Acc", "Address", "Email"};
	}

	@Override
	public String getTitle() {
		return "Supplier";
	}

	//==========================================================================================================================================================
	public boolean Save() {

		return Save(FileManger.LoadHash(new Supplier()), FileManger.LoadHash(new Product()));
	}

	public boolean Delete() {

		return Delete(FileManger.LoadHash(new Supplier()), FileManger.LoadHash(new Product()));
	}

	private boolean Save(HashMap<Long, Supplier> SupplierList, HashMap<Long, Product> productList) {
		if (SupplierList == null) {
			SupplierList = new HashMap<Long, Supplier>();
		}
		if (productList == null) {
			productList = new HashMap<Long, Product>();
		}

		SupplierList.put(this.getID(), this);
		for (int i = 0; i < this.getProductsIDs().size(); i++) {
			Product temp = productList.get(this.getProductsIDs().get(i));
			if (temp != null) {
				temp.addToSupplierList(this);
			}
		}
		return FileManger.Save(SupplierList, new Supplier()) && FileManger.Save(productList, new Product());
	}

	private boolean Delete(HashMap<Long, Supplier> SupplierList, HashMap<Long, Product> productList) {
		if (SupplierList == null) {
			SupplierList = new HashMap<Long, Supplier>();
		}
		if (productList == null) {
			productList = new HashMap<Long, Product>();
		}

		for (int i = 0; i < this.getProductsIDs().size(); i++) {
			Product temp = productList.get(this.getProductsIDs().get(i));
			if (temp != null) {
				temp.deleteFromSupplierList(this);
			}
		}
		if (SupplierList.remove(this.getID()) == null) {
			return true;// if object not found
		}
		return FileManger.Save(SupplierList, new Supplier()) && FileManger.Save(productList, new Product());
	}

	/**
	 *
	 * @return
	 */
	public static HashMap<Long, Supplier> loadSuppliers() {
		return FileManger.LoadHash(new Supplier());
	}

	/**
	 *
	 * @param ID
	 * @return
	 */
	public static Supplier searchSupplier(long ID) {
		return loadSuppliers().get(ID);
	}

	public static Supplier searchSupplier(String ID) {
		if (FileManger.isNumber(ID)) {
			return searchSupplier(new Long(ID));
		}
		return null;
	}

	public static String[] SuppliersIDs() {
		HashMap<Long, Supplier> temp = loadSuppliers();
		String[] ret = new String[temp.size()];
		int i = 0;
		for (Long key : temp.keySet()) {
			ret[i] = key.toString();
			i++;
		}
		return ret;
	}
}
