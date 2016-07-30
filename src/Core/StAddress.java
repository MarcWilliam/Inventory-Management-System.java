package Core;

import java.io.*;

/**
 * represent an address in specific format
 *
 * @author Marc Wafik
 */
public class StAddress implements Serializable, Comparable<StAddress> {

	private int StNumber;
	private String StName;
	private String City;
	private String Country;

	/**
	 * set the class to it's default values
	 */
	public StAddress() {
		this.StNumber = 0;
		this.StName = "Empty";
		this.City = "Empty";
		this.Country = "Empty";
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @param StNumber
	 * @return
	 */
	public boolean setStNumber(int StNumber) {
		if (StNumber > 0 && StNumber < 999) {
			this.StNumber = StNumber;
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param StName
	 * @return
	 */
	public boolean setStName(String StName) {
		if (StName.length() > 3) {
			this.StName = StName;
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param City
	 * @return
	 */
	public boolean setCity(String City) {
		if (City.length() > 3) {
			this.City = City;
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param Country
	 * @return
	 */
	public boolean setCountry(String Country) {
		if (Country.length() > 3) {
			this.Country = Country;
			return false;
		}
		return true;
	}

	//==========================================================================================================================================================
	/**
	 *
	 * @return
	 */
	public int getStNumber() {
		return StNumber;
	}

	/**
	 *
	 * @return
	 */
	public String getStName() {
		return StName;
	}

	/**
	 *
	 * @return
	 */
	public String getCity() {
		return City;
	}

	/**
	 *
	 * @return
	 */
	public String getCountry() {
		return Country;
	}

	//==========================================================================================================================================================
	@Override
	public String toString() {
		return StNumber + "  St " + StName + ", " + City + ", " + Country + '}';
	}

	@Override
	public int compareTo(StAddress o) {
		if (this.StNumber == o.StNumber && this.StName.equals(o.StName) && this.City.equals(o.City) && this.Country.equals(o.Country)) {
			return 0;
		} else {
			return -1;
		}
	}

}
