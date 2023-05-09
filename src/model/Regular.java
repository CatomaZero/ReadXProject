package model;
import java.util.Calendar;
import java.util.Random;

public class Regular extends User implements Announceable{
	//Atributes
	private int maxBooks;
	private int maxMagazines;

	public Regular(String nameUser,String idUser,Calendar registerDate, int usertp){
		super(nameUser,idUser,registerDate,usertp);
		adds=true;
		maxBooks=5;
		maxMagazines=2;
	}
	public String toString(){
		return super.toString()+"   "+maxBooks+"    "+maxMagazines;
	}
	public boolean userVerification(){
		boolean perm=true;
		int booksPurchased=super.getBooksPurchased();
		int magazinesSubscribed=super.getMagazinesSubscribed();
		if(booksPurchased>=maxBooks||magazinesSubscribed>=maxMagazines){
			perm=false;
		}
		return perm;
	}
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