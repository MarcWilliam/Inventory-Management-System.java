package Interfaces;

public interface IDate {

	boolean SetDate(String SDate);

	int CompareToCurrentDate();

	void SetDateToCurent();

	@Override
	String toString();

	int[] toIntgersDayMonthYear();

}
