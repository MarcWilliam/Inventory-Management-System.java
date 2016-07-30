package User;

import Core.*;
import InterFaces.*;
import Lib.*;
import java.io.*;

/**
 * @author marcw
 */
public class Cashier extends Employee implements Serializable, IHashMap {

	public Cashier() {
		super();
	}

	public Cashier(Employee Emp) {
		super(Emp);
	}

	/**
	 *
	 * @param invoice
	 * @return
	 */
	public boolean addInvoice(Invoice invoice) {
		return invoice.Save();
	}

	/**
	 *
	 * @param InvoiceID
	 * @return
	 */
	public boolean deleteInvoice(Invoice invoice) {
		return invoice.Delete();
	}

	public String[][] ListEInvoice() {
		return FileManger.ListObj(FileManger.LoadHash(new Invoice()));
	}
}
