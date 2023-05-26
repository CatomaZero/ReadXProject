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
		if(verification(product)){
			publicationDateVerification(product);
		}else{
			for(int i=0; i<purchasedProducts.length&&!acquire;i++){
				for(int k=0;k<purchasedProducts[0].length&&!acquire;k++){
					if(purchasedProducts[i][k]==null){
						System.out.println("Se agrego este producto: " + product.getIndentifier());
						purchasedProducts[i][k]=product;
						acquire=true;
					}
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
		alert += "       0     1     2     3     4   \n    ";
		for (int i = 0; i <purchasedProducts[0].length; i++){
			alert+=("------");
		}
		alert+="\n";
		for (int i=0; i<purchasedProducts.length; i++) {
			alert+=String.format("%-2d", i)+"  ";    
			for (int j=0; j< purchasedProducts[0].length;j++) {
				if(purchasedProducts[i][j]!=null){
					alert+="| " + purchasedProducts[i][j].getIndentifier() + " ";
				}else{
					alert+="| " + "---" + " ";
				}
			}
			alert+="|\n"; 
			alert+="    ";
			for (int j = 0; j < purchasedProducts[0].length; j++) {
				alert+="------";
			}
			alert+="\n";
		}
		return alert;
	}
	public String simulateReading(BibliographicProduct product){
		String alert;
		alert="Reading session in progress:\n\nReading:"+product.getName()+"\n";
		return alert;
	}
	public void publicationDateVerification(BibliographicProduct product){
		boolean perm=true;
		boolean finished=false;
		BibliographicProduct pastProduct=null;
		for(int i=0; i<purchasedProducts.length&&!finished;i++){
			for(int k=0;k<purchasedProducts[0].length&&!finished;k++){
				if(perm){
					pastProduct=purchasedProducts[i][k];
				}
				if(purchasedProducts[i][k]!=null&&product.getPublicationDate().before(pastProduct.getPublicationDate())){
					System.out.println("product que se metera en la casilla: "+product.getIndentifier());
					purchasedProducts[i][k]=product;
					product=pastProduct;
					if(k<4){
						if(purchasedProducts[i][k+1]!=null){
							pastProduct=purchasedProducts[i][k+1];
							purchasedProducts[i][k+1]=null;
							purchasedProducts[i][k+1]=product;
						}else{
							purchasedProducts[i][k+1]=product;
						}
					}else if(k==4&&i<4){
						if(purchasedProducts[i+1][0]!=null){
							pastProduct=purchasedProducts[i][k+1];
							purchasedProducts[i+1][0]=null;
							purchasedProducts[i+1][0]=product;
						}else{
							purchasedProducts[i+1][0]=product;
						}
					}
					perm=false;
					System.out.println("producto que cambiara de casilla: "+pastProduct.getIndentifier());
				}
			}
		}
	}
	public boolean verification(BibliographicProduct product){
		boolean done=false;
		for(int i=0; i<purchasedProducts.length&&!done;i++){
			for(int k=0;k<purchasedProducts[0].length&&!done;k++){
				if(purchasedProducts[i][k]!=null&&product.getPublicationDate().before(purchasedProducts[i][k].getPublicationDate())){
					done=true;
				}
			}
		}
		return done;
	}
}