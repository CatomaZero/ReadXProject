package ui;
import model.*;
import java.util.Scanner;
import java.util.Calendar;

public class ReadXManager{
	private ReadXCompany readx;
	private Scanner lector;
	
	public ReadXManager(){
		readx=new ReadXCompany("ReadX", "00399905");
		lector= new Scanner(System.in);
	}
	public static void main(String[] args){
		ReadXManager objReadX=new ReadXManager();
		
		objReadX.menu();
	}
	public void menu(){
		boolean status=true;
		int op;
		System.out.println("Welcome to ReadX's app. What do you want to do?");
		while(status){
			System.out.println("What do you want to do? Enter an option:");
			System.out.println("\n1.Manage Bibliographic products.\n2.Register a user.\n3.Acquire a bibliographic product.\n4.Finish a magazine suscription.\n5.See product library.\n6.Generate objects automatically.\n7.Simulate a reading.");
			op = lector.nextInt();
			switch(op){
				case 1:
					manageBibliographic();
					break;
				case 2:
					userRegister();
					break;
				case 3:
					acquireProducts();
					break;
				case 4:
					finishSuscription();
					break;
				case 5:
					productsLibrary();
					break;
				case 6:
					generateObjects();
					break;
				case 7:
					simulateReading();
					break;
				case 8:
					imprimProduct();
					break;
				case 9:
					imprimUser();
					break;
				default:
					status=false;
			}
		}			
	}
	public void manageBibliographic(){
		int op;
		System.out.println("What you want to do:\n1.Register.\n2.Modify.\n3.Delete.");
		op=lector.nextInt();
		switch(op){
			case 1:
				registerBibliographic();
				break;
			case 2:
				modifyBibliographic();
				 break;
			case 3:
				deleteBibliographic();
				break;
			default:
				System.out.println("Select a valid option");
		}
	}
	public void registerBibliographic(){
		String indentifier;
		String name;
		int pagesNumber;
		Calendar publicationDate=Calendar.getInstance();
		int day_pu;
		int month_pu;
		int year_pu;
		String url;
		int typeBibliographic=0;
		boolean exit=true;
		
		System.out.println("Enter the type of bibliographic:\n1.Book.\n2.Magazine.");
		while(exit){
			typeBibliographic=lector.nextInt();
			if(typeBibliographic==1||typeBibliographic==2){
				exit=false;
			}else{
				System.out.println("Enter a valid type:\n1.Book.\n2.Magazine.");
			}
		}
		lector.nextLine();
		System.out.println("Enter the identifier");
		indentifier=lector.nextLine();
		System.out.println("Enter the name");
		name=lector.nextLine();

		System.out.println("enter the number of pages");
		pagesNumber=lector.nextInt();
		
		System.out.print("Enter the day of publication ");
		day_pu = lector.nextInt();
		System.out.print("Enter the month of publication ");
		month_pu = lector.nextInt() - 1;
		System.out.print("Enter the year of publication ");
		year_pu = lector.nextInt();
		publicationDate.set(year_pu,month_pu,day_pu);
		
		System.out.println("enter the url of the bibliographic");
		url=lector.next();
		
		if(typeBibliographic==1){
			String review;
			int gender;
			double value;
			lector.nextLine();
			System.out.println("Enter the book's review");
			review=lector.nextLine();
			System.out.println("Enter the number of the gender: \n1.Science fiction.\n2.Fantasy.\n3.Historical novel.");
			gender=lector.nextInt();
			System.out.println("Enter the book's value");
			value=lector.nextDouble();
			
			System.out.println(readx.registerBibliographic(indentifier,name,pagesNumber,publicationDate,url,review,gender,value,typeBibliographic));
			
		}else if(typeBibliographic==2){
			int category;
			double suscriptionValue;
			int publicationFrequency;
			
			System.out.println("Enter the number of the category:\n1.Variety.\n2.Desing.\n3.Scientific");
			category=lector.nextInt();
			System.out.println("Enter the suscription value");
			suscriptionValue=lector.nextDouble();
			System.out.println("Enter the publication frequency:\n1.Daily.\n2.Monthly.\n3.Yearly");
			publicationFrequency=lector.nextInt();
			
			System.out.println(readx.registerBibliographic(indentifier,name,pagesNumber,publicationDate,url,category,suscriptionValue,publicationFrequency,typeBibliographic));
			
		}
	}
	public void modifyBibliographic(){
		String indentifier;
		int op;
		String newName;
		Double newPrice;
		System.out.println("Enter the identifier of the bibliographic product that you want to modify");
		indentifier=lector.next();
		if(readx.searchProduct(indentifier)!=null){
			System.out.println(readx.imprimBibliographicName(indentifier));
			System.out.println("What would you like to change?:\n1.Name.\n2.Price");
			op=lector.nextInt();
			if(op==1){
				System.out.println("Enter the new name");
				lector.nextLine();
				newName=lector.nextLine();
				System.out.println(readx.modifyBibliographic(newName,indentifier));
			}else if(op==2){
				System.out.println("Enter the new price");
				newPrice=lector.nextDouble();
				System.out.println(readx.modifyBibliographic(newPrice,indentifier));
			}
		}else{
			System.out.println("Bibliographic product not founded");
		}
	}
	public void deleteBibliographic(){
		System.out.println("Enter the identifier of the bibliographic product that you want to delete");
		String indentifier=lector.next();
		
		System.out.println(readx.deleteBibliographic(indentifier));
	}
	public void userRegister(){
		int typeUser=0;
		String nameUser;
		String idUser;
		Calendar registerDate=Calendar.getInstance();
		boolean exit=true;
		
		System.out.println("Enter the type of user:\n1.Regular.\n2.Premium.");
		while(exit){
			typeUser=lector.nextInt();
			if(typeUser==1||typeUser==2){
				exit=false;
			}else{
				System.out.println("Enter a valid type:\n1.Regular.\n2.Premium.");
			}
		}
		
		lector.nextLine();
		System.out.println("Enter the user's name");
		nameUser=lector.nextLine();
		System.out.println("Enter the user's id");
		idUser=lector.next();
		
		if(typeUser==1){
			System.out.println(readx.userRegister(nameUser,idUser,registerDate,typeUser));
		}else{
			String nickName;
			String creditCard;
			
			lector.nextLine();
			System.out.println("Enter the user's nickname");
			nickName=lector.nextLine();
			System.out.println("Enter the user's credit card");
			creditCard=lector.nextLine();
			System.out.println(readx.userRegister(nameUser,idUser,registerDate,typeUser,nickName,creditCard));
		}
		
	}
	public void acquireProducts(){
		System.out.println("Enter the id of the user who wishes to acquire a bibliographic product ");
		String idUser=lector.next();
		if(readx.searchUser(idUser)!=null){
			System.out.println(readx.imprimUserName(idUser));
			System.out.println("Enter the indentifier of the product you want");
			String indentifier=lector.next();
			System.out.println(readx.acquireProducts(idUser,indentifier));
		}else{
			System.out.println("User not founded");
		}
	}
	public void finishSuscription(){
		System.out.println("Enter the id of the user who wants to end a subscription");
		String idUser=lector.next();
		if(readx.searchUser(idUser)!=null){
			System.out.println(readx.imprimUserName(idUser));
			System.out.println("Enter the indentifier of the subscribed magazine you want to cancel");
			String indentifier=lector.next();
			System.out.println(readx.finishSuscription(idUser,indentifier));
		}else{
			System.out.println("User not founded");
		}
	}
	public void productsLibrary(){
		System.out.println("Enter the id of the person who wants to see the library THIS METHOD IS NOT FINISHED ALREADY");
		String idUser=lector.next();
		if(readx.searchUser(idUser)!=null){
			boolean exit=false;
			int op;
			while(!exit){
				System.out.println(readx.imprimUserName(idUser));
				System.out.println(readx.productsLibrary(idUser));
				op=lector.nextInt();
				if(op==3){
					exit=true;
				}
			}
		}else{
			System.out.println("User not founded");
		}
	}
	public void generateObjects(){
		boolean exit=true;
		int op=0;
		System.out.println("What do you want to generate automatically? \n1.Bibliographic product.\n2.User.");
		while(exit){
			op=lector.nextInt();
			if(op==1||op==2){
				exit=false;
			}else{
				System.out.println("Enter a valid option:\n1.Bibliographic product.\n2.User.");
			}
		}
		if(op==1){
			generateBiliographicProduct();
		}else if(op==2){
			generateUser();
		}
	}
	public void generateBiliographicProduct(){
		int opbibliographic=0;
		boolean exit=true;
		System.out.println("What kind of bibliographic product do you want to generate?\n1.Book.\n2.Magazine.");
		while(exit){
			opbibliographic=lector.nextInt();
			if(opbibliographic==1||opbibliographic==2){
				exit=false;
			}else{
				System.out.println("Enter a valid option:\n1.Book\n2.Magazine");
			}
		}
		System.out.println(readx.generateBiliographicProduct(opbibliographic));
	}
	public void generateUser(){
		int opusers=0;
		boolean exit=true;
		System.out.println("What kind of user do you want to generate?\n1.Regular.\n2.Premium.");
		while(exit){
			opusers=lector.nextInt();
			if(opusers==1||opusers==2){
				exit=false;
			}else{
				System.out.println("Enter a valid option:\n1.Regular.\n2.Premium");
			}
		}
		System.out.println(readx.generateUser(opusers));
	}
	public void simulateReading(){
		System.out.println("Enter the id of the user who wants to do the reading");
		String idUser=lector.next();
		if(readx.searchUser(idUser)!=null){
			boolean library=true;
			while(library){
				System.out.println(readx.imprimUserName(idUser)+"\nEnter the indentifier of the bibliographic product you want to read");
				System.out.println(readx.productsLibrary(idUser));
				String opString=lector.next();
				if(opString.equalsIgnoreCase("E")){
					library=false;
				}
				if(readx.searchAcquireProduct(opString,idUser)){
					System.out.println("Reading initialized\n");
					int op=2;
					int pag=0;
					boolean reading=true;
					while(reading){
						System.out.println(readx.simulateReading(idUser,opString));
						pag=readx.count(op,pag);
						System.out.println(readx.countSimulateReading(idUser, opString,pag)+"\nEnter 1 to go to the previous page.\nEnter 2 to go to the next page.\nEnter 3 to return to library.");
						op=lector.nextInt();
						if(op==3){
							reading=false;
						}
					}
				}else{
					System.out.println("This product has not been purchased yet");
				}
			}
		}else{
			System.out.println("User not founded");
		}
	}
	public void imprimProduct(){
		System.out.println("Enter the identifier");
		String indentifier=lector.next();
		
		System.out.println(readx.searchProduct(indentifier).toString());
	}
	public void imprimUser(){
		System.out.println("Enter the user's id");
		String id=lector.next();
		System.out.println(readx.searchUser(id).toString());
	}
}