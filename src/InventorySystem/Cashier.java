package InventorySystem;

import Interfaces.*;
import java.util.*;

import static InventorySystem.InventorySystem.ScanInt;

/**
 * @author marcw
 */
public class Cashier extends Employee implements ICashier {

	Cashier() {
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

	Cashier(String LoadFromString) {
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
	public boolean GenerateID() {
		return super.GenerateID(getFilePath());
	}

	/**
	 * remove the products from storage and return it to the invoice
	 *
	 * @param invoice
	 * @return true if File does not exist or invoice quantity is more than the available quantity
	 */
	@Override
	public boolean addInvoice(Invoice invoice) {
		for (int i = 0; i < invoice.ProductsListSize(); i++) {
			Product temp = new Product(FileManger.Search(invoice.getProductsName(i), "Product.txt", 2));
			if (temp.QuantityLeft < invoice.getProductsQuantity(i)) {
				return true;
			}
			temp.QuantityLeft -= invoice.getProductsQuantity(i);
		}
		if (FileManger.Append(invoice.toSaveString(), "Invoice.txt")) {
			return true;
		}
		return false;
	}

	/**
	 * remove the products from the invoice and return it to the storage
	 *
	 * @param InvoiceID
	 * @param invoice
	 * @return true if File does not exist
	 */
	@Override
	public boolean deleteInvoice(int InvoiceID) {
		Invoice invoice = new Invoice();
		String info;
		if ((info = FileManger.Search(Integer.toString(ScanInt()), invoice.getFilePath(), 1)) == null) {
			return true;
		}
		invoice = new Invoice(info);
		for (int i = 0; i < invoice.ProductsListSize(); i++) {
			Product temp = new Product(FileManger.Search(invoice.getProductsName(i), "Product.txt", 2));
			temp.QuantityLeft += invoice.getProductsQuantity(i);
		}
		if (FileManger.Delete(invoice.toSaveString(), "Invoice.txt")) {
			return true;
		}
		return false;
	}
}
