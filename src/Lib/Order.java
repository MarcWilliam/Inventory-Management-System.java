package Lib;

import Abstract.*;
import Core.*;
import InterFaces.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Order extends EntityObject implements Serializable, IHashMap, Comparable<Order>, IList {

	private boolean Payedfor;
	private boolean Delivered;
	private long ProductID;
	private long SupplierID;

	/**
	 *
	 */
	public Order() {
		super();
		this.Payedfor = false;
		this.Delivered = true;
		this.SupplierID = 0;
		this.ProductID = 0;
	}
	//==========================================================================================================================================================

	/**
	 *
	 * @return
	 */
	public boolean isPayedfor() {
		return Payedfor;
	}

	/**
	 *
	 * @return
	 */
	public boolean isDelivered() {
		return Delivered;
	}

	/**
	 *
	 * @param Payedfor
	 * @return
	 */
	public boolean setPayedfor(boolean Payedfor) {
		this.Payedfor = Payedfor;
		return false;
	}

	/**
	 *
	 * @param Delivered
	 * @return
	 */
	public boolean setDelivered(boolean Delivered) {
		this.Delivered = Delivered;
		return false;
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @param ProductID
	 * @return
	 */
	public boolean setProductID(long ProductID) {
		this.ProductID = ProductID;
		return false;
	}

	/**
	 *
	 * @param Product
	 * @return
	 */
	public boolean setProductID(Product Product) {
		if (Product == null) {
			return true;
		}
		this.ProductID = Product.getID();
		return false;
	}

	/**
	 *
	 * @return
	 */
	public Long getProductID() {
		return ProductID;
	}

	/**
	 *
	 * @return
	 */
	public Product getProduct() {
		HashMap<Long, Product> temp = FileManger.LoadHash(new Product());
		return temp.get(ProductID);
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public Long getSupplierID() {
		return SupplierID;
	}

	/**
	 *
	 * @return
	 */
	public Supplier getSupplier() {
		HashMap<Long, Supplier> temp = FileManger.LoadHash(new Supplier());
		return temp.get(SupplierID);
	}

	/**
	 *
	 * @param SupplierID
	 */
	public boolean setSupplierID(long SupplierID) {
		this.SupplierID = SupplierID;
		return false;
	}

	/**
	 *
	 * @param Supplier
	 */
	public boolean setSupplierID(Supplier Supplier) {
		if (Supplier == null) {
			return true;
		}
		this.SupplierID = Supplier.getID();
		return false;
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	@Override
	public String getFilePath() {
		return "Order.bin";
	}

	@Override
	public String toString() {
		return "ID: " + this.getID()
				+ "					Order Date: " + this.getStartDate().toString()
				+ "					Delivery Date: " + this.getEndDate().toString()
				+ "					Quantity: " + Long.toString(this.getQuantity())
				+ "					Price/Unit: " + Float.toString(this.getPricePerUnit())
				+ "					Payedfor: " + Boolean.toString(this.Payedfor)
				+ "					Delivered: " + Boolean.toString(this.Delivered)
				+ "					Product ID: " + Long.toString(this.ProductID)
				+ "					Supplier ID: " + Long.toString(this.SupplierID);
	}

	@Override
	public int compareTo(Order o) {
		return (int) (o.getID() - this.getID());
	}

	@Override
	public void generateID() {
		HashMap<Long, Order> list = FileManger.LoadHash(this);
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
		return new String[]{Long.toString(this.getID()), Long.toString(ProductID), Long.toString(SupplierID),
			Long.toString(this.getQuantity()), Float.toString(this.getPricePerUnit()), this.getStartDate().toString(), this.getEndDate().toString()};
	}

	@Override
	public String[] getListHeader() {
		return new String[]{"Order ID", "Product ID", "Supplier ID", "Quantity", "Price / Unit", "Order Date", "Delivery Date"};
	}

	@Override
	public String getTitle() {
		return "Order";
	}
	//==========================================================================================================================================================

	@Override
	public boolean Save() {

		return Save(FileManger.LoadHash(new Order()), FileManger.LoadHash(new Product()), FileManger.LoadHash(new Supplier()));
	}

	@Override
	public boolean Delete() {

		return Delete(FileManger.LoadHash(new Order()), FileManger.LoadHash(new Product()), FileManger.LoadHash(new Supplier()));
	}

	private boolean Save(HashMap<Long, Order> orderList, HashMap<Long, Product> productList, HashMap<Long, Supplier> SupplierList) {
		if (productList == null) {
			productList = new HashMap<Long, Product>();
		}
		if (SupplierList == null) {
			SupplierList = new HashMap<Long, Supplier>();
		}
		if (orderList == null) {
			orderList = new HashMap<Long, Order>();
		}

		Order Old = orderList.put(this.getID(), this);

		Product temp = productList.get(this.getID());
		if (temp != null) {
			temp.addToOrderList(this);
		}

		Supplier temps = SupplierList.get(this.getID());
		if (temps != null) {
			temps.addToOrderList(this);
		}

		if ((Old != null && !Old.isDelivered() && this.isDelivered()) || (Old == null && this.isDelivered())) // if the object is found and delivered it ill increment the quantity***********************May cause null pointer exception NOT SURE YET (not tested)
		{
			productList.get(this.getID()).setQuantityIncrement(this.getQuantity());
		}
		return FileManger.Save(orderList, new Order()) && FileManger.Save(productList, new Product()) && FileManger.Save(SupplierList, new Supplier());
	}

	private boolean Delete(HashMap<Long, Order> orderList, HashMap<Long, Product> productList, HashMap<Long, Supplier> SupplierList) {
		if (productList == null) {
			productList = new HashMap<Long, Product>();
		}
		if (SupplierList == null) {
			SupplierList = new HashMap<Long, Supplier>();
		}
		if (orderList == null) {
			orderList = new HashMap<Long, Order>();
		}

		Order Old = orderList.remove(this.getID());

		Product temp = productList.get(this.getID());
		if (temp != null) {
			temp.deleteFromOrderList(this);
		}

		Supplier temps = SupplierList.get(this.getID());
		if (temps != null) {
			temps.deleteFromOrderList(this);
		}

		if ((Old != null && !Old.isDelivered() && this.isDelivered()) || (Old == null && this.isDelivered())) // if the object is found and delivered it ill increment the quantity
		{
			productList.get(this.getID()).setQuantityIncrement(-this.getQuantity());
		}
		return FileManger.Save(orderList, new Order()) && FileManger.Save(productList, new Product()) && FileManger.Save(SupplierList, new Supplier());
	}

	/**
	 *
	 * @param ID
	 * @return
	 */
	public static Order searchOrder(long ID) {
		return loadOrders().get(ID);
	}

	public static Order searchOrder(String ID) {
		if (FileManger.isNumber(ID)) {
			return searchOrder(new Long(ID));
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	public static HashMap<Long, Order> loadOrders() {
		return FileManger.LoadHash(new Order());
	}
}
