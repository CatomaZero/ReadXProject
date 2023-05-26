package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class BibliographicProduct{
	//Atributes
	private String indentifier;
	private String name;
	private int pagesNumber;
	private Calendar publicationDate;
	private String url;
	private int numReadPages;
	private TypeProduct typeProduct;
	
	/**
	* Bibliographic product constructor: Constructor for BibliographicProduct class.
	*
	* <br>Postconditions:<br> A new instance of BibliographicProduct is created with the given parameters
	*
	* @param id The identifier of the bibliographic product.
	* @param na The name of the bibliographic product.
	* @param pn The number of pages of the bibliographic product.
	* @param pd The publication date of the bibliographic product.
	* @param ur The URL of the bibliographic product.
	* @param tB The type of the bibliographic product.
	*/
	public BibliographicProduct(String id,String na,int pn,Calendar pd,String ur,int tB){
		indentifier=id;
		name=na;
		pagesNumber=pn;
		publicationDate=pd;
		url=ur;
		numReadPages=0;
		typeProduct=menuTypeProduct(tB);
	}
	/**
	*
	*Menu product's type: Returns the product type based on the user's input.
	*
	*<br>Preconditions:<br> The parameter op must be either 1 or 2.
	*
	*<br>Postconditions:<br> The product type is determined based on the user's input and returned as a TypeProduct object.
	*
	*@param op An integer value representing the user's choice of product type.
	*@return A TypeProduct object representing the product type based on the user's input. Returns null if the user's input is not 1 or 2.
	*/
	public TypeProduct menuTypeProduct(int op){
		TypeProduct productOp=null;
		switch(op){
			case 1:
				productOp=TypeProduct.BOOK;
				break;
			case 2:
				productOp=TypeProduct.MAGAZINE;
				break;
		}
		return productOp;
	}
	/**
	*
	*Get product's indentifier: Returns the identifier of the object.
	*
	*<br>Postconditions:<br> The identifier of the object is returned as a String.
	*
	*@return A String representing the identifier of the object.
	*/
	public String getIndentifier(){
		return indentifier;
	}
	/**
	*
	*To String: Returns a String representation of the object.
	*
	*<br>Postconditions:<br> A String representation of the object is returned.
	*
	*@return A String representing the object, including its identifier, name, number of pages, publication date, URL, number of read pages, and product type.
	*/
	public String toString(){
		SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
		return indentifier+"  "+name+"   "+pagesNumber+"   "+timeStamp.format(publicationDate.getTime())+"   "+url+"   "+numReadPages+"     "+typeProduct;
	}
	/**
	*
	*Get product's name: Returns the name of the object.
	*
	*<br>Postconditions:<br> The name of the object is returned as a String.
	*
	*@return A String representing the name of the object.
	*/
	public String getName(){
		return name;
	}
	/**
	*
	*Get product's type: Returns the type of the product.
	*
	*<br>Postconditions:<br> The type of the product is returned as a TypeProduct object.
	*
	*@return A TypeProduct object representing the type of the product.
	*/
	public TypeProduct getType(){
		return typeProduct;
	}
	/**
	*
	*Set a new product's name: Sets the name of the object to a new value.
	*
	*<br>Preconditions:<br> The parameter newName must not be null.
	*
	*<br>Postconditions:<br> The name of the object is set to the value of newName.
	*
	*@param newName A String representing the new name of the object. Must not be null.
	*/
	public void setName(String newName){
		name=newName;
	}
	/**
	* Get product's number of pages: Returns the number of pages in the object.
	*
	* <br>Postconditions:<br> The number of pages in the object is returned as an integer.
	*
	* @return An integer representing the number of pages in the object.
	*/
	public int getPagesNumber(){
		return pagesNumber;
	}
	/**
	* Incremets product's number of pages read: Increments the number of pages read in the object by one.
	*
	* <br>Postconditions:<br> The number of pages read in the object is incremented by one.
	*/
	public void sumReadingPag(){
		numReadPages++;
	}
	public Calendar getPublicationDate(){
		return publicationDate;
	}
}