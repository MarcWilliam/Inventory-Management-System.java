package Abstract;

import Core.*;
import java.io.*;

/**
 *
 * @author Marc Wafik
 */
public abstract class EntityUser extends ID implements Serializable {

	private String Name;
	private String Email;
	private long PhoneNumber;
	private String Address;

	/**
	 *
	 */
	public EntityUser() {
		super();
		this.Name = "Enter Name Here";
		this.Email = "Example@domain.com";
		this.PhoneNumber = 0;
		this.Address = "010 st. name , country";
	}

	/**
	 *
	 * @param Copy
	 */
	public EntityUser(EntityUser Copy) {
		super();
		this.Name = Copy.Name;
		this.Email = Copy.Email;
		this.PhoneNumber = Copy.PhoneNumber;
		this.Address = Copy.Address;
	}

	//==========================================================================================================================================================
	/**
	 * Check if the E-mail is in the correct format example@example.com and assign the value
	 *
	 * @param email
	 * @return is the input Wrong ?
	 */
	public boolean setEmail(String email) {//^ ==> begin of the line				// - ==> something to somethiong				//+@ mean @ must exist				//$ end of the line
		if (email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
			this.Email = email;
			return false;
		} else {
			return true;
		}
	}

	/**
	 *
	 * @param Address
	 * @return
	 */
	public boolean setAddress(String Address) {
		this.Address = Address;
		return false;
	}

	/**
	 * Check if the Name is in the correct format first char of each name is capital and has at Least 2 names and assign the value
	 *
	 * @param Name
	 * @return is the input Wrong ?
	 */
	public boolean setName(String Name) {//^ ==> begin of the line				// - ==> something to somethiong				//+@ mean @ must exist				//$ end of the line
		if (Name.matches("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$")) {
			this.Name = Name;
			return false;
		} else {
			return true;
		}
	}

	/**
	 *
	 * @param PhoneNo
	 * @return
	 */
	public boolean setPhoneNumber(long PhoneNo) {//^ ==> begin of the line				// - ==> something to somethiong				//+@ mean @ must exist				//$ end of the line
		if (PhoneNo < 999999999 && PhoneNo > 999) {
			this.PhoneNumber = PhoneNo;
			return false;
		} else {
			return true;
		}
	}

	public boolean setPhoneNumber(String PhoneNo) {//^ ==> begin of the line				// - ==> something to somethiong				//+@ mean @ must exist				//$ end of the line
		if (FileManger.isNumber(PhoneNo)) {
			return setPhoneNumber(new Long(PhoneNo));
		}
		return true;
	}
	//==========================================================================================================================================================

	/**
	 *
	 * @return
	 */
	public String getEmail() {
		return this.Email;
	}

	/**
	 *
	 * @return
	 */
	public String getAddress() {
		return Address;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return this.Name;
	}

	/**
	 * Check if the Phone is in the correct format and assign the value capital and has at Least 2 names
	 *
	 * @return is the input Wrong ?
	 */
	public long getPhoneNumber() {
		return this.PhoneNumber;
	}

}
