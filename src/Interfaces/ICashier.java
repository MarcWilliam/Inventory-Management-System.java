package Interfaces;

import InventorySystem.*;

public interface ICashier {

	boolean GenerateID();

	boolean addInvoice(Invoice invoice);

	boolean deleteInvoice(int ID);

}
