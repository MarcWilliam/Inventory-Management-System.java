package InventorySystem;

import Interfaces.*;
import InventorySystem.Date;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Invoice extends Entity implements IInvoice {

	private ArrayList<Integer> ProductsIDs;
	private ArrayList<String> Productsname;
	private ArrayList<Integer> Quantity;
	private ArrayList<Float> PricePerUnit;
	public int currentTax;
	public Date SoldDate;

	public Invoice() {
		this.ID = 0;
		this.ProductsIDs = new ArrayList();
		this.Productsname = new ArrayList();
		this.Quantity = new ArrayList();
		this.PricePerUnit = new ArrayList();
		this.currentTax = 10;
		this.SoldDate = new Date();
		this.SoldDate.SetDateToCurent();
	}

	public Invoice(String LoadFromString) {
		LoadFromString = LoadFromString.trim();
		StringTokenizer st = new StringTokenizer(LoadFromString, "#$#");
		this.ID = Integer.valueOf(st.nextToken());
		FileManger.StringToIntegerArraylist(st.nextToken(), this.ProductsIDs);
		FileManger.StringToStringArraylist(st.nextToken(), this.Productsname);
		FileManger.StringToIntegerArraylist(st.nextToken(), this.Quantity);
		FileManger.StringToFloatArraylist(st.nextToken(), this.PricePerUnit);
		this.currentTax = Integer.valueOf(st.nextToken());
		this.SoldDate = new Date(st.nextToken());
	}

	static public String getFilePath() {
		return "Invoice.txt";
	}

	@Override
	public boolean GenerateID() {
		return super.GenerateID(getFilePath());
	}

	@Override
	public void addToProductsList(Product Product, int Quantity) {
		if (this.ProductsIDs.contains(Product.ID)) {
			this.Quantity.set(this.ProductsIDs.indexOf(Product.ID), Quantity);
		} else {
			this.Productsname.add(Product.Name);
			this.ProductsIDs.add(Product.ID);
			this.Quantity.add(Quantity);
			this.PricePerUnit.add(Product.PricePerUnit);
		}
	}

	public int ProductsListSize() {
		return ProductsIDs.size();
	}

	public String getProductsName(int Index) {
		return this.Productsname.get(Index);
	}

	public int getProductsQuantity(int Index) {
		return this.Quantity.get(Index);
	}

	public int getProductsIDs(int Index) {
		return this.ProductsIDs.get(Index);
	}

	@Override
	public void deleteFromProductsList(Product Product) {
		int Index = this.ProductsIDs.indexOf(Product.ID);
		this.Productsname.remove(Index);
		this.ProductsIDs.remove(Index);
		this.Quantity.remove(Index);
		this.PricePerUnit.remove(Index);
	}

	@Override
	public String toString() {
		String temp = "Curent Tax :" + Integer.toString(this.currentTax) + "						Issue Date: " + SoldDate.toString() + '\n'
				+ "Productsname								Quantity							Price/Unit\n";
		for (int i = 0; i < Quantity.size(); i++) {
			temp += this.Productsname.get(i).toString() + "						" + this.Quantity.get(i).toString() + "							" + this.PricePerUnit.get(i).toString() + '\n';
		}
		return temp += "Total: " + Float.toString(calculateTotal());
	}

	@Override
	public String toSaveString() {
		return "#$#"// Seperate eatch variableSeperator look like a square
				+ Integer.toString(this.ID) + "#$#"
				+ this.ProductsIDs.toString() + "#$#"
				+ this.Productsname.toString() + "#$#"
				+ this.Quantity.toString() + "#$#"
				+ this.PricePerUnit.toString() + "#$#"
				+ Integer.toString(this.currentTax) + "#$#"
				+ this.SoldDate.toString() + "#$#";
	}

	@Override
	public float calculateTotal() {
		float temp = 0;
		for (int i = 0; i < this.PricePerUnit.size(); i++) {
			temp += this.PricePerUnit.get(i) * this.Quantity.get(i);
		}
		return temp * ((currentTax / 100) + 1);
	}

}
