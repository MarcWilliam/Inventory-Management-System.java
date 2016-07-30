package InventorySystem;

import Interfaces.*;
import InventorySystem.Date;
import java.util.*;

/**
 * @author Marc
 */
public class Product extends Entity implements IProduct {

	public String Name;
	public String Category;
	public String Brand;
	public int TotalQuantity;
	public int QuantityLeft;
	public float PricePerUnit;
	public Date ExpireDate;
	public Date ProductionDate;
	private ArrayList<Integer> OrdersIDs;				 //[*]{ordered}
	private ArrayList<Integer> SuppliersIDs;			 //{*}[unique]

	public Product() {
		this.ID = 0;
		this.Name = "Empty";
		this.Category = "Empty";
		this.Brand = "Empty";
		this.TotalQuantity = 0;
		this.QuantityLeft = 0;
		this.PricePerUnit = 0;
		this.ExpireDate = new Date();
		this.ProductionDate = new Date();
		this.OrdersIDs = new ArrayList();
		this.SuppliersIDs = new ArrayList();
	}

	public Product(String LoadFromString) {
		LoadFromString = LoadFromString.trim();
		StringTokenizer st = new StringTokenizer(LoadFromString, "#$#");
		this.ID = Integer.valueOf(st.nextToken());
		this.Name = st.nextToken();
		this.Category = st.nextToken();
		this.Brand = st.nextToken();
		this.TotalQuantity = Integer.valueOf(st.nextToken());
		this.QuantityLeft = Integer.valueOf(st.nextToken());
		this.PricePerUnit = Float.valueOf(st.nextToken());
		this.ExpireDate = new Date(st.nextToken());
		this.ProductionDate = new Date(st.nextToken());
		FileManger.StringToIntegerArraylist(st.nextToken(), this.OrdersIDs);
		FileManger.StringToIntegerArraylist(st.nextToken(), this.SuppliersIDs);
	}

	static public String getFilePath() {
		return "Product.txt";
	}

	@Override
	public boolean GenerateID() {
		return super.GenerateID(getFilePath());
	}

	@Override
	public void addToOrderList(Order Order) {
		this.OrdersIDs.add(Order.ID);
	}

	@Override
	public void deleteFromOrderList(Order Order) {
		this.OrdersIDs.remove(Order.ID);
	}

	@Override
	public void addToSupplierList(int ID) {
		this.SuppliersIDs.add(ID);
	}

	@Override
	public void deleteFromSupplierList(Supplier x) {
		this.SuppliersIDs.remove(x.ID);
	}

	@Override
	public String toString() {
		return "ID: " + Integer.toString(this.ID)
				+ "					Name: " + this.Name
				+ "					Category: " + this.Category
				+ "					Brand: " + this.Brand
				+ "					Quantity: " + Integer.toString(this.QuantityLeft)
				+ "					Price/Unit :" + Float.toString(this.PricePerUnit)
				+ "					Exp Date: " + this.ExpireDate.toString()
				+ "					ProdDate: " + this.ProductionDate.toString()
				+ "					SuppliersIDs: " + this.SuppliersIDs.toString();
	}

	@Override
	public String toSaveString() {
		return "#$#"
				+ Integer.toString(this.ID) + "#$#"
				+ this.Name + "#$#"
				+ this.Category + "#$#"
				+ this.Brand + "#$#"
				+ Integer.toString(this.TotalQuantity) + "#$#"
				+ Integer.toString(this.QuantityLeft) + "#$#"
				+ Float.toString(this.PricePerUnit) + "#$#"
				+ this.ExpireDate.toString() + "#$#"
				+ this.ProductionDate.toString() + "#$#"
				+ this.OrdersIDs.toString() + "#$#"
				+ this.SuppliersIDs.toString() + "#$#";
	}
}
