package Interfaces;

import InventorySystem.*;

public interface IProduct {

	boolean GenerateID();

	void addToOrderList(Order Order);

	void deleteFromOrderList(Order Order);

	void addToSupplierList(int ID);

	void deleteFromSupplierList(Supplier x);

	@Override
	String toString();

	String toSaveString();

}
