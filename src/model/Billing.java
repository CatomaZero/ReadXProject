package model;
import java.util.Calendar;

public class Billing{
	//Atributes 
	private Calendar transactionDate;
	private double price;
	/**
	* Billing constructor: Constructs a new Billing object with the specified transaction date and price.
	*
	* <br>Postconditions:<br> A new Billing object is created with the specified transaction date and price.
	*
	* @param tdate The transaction date.
	* @param pr The price of the transaction.
	*/
	public Billing(Calendar tdate, Double pr){
		transactionDate=tdate;
		price=pr;
	}
}