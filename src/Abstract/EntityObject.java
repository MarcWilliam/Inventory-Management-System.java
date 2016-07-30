package Abstract;

import Core.*;
import java.io.*;

/**
 *
 * @author Marc Wafik
 */
public abstract class EntityObject extends ID implements Serializable {

	private Date StartDate;
	private Date EndDate;
	private Long Quantity;
	private float PricePerUnit;

	/**
	 *
	 */
	public EntityObject() {
		super();
		this.StartDate = new Date();
		this.EndDate = new Date();
		this.Quantity = 0l;
		this.PricePerUnit = 0;
	}

	//==========================================================================================================================================================
	/**
	 * expire date must be larger than the production date
	 *
	 * @param Exp the expire date
	 * @param Pro the production date
	 * @return
	 * @returnis it in the correct format ?
	 */
	public boolean setDate(Date Exp, Date Pro) {
		if (Exp.CompareToCurrent() || Exp.GetDate() > Pro.GetDate()) {
			this.StartDate = Pro;
			this.EndDate = Exp;
			return false;
		}
		return true;
	}

	/**
	 * the production date must be smaller than the current time
	 *
	 * @param Pro the production date
	 * @return is it in the correct format ?
	 */
	public boolean setStartDate(Date Pro) {
		if (Pro.CompareToCurrent() || EndDate.GetDate() >= Pro.GetDate()) {
			this.StartDate = Pro;
			return false;
		}
		return true;
	}

	/**
	 * the expire date must be larger than current time and production date
	 *
	 * @param Exp the Expire Date
	 * @return is it in the correct format ?
	 */
	public boolean setEndDate(Date Exp) {
		if (Exp.CompareToCurrent() || Exp.GetDate() > this.StartDate.GetDate()) {
			this.EndDate = Exp;
			return false;
		}
		return true;
	}

	/**
	 * the production date must be smaller than the current time
	 *
	 * @param Pro the production date
	 * @return is it in the correct format ?
	 */
	public boolean setStartDate(long day, long month, long year) {
		Date temp = new Date();
		if (temp.SetDate(day, month, year)) {
			return true;
		}
		return setStartDate(temp);
	}

	public boolean setStartDate(String Date) {
		Date temp = new Date();
		if (Date == null) {
			return true;
		}
		return setStartDate(temp);
	}

	/**
	 * the expire date must be larger than current time and production date
	 *
	 * @param Exp the Expire Date
	 * @return is it in the correct format ?
	 */
	public boolean setEndDate(long day, long month, long year) {
		Date temp = new Date();
		if (temp.SetDate(day, month, year)) {
			return true;
		}
		return setEndDate(temp);
	}

	public boolean setEndDate(String Date) {
		Date temp = new Date();
		if (Date == null) {
			return true;
		}
		return setEndDate(temp);
	}

	/**
	 * set production date to the initial value
	 */
	public void setStartDateToCurrent() {
		this.EndDate.SetDateToCurent();
	}

	/**
	 * set expire date to the initial value
	 */
	public void setEndDateToCurrent() {
		this.EndDate.SetDateToCurent();
	}

	/**
	 * must be larger than or equal to 0
	 *
	 * @param Quantity
	 * @return is it in the correct format ?
	 */
	public boolean setQuantity(Long Quantity) {
		if (Quantity >= 0) {
			this.Quantity = Quantity;
			return false;
		}
		return true;
	}

	/**
	 * must be larger than or equal to 0
	 *
	 * @param Quantity
	 * @return is it in the correct format ?
	 */
	public boolean setQuantity(String Quantity) {
		if (FileManger.isNumber(Quantity)) {
			return setQuantity(new Long(Quantity));
		}
		return true;
	}

	/**
	 * the remaining quantity must be larger than or equal to 0
	 *
	 * @param Quantity
	 * @return is it in the correct format ?
	 */
	public boolean setQuantityIncrement(Long Quantity) {
		this.Quantity += Quantity;
		if (Quantity <= 0) {
			this.Quantity -= Quantity;
			return true;
		}
		return false;
	}

	/**
	 * Price/unit must be larger than 0
	 *
	 * @param PricePerUnit
	 * @return is it in the correct format ?
	 */
	public boolean setPricePerUnit(Float PricePerUnit) {
		if (PricePerUnit > 0.0) {
			this.PricePerUnit = PricePerUnit;
			return false;
		}
		return true;
	}

	public boolean setPricePerUnit(String PricePerUnit) {
		if (FileManger.isFloat(PricePerUnit)) {
			return setPricePerUnit(new Float(PricePerUnit));
		}
		return true;
	}
	//==========================================================================================================================================================

	/**
	 *
	 * @return Production Date
	 */
	public Date getStartDate() {
		return this.StartDate;
	}

	/**
	 *
	 * @return Expire Date
	 */
	public Date getEndDate() {
		return this.EndDate;
	}

	/**
	 *
	 * @return integer Quantity
	 */
	public Long getQuantity() {
		return this.Quantity;
	}

	/**
	 *
	 * @return float Price/unit
	 */
	public float getPricePerUnit() {
		return this.PricePerUnit;
	}

	/**
	 *
	 * @return float total price
	 */
	public float getTotalPrice() {
		return this.PricePerUnit * this.Quantity;
	}
}
