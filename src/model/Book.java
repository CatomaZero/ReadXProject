package model;
import java.util.Calendar;

public class Book extends BibliographicProduct{
	//Atributes
	private String review;
	private GenderType gender;
	private double value;
	private int numSold;
	
	/**
	* Constructor Book: Creates a new Book object with the given parameters.
	*
	* <br>Postconditions:<br> A new Book object is created with the given parameters.
	*
	* @param indentifier A String representing the identifier of the book. Must not be null.
	* @param name A String representing the name of the book. Must not be null.
	* @param pagesNumber An integer representing the number of pages in the book.
	* @param publicationDate A Calendar object representing the publication date of the book.
	* @param url A String representing the URL of the book.
	* @param rd A String representing the review of the book.
	* @param gen An integer representing the gender of the book.
	* @param val A double representing the value of the book.
	* @param typeProduct An integer representing the type of product.
	*/
	public Book(String indentifier,String name,int pagesNumber,Calendar publicationDate,String url,String rd, int gen, double val, int typeProduct){
		super(indentifier, name, pagesNumber, publicationDate, url, typeProduct);
		review=rd;
		gender=menuGender(gen);
		value=val;
		numSold=0;
	}
	/**
	* Gender Menu: Returns the GenderType corresponding to the given option.
	*
	* <br>Postconditions:<br> The GenderType corresponding to the given option is returned.
	*
	* @param op An integer representing the selected option. Must be 1, 2 or 3.
	*
	* @return A GenderType object corresponding to the selected option.
	*/
	public GenderType menuGender(int op){
		GenderType genderOp=null;
		switch(op){
			case 1:
				genderOp=GenderType.SCIENCE_FICTION;
				break;
			case 2:
				genderOp=GenderType.FANTASY;
				break;
			case 3:
				genderOp=GenderType.HISTORICAL_NOVEL;
				break;
		}
		return genderOp;
	}
	/**
	* To string: Returns a String representation of the Book object.
	*
	* <br>Postconditions:<br> A String representation of the Book object is returned.
	*
	* @return A String representation of the Book object.
	*/
	public String toString(){
		return super.toString()+"   "+review+"   "+gender+"   "+value+"   "+numSold;
	}
	/**
	* Get book's value: Returns the value of the book.
	*
	* <br>Postconditions:<br> The value of the book is returned as a double.
	*
	* @return A double representing the value of the book.
	*/
	public double getValue(){
		return value;
	}
	/**
	* Increments the number of solds book: Increments the number of books sold by one.
	*
	* <br>Postconditions:<br> The number of books sold is incremented by one.
	*/
	public void addSold(){
		numSold++;
	}
	/**
	* Set new book's value: Sets the value of the book to the given value.
	*
	* <br>Postconditions:<br> The value of the book is set to the given value.
	*
	* @param nv A double representing the new value of the book.
	*/
	public void setValue(double nv){
		value=nv;
	}
}