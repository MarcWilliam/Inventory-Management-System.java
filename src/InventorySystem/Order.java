package InventorySystem;

import Interfaces.*;
import InventorySystem.Date;
import java.util.*;

/**
 * @author Marc
 */
public class Order extends Entity implements IOrder {

	Date OrderDate;
	Date DeliveryDate;
	public int Quantity;
	public float PricePerUnit;
	public boolean Payedfor;   //0 for unpaied   1 for paied 
	public boolean Delivered;  // 0 for not yet Delivered  1 for Delivered
	public int ProductID;
	int SupplierID;

	Order() {
		this.ID = 0;
		this.OrderDate = new Date();
		this.OrderDate.SetDateToCurent();
		this.DeliveryDate = new Date();
		this.Quantity = 0;
		this.PricePerUnit = 0;
		this.Payedfor = false;
		this.Delivered = false;
		this.SupplierID = 0;
		this.ProductID = 0;
	}

	Order(String LoadFromString) {
		LoadFromString = LoadFromString.trim();
		StringTokenizer st = new StringTokenizer(LoadFromString, "#$#");
		this.ID = Integer.valueOf(st.nextToken());
		this.OrderDate = new Date(st.nextToken());
		this.DeliveryDate = new Date(st.nextToken());
		this.Quantity = Integer.valueOf(st.nextToken());
		this.PricePerUnit = Float.valueOf(st.nextToken());
		if ("true" == st.nextToken()) {
			this.Payedfor = true;
		} else {
			this.Payedfor = false;
		}
		if ("true" == st.nextToken()) {
			this.Delivered = true;
		} else {
			this.Delivered = false;
		}
		this.ProductID = Integer.valueOf(st.nextToken());
		this.SupplierID = Integer.valueOf(st.nextToken());
	}

	/**
	 *
	 * @return
	 */
	static public String getFilePath() {
		return "Order.txt";
	}

	/**
	 *
	 * @return
	 */
	@Override
	public boolean GenerateID() {
		return super.GenerateID(getFilePath());
	}

	@Override
	public String toString() {
		return "ID: " + this.ID
				+ "					Order Date: " + this.OrderDate.toString()
				+ "					Delivery Date: " + this.DeliveryDate.toString()
				+ "					Quantity: " + Integer.toString(this.Quantity)
				+ "					Price/Unit: " + Float.toString(this.PricePerUnit)
				+ "					Payedfor: " + Boolean.toString(this.Payedfor)
				+ "					Delivered: " + Boolean.toString(this.Delivered)
				+ "					Product ID: " + Integer.toString(this.ProductID)
				+ "					Supplier ID: " + Integer.toString(this.SupplierID);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toSaveString() {
		return "#$#"// Seperate eatch variableSeperator look like a square 
				+ this.ID + "#$#"
				+ this.OrderDate.toString() + "#$#"
				+ this.DeliveryDate.toString() + "#$#"
				+ Integer.toString(this.Quantity) + "#$#"
				+ Float.toString(this.PricePerUnit) + "#$#"
				+ Boolean.toString(this.Payedfor) + "#$#"
				+ Boolean.toString(this.Delivered) + "#$#"
				+ Integer.toString(this.ProductID) + "#$#"
				+ Integer.toString(this.SupplierID) + "#$#";
	}
}
