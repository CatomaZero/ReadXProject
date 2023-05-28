package model;
import java.util.Calendar;

public class Magazine extends BibliographicProduct{
	//Atributes
	private CategoryType category;
	private double suscriptionValue;
	private Frequency publicationFrequency;
	private int numSuscriptions;
	
	/**
	*
	*Magazine's constructor: Constructor method for Magazine class.
	*
	*@param indentifier a String representing the identifier of the magazine
	*@param name a String representing the name of the magazine
	*@param pagesNumber an integer representing the number of pages of the magazine
	*@param publicationDate a Calendar object representing the date of publication of the magazine
	*@param url a String representing the URL of the magazine
	*@param cate an integer representing the category of the magazine
	*@param sv a double representing the subscription value of the magazine
	*@param pf an integer representing the publication frequency of the magazine
	*@param typeProduct an integer representing the type of product
	*/
	public Magazine(String indentifier,String name,int pagesNumber,Calendar publicationDate,String url,int cate, double sv, int pf,int typeProduct){
		super(indentifier, name, pagesNumber, publicationDate, url, typeProduct);
		category=menuCategory(cate);
		suscriptionValue=sv;
		publicationFrequency=menuFrequency(pf);
		numSuscriptions=0;
	}
	/**
	*
	*Magazine's category menu: Returns the CategoryType object based on the selected category option.
	*
	*@param op an integer representing the selected category option
	*@return the CategoryType object based on the selected category option
	*/
	public CategoryType menuCategory(int op){
		CategoryType categoryOp=null;
		switch(op){
			case 1:
				categoryOp=CategoryType.VARIETY;
				break;
			case 2:
				categoryOp=CategoryType.DESIGN;
				break;
			case 3:
				categoryOp=CategoryType.SCIENTIFIC;
				break;
		}
		return categoryOp;
	}
	/**
	* Magazine frequency's menu: Returns a Frequency object based on the specified menu option.
	*
	* <br>Preconditions:<br> The menu option (op) must be an integer value between 1 and 3, inclusive.
	*
	* <br>Postconditions:<br> The appropriate Frequency object is returned based on the value of op.
	*
	* @param op An integer representing the menu option selected by the user.
	*
	* @return A Frequency object representing the frequency corresponding to the specified menu option.
	*/
	public Frequency menuFrequency(int op){
		Frequency frequencyOp=null;
		switch(op){
			case 1:
				frequencyOp=Frequency.DAILY;
				break;
			case 2:
				frequencyOp=Frequency.MONTHLY;
				break;
			case 3:
				frequencyOp=Frequency.YEARLY;
				break;
		}
		return frequencyOp;
	}
	/**
	*To string: Returns a string representation of the Magazine object.
	*
	* <br>Postconditions:<br> The Magazine object is represented as a string, including its superclass's toString method,the category, subscription value, publication frequency, and number of subscriptions.
	*
	* @return A string representation of the Magazine object.
	*/
	public String toString(){
		return super.toString()+"   "+category+"   "+suscriptionValue+"   "+publicationFrequency+"   "+ numSuscriptions;
	}
	/**
	* Get magazine's suscription valueReturns: The subscription value of the Magazine object.
	*
	* <br>Postconditions:<br> The subscription value of the Magazine object is returned as a double.
	*
	* @return A double value representing the subscription value of the Magazine object.
	*/
	public double getSuscriptionValue(){
		return suscriptionValue;
	}
	/**
	* Increases the number of a magazine's suscriptions: Increases the number of subscriptions to the Magazine object by one.
	*
	* <br>Postconditions:<br> The number of subscriptions to the Magazine object is increased by one.
	*/
	public void addSuscription(){
		numSuscriptions++;
	}
	/**
	* Set a new magazine's suscription value: Sets the subscription value of the Magazine object to the specified value.
	*
	* <br>Preconditions:<br> ns must be a valid double value.
	*
	* <br>Postconditions:<br> The subscription value of the Magazine object is set to ns.
	*
	* @param ns A double value representing the new subscription value of the Magazine object.
	* <br>Must be a valid double value.
	*/
	public void setSuscriptionValue(double ns){
		suscriptionValue=ns;
	}
	public CategoryType getCategoryType(){
		return category;
	}
	public void eliminateSuscription(){
		numSuscriptions--;
	}
	public int getNumSuscriptions(){
		return numSuscriptions;
	}
}
