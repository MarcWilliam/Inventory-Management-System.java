package Lib;

import Abstract.*;
import Core.Date;
import Core.*;
import InterFaces.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Invoice extends ID implements Serializable, IHashMap, Comparable<Invoice>, IList {

	private ArrayList<Long> ProductsIDs;
	private ArrayList<String> Productsname;
	private ArrayList<Long> Quantity;
	private ArrayList<Float> PricePerUnit;
	private int currentTax;
	private Date SoldDate;

	/**
	 *
	 */
	public Invoice() {
		super();
		this.ProductsIDs = new ArrayList();
		this.Productsname = new ArrayList();
		this.Quantity = new ArrayList();
		this.PricePerUnit = new ArrayList();
		this.currentTax = 10;
		this.SoldDate = new Date();
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public HashMap<Long, Product> getProducts() //===============================================
	{
		HashMap<Long, Product> temp = FileManger.LoadHash(new Product());
		HashMap<Long, Product> ret = new HashMap();
		for (Long ProductsIDIterator : this.ProductsIDs) {
			ret.put(temp.get(ProductsIDIterator).getID(), temp.get(ProductsIDIterator));
		}
		return ret;
	}

	/**
	 *
	 * @return
	 */
	public int getCurrentTax() {
		return this.currentTax;
	}

	/**
	 *
	 * @return
	 */
	public Date getSoldDate() {
		return this.SoldDate;
	}

	/**
	 *
	 * @param Index
	 * @return
	 */
	public String getProductsName(int Index) {
		return this.Productsname.get(Index);
	}

	/**
	 *
	 * @param Index
	 * @return
	 */
	public Long getProductsQuantity(int Index) {
		return this.Quantity.get(Index);
	}

	/**
	 *
	 * @param Index
	 * @return
	 */
	public Long getProductsIDs(int Index) {
		return this.ProductsIDs.get(Index);
	}

	/**
	 *
	 * @return
	 */
	public int ProductsListSize() {
		return ProductsIDs.size();
	}

	public String[][] getTabel() // may cause index out of bound <<<<<<<<<<<<<<=============================================
	{
		String[][] ret = new String[ProductsListSize()][4];
		for (int i = 0; i < ProductsListSize(); i++) {
			ret[i][0] = Long.toString(ProductsIDs.get(i));
			ret[i][1] = Productsname.get(i);
			ret[i][2] = Float.toString(PricePerUnit.get(i));
			ret[i][3] = Long.toString(Quantity.get(i));
		}
		return ret;
	}

	public String[] getTabelHeader() {
		return new String[]{"ID", "Name", "Price/Unit", "Quantity"};
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @param Product
	 * @param Quantity
	 */
	public boolean addToProductsList(Product Product, Long Quantity) {
		if (Product == null) {
			return true;
		}
		if (this.ProductsIDs.contains(Product.getID())) {
			this.Quantity.set(this.ProductsIDs.indexOf(Product.getID()), Quantity);
		} else {
			this.Productsname.add(Product.getName());
			this.ProductsIDs.add(Product.getID());
			this.Quantity.add(Quantity);
			this.PricePerUnit.add(Product.getPricePerUnit());
		}
		return false;
	}

	public boolean addToProductsList(Product Product, String Quantity) {
		if (!FileManger.isNumber(Quantity)) {
			return true;
		}
		return addToProductsList(Product, new Long(Quantity));
	}

	/**
	 *
	 * @param Product
	 */
	public boolean deleteFromProductsList(Product Product) {
		if (Product == null) {
			return true;
		}
		int Index = this.ProductsIDs.indexOf(Product.getID());
		this.Productsname.remove(Index);
		this.ProductsIDs.remove(Index);
		this.Quantity.remove(Index);
		this.PricePerUnit.remove(Index);
		return false;
	}

	/**
	 *
	 * @return
	 */
	public float calculateTotal() {
		float temp = 0;
		for (int i = 0; i < this.PricePerUnit.size(); i++) {
			temp += this.PricePerUnit.get(i) * this.Quantity.get(i);
		}
		return temp * ((currentTax / 100) + 1);
	}

	//==========================================================================================================================================================
	@Override
	public String toString() {
		String temp = "Curent Tax :" + Integer.toString(this.currentTax) + "						Issue Date: " + SoldDate.toString() + '\n' + "Productsname								Quantity							Price/Unit\n";
		for (int i = 0; i < Quantity.size(); i++) {
			temp += this.Productsname.get(i).toString() + "						" + this.Quantity.get(i).toString() + "							" + this.PricePerUnit.get(i).toString() + '\n';
		}
		return temp += "Total: " + Float.toString(calculateTotal());
	}

	@Override
	public int compareTo(Invoice o) {
		return (int) (o.getID() - this.getID());
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String getFilePath() {
		return "Invoice.bin";
	}

	@Override
	public void generateID() {
		HashMap<Long, Invoice> list = FileManger.LoadHash(this);
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
		return new String[]{Long.toString(this.getID()), this.SoldDate.toString(), Integer.toString(currentTax)};
	}

	@Override
	public String[] getListHeader() {
		return new String[]{"ID", "Issue Date", "Tax"};
	}

	@Override
	public String getTitle() {
		return "Invoice";
	}
	//==========================================================================================================================================================

	/**
	 *
	 * @param invoice
	 * @return
	 */
	public boolean Save() {
		return Save(LoadInvoices(), Product.loadProducts());
	}

	/**
	 *
	 * @param InvoiceID
	 * @return
	 */
	public boolean Delete() {
		return Delete(LoadInvoices(), Product.loadProducts());
	}

	/**
	 *
	 * @param this
	 * @param listInvoice
	 * @param listProduct
	 * @return
	 */
	private boolean Save(HashMap<Long, Invoice> listInvoice, HashMap<Long, Product> listProduct) {
		if (listProduct == null) {
			listProduct = new HashMap<Long, Product>();
		}
		if (listInvoice == null) {
			listInvoice = new HashMap<Long, Invoice>();
		}

		for (int i = 0; i < this.ProductsListSize(); i++) {
			//				if (listProduct.get(this.getProductsIDs(i)).getQuantity() < this.getProductsQuantity(i))
			//						return true;
			Product tempp = listProduct.get(this.getProductsIDs(i));
			if (tempp != null) {
				tempp.setQuantitySoldIncrement(this.getProductsQuantity(i));
				tempp.setQuantityIncrement(-this.getProductsQuantity(i));
			}
		}
		return FileManger.Save(listInvoice, new Invoice()) && FileManger.Save(listProduct, new Product());
	}

	/**
	 *
	 * @param InvoiceID
	 * @param listInvoice
	 * @param listProduct
	 * @return
	 */
	private boolean Delete(HashMap<Long, Invoice> listInvoice, HashMap<Long, Product> listProduct) {
		if (listProduct == null) {
			listProduct = new HashMap<Long, Product>();
		}
		if (listInvoice == null) {
			listInvoice = new HashMap<Long, Invoice>();
		}

		Invoice invoice = listInvoice.get(this.getID());
		for (int i = 0; i < invoice.ProductsListSize(); i++) {

			Product tempp = listProduct.get(this.getProductsIDs(i));
			if (tempp != null) {
				tempp.setQuantitySoldIncrement(-invoice.getProductsQuantity(i));
				tempp.setQuantityIncrement(invoice.getProductsQuantity(i));
			}
		}
		return FileManger.Save(listInvoice, new Invoice()) && FileManger.Save(listProduct, new Product());
	}

	/**
	 *
	 * @return
	 */
	public static HashMap<Long, Invoice> LoadInvoices() {
		return FileManger.LoadHash(new Invoice());
	}

	public static Invoice searchInvoice(long ID) {
		return LoadInvoices().get(ID);
	}

	public static Invoice searchInvoice(String ID) {
		if (FileManger.isNumber(ID)) {
			return searchInvoice(new Long(ID));
		}
		return null;
	}
}
