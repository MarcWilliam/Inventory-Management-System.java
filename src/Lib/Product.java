package Lib;

import Abstract.*;
import Core.*;
import InterFaces.*;
import User.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Product extends EntityObject implements Serializable, IHashMap, Comparable<Product>, IList {

	private String Name;
	private String Category;
	private String Brand;
	private long QuantitySold;
	private ArrayList<Long> OrdersIDs;
	private ArrayList<Long> SuppliersIDs;

	/**
	 *
	 */
	public Product() {
		super();
		this.Name = "Enter Name here";
		this.Category = "Enter Category";
		this.Brand = "Enter a Brand name";
		this.QuantitySold = 0;
		this.OrdersIDs = new ArrayList();
		this.SuppliersIDs = new ArrayList();
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public ArrayList<Long> getOrdersIDs() {
		return OrdersIDs;
	}

	/**
	 *
	 * @return
	 */
	public HashMap<Long, Order> getOrders() {
		HashMap<Long, Order> temp = FileManger.LoadHash(new Order());
		HashMap<Long, Order> ret = new HashMap();
		for (Long OrdersIDIterator : this.OrdersIDs) {
			ret.put(temp.get(OrdersIDIterator).getID(), temp.get(OrdersIDIterator));
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
		this.OrdersIDs.add(Order.getID());
		return false;
	}

	public void addToOrderList(Long Order) {
		this.OrdersIDs.add(Order);
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

	/**
	 *
	 * @return
	 */
	public ArrayList<Long> getSuppliersIDs() {
		return SuppliersIDs;
	}

	public String[] getSuppliersIDsArrStr() {
		String ret[] = new String[SuppliersIDs.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = SuppliersIDs.get(i).toString();
		}
		return ret;
	}

	/**
	 *
	 * @return
	 */
	public HashMap<Long, Supplier> getSuppliers() {
		HashMap<Long, Supplier> temp = FileManger.LoadHash(new Supplier());
		HashMap<Long, Supplier> ret = new HashMap();
		for (Long Iterator : this.SuppliersIDs) {
			ret.put(temp.get(Iterator).getID(), temp.get(Iterator));
		}
		return ret;
	}

	/**
	 *
	 * @param ID
	 */
	public void addToSupplierList(Long ID) {
		this.SuppliersIDs.add(ID);
	}

	public boolean addToSupplierList(Supplier sup) {
		if (sup == null) {
			return true;
		}
		this.SuppliersIDs.add(sup.getID());
		return false;
	}

	/**
	 *
	 * @param x
	 */
	public void deleteFromSupplierList(Supplier x) {
		this.SuppliersIDs.remove(x.getID());
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public String getCategory() {
		return Category;
	}

	/**
	 *
	 * @param Category
	 */
	public boolean setCategory(String Category) {
		if (Category == null) {
			return true;
		}
		if (Category.length() < 4) {
			return true;
		}
		this.Category = Category;
		return false;
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public String getBrand() {
		return Brand;
	}

	/**
	 *
	 * @param Brand
	 */
	public boolean setBrand(String Brand) {
		if (Brand == null) {
			return true;
		}
		if (Brand.length() < 4) {
			return true;
		}
		this.Brand = Brand;
		return false;
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public long getQuantitySold() {
		return QuantitySold;
	}

	/**
	 *
	 * @param QuantitySold
	 */
	public boolean setQuantitySold(long QuantitySold) {
		if (QuantitySold < 0) {
			return true;
		}
		this.QuantitySold = QuantitySold;
		return false;
	}

	public boolean setQuantitySold(String QuantitySold) {
		if (!FileManger.isNumber(QuantitySold)) {
			return true;
		}
		return setQuantitySold(new Long(QuantitySold));
	}

	/**
	 *
	 * @param QuantitySold
	 * @return
	 */
	public boolean setQuantitySoldIncrement(Long QuantitySold) {
		this.QuantitySold += QuantitySold;
		if (QuantitySold <= 0) {
			this.QuantitySold -= QuantitySold;
			return true;
		}
		return false;
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @param Name
	 * @return
	 */
	public boolean setName(String Name) {
		if (Name == null) {
			return true;
		}
		if (Name.length() < 4) {
			return true;
		}
		this.Name = Name;
		return false;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return this.Name;
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	@Override
	public String getFilePath() {
		return "Product.bin";
	}

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
	public String toString() {
		return "ID: " + Long.toString(this.getID())
				+ "					Name: " + this.Name
				+ "					Category: " + this.Category
				+ "					Brand: " + this.Brand
				+ "					Quantity: " + Long.toString(this.getQuantity())
				+ "					Price/Unit :" + Float.toString(this.getPricePerUnit())
				+ "					Exp Date: " + this.getEndDate().toString()
				+ "					ProdDate: " + getStartDate().toString()
				+ "					SuppliersIDs: " + this.SuppliersIDs.toString();
	}

	@Override
	public int compareTo(Product o) {
		return (int) (o.getID() - this.getID());
	}

	@Override
	public String[] getList() {
		return new String[]{Long.toString(this.getID()), this.getName(), this.Category, this.Brand,
			Long.toString(this.getQuantity()), Float.toString(this.getPricePerUnit()), this.getStartDate().toString(), this.getEndDate().toString()};
	}

	@Override
	public String[] getListHeader() {
		return new String[]{"ID", "Name", "Category", "Brand", "Quantity", "Price / Unit", "Pro Date", "Exp Date"};
	}

	@Override
	public String getTitle() {
		return "Product";
	}
//==========================================================================================================================================================

	public boolean Save() {
		return Save(FileManger.LoadHash(this), FileManger.LoadHash(new Supplier()));
	}

	private boolean Save(HashMap<Long, Product> ProductList, HashMap<Long, Supplier> supplierList) //May cause an error need to bebugg later
	{
		if (ProductList == null) {
			ProductList = new HashMap<Long, Product>();
		}
		if (supplierList == null) {
			supplierList = new HashMap<Long, Supplier>();
		}

		for (int i = 0; i < this.getSuppliersIDs().size(); i++) {
			Supplier temp = supplierList.get(this.getSuppliersIDs().get(i));
			if (temp != null) {
				temp.addToProductsList(this);
			}
		}
		ProductList.put(this.getID(), this);

		return FileManger.Save(ProductList, this) && FileManger.Save(supplierList, new Supplier());
	}

	public boolean Delete() {
		return Delete(FileManger.LoadHash(this), FileManger.LoadHash(new Supplier()));
	}

	private boolean Delete(HashMap<Long, Product> ProductList, HashMap<Long, Supplier> supplierList) //May cause an error need to bebugg later
	{
		if (ProductList == null) {
			ProductList = new HashMap<Long, Product>();
		}
		if (supplierList == null) {
			supplierList = new HashMap<Long, Supplier>();
		}

		for (int i = 0; i < this.getSuppliersIDs().size(); i++) {
			Supplier temp = supplierList.get(this.getSuppliersIDs().get(i));
			if (temp != null) {
				temp.deleteFromProductsList(this);
			}
		}

		ProductList.remove(this.getID());
		return FileManger.Save(ProductList, this) && FileManger.Save(supplierList, new Supplier());
	}

	public static HashMap<Long, Product> loadProducts() {
		return FileManger.LoadHash(new Product());
	}

	public static String[] ProductsIDs() {
		HashMap<Long, Product> temp = loadProducts();
		String[] ret = new String[temp.size()];
		int i = 0;
		for (Long key : temp.keySet()) {
			ret[i] = key.toString();
			i++;
		}
		return ret;
	}

	public static Product searchProduct(long ID) {

		return loadProducts().get(ID);
	}

	public static Product searchProduct(String ID) {
		if (FileManger.isNumber(ID)) {
			return searchProduct(new Long(ID));
		}
		return null;
	}

}
