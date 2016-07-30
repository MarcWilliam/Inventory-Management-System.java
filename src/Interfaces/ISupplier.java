package Interfaces;

import InventorySystem.*;

public interface ISupplier {

	boolean GenerateID();

	void addToProductsList(Product Product);

	void deleteFromProductsList(Product Product);

	void addToOrderList(Order Order);

	void deleteFromOrderList(Order Order);

	boolean setEmail(String email);

	String getEmail();

	@Override
	String toString();

	String toSaveString();
}
