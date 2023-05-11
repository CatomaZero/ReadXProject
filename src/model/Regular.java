package model;
import java.util.Calendar;
import java.util.Random;

public class Regular extends User implements Announceable{
	//Atributes
	private int maxBooks;
	private int maxMagazines;
	
	/**
	* Constructor Regular: Creates a new Regular user with the given parameters.
	*
	* <br>Preconditions:<br> The name, ID, register date, and user type must be passed as parameters.
	*
	* <br>Postconditions:<br> A new Regular user object is created with the specified properties.
	*
	* @param nameUser A String representing the name of the Regular user.
	* @param idUser A String representing the ID of the Regular user.
	* @param registerDate A Calendar object representing the registration date of the Regular user.
	* @param usertp An int representing the user type of the Regular user.
	*/
	public Regular(String nameUser,String idUser,Calendar registerDate, int usertp){
		super(nameUser,idUser,registerDate,usertp);
		adds=true;
		maxBooks=5;
		maxMagazines=2;
	}
	/**
	*To String: Returns a string representation of this object.
	*
	* <br>Postconditions:<br> A string containing information about this object is returned.
	*
	* @return A string representation of this object, including the string
	*/
	public String toString(){
		return super.toString()+"   "+maxBooks+"    "+maxMagazines;
	}
	/**
	* User verification: Verifies whether a user is eligible to purchase more books or subscribe to more magazines.
	*
	* <br>Postconditions:<br> Returns a boolean indicating whether the user is eligible to purchase more books or subscribe to more magazines.
	*
	* @return A boolean indicating whether the user is eligible to purchase more books or subscribe to more magazines.
	*/
	public boolean userVerification(){
		boolean perm=true;
		int booksPurchased=super.getBooksPurchased();
		int magazinesSubscribed=super.getMagazinesSubscribed();
		if(booksPurchased>=maxBooks||magazinesSubscribed>=maxMagazines){
			perm=false;
		}
		return perm;
	}
	/**
	* Add menssages: Adds a promotional message to the user's account.
	*
	* <br>Postconditions:<br> Returns a string containing a promotional message that is randomly selected from a set of three possible messages.
	*
	* @return A string containing a promotional message that is randomly selected from a set of three possible messages.
	*/
	public String addAppearance(){
		String add="";
		int randomNumber;
		Random random = new Random();
		randomNumber = random.nextInt(3);
		if(randomNumber==0){
			add="/// Subscribe to Combo Plus and get Disney+ and Star+ at an incredible price! ///";
		}else if(randomNumber==1){
			add="///  Now your pets have a favorite app: Laika. The best products for your furry.  ///";
		}else if(randomNumber==2){
			add="///  We are celebrating our anniversary! Visit your nearest Éxito and be surprised with the best offers.  ///";
		}
		return add;
	}
}