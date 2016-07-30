package Core;

import InterFaces.*;
import Lib.*;
import User.*;
import java.io.*;
import java.util.*;

/**
 * Manger the files
 *
 * @author Marc Wafik
 */
public final class FileManger {

	final static IHashMap FileLocHash[] = {new Employee(), new Product(), new Supplier(), new Invoice(), new Order()};
	final static IArrayList FileLocAryy[] = {new Shift(new Employee())};

	/**
	 * Saves the following object to binary file
	 *
	 * @param ToBeSaved the Object to be Saved to binary files
	 * @param fileloc any new object to identify the location
	 * @return is there any errors True for yes False for no
	 */
	public static boolean Save(Object ToBeSaved, IFilePath fileloc) {
		if (ToBeSaved == null) {
			return true;
		}
		try {
			ObjectOutputStream writetofile = new ObjectOutputStream(new FileOutputStream(fileloc.getFilePath()));
			writetofile.writeObject(ToBeSaved);
			writetofile.close();
			return false;
		} catch (IOException ex) {
			return true;
		}
	}

	/**
	 * Load the object from binary files
	 *
	 * @param fileloc any new object to identify the location
	 * @return the ArrayList of the selected object
	 */
	public static ArrayList LoadList(IArrayList fileloc) {
		try {
			ObjectInputStream writetofile = new ObjectInputStream(new FileInputStream(fileloc.getFilePath()));
			return (ArrayList) writetofile.readObject();
		} catch (Exception ex) {
			return new ArrayList();
		}
	}

	/**
	 * Load the object from binary files
	 *
	 * @param fileloc any new object to identify the location
	 * @return the HashMap of the selected object
	 */
	public static HashMap LoadHash(IHashMap fileloc) {
		try {
			ObjectInputStream writetofile = new ObjectInputStream(new FileInputStream(fileloc.getFilePath()));
			return (HashMap) writetofile.readObject();
		} catch (Exception ex) {
			return new HashMap();
		}

	}

	/**
	 * check if files Exists
	 *
	 * @return false if FILE NOT FOUND , True if FILES FOUND
	 */
	public static boolean CheckFiles() {
		for (IHashMap FileLocHash1 : FileLocHash) {
			File file = new File(FileLocHash1.getFilePath());
			if (file.exists() && file.canRead() && file.canWrite() && file.isFile()) {
				return true;
			}
		}
		for (IArrayList FileLocAryy1 : FileLocAryy) {
			File file = new File(FileLocAryy1.getFilePath());
			if (file.exists() && file.canRead() && file.canWrite() && file.isFile()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * create a new files with empty getList
	 *
	 * @return
	 */
	public static boolean CreatFiles() {
		HashMap<Long, Employee> emp = new HashMap<>();
		//		emp.put(0l, new Employee());
		HashMap<Long, Product> pro = new HashMap<>();
		//		pro.put(0l, new Product());
		HashMap<Long, Supplier> sup = new HashMap<>();
		//		sup.put(0l, new Supplier());
		HashMap<Long, Invoice> inv = new HashMap<>();
		//		inv.put(0l, new Invoice());
		HashMap<Long, Order> ord = new HashMap<>();
		//	ord.put(0l, new Order());
		ArrayList<Shift> shi = new ArrayList<>();
		//			shi.add(new Shift(new Employee()));
		return Save(emp, new Employee())
				|| Save(sup, new Supplier())
				|| Save(pro, new Product())
				|| Save(inv, new Invoice())
				|| Save(ord, new Order())
				|| Save(shi, new Shift(new Employee()));
	}

	public static boolean CreatBackUp(String FilePath) {

		try {
			ObjectOutputStream writetofile = new ObjectOutputStream(new FileOutputStream(FilePath + "//backup.inventorysys"));
			for (IHashMap FileLocHash1 : FileLocHash) {
				writetofile.writeObject(LoadHash(FileLocHash1));
			}
			for (IArrayList FileLocAryy1 : FileLocAryy) {
				writetofile.writeObject(LoadList(FileLocAryy1));
			}
			writetofile.close();
			return false;
		} catch (Exception ex) {
			return true;
		}
	}

	public static boolean LoadBackup(String FilePath) {
		try {
			ObjectInputStream writetofile = new ObjectInputStream(new FileInputStream(FilePath));
			for (int i = 0; FileLocHash.length < i; i++) {
				FileManger.Save((HashMap) writetofile.readObject(), FileLocHash[i]);
			}
			for (int i = 0; FileLocAryy.length < i; i++) {
				FileManger.Save((ArrayList) writetofile.readObject(), FileLocAryy[i]);
			}
			writetofile.close();
			return false;
		} catch (Exception ex) {
			return true;
		}
	}

	public static boolean isNumber(String number) {
		if (number == null) {
			return false;
		}
		if (number.length() > 19) {
			return false;
		}
		return number.matches("\\d+");
	}

	public static boolean isFloat(String number) {
		if (number == null) {
			return false;
		}
		if (number.length() > 19) {
			return false;
		}
		String temp[] = number.split(",");
		if (temp.length != 2) {
			return number.matches("\\d+");
		}

		return (temp[0].matches("\\d+") && temp[1].matches("\\d+"));
	}

	public static String[][] ListObj(HashMap<Long, IList> list) {
		if (list == null) {
			return new String[0][0];
		}
		if (list.isEmpty()) {
			return new String[0][0];
		}
		ArrayList<String[]> temp = new ArrayList<>();
		for (Long key : list.keySet()) {
			temp.add(list.get(key).getList());
		}
		if (temp.isEmpty()) {
			return new String[0][0];
		}
		return temp.toArray(new String[temp.size()][temp.get(0).length]);
	}
}
