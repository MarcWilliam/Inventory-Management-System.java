package Abstract;

import java.io.*;

/**
 *
 * @author marcw
 */
public abstract class ID implements Serializable {

	private long ID;

	/**
	 *
	 */
	public ID() {
		this.ID = 0;
	}

	/**
	 *
	 * @param Copy
	 */
	public ID(ID Copy) {
		this.ID = Copy.ID;
	}

	/**
	 *
	 */
	abstract public void generateID();

	/**
	 *
	 * @return
	 */
	public long getID() {
		return this.ID;
	}

	/**
	 *
	 * @param ID
	 * @return
	 */
	public boolean setID(long ID) {
		this.ID = ID;
		return false;
	}

	/**
	 *
	 */
	public void setID() {
		this.ID = 0;
	}

}
