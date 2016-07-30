package Interfaces;

public interface IEmployee {

	boolean GenerateID();

	boolean LogShiftIn();

	boolean LogShiftOut();

	boolean LogIn(String PW);

	@Override
	String toString();

	String toSaveString();

	boolean setEmail(String email);

	String getEmail();

	float CalculateInsurence();

	float CalculateTaxes();

	float CalculateNetSalery();

	String RandEncry(String StringE);

	String DeRandEncry(String StringE);
}
