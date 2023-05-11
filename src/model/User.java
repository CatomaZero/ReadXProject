package model;
import java.util.Calendar;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class User{
	//Atributes
	private String nameUser;
	private String idUser;
	private Calendar registerDate;
	private UserType userType;
	private int booksPurchased;
	private int magazinesSubscribed;
	protected boolean adds;
	
	//Relations
	private ArrayList<BibliographicProduct> purchasedProducts;
	private ArrayList<Billing> billings;
	
	/**
	* User's contructor: Constructs a new User object with the specified attributes.
	*
	* <br>Postconditions: A new User object is created with the specified name, ID, registration date, user type, number of books purchased, number of magazines subscribed, and whether or not the user receives advertisements. The purchased products and billings lists are also initialized as empty ArrayLists.
	*
	* @param nau A String representing the name of the user.
	* @param idu A String representing the ID of the user.
	* @param reu A Calendar representing the registration date of the user.
	* @param ustp An integer representing the user type, as determined by the menuUserType method.
	*/
	public User(String nau,String idu,Calendar reu,int ustp){
		nameUser=nau;
		idUser=idu;
		registerDate=reu;
		userType=menuUserType(ustp);
		booksPurchased=0;
		magazinesSubscribed=0;
		adds=true;
		
		purchasedProducts=new ArrayList <BibliographicProduct>();
		billings=new ArrayList <Billing>();
	}
	/**
	* User's type menu: Returns the UserType corresponding to the specified integer value.
	*
	* <br>Postconditions:<br> The UserType corresponding to the specified integer value is returned.
	*
	* @param op An integer representing the user type.
	*
	* @return The UserType corresponding to the specified integer value.
	*/
	public UserType menuUserType(int op){
		UserType userOp=null;
		switch(op){
			case 1:
				userOp=UserType.REGULAR;
				break;
			case 2:
				userOp=UserType.PREMIUM;
				break;
		}
		return userOp;
	}
	/**
	* Get user's type: Returns the UserType of the User object.
	*
	* <br>Postconditions:<br> The UserType of the User object is returned.
	*
	* @return The UserType of the User object.
	*/
	public UserType getUserType(){
		return userType;
	}
	/**
	* Get user's name: Returns the name of the User object.
	*
	* <br>Postconditions:<br> The name of the User object is returned.
	*
	* @return The name of the User object.
	*/
	public String getName(){
		return nameUser;
	}
	/**
	* Get user's id: Returns the ID of the User object.
	*
	* <br>Postconditions:<br> The ID of the User object is returned.
	*
	* @return The ID of the User object.
	*/
	public String getId(){
		return idUser;
	}
	/**
	* To string: Returns a String representation of the User object.
	*
	* <br>Postconditions:<br> A String representation of the User object is returned.
	*
	* @return A String representation of the User object.
	*/
	public String toString(){
		SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
		return nameUser+"   "+idUser+"   "+timeStamp.format(registerDate.getTime())+"   "+userType;
	}
	/**
	* Search a bibliographic product acquire: Searches for a purchased BibliographicProduct with the same identifier as the specified BibliographicProduct.
	*
	* <br>Postconditions:<br> If a purchased BibliographicProduct with the same identifier as the specified BibliographicProduct is found, it is returned.
	*
	* @param product The BibliographicProduct to search for.
	*
	* @return The purchased BibliographicProduct with the same identifier as the specified BibliographicProduct, or null if none is found.
	*/
	public BibliographicProduct searchPurshased(BibliographicProduct product){
		BibliographicProduct obj=null;
		boolean created=false;
		for(int i=0; i<purchasedProducts.size()&&!created;i++){
			if(purchasedProducts.get(i)!=null&&product.getIndentifier().equalsIgnoreCase(purchasedProducts.get(i).getIndentifier())){
				obj=purchasedProducts.get(i);
				created=true;
			}
		}
		return obj;
	}
	/**
	* Acquire a new product: Acquires a new bibliographic product, creates a new billing, and adds them to the user's list of purchases and billings.
	*
	* @param product The bibliographic product to be acquired.
	* @param price The price of the acquired bibliographic product.
	* @param transactionDate The date of the transaction when the bibliographic product was acquired.
	*/
	public void acquireProducts(BibliographicProduct product, double price, Calendar transactionDate){
		Billing newBilling=new Billing(transactionDate,price);
		billings.add(newBilling);
		purchasedProducts.add(product);
	}
	/**
	* Finished a magazine's subscription: Removes the given bibliographic product from the list of purchased products.
	* 
	* <br>Preconditions:<br> The given product must be previously purchased.
	* 
	* <br>Postconditions:<br> The given product is removed from the list of purchased products, if it exists.
	* 
	* @param product The bibliographic product to be removed from the list of purchased products.
	* @return true if the product was successfully removed from the list, false otherwise.
	*/
	public boolean finishSuscription(BibliographicProduct product){
		boolean done;
		if(searchPurshased(product)!=null){
			purchasedProducts.remove(product);
			done=true;
		}else{
			done=false;
		}
		return done;
	}
	/**
	* Show acquire products: Generates a list of purchased bibliographic products.
	*
	* <br>Postconditions:<br> A list of purchased bibliographic products is generated.
	*
	* @return A string containing the identifier of each purchased bibliographic product and an "Enter E to exit" message.
	*/
	public String productsLibrary(){
		String alert="";
		for(int i=0;i<purchasedProducts.size();i++){
			if(purchasedProducts!=null){
				alert+=purchasedProducts.get(i).getIndentifier()+"\n";
			}
		}
		alert+="Enter E to exit";
		return alert;
	}
	/**
	* Increases the books purchased: Increases the count of purchased books by 1.
	*
	* <br>Postconditions:<br> The count of purchased books is incremented by 1.
	*/
	public void sumBooks(){
		booksPurchased++;
	}
	/**
	* Increases the magazines subscribed: Increases the count of subscribed magazines by 1.
	*
	* <br>Postconditions:<br> The count of subscribed magazines is incremented by 1.
	*/
	public void magazinesSubscribedCount(){
		magazinesSubscribed++;
	}
	/**
	* Get number of books purchased: Returns the count of purchased books.
	*
	* <br>Postconditions:<br> The count of purchased books is returned.
	*
	* @return An integer representing the count of purchased books.
	*/
	public int getBooksPurchased(){
		return booksPurchased;
	}
	/**
	* Get number of magazines subscribed: Returns the count of subscribed magazines.
	*
	* <br>Postconditions:<br> The count of subscribed magazines is returned.
	*
	* @return An integer representing the count of subscribed magazines.
	*/
	public int getMagazinesSubscribed(){
		return magazinesSubscribed;
	}
	/**
	* Set a new value of adds: Sets the value of the boolean adds to the given value.
	*
	* <br>Postconditions: The value of the boolean adds is set to the given value.
	*
	* @param add A boolean representing the new value of the adds field.
	*/
	public void setAdds(boolean add){
		adds=add;
	}
	/**
	* Simulate reading sessions: Simulates a reading session of the given bibliographic product.
	*
	* <br>Preconditions:<br> A bibliographic product must be passed as a parameter.
	*
	* <br>Postconditions:<br> A string indicating the start of the reading session is returned.
	*
	* @param product A BibliographicProduct object representing the product to be read.
	*
	* @return A string indicating the start of the reading session, or an empty string if the product is not found in the list of purchased products.
	*/
	public String simulateReading(BibliographicProduct product){
		String alert="";
		boolean searched=false;
		for(int i=0;i<purchasedProducts.size()&&!searched;i++){
			if(purchasedProducts.get(i)!=null&&searchAcquireProduct(product)!=null){
				searched=true;
				alert="Reading session in progress:\n\nReading:"+product.getName()+"\n";
			}
		}
		return alert;
	}
	/**
	* Count the pages of the reading simulation: Counts the pages read during a simulated reading session of the given bibliographic product.
	*
	* <br>Preconditions:<br> A bibliographic product and a page number must be passed as parameters.
	*
	* <br>Postconditions:<br> A string indicating the current page being read is returned.
	*
	* @param product A BibliographicProduct object representing the product being read.
	* @param pag An integer representing the current page being read.
	*
	* @return A string indicating the current page being read, or an empty string if the product is not found in the list of purchased products.
	*/
	public String countSimulateReading(BibliographicProduct product,int pag){
		String alert="";
		boolean changed=false;
		BibliographicProduct acquire=searchAcquireProduct(product);
		alert="Reading page "+pag+" of "+acquire.getPagesNumber()+"\n";
		return alert;
	}
	/**
	* Search for acquire products: Searches the list of purchased products for a product with the same identifier as the given product.
	*
	* <br>Preconditions:<br> A bibliographic product must be passed as a parameter.
	*
	* <br>Postconditions:<br> The first bibliographic product found with the same identifier as the given product is returned.
	*
	* @param product A BibliographicProduct object representing the product to search for.
	*
	* @return A BibliographicProduct object with the same identifier as the given product, or null if no matching product is found in the list of purchased products.
	*/
	public BibliographicProduct searchAcquireProduct(BibliographicProduct product){
		BibliographicProduct obj=null;
		boolean created=false;
		for(int i=0; i<purchasedProducts.size()&&!created;i++){
			if(purchasedProducts.get(i)!=null&&product.getIndentifier().equalsIgnoreCase(purchasedProducts.get(i).getIndentifier())){
				obj=purchasedProducts.get(i);
				created=true;
			}
		}
		return obj;
	}
	/**
	* Comprobation of user's acquire products: Determines if the given bibliographic product has been purchased by the user.
	*
	* <br>Preconditions:<br> A bibliographic product must be passed as a parameter.
	*
	* <br>Postconditions:<br> A boolean indicating whether or not the product has been purchased is returned.
	*
	* @param product A BibliographicProduct object representing the product to check for purchase.
	*
	* @return A boolean indicating whether or not the product has been purchased.
	*/
	public boolean acquireProuct(BibliographicProduct product){
		boolean acquire;
		if(searchAcquireProduct(product)!=null){
			acquire=true;
		}else{
			acquire=false;
		}
		return acquire;
	}
}