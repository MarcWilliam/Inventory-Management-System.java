package Interfaces;

import InventorySystem.*;

public interface IInvoice {

	boolean GenerateID();

	void addToProductsList(Product Product, int Quantity);

	void deleteFromProductsList(Product Product);

	@Override
	String toString();

	String toSaveString();

	float calculateTotal();
}
