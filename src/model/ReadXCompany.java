package model;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;


public class ReadXCompany{
	//Atributes
	private String nameCompany;
	private String nitCompany;
	
	//Relations
	private ArrayList<BibliographicProduct> bibliographic;
	private ArrayList<User> users;
	
	/**
	*
	*New Company: Constructs a new ReadX company with the specified name and NIT.
	*<br>Preconditions:<br> None.
	*<br>Postconditions:<br> A new ReadX company is created with an empty list of bibliographic products and users.
	*@param name The name of the ReadX company.
	*@param nit The NIT (tax identification number) of the ReadX company.
	*/
	public ReadXCompany(String name, String nit){
		nameCompany=name;
		nitCompany=nit;
		
		bibliographic=new ArrayList <BibliographicProduct>();
		users=new ArrayList <User>();
	}
	/**
	*
	*Regist a book: Registers a new bibliographic product with the specified parameters.
	*<br>Preconditions:<br> None.
	*<br>Postconditions:<br> If the specified identifier does not already exist, a new Book object is created and added to the ReadX company's list of bibliographic products.
	*@param indentifier The identifier for the new bibliographic product.
	*@param name The name of the new bibliographic product.
	*@param pagesNumber The number of pages in the new bibliographic product.
	*@param publicationDate The publication date of the new bibliographic product.
	*@param url The URL for the new bibliographic product.
	*@param review The review for the new bibliographic product.
	*@param gender The gender for the new bibliographic product.
	*@param value The value for the new bibliographic product.
	*@param typeBibliographic The type of bibliographic product (book, magazine, or newspaper).
	*@return A message indicating whether the new bibliographic product was registered successfully or if the specified identifier already exists.
	*/
	public String registerBibliographic(String indentifier,String name,int pagesNumber,Calendar publicationDate,String url,String review,int gender,double value,int typeBibliographic){
		String alert="";
		BibliographicProduct obj=searchProduct(indentifier);
		if(obj!=null){
			alert="That identifier already exist. Book can not register.";
		}else{
			Book newbook=new Book(indentifier,name,pagesNumber,publicationDate,url,review,gender,value,typeBibliographic);
			bibliographic.add(newbook);
			alert="The book was register sucesfully";
		}
		return alert;
	}
	/**
	*
	*Regist a magazine: Registers a new bibliographic product with the specified parameters.
	*<br>Postconditions:<br> If the specified identifier does not already exist, a new Magazine object is created and added to the ReadX company's list of bibliographic products.
	*@param indentifier The identifier for the new bibliographic product.
	*@param name The name of the new bibliographic product.
	*@param pagesNumber The number of pages in the new bibliographic product.
	*@param publicationDate The publication date of the new bibliographic product.
	*@param url The URL for the new bibliographic product.
	*@param category The category of the new bibliographic product.
	*@param suscriptionValue The subscription value for the new bibliographic product.
	*@param publicationFrequency The publication frequency for the new bibliographic product.
	*@param typeBibliographic The type of bibliographic product (book, magazine, or newspaper).
	*@return A message indicating whether the new bibliographic product was registered successfully or if the specified identifier already exists.
	*/
	public String registerBibliographic(String indentifier,String name,int pagesNumber,Calendar publicationDate,String url,int category,double suscriptionValue,int publicationFrequency, int typeBibliographic){
		BibliographicProduct obj=searchProduct(indentifier);
		String alert="";
		if(obj!=null){
			alert="That identifier already exist. Magazine can not be register.";
		}else{
			Magazine newmagazine=new Magazine(indentifier,name,pagesNumber,publicationDate,url,category,suscriptionValue,publicationFrequency,typeBibliographic);
			bibliographic.add(newmagazine);
			alert="The magazine was register sucesfully";
		}
		return alert;
	}
	/**
	*
	*Search a bibliographic product: Searches for a bibliographic product with the given identifier.
	*@param indentifier a string representing the identifier of the bibliographic product to be searched.
	*@return a BibliographicProduct object if found, null otherwise.
	*/
	public BibliographicProduct searchProduct(String indentifier){
		BibliographicProduct obj=null;
		boolean created=false;
		for(int i=0; i<bibliographic.size()&&!created;i++){
			if(bibliographic.get(i)!=null&&indentifier.equalsIgnoreCase(bibliographic.get(i).getIndentifier())){
				obj=bibliographic.get(i);
				created=true;
			}
		}
		return obj;
	}
	/**
	*
	*Search a user: Searches for a user with the given ID.
	*@param id a string representing the ID of the user to be searched.
	*@return a User object if found, null otherwise.
	*/
	public User searchUser(String id){
		User obj=null;
		boolean created=false;
		for(int i=0; i<users.size()&&!created;i++){
			if(users.get(i)!=null&&id.equalsIgnoreCase(users.get(i).getId())){
				obj=users.get(i);
				created=true;
			}
		}
		return obj;
	}
	/**
	*
	*Modify product's name: Modifies the name of a bibliographic product in the ReadX's catalog.
	*@param newName the new name to be set for the bibliographic product
	*@param indentifier the identifier of the bibliographic product to be modified
	*@return a String message indicating the success or failure of the operation
	*/
	public String modifyBibliographic(String newName,String indentifier){
		String alert="";
		BibliographicProduct product=searchProduct(indentifier);
		if(product!=null){
			product.setName(newName);
			alert="The bibliographic product's name was changed sucesfully to "+product.getName();
			modifyUsersProduct(product);
		}else{
			alert="The name could not be changed";
		}
		return alert;
	}
	public void modifyUsersProduct(BibliographicProduct product){
		for(int i=0; i<users.size();i++){
			if(users.get(i)!=null){
				users.get(i).modifyUsersProduct(product);
			}
		}
	}
	/**
	*
	*Modify product's price: Modifies the price of a bibliographic product in the ReadX's catalog.
	*@param newPrice the new price to be set for the bibliographic product
	*@param indentifier the identifier of the bibliographic product to be modified
	*@return a String message indicating the success or failure of the operation
	*/
	public String modifyBibliographic(double newPrice,String indentifier){
		String alert="";
		BibliographicProduct product=searchProduct(indentifier);
		if(product!=null){
			if(product instanceof Book){
				Book obj=(Book) product;
				obj.setValue(newPrice);
				alert="The price of "+ product.getName() +" was changed sucesfully to: "+obj.getValue();
			}else if(product instanceof Magazine){
				Magazine obj=(Magazine) product;
				obj.setSuscriptionValue(newPrice);
				alert="The price of "+ product.getName() +" was changed sucesfully to: "+obj.getSuscriptionValue();
			}
		}
		return alert;
	}
	/**
	*
	*Delete a product: Deletes a bibliographic product from the ReadX's catalog.
	*@param indentifier the identifier of the bibliographic product to be deleted
	*@return a String message indicating the success or failure of the operation
	*/
	public String deleteBibliographic(String indentifier){
		String alert="";
		BibliographicProduct product=searchProduct(indentifier);
		if(product==null){
			alert="Bibliographic product not found";
		}else{
			alert="The bibliographic product named " + product.getName() +" was eliminated sucesfully.";
			bibliographic.remove(product);
		}
		return alert;
	}
	/**
	*
	*Regular user register: Registers a regular user with the specified information.
	*@param nameUser the name of the user
	*@param idUser the ID of the user
	*@param registerDate the registration date of the user
	*@param typeUser the type of user (regular or premium)
	*@return a String message indicating the success or failure of the operation
	*/
	public String userRegister(String nameUser,String idUser,Calendar registerDate,int typeUser){
		String alert="";
		User user=searchUser(idUser);
		if(user!=null){
			alert="A user with that id already exist.";
		}else{
			Regular newUser=new Regular(nameUser, idUser, registerDate,typeUser);
			users.add(newUser);
			alert="A regular user was register sucesfully";
		}
		return alert;
	}
	/**
	*
	*Premium user register: Registers a premium user with the specified information.
	*@param nameUser the name of the user
	*@param idUser the ID of the user
	*@param registerDate the registration date of the user
	*@param typeUser the type of user (regular or premium)
	*@param nickName the nickname of the user
	*@param creditCard the credit card information of the user
	*@return a String message indicating the success or failure of the operation
	*/
	public String userRegister(String nameUser,String idUser,Calendar registerDate,int typeUser,String nickName, String creditCard){
		String alert="";
		User user=searchUser(idUser);
		if(user!=null){
			alert="A user with that id already exist.";
		}else{
			Premium newUser=new Premium(nameUser, idUser, registerDate,typeUser,nickName,creditCard);
			users.add(newUser);
			alert="A premium user was register sucesfully";
		}
		return alert;
	}
	/**
	* Print a product's name: Returns the name of a bibliographic product with the given identifier.
	* 
	* <br>Preconditions:<br> A bibliographic product with the given identifier must exist in the catalog.
	* 
	* <br>Postconditions:<br> The name of the bibliographic product is returned as a String.
	*
	* @param indentifier The identifier of the bibliographic product to search for.
	* @return The name of the bibliographic product as a String.
	*/
	public String imprimBibliographicName(String indentifier){
		BibliographicProduct bibliographic=searchProduct(indentifier);
		String alert="The product searched was: "+bibliographic.getName();
		return alert;
	}
	/**
	* Print user's name: Returns a greeting message for the user with the given identifier, including their name and a message about their appearance if they are a regular user.
	* 
	* <br>Preconditions:<br> A user with the given identifier must exist in the system.
	* 
	* <br>Postconditions:<br> A greeting message is returned as a String.
	*
	* @param idUser The identifier of the user to greet.
	* @return A greeting message for the user as a String.
	*/
	public String imprimUserName(String idUser){
		User user=searchUser(idUser);
		Regular regular;
		String alert="Hi, " + user.getName()+". How are you today? :D\n";
		if(user instanceof Regular){
			regular=(Regular) user;
			alert+=regular.addAppearance();
		}
		return alert;
	}
	/**
	* Acquire products: Acquires a bibliographic product for the user with the given identifier, updating their library, generating a billing, and possibly updating their regular user status.
	* 
	* <br>Preconditions:<br> A user with the given identifier and a bibliographic product with the given identifier must exist in the system.
	* 
	* <br>Postconditions:<br> The bibliographic product is acquired by the user and added to their library, a billing is generated, and the regular user status is updated if applicable. A message describing the result of the operation is returned as a String.
	*
	* @param idUser The identifier of the user acquiring the product.
	* @param indentifier The identifier of the bibliographic product to acquire.
	* @return A message describing the result of the operation as a String.
	*/
	public String acquireProducts(String idUser,String indentifier){
		User user=searchUser(idUser);
		BibliographicProduct product=searchProduct(indentifier);
		Regular obj2=null;
		int count=0;
		String alert="";
		double price;
		Calendar transactionDate;
		if(user instanceof Regular){
			obj2=(Regular) user;
			alert=obj2.addAppearance()+"\n";
		}
		if(product!=null){
			if(!searchAcquireProduct(indentifier,idUser)){
				if(userVerification(user)){
					if(product instanceof Book){
						Book obj=(Book) product;
						obj.addSold();
						price=obj.getValue();
						transactionDate=Calendar.getInstance();
						user.acquireProducts(product,price,transactionDate);
						alert+="The book "+product.getName()+ " was added to your library sucesfully.\nAlso, we generated a billing with the price "+price;
						count=1;
					}else if(product instanceof Magazine){
						Magazine obj=(Magazine) product;
						obj.addSuscription();
						price=obj.getSuscriptionValue();
						transactionDate=Calendar.getInstance();
						user.acquireProducts(product,price,transactionDate);
						alert+="The magazine "+product.getName()+ " suscription was added to your library sucesfully\nAlso, we generated a billing with the price " + price;
						count=2;
					}
					if(user instanceof Regular&&userVerification(user)){
						updateRegularUser(count,user);
					}
				}else{
					alert="A regular user cannot buy more than 5 books and subscribe to 2 magazines. You already have " + obj2.getBooksPurchased() +" books and "+ obj2.getMagazinesSubscribed() +" magazines";
				}
			}else{
				alert="You cannot add a product that you already have";
			}
		}else{
			alert="Bibliographic product not founded";
		}
		return alert;
	}
	/**
	* User's verification: Verifies if the user has reached the limit of books and magazines they can acquire, depending on their regular user status.
	* 
	* <br>Preconditions:<br> The user must exist in the system.
	* 
	* <br>Postconditions:<br> None.
	*
	* @param user The user to verify.
	* @return True if the user has not reached their acquisition limits, false otherwise.
	*/
	public boolean userVerification(User user){
		boolean perm=true;
		if(user instanceof Regular){
			Regular obj=(Regular) user;
			perm=obj.userVerification();
		}
		return perm;
	}
	/**
	* Update regular user's verification: Updates the regular user's statistics based on the provided operation.
	* 
	* <br>Preconditions:<br> The user must be a regular user.
	* 
	* <br>Postconditions:<br> The regular user's statistics are updated based on the provided operation.
	*
	* @param op The operation to perform: 1 for updating the books count, 2 for updating the magazines subscribed count.
	* @param user The regular user to update.
	*/
	public void updateRegularUser(int op, User user){
		Regular obj2=(Regular) user;
		if(op==1){
			obj2.sumBooks();
		}else if(op==2){
			obj2.magazinesSubscribedCount();
		}
	}
	/**
	* Finish a user's suscription: Finishes the suscription to a magazine for the specified user.
	*
	* <br>Preconditions:<br> The user must exist and have an active suscription to the magazine.
	*
	* <br>Postconditions:<br> The user's suscription to the magazine is finished and removed from their library.
	*
	* @param idUser The ID of the user who wants to finish the suscription.
	* @param indentifier The identifier of the magazine to finish the suscription to.
	* @return A message indicating whether the suscription was successfully finished or not.
	*/
	public String finishSuscription(String idUser,String indentifier){
		User user=searchUser(idUser);
		BibliographicProduct product=searchProduct(indentifier);
		boolean done;
		String alert="";
		if(product!=null&&product instanceof Magazine){
			done=user.finishSuscription(product);
			if(done){
				Magazine obj=(Magazine) product;
				obj.eliminateSuscription();
				alert="The suscription to "+product.getName()+" was finished sucesfully";
			}else{
				alert="You have not a suscription to "+product.getName();
			}
		}else{
			alert="That product doesn't exist or is a book";
		}
		return alert;
	}
	/**
	* User's library: Retrieves the list of products in the library of the specified user.
	*
	* <br>Preconditions:<br> The user must exist in the system.
	*
	* <br>Postconditions:<br> The list of products in the user's library is returned as a formatted string.
	*
	* @param idUser The ID of the user whose library to retrieve.
	* @param libraryPag A library page
	* @return A formatted string containing the list of products in the user's library.
	*/
	public String productsLibrary(String idUser, int libraryPag){
		String alert="";
		User user=searchUser(idUser);
		alert="\n"+user.getName()+"'s library\n";
		alert=user.productsLibrary(libraryPag);
		return alert;
	}
	/**
	* Generate bibliographic product automatically: Generates a bibliographic product based on the specified operation type.
	*
	* @param opbi The operation type for generating the bibliographic product. 
	*If opbi is 1, a random book is generated. 
	*If opbi is any other value, a random magazine is generated.
	* @return A string containing information about the generated bibliographic product.
	*/
	public String generateBiliographicProduct(int opbi){
		String alert="";
		SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
		String indentifier=generateRandomIndentifier();
		String name=generateRandomName();
		int pagesNumber=generateRandomPagesNumber();
		Calendar publicationDate=generateRandomPublicationDate();
		int numCategoryOrGender=generateRandomNumCatGen();
		if(opbi==1){
			Book newbook=new Book(indentifier,name,pagesNumber,publicationDate,"RandomBooks.com","Random review, random review",numCategoryOrGender,50,1);
			bibliographic.add(newbook);
			alert="A random book was register sucesfully with the following data:\nIndentifier: "+indentifier+"\nName:"+name+"\nNumber of pages: "+pagesNumber+"\nPublication date: "+timeStamp.format(publicationDate.getTime());
		}else{
			Magazine newmagazine=new Magazine(indentifier,name,pagesNumber,publicationDate,"RandomMagazines.com",numCategoryOrGender,20,2,2);
			bibliographic.add(newmagazine);
			alert="A random magazine was register sucesfully with the follow data:\nIndentifier: "+indentifier+"\nName:"+name+"\nNumber of pages: "+pagesNumber+"\nPublication date: "+timeStamp.format(publicationDate.getTime());			
		}
		return alert;
	}
	/**
	* Generate users automatically: Generates a new user.
	*
	* <br>Preconditions:<br> None.
	*
	* <br>Postconditions:<br> A new user is generated and added to the user list.
	*
	* @param opuse An integer representing the type of user to generate. If opuse is 1, a regular user is generated, otherwise a premium user is generated.
	* @return A string containing information about the generated user.
	*/
	public String generateUser(int opuse){
		String alert="";
		SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
		String nameUser=generateRandomNameUser();
		String idUser=generateRandomIdUser();
		Calendar registerDate=generateRandomRegisterDate();
		if(opuse==1){
			Regular newUser=new Regular(nameUser, idUser, registerDate,1);
			users.add(newUser);
			alert="A regular user was register sucesfully with the following data:\nName: "+nameUser+"\nId User: "+idUser+"\nRegister date: "+timeStamp.format(registerDate.getTime());
		}else{
			Premium newUser=new Premium(nameUser, idUser, registerDate,2,"RandomNickName","12345");
			users.add(newUser);
			alert="A premium user was register sucesfully with the follow data:\nName: "+nameUser+"\nId User: "+idUser+"\nRegister date: "+timeStamp.format(registerDate.getTime());
		}
		return alert;
	}
	/**
	* Generate a random product's name: Generates a random name for a bibliographic product.
	*
	* <br>Postconditions:<br> A random name is generated and returned.
	*
	* @return A string representing a randomly generated name for a bibliographic product.
	*/
	public String generateRandomName(){
		String name="";
		Random random = new Random();
		int randomNumber = random.nextInt(6);
		switch(randomNumber){
			case 0:
				name="Star Wars";
				break;
			case 1:
				name="The lord of the rings";
				break;
			case 2:
				name="The secret";
				break;
			case 3:
				name="Heartstopper";
				break;
			case 4:
				name="The Little Prince";
				break;
			case 5:
				name="Mathematics for dummies";
				break;
		}
		return name;
	}
	/**
	* Generate a random product's indentifier: Generates a random identifier for a bibliographic product.
	*
	* <br>Postconditions:<br> A random identifier is generated and returned.
	*
	* @return A string representing a randomly generated identifier for a bibliographic product.
	*/
	public String generateRandomIndentifier(){
		Random random = new Random();
		int randomNum = random.nextInt(0xFFF) + 1;
		String hexCode = Integer.toHexString(randomNum).toUpperCase(); 
		while(hexCode.length() < 3) { 
		hexCode = "0" + hexCode;
		}
		return hexCode;
	}
	/**
	* Generate random product's number of pages: Generates a random number of pages for a bibliographic product.
	*
	* <br>Postconditions:<br> A random number of pages is generated and returned.
	*
	* @return An integer representing a randomly generated number of pages for a bibliographic product.
	*/
	public int generateRandomPagesNumber(){
		int pagesNumber;
		Random random = new Random();
		pagesNumber = random.nextInt(1000);
		return pagesNumber;
	}
	public int generateRandomNumCatGen(){
		int numCatorGen;
		Random random = new Random();
		numCatorGen = random.nextInt(3)+1; 
		return numCatorGen;
	}
	/**
	* Generate random product's publication date: Generates a random publication date for a bibliographic product.
	*
	* <br>Postconditions:<br> A Calendar object is returned with a random date between January 1, 1500 and May 10, 2023.
	*
	* @return A Calendar object representing the random publication date.
	*/
	public Calendar generateRandomPublicationDate(){
		Calendar publicationDate=Calendar.getInstance();
		Random random = new Random();
		int dayMin = 1;
		int dayMax = 28;
		int range = dayMax - dayMin + 1;
		int randomDay = random.nextInt(range) + dayMin;
		int monthMin = 1;
		int monthMax = 12;
		range = monthMax - monthMin + 1;
		int randomMonth = random.nextInt(range) + monthMin;
		int yearMin = 1500;
		int yearMax = 2023;
		range = yearMax - yearMin + 1;
		int randomYear = random.nextInt(range) + yearMin;
		
		publicationDate.set(randomYear,randomMonth,randomDay);
		return publicationDate;
	}
	/**
	* Generate a user's random name: Generates a random name for a user.
	*
	* <br>Postconditions:<br> A random name is generated and returned as a String.
	*
	* @return A String representing a random name for a user.
	*/
	public String generateRandomNameUser(){
		String name="";
		Random random = new Random();
		int randomNumber = random.nextInt(6);
		switch(randomNumber){
			case 0:
				name="Juan Camilo Tobar";
				break;
			case 1:
				name="Julian Muller";
				break;
			case 2:
				name="Sara Lucia";
				break;
			case 3:
				name="Raul Quihua";
				break;
			case 4:
				name="Liliana Reina";
				break;
			case 5:
				name="Alejandra Tobar";
				break;
		}
		return name;
	}
	/**
	* Generate user's random id: Generates a random ID for a user.
	*
	* <br>Postconditions:<br> A random ID is generated and returned as a String.
	*
	* @return A String representing a random ID for a user.
	*/
	public String generateRandomIdUser(){
		String id="";
		Random random = new Random();
		int randomNumber = random.nextInt(50);
		int randomNumber2 = random.nextInt(50);
		int result=randomNumber+randomNumber2;
		String resultAsString = Integer.toString(result);
		id=resultAsString;
		return id;
	}
	/**
	* Generate a user's random register date: Generates a random date for user registration.
	*
	* <br>Postconditions:<br> A random date is generated and returned as a Calendar object.
	*
	* @return A Calendar object representing a random date for user registration.
	*/
	public Calendar generateRandomRegisterDate(){
		Calendar registerDate=Calendar.getInstance();
		Random random = new Random();
		int dayMin = 1;
		int dayMax = 28;
		int range = dayMax - dayMin + 1;
		int randomDay = random.nextInt(range) + dayMin;
		int monthMin = 1;
		int monthMax = 12;
		range = monthMax - monthMin + 1;
		int randomMonth = random.nextInt(range) + monthMin;
		int yearMin = 1970;
		int yearMax = 2015;
		range = yearMax - yearMin + 1;
		int randomYear = random.nextInt(range) + yearMin;
		
		registerDate.set(randomYear,randomMonth,randomDay);
		return registerDate;
	}
	/**
	* Search a user's acquire product: Searches for a bibliographic product by identifier and attempts to acquire it for a user.
	*
	* <br>Preconditions:<br> The identifier and user ID must be non-null and non-empty.
	*
	* <br>Postconditions:<br> If the product is found and acquired by the user, returns true. Otherwise, returns false.
	*
	* @param indentifier A String representing the identifier of the bibliographic product to search for.
	* @param idUser A String representing the ID of the user attempting to acquire the product.
	* @return A boolean indicating whether or not the product was successfully acquired by the user.
	*/
	public boolean searchAcquireProduct(String indentifier,String idUser){
		BibliographicProduct obj;
		User user=searchUser(idUser);
		boolean found=false;
		BibliographicProduct product=searchProduct(indentifier);
		if(product!=null){
			obj=user.searchAcquireProduct(product);
			if(obj!=null){
				found=true;
			}
		}
		return found;
	}
	/**
	* Reading simulations: Simulates a user reading a bibliographic product.
	*
	* <br>Preconditions:<br> The user ID and product identifier must be non-null and non-empty.
	*
	* <br>Postconditions:<br> If the user and product are found, simulates the user reading the product and returns an alert message. Otherwise, returns an error message indicating the user or product was not found.
	*
	* @param op A identifier's product or a matriz position
	* @param idUser A String representing the ID of the user simulating the reading.
	* @param identifier A String representing the identifier of the bibliographic product being read.
	* @param row The row number of the product in the library.
	* @param columns The column number of the product in the library.
	* @return A String representing an alert message indicating the success or failure of the simulation.
	*/
	public BibliographicProduct searchAcquireProduct(String op,int row,int columns,String idUser,int libraryPag){
		User user=searchUser(idUser);
		BibliographicProduct productSearch=null;
		if(op!=""){
			BibliographicProduct product=searchProduct(op);
			productSearch=user.searchAcquireProduct(product);
		}else{
			productSearch=user.searchAcquireProduct(row,columns,libraryPag);
		}
		return productSearch;
	}
	/**
	* Number of pages watched: Counts the number of pages a user has simulated reading in a bibliographic product.
	*
	* <br>Preconditions:<br> The user ID and product identifier must be non-null and non-empty. The page number must be greater than 0.
	*
	* <br>Postconditions:<br> If the user and product are found, counts the number of pages simulated and returns an alert message indicating the success of the operation. Otherwise, returns an error message indicating the user or product was not found.
	*
	* @param idUser A String representing the ID of the user whose reading is being counted.
	* @param row The row number of the product in the library.
	* @param columns The column number of the product in the library.
	* @param identifier A String representing the identifier of the bibliographic product being read.
	* @param pag An integer representing the number of pages to count.
	* @return A String representing an alert message indicating the success or failure of the operation.
	*/
	public String countSimulateReading(String op,int row,int columns,String idUser,int pag,int libraryPag){
		String alert="";
		User user=searchUser(idUser);
		BibliographicProduct product=searchAcquireProduct(op,row,columns,idUser,libraryPag);
		alert="Reading page "+pag+" of "+product.getPagesNumber()+"\n";
		return alert;
	}
	/**
	* Pages count update: Adjusts the current page of a bibliographic product based on a given operation and page number.
	*
	* <br>Preconditions:<br> The operation must be either 1 (decrement) or 2 (increment). The page number must be greater than 0. The product identifier must be non-null and non-empty.
	*
	* <br>Postconditions:<br> If the product is found, adjusts the current page and updates the product's reading statistics if necessary. Returns the adjusted page number.
	*
	* @param option An integer representing the operation to perform on the current page. 1 indicates decrement, 2 indicates increment.
	* @param pag An integer representing the current page number.
	* @param op A String representing the identifier of the bibliographic product being read.
	* @param row An integer representing the row of the product.
	* @param columns An integer representing the column of the product.
	* @param idUser A string representing the ID of the user.
	* @param libraryPag An integer representing the library page of the user.
	* @return An integer representing the adjusted page number.
	*/
	public int count(int option,int pag,String op,int row,int columns,String idUser,int libraryPag){
		BibliographicProduct product=searchAcquireProduct(op,row,columns,idUser,libraryPag);
		if(option==1){
			pag=pag-1;
		}else if(option==2&&product.getPagesNumber()>pag){
			pag=pag+1;
			product.sumReadingPag();
		}
		if(pag<1){
			pag=1;
		}
		return pag;
	}
	/**
	*Simulate Reading: Simulates the reading process for a user.
	*<br>Preconditions:<br> The product must be acquired by the user.
	*<br>Postconditions:<br> The reading process is simulated based on the given parameters.
	*@param op A string representing the type of product being read.
	*@param row An integer representing the row of the product.
	*@param columns An integer representing the column of the product.
	*@param idUser A string representing the ID of the user.
	*@param libraryPag An integer representing the library page of the user.
	*@return A string representing the alert message for the simulated reading process.
	*/
	public String simulateReading(String op,int row,int columns,String idUser,int libraryPag){
		String alert="";
		User user=searchUser(idUser);
		BibliographicProduct product=searchAcquireProduct(op,row,columns,idUser,libraryPag);
		alert="Reading: "+product.getName()+"\n";
		return alert;
	}
	/**
	*
	*Generate Reports: Generates various reports.
	*
	*<br>Preconditions:<br> None.
	*
	*<br>Postconditions:<br> Reports are generated and returned as a string.
	*
	*@return A string containing the generated reports.
	*/
	public String generateReports(){
		String alert="";
		int conMagazine=0;
		int conBook=0;

		for(int i=0;i<bibliographic.size();i++){
			BibliographicProduct product=bibliographic.get(i);
			if(product instanceof Magazine){
				conMagazine+=product.getReadingPag();
			}else if(product instanceof Book){
				conBook+=product.getReadingPag();
			}
		}
		alert="--------------------\nPages of books and magazines read:\nMagazine: "+conMagazine+" Book: "+conBook+"\n--------------------\n";
		alert+="Genre of book and category of most read magazine:\n"+mostReadType()+"\n--------------------\n";
		alert+="Top 5 books and magazines:\n"+topProducts()+"--------------------\n";
		alert+="Sales by book genre:\n"+salesByGender()+"\n--------------------\n";
		alert+="Subscriptions by category:\n"+suscriptionByCategory()+"\n--------------------\n";
		return alert;
	}
	/**
	*
	*Most Read Type: Determines the most read category of magazines and genre of books.
	*
	*<br>Preconditions:<br> None.
	*
	*<br>Postconditions:<br> The most read category of magazines and genre of books are determined and returned as a string.
	*
	*@return A string representing the most read category of magazines and genre of books.
	*/
	public String mostReadType(){
		String alert="";
		int conVariety=0;
		int conDesing=0;
		int conScientific=0;
		int conScienceFic=0;
		int conFantasy=0;
		int conNovel=0;

		for(int i=0;i<bibliographic.size();i++){
			BibliographicProduct obj=bibliographic.get(i);
			if(obj instanceof Magazine){
				Magazine product=(Magazine) obj;
				if(product.getCategoryType()==CategoryType.VARIETY){
					conVariety+=obj.getReadingPag();
				}else if(product.getCategoryType()==CategoryType.DESIGN){
					conDesing+=obj.getReadingPag();
				}else if(product.getCategoryType()==CategoryType.SCIENTIFIC){
					conScientific+=obj.getReadingPag();
				}
			}else if(obj instanceof Book){
				Book product=(Book) obj;
				if(product.getGender()==GenderType.SCIENCE_FICTION){
					conScienceFic+=obj.getReadingPag();
				}else if(product.getGender()==GenderType.FANTASY){
					conFantasy+=obj.getReadingPag();
				}else if(product.getGender()==GenderType.HISTORICAL_NOVEL){
					conNovel+=obj.getReadingPag();
				}
			}
		}
		if(conVariety>conDesing&&conVariety>conScientific){
			alert="Variety: "+conVariety;
		}else if(conDesing>conVariety&&conDesing>conScientific){
			alert="Desing: "+conDesing;
		}else if(conScientific>conVariety&&conScientific>conDesing){
			alert="Scientific: "+conScientific;
		}
		alert+="\n";
		if(conScienceFic>conFantasy&&conScienceFic>conNovel){
			alert+="Science fiction: "+conScienceFic;
		}else if(conFantasy>conScienceFic&&conFantasy>conNovel){
			alert+="Fantasy: "+conFantasy;
		}else if(conNovel>conScienceFic&&conNovel>conFantasy){
			alert+="Historical novel: "+conNovel;
		}
		return alert;
	}
	/**
	*
	*Top Products: Retrieves the top books and magazines based on reading pages.
	*
	*<br>Preconditions:<br> None.
	*
	*<br>Postconditions:<br> The top books and magazines are determined and returned as a string.
	*
	*@return A string containing the top books and magazines based on reading pages.
	**/
	public String topProducts() {
		String alert = "";
		BibliographicProduct[] topBooks = new BibliographicProduct[5];
		BibliographicProduct[] topMagazines = new BibliographicProduct[5];
		int canPages;

		for (int i = 0; i < bibliographic.size(); i++) {
			BibliographicProduct obj = bibliographic.get(i);
			canPages = obj.getReadingPag();
			if (obj instanceof Magazine) {
				for (int j = 0; j < topMagazines.length; j++) {
					if (topMagazines[j] == null) {
						topMagazines[j] = obj;
						break;
					} else if (topMagazines[j].getReadingPag() < canPages) {
						if (j < 4) {
							for (int k = 3; k >= j; k--) {
								topMagazines[k + 1] = topMagazines[k];
							}
							topMagazines[j] = obj;
							break;
						} else {
							topMagazines[j] = obj;
							break;
						}
					}
				}
			} else if (obj instanceof Book) {
				for (int j = 0; j < topBooks.length; j++) {
					if (topBooks[j] == null) {
						topBooks[j] = obj;
						break;
					} else if (topBooks[j].getReadingPag() < canPages) {
						if (j < 4) {
							for (int k = 3; k >= j; k--) {
								topBooks[k + 1] = topBooks[k];
							}
							topBooks[j] = obj;
							break;
						} else {
							topBooks[j] = obj;
							break;
						}
					}
				}
			}
		}
		alert += "Top Magazines:\n";
		for (int i = 0; i < topMagazines.length; i++) {
			if (topMagazines[i] != null) {
				Magazine product = (Magazine) topMagazines[i];
				alert +="Name: "+ topMagazines[i].getName() + " Category: " + product.getCategoryType() + " Reading pages: " + topMagazines[i].getReadingPag() + "\n";
			} else {
				alert += "----\n";
			}	
		}	
		alert += "Top books:\n";
		for (int i = 0; i < topBooks.length; i++) {
			if (topBooks[i] != null) {
				Book product = (Book) topBooks[i];
				alert +="Name: "+topBooks[i].getName() + " Gender: " +product.getGender()+ " Reading pages: " + topBooks[i].getReadingPag() + "\n";
			} else {
				alert += "----\n";
			}
		}
		return alert;
	}
	/**
	*
	*Sales by Gender: Retrieves the sales information for each book genre.
	*<br>Preconditions:<br> None.
	*<br>Postconditions:<br> The sales information by book genre is determined and returned as a string.
	*@return A string containing the sales information by book genre.
	*/
	public String salesByGender(){
		String alert="";
		int conScienceFic=0;
		double salesScienceFic=0;
		int conFantasy=0;
		double salesFantasy=0;
		int conNovel=0;
		double salesNovel=0;
		
		for (int i = 0; i < bibliographic.size(); i++) {
			BibliographicProduct obj = bibliographic.get(i);
			if(obj instanceof Book){
				Book product=(Book) obj;
				if(product.getGender()==GenderType.SCIENCE_FICTION){
					conScienceFic+=product.getNumSold();
					salesScienceFic+=product.getValue();
				}else if(product.getGender()==GenderType.FANTASY){
					conFantasy+=product.getNumSold();
					salesFantasy+=product.getValue();
				}else if(product.getGender()==GenderType.HISTORICAL_NOVEL){
					conNovel+=product.getNumSold();
					salesNovel+=product.getValue();
				}
			}
		}
		alert="Science Fiction:\nBooks sold:"+conScienceFic+" Sales value:"+salesScienceFic+"\nFantasy:\nBooks sold:"+conFantasy+" Sales value:"+salesFantasy+"\nNovel:\nBooks sold:"+conNovel+" Sales value:"+salesNovel;
		return alert;
	}
	/**
	*
	*Subscription by Category: Retrieves the subscription information for each magazine category.
	*
	*<br>Preconditions:<br> None.
	*
	*<br>Postconditions:<br> The subscription information by magazine category is determined and returned as a string.
	*
	*@return A string containing the subscription information by magazine category.
	*/
	public String suscriptionByCategory(){
		String alert="";
		int conVariety=0;
		double salesVariety=0;
		int conDesing=0;
		double salesDesing=0;
		int conScientific=0;
		double salesScience=0;
		
		for (int i = 0; i < bibliographic.size(); i++) {
			BibliographicProduct obj = bibliographic.get(i);
			if(obj instanceof Magazine){
				Magazine product=(Magazine) obj;
				if(product.getCategoryType()==CategoryType.VARIETY){
					conVariety+=product.getNumSuscriptions();
					salesVariety+=product.getSuscriptionValue();
				}else if(product.getCategoryType()==CategoryType.DESIGN){
					conDesing+=product.getNumSuscriptions();
					salesDesing+=product.getSuscriptionValue();
				}else if(product.getCategoryType()==CategoryType.SCIENTIFIC){
					conScientific+=product.getNumSuscriptions();
					salesScience+=product.getSuscriptionValue();
				}
			}
		}
		alert="Variety:\nActive subscriptions:"+conVariety+" Sales value:"+salesVariety+"\nDesing:\nActive subscriptions:"+conDesing+" Sales value:"+salesDesing+"\nScientific:\nActive subscriptions:"+conScientific+" Sales value:"+salesScience;
		return alert;
	}
}