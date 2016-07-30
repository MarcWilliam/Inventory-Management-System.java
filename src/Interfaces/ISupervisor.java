package Interfaces;

import InventorySystem.*;
import java.util.*;

public interface ISupervisor {

	boolean GenerateID();

	boolean addProduct(Product Product);

	boolean updateProduct(Product Product);

	boolean deleteProduct(int ID);

	boolean deleteProduct(String Name);

	boolean addOrder(Order Order);

	boolean deleteOrder(int ID);

	boolean updateOrder(Order Order);

	boolean addSupplier(Supplier Supplier);

	boolean deleteSupplier(String Name);

	boolean deleteSupplier(int ID);

	boolean updateSupplier(Supplier Supplier);

	ArrayList<Product> lowQuantityProduct(int LowQuantity);

	ArrayList<Product> listSoonToExpProduct(int daysToExp);

	String listProducts();

	String listSuppliers();

	String listOrders();

}
