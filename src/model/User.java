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
	public UserType getUserType(){
		return userType;
	}
	public String getName(){
		return nameUser;
	}
	public String getId(){
		return idUser;
	}
	public String toString(){
		SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
		return nameUser+"   "+idUser+"   "+timeStamp.format(registerDate.getTime())+"   "+userType;
	}
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
	public void acquireProducts(BibliographicProduct product, double price, Calendar transactionDate){
		Billing newBilling=new Billing(transactionDate,price);
		billings.add(newBilling);
		purchasedProducts.add(product);
	}
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
	public void sumBooks(){
		booksPurchased++;
	}
	public void magazinesSubscribedCount(){
		magazinesSubscribed++;
	}
	public int getBooksPurchased(){
		return booksPurchased;
	}
	public int getMagazinesSubscribed(){
		return magazinesSubscribed;
	}
	public void setAdds(boolean add){
		adds=add;
	}
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
	public String countSimulateReading(BibliographicProduct product,int pag){
		String alert="";
		boolean changed=false;
		BibliographicProduct acquire=searchAcquireProduct(product);
		alert="Reading page "+pag+" of "+acquire.getPagesNumber()+"\n";
		return alert;
	}
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