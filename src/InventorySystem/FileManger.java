package InventorySystem;

import java.io.*;
import java.util.*;

/**
 *
 * @author marcw
 */
public final class FileManger {

	/**
	 * delete a line from a text file
	 *
	 * @param LineToDelete the line to be deleted
	 * @param FilePath the location of the file
	 * @return true if file not found false if everything is okay
	 */
	public static boolean Delete(String LineToDelete, String FilePath) {
		try {
			BufferedReader Update = new BufferedReader(new FileReader(FilePath));
			String raw;
			String input = "";
			while ((raw = Update.readLine()) != null) {
				input += raw + System.lineSeparator();
			}

			input = input.replace(LineToDelete, "");
			FileOutputStream os = new FileOutputStream(FilePath);
			os.write(input.getBytes());

			Update.close();
			os.close();
		} catch (Exception ex) {
			return true;
		}
		return false;
	}

	/**
	 * replace a line from a text file
	 *
	 * @param LineToReplace the line to be replaced (deleted)
	 * @param object the new line to replace LineToReplace
	 * @param FilePath the location of the file
	 * @return true if file not found false if everything is okay
	 */
	public static boolean Update(String LineToReplace, String object, String FilePath) //Abdelrahmen
	{
		try {
			BufferedReader Update = new BufferedReader(new FileReader(FilePath));
			String raw;
			String input = "";
			while ((raw = Update.readLine()) != null) {
				input += raw + System.lineSeparator();
			}

			input = input.replace(LineToReplace, object);
			FileOutputStream os = new FileOutputStream(FilePath);
			os.write(input.getBytes());

			Update.close();
			os.close();
		} catch (Exception ex) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param object
	 * @param FilePath
	 * @return true if file not found false if everything is okay
	 */
	public static boolean Append(String object, String FilePath)//Abdelrahmen
	{
		try {
			BufferedWriter writetofile = new BufferedWriter(new FileWriter(FilePath, true));
			writetofile.write(object + System.lineSeparator());
			writetofile.close();
		} catch (IOException ex) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param object the object to search for example:( ID / Name ) something Unique
	 * @param FilePath location of the file
	 * @param LocationInLine location of the object in each line between tokens (the square)
	 * @return return the line that contains that object if not found returns null
	 */
	public static String Search(String object, String FilePath, int LocationInLine) {
		try {
			BufferedReader search = new BufferedReader(new FileReader(FilePath));
			String line;
			while ((line = search.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "#$#");
				for (int i = 0; i < LocationInLine - 1; i++) {
					st.nextToken();
				}
				if (object.equals(st.nextToken())) {
					return line;
				}
			}
		} catch (Exception e) {
		}

		return null;
	}

	/**
	 * reverse the array.toString method
	 *
	 * @param string the string that u get from arraylist.tostring();
	 * @param list the empty list to fill
	 */
	public static void StringToIntegerArraylist(String string, ArrayList<Integer> list) {
		string = string.replace("[", "");
		string = string.replace("]", "");
		StringTokenizer st = new StringTokenizer(string, ", ");
		while (st.hasMoreTokens()) {
			list.add(Integer.valueOf(st.nextToken()));
		}
	}

	/**
	 * reverse the array.toString method
	 *
	 * @param string the string that u get from arraylist.tostring();
	 * @param list the empty list to fill
	 */
	public static void StringToStringArraylist(String string, ArrayList<String> list) {
		string = string.replace("[", "");
		string = string.replace("]", "");
		StringTokenizer st = new StringTokenizer(string, ", ");
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
	}

	/**
	 * reverse the array.toString method
	 *
	 * @param string the string that u get from arraylist.tostring();
	 * @param list the empty list to fill
	 */
	public static void StringToFloatArraylist(String string, ArrayList<Float> list) {
		string = string.replace("[", "");
		string = string.replace("]", "");
		StringTokenizer st = new StringTokenizer(string, ", ");
		while (st.hasMoreTokens()) {
			list.add(Float.valueOf(st.nextToken()));
		}
	}

	/**
	 * check if files Exists
	 *
	 * @param FilePath
	 * @return
	 */
	public static boolean CheckFiles(String FilePath) {
		File file = new File(FilePath);
		if (file.exists() && file.canRead() && file.canRead()) {
			return false;
		}
		return true;
	}

	/**
	 * create a new file
	 *
	 * @param FilePath
	 * @return
	 */
	public static boolean CreatFiles(String FilePath) {
		try {
			File file = new File(FilePath);
			file.createNewFile();
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	/*
		 public static boolean Delete(String filename, int startline, int numlines)  // Ghoneim
		 {
		 try {
		 BufferedReader br = new BufferedReader(new FileReader(filename));

		 StringBuffer sb = new StringBuffer("");

		 int linenumber = 1;
		 String line;

		 while ((line = br.readLine()) != null) 
		 {
		 if (linenumber < startline || linenumber >= startline + numlines)
		 sb.append(line + "\n");
		 linenumber++;
		 }
		 br.close();

		 FileWriter fw = new FileWriter(new File(filename));

		 fw.write(sb.toString());
		 fw.close();
		 } 
		 catch (Exception e) { return true; }
		 return false;
		 }
	 */
}
