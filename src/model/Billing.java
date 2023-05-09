package model;
import java.util.Calendar;

public class Billing{
	//Atributes 
	private Calendar transactionDate;
	private double price;
	
	public Billing(Calendar tdate, Double pr){
		transactionDate=tdate;
		price=pr;
	}
}