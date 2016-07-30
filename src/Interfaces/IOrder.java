package Interfaces;

public interface IOrder {

	boolean GenerateID();

	@Override
	String toString();

	String toSaveString();

}
