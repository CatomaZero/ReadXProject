package model;
import java.util.Calendar;

public class Premium extends User{
	//Atributes
	private String nickName;
	private String creditCard;
	
	public Premium(String nameUser,String idUser,Calendar registerDate,int usertp,String nickn, String creditc){
		super(nameUser,idUser,registerDate,usertp);
		super.setAdds(false);
		nickName=nickn;
		creditCard=creditc;
	}
	public String toString(){
		return super.toString()+"   "+nickName+"    "+creditCard;
	}
}