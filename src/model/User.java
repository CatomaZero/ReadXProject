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
	private ArrayList<Section> library;
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
		
		library=new ArrayList <Section>();
		Section newSection=new Section(1);
		library.add(newSection);
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
	public void modifyUsersProduct(BibliographicProduct product){
		BibliographicProduct obj=searchAcquireProduct(product);
		if(obj!=null){
			for(int i=0; i<library.size();i++){
				if(library!=null){
					library.get(i).modifyUsersProduct(product);
				}
			}
		}
	}
	/**
	* Acquire a new product: Acquires a new bibliographic product, creates a new billing, and adds them to the user's list of purchases and billings.
	*
	* @param product The bibliographic product to be acquired.
	* @param price The price of the acquired bibliographic product.
	* @param transactionDate The date of the transaction when the bibliographic product was acquired.
	*/
	public void acquireProducts(BibliographicProduct product, double price, Calendar transactionDate){
		boolean created=false;
		for(int i=0; i<library.size()&&!created;i++){
			if(sectionVerification()){
				Billing newBilling=new Billing(transactionDate,price);
				billings.add(newBilling);
				library.get(i).acquireProuct(product);
				created=true;
			}else{
				createSection();
			}
		}
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
		boolean done=false;
		if(searchAcquireProduct(product)!=null){
			for(int i=0; i<library.size()&&!done;i++){
				if(library.get(i)!=null){
					done=library.get(i).finishSuscription(product);
				}
			}
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
	public String productsLibrary(int libraryPag){
		String alert="";
		while(alert==""){
			for(int i=0;i<library.size();i++){
				if(library.get(i)!=null&&library.get(i).getSectionNumber()==libraryPag){
					alert+=library.get(i).productsLibrary();
				}
			}
			if(alert==""){
				createSection();
			}
		}
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
		for(int i=0;i<library.size()&&!searched;i++){
			if(library.get(i)!=null){
				searched=true;
				alert=library.get(i).simulateReading(product);
			}
		}
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
		for(int i=0; i<library.size()&&!created;i++){
			if(library.get(i)!=null){
				obj=library.get(i).searchPurshased(product);
				if(obj!=null){
					created=true;
				}
			}
		}
		return obj;
	}
	public BibliographicProduct searchAcquireProduct(int row,int columns, int libraryPag){
		BibliographicProduct obj=null;
		boolean created=false;
		for(int i=0;i<library.size();i++){
			if(library.get(i)!=null&&library.get(i).getSectionNumber()==libraryPag){
				obj=library.get(i).searchPurshased(row,columns);
				if(obj!=null){
					created=true;
				}
			}
		}
		return obj;
	}
	public boolean sectionVerification(){
		boolean perm=false;
		for(int i=0; i<library.size()&&!perm;i++){
			perm=library.get(i).sectionVerification();
		}
		return perm;
	}
	public void createSection(){
		int con=0;
		for(int i=0; i<library.size();i++){
			if(library.get(i)!=null){
				con=library.get(i).getSectionNumber();
			}
		}
		Section newSection=new Section(con+1);
		library.add(newSection);
	}
}