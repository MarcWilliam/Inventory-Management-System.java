package Interfaces;

public interface IShift {

	@Override
	String toString();

	String toSaveString();

	void setEnd_time();

	long calculateTimeInHour();

	String entryDate();
}
