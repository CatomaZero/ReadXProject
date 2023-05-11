package model;
import java.util.Calendar;

public class Premium extends User{
	//Atributes
	private String nickName;
	private String creditCard;
	/**
	* Constructor Premium: Constructs a new Premium object with the specified attributes.
	*
	* <br>Postconditions:<br> A new Premium object is created with the specified attributes and added to the ReadX's catalog.
	*
	* @param nameUser The name of the user.
	* @param idUser The ID of the user.
	* @param registerDate The registration date of the user.
	* @param usertp The user type (1 for standard users, 2 for premium users).
	* @param nickn The user's nickname.
	* @param creditc The user's credit card information.
	*/
	public Premium(String nameUser,String idUser,Calendar registerDate,int usertp,String nickn, String creditc){
		super(nameUser,idUser,registerDate,usertp);
		super.setAdds(false);
		nickName=nickn;
		creditCard=creditc;
	}
	/**
	* To string: Returns a string representation of this object.
	*
	* <br>Postconditions:<br> A string containing information about this object is returned.
	*
	* @return A string representation of this object, including the string representation of the parent class, the user's nickname, and the user's credit card information.
	*/
	public String toString(){
		return super.toString()+"   "+nickName+"    "+creditCard;
	}
}