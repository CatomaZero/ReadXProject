package model;
import java.util.Calendar;

public class Section{
	//Atributes
	private int sectionNumber;
	
	//Constants
	private final int MAXPRODUCTSX=5;
	private final int MAXPRODUCTSY=5;
	
	//Relations
	private BibliographicProduct[][] purchasedProducts;
	
	public Section(int secNumb){
		sectionNumber=secNumb;
		
		purchasedProducts=new BibliographicProduct[MAXPRODUCTSX][MAXPRODUCTSY];
	}
	public int getSectionNumber(){
		return sectionNumber;
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
		for(int i=0; i<purchasedProducts.length&&!created;i++){
			for(int k=0;k<purchasedProducts[0].length&&!created;k++){
				if(purchasedProducts[i][k]!=null&&product.getIndentifier().equalsIgnoreCase(purchasedProducts[i][k].getIndentifier())){
					obj=purchasedProducts[i][k];
					created=true;
				}
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
	public void acquireProuct(BibliographicProduct product){
		boolean acquire=false;
		for(int i=0; i<purchasedProducts.length&&!acquire;i++){
			for(int k=0;k<purchasedProducts[0].length&&!acquire;k++){
				if(purchasedProducts[i][k]==null){
					purchasedProducts[i][k]=product;
					acquire=true;
				}
			}
		}
	}
	public boolean sectionVerification(){
		boolean perm=false;
		for(int i=0; i<purchasedProducts.length&&!perm;i++){
			for(int k=0;k<purchasedProducts[0].length&&!perm;k++){
				if(purchasedProducts[i][k]==null){
					perm=true;
				}
			}
		}
		return perm;
	}
	public boolean finishSuscription(BibliographicProduct product){
		boolean done=false;
		for(int i=0; i<purchasedProducts.length&&!done;i++){
			for(int k=0;k<purchasedProducts[0].length&&!done;k++){
				if(purchasedProducts[i][k]!=null&&product.getIndentifier().equalsIgnoreCase(purchasedProducts[i][k].getIndentifier())){
					purchasedProducts[i][k]=null;
					done=true;
				}
			}
		}
		return done;
	}
	public String productsLibrary(){
		String alert="";
		for(int i=0; i<purchasedProducts.length;i++){
			for(int k=0;k<purchasedProducts[0].length;k++){
				if(purchasedProducts[i][k]!=null){
					alert+=purchasedProducts[i][k].getIndentifier()+"\n";
				}
			}
		}
		return alert;
	}
	public String simulateReading(BibliographicProduct product){
		String alert;
		alert="Reading session in progress:\n\nReading:"+product.getName()+"\n";
		return alert;
	}
}