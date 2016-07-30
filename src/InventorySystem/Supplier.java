package InventorySystem;

import Interfaces.*;
import java.util.*;
import java.util.regex.*;

/**
 * @author Marc
 */
public class Supplier extends Entity implements ISupplier {

	public String Name;
	public String Address;
	public String Website;
	private String Email;
	public int PhoneNum;
	public int BankAcc;
	private ArrayList<Integer> ProductsIDs;			 //{*}[unique]
	private ArrayList<Integer> OrdersIDs;					//{*}[unique]

	public Supplier() {
		this.ID = 0;
		this.Name = "Empty";
		this.Address = "Empty";
		this.Website = "Empty";
		this.Email = "Empty";
		this.PhoneNum = 0;
		this.BankAcc = 0;
		this.ProductsIDs = new ArrayList();
		this.OrdersIDs = new ArrayList();
	}

	public Supplier(String LoadFromString) {
		LoadFromString = LoadFromString.trim();
		StringTokenizer st = new StringTokenizer(LoadFromString, "#$#");
		this.ID = Integer.valueOf(st.nextToken());
		this.Name = st.nextToken();
		this.Address = st.nextToken();
		this.Website = st.nextToken();
		this.Email = st.nextToken();
		this.PhoneNum = Integer.valueOf(st.nextToken());
		this.BankAcc = Integer.valueOf(st.nextToken());
		FileManger.StringToIntegerArraylist(st.nextToken(), this.ProductsIDs);
		FileManger.StringToIntegerArraylist(st.nextToken(), this.OrdersIDs);
	}

	@Override
	public boolean GenerateID() {
		return super.GenerateID(getFilePath());
	}

	static public String getFilePath() {
		return "Supplier.txt";
	}

	@Override
	public void addToProductsList(Product Product) {
		if (!(this.ProductsIDs.contains(Product.ID))) {
			this.ProductsIDs.add(Product.ID);
		}
	}

	@Override
	public void deleteFromProductsList(Product Product) {
		this.ProductsIDs.remove(Product.ID);
	}

	@Override
	public void addToOrderList(Order Order) {
		if (!(this.OrdersIDs.contains(Order.ID))) {
			this.OrdersIDs.add(Order.ID);
		}
	}

	@Override
	public void deleteFromOrderList(Order Order) {
		this.OrdersIDs.remove(Order.ID);
	}

	@Override
	public boolean setEmail(String email) // abdelrahmen wrote it not my problem ^_^ i just modified a little
	{//^ ==> begin of the line				// - ==> something to somethiong				//+@ mean @ must exist				//$ end of the line
		Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
		Matcher m = p.matcher(email);
		if (m.matches()) {
			this.Email = email;
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getEmail() {
		return this.Email;
	}

	@Override
	public String toString() {
		return "ID : " + Integer.toString(this.ID)
				+ "			Name : " + this.Name
				+ "				Address : " + this.Address
				+ "				WebSite : " + this.Website
				+ "				E-mail : " + this.Email
				+ "				Phone No : " + Integer.toString(this.PhoneNum)
				+ "				Bank Acc : " + Integer.toString(this.BankAcc);
	}

	@Override
	public String toSaveString() {
		return "#$#"
				+ Integer.toString(this.ID) + "#$#"
				+ this.Name + "#$#"
				+ this.Address + "#$#"
				+ this.Website + "#$#"
				+ this.Email + "#$#"
				+ Integer.toString(this.PhoneNum) + "#$#"
				+ Integer.toString(this.BankAcc) + "#$#"
				+ this.ProductsIDs.toString() + "#$#"
				+ this.OrdersIDs.toString() + "#$#";
	}
}
