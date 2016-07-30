package InventorySystem;

import Interfaces.*;
import java.io.*;
import java.util.*;

public class Entity implements IEntity {

	public int ID;

	Entity() {
		this.ID = 0;
	}

	/**
	 * will generate id based on last ID in file then save to file take a parameter of file location if file not found ill generate a new file ID should be the first variable in the class file the ID should be the leading variable in each line █ ID █ .
	 *
	 * @param FilePath
	 * @return
	 */
	@Override
	public boolean GenerateID(String FilePath) {
		try {
			BufferedReader search = new BufferedReader(new FileReader(FilePath));
			String line;
			while ((line = search.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "#$#");
				this.ID = Integer.valueOf(st.nextToken()) + 1;
			}
			search.close();
		} catch (Exception ex) {
			return true;
		}
		return false;
	}

	@Override
	public int GetID() {
		return this.ID;
	}

}
