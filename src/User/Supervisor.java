package User;

import Core.Date;
import Core.*;
import InterFaces.*;
import Lib.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Supervisor extends Employee implements Serializable, IHashMap {

	public Supervisor() {
		super();
	}

	public Supervisor(Employee emp) {
		super(emp);
	}

//==========================================================================================================================================================
	/**
	 *
	 * @param Pro
	 * @return
	 */
	public boolean updateProduct(Product Pro) {
		return Pro.Save();
	}

	/**
	 *
	 * @param ID
	 * @return
	 */
	public boolean deleteProduct(Product Pro) {
		return Pro.Delete();
	}

	public String[][] ListProduct() {
		return FileManger.ListObj(FileManger.LoadHash(new Product()));
	}
//==========================================================================================================================================================

	/**
	 *
	 * @param order
	 * @return
	 */
	public boolean updateOrder(Order order) {
		return order.Save();
	}

	/**
	 *
	 * @param order
	 * @param ID
	 * @return
	 */
	public boolean deleteOrder(Order order) {
		return order.Delete();
	}

	public String[][] ListOrder() {
		return FileManger.ListObj(FileManger.LoadHash(new Order()));
	}
//==========================================================================================================================================================

	/**
	 *
	 * @param Supp
	 * @return
	 */
	public boolean updateSupplier(Supplier Supp) {
		return Supp.Save();
	}

	/**
	 *
	 * @param Supp
	 * @param ID
	 * @return
	 */
	public boolean deleteSupplier(Supplier Supp) {
		return Supp.Delete();
	}

	public String[][] ListSupplier() {
		return FileManger.ListObj(FileManger.LoadHash(new Supplier()));
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @param LowQuantity
	 * @return
	 */
	public String[][] lowQuantityProduct(long LowQuantity) {
		return FileManger.ListObj(lowQuantityProduct(LowQuantity, Product.loadProducts()));
	}

	public String[][] lowQuantityProduct(String LowQuantity) {
		if (FileManger.isNumber(LowQuantity)) {
			return lowQuantityProduct(new Long(LowQuantity));
		}
		return null;
	}

	/**
	 *
	 * @param daysToExp
	 * @return
	 */
	public String[][] listSoonToExpProduct() {
		return FileManger.ListObj(listSoonToExpProduct(Product.loadProducts()));
	}

	private HashMap lowQuantityProduct(long LowQuantity, HashMap<Long, Product> list) {
		HashMap<Long, Product> x = new HashMap();
		for (Long key : list.keySet()) {
			if (list.get(key).getQuantity() >= LowQuantity) {
				x.put(key, list.get(key));
			}
		}
		return x;
	}

	private HashMap listSoonToExpProduct(HashMap<Long, Product> list) {
		HashMap<Long, Product> x = new HashMap();
		for (Long key : list.keySet()) {
			if (list.get(key).getEndDate().getValue() < new Date().getValue()) {
				x.put(key, list.get(key));
			}
		}
		return x;
	}

}
