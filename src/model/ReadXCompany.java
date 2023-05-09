package model;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Random;
import java.text.SimpleDateFormat;

public class ReadXCompany{
	//Atributes
	private String nameCompany;
	private String nitCompany;
	
	//Relations
	private ArrayList<BibliographicProduct> bibliographic;
	private ArrayList<User> users;
	
	public ReadXCompany(String name, String nit){
		nameCompany=name;
		nitCompany=nit;
		
		bibliographic=new ArrayList <BibliographicProduct>();
		users=new ArrayList <User>();
	}
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
	public String modifyBibliographic(String newName,String indentifier){
		String alert="";
		BibliographicProduct product=searchProduct(indentifier);
		if(product!=null){
			product.setName(newName);
			alert="The bibliographic product's name was changed sucesfully to "+product.getName();
		}else{
			alert="The name could not be changed";
		}
		return alert;
	}
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
	public String imprimBibliographicName(String indentifier){
		BibliographicProduct bibliographic=searchProduct(indentifier);
		String alert="The product searched was: "+bibliographic.getName();
		return alert;
	}
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
			alert="Bibliographic product not founded";
		}
		return alert;
	}
	public boolean userVerification(User user){
		boolean perm=true;
		if(user instanceof Regular){
			Regular obj=(Regular) user;
			perm=obj.userVerification();
		}
		return perm;
	}
	public void updateRegularUser(int op, User user){
		Regular obj2=(Regular) user;
		if(op==1){
			obj2.sumBooks();
		}else if(op==2){
			obj2.magazinesSubscribedCount();
		}
	}
	public String finishSuscription(String idUser,String indentifier){
		User user=searchUser(idUser);
		BibliographicProduct product=searchProduct(indentifier);
		boolean done;
		String alert="";
		if(product!=null&&product instanceof Magazine){
			Magazine obj=(Magazine) product;
			done=user.finishSuscription(product);
			if(done){
				alert="The suscription to "+product.getName()+" was finished sucesfully";
				obj.eliminatedSuscription();
			}else{
				alert="You have not a suscription to "+product.getName();
			}
		}else{
			alert="That product doesn't exist or is a book";
		}
		return alert;
	}
	public String productsLibrary(String idUser){
		String alert="";
		User user=searchUser(idUser);
		alert+="\n"+user.getName()+"'s library\n";
		alert+=user.productsLibrary();
		return alert;
	}
	public String generateBiliographicProduct(int opbi){
		String alert="";
		SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
		String indentifier=generateRandomIndentifier();
		String name=generateRandomName();
		int pagesNumber=generateRandomPagesNumber();
		Calendar publicationDate=generateRandomPublicationDate();
		if(opbi==1){
			Book newbook=new Book(indentifier,name,pagesNumber,publicationDate,"RandomBooks.com","Random review, random review",2,50,1);
			bibliographic.add(newbook);
			alert="A random book was register sucesfully with the follow data:\nIndentifier: "+indentifier+"\nName:"+name+"\nNumber of pages: "+pagesNumber+"\nPublication date: "+timeStamp.format(publicationDate.getTime());
		}else{
			Magazine newmagazine=new Magazine(indentifier,name,pagesNumber,publicationDate,"RandomMagazines.com",2,20,2,2);
			bibliographic.add(newmagazine);
			alert="A random magazine was register sucesfully with the follow data:\nIndentifier: "+indentifier+"\nName:"+name+"\nNumber of pages: "+pagesNumber+"\nPublication date: "+timeStamp.format(publicationDate.getTime());			
		}
		return alert;
	}
	public String generateUser(int opuse){
		String alert="";
		SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
		String nameUser=generateRandomNameUser();
		String idUser=generateRandomIdUser();
		Calendar registerDate=generateRandomRegisterDate();
		if(opuse==1){
			Regular newUser=new Regular(nameUser, idUser, registerDate,1);
			users.add(newUser);
			alert="A regular user was register sucesfully with the follow data:\nName: "+nameUser+"\nId User: "+idUser+"\nRegister date: "+timeStamp.format(registerDate.getTime());
		}else{
			Premium newUser=new Premium(nameUser, idUser, registerDate,2,"RandomNickName","12345");
			users.add(newUser);
			alert="A premium user was register sucesfully with the follow data:\nName: "+nameUser+"\nId User: "+idUser+"\nRegister date: "+timeStamp.format(registerDate.getTime());
		}
		return alert;
	}
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
	public String generateRandomIndentifier(){
		String indentifier="";
		Random random = new Random();
		int randomNumber = random.nextInt(10);
		String randomNumberAsString = Integer.toString(randomNumber);
		char randomLetter = (char) (random.nextInt(26) + 'A');
		char randomLetter2 = (char) (random.nextInt(26) + 'A');
		indentifier=randomLetter+randomNumberAsString+randomLetter2;
		return indentifier;
	}
	public int generateRandomPagesNumber(){
		int pagesNumber;
		Random random = new Random();
		pagesNumber = random.nextInt(1000);
		return pagesNumber;
	}
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
	public boolean searchAcquireProduct(String indentifier,String idUser){
		boolean found;
		User user=searchUser(idUser);
		BibliographicProduct product=searchProduct(indentifier);
		if(product!=null){
			found=user.acquireProuct(product);
		}else{
			found=false;
		}
		return found;
	}
	public String simulateReading(String idUser, String indentifier){
		String alert="";
		User user=searchUser(idUser);
		BibliographicProduct product=searchProduct(indentifier);
		if(user!=null){
			if(product!=null){
				alert=user.simulateReading(product);
			}else{
				alert="Bibliographic product not found";
			}
		}else{
			alert="User not found";
		}
		return alert;
	}
	public String countSimulateReading(String idUser,String indentifier,int pag){
		String alert="";
		User user=searchUser(idUser);
		BibliographicProduct product=searchProduct(indentifier);
		if(user!=null){
			if(product!=null){
				alert=user.countSimulateReading(product,pag);
			}
		}
		return alert;
	}
	public int count(int op,int pag){
		if(op==1){
			pag=pag-1;
		}else if(op==2){
			pag=pag+1;
		}
		if(pag<1){
			pag=1;
		}
		return pag;
	}
}