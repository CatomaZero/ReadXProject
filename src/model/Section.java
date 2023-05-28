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
	
	/**
	*
	*Section Constructor: Initializes a new section with the specified section number.
	*@param secNumb The section number of the section.
	*/
	public Section(int secNumb){
		sectionNumber=secNumb;
		
		purchasedProducts=new BibliographicProduct[MAXPRODUCTSX][MAXPRODUCTSY];
	}
	/**
	*
	*Get Section Number: Retrieves the section number of the section.
	*@return The section number.
	*/
	public int getSectionNumber(){
		return sectionNumber;
	}
	/**
	*
	*Modify User's Product: Modifies a user's purchased product in the section.
	*<br>Preconditions:<br> The product must exist in the section.
	*<br>Postconditions:<br> The user's product is modified with the new product data.
	*@param product The new product data to replace the user's product.
	*/
	public void modifyUsersProduct(BibliographicProduct product){
		boolean modified=false;
		for(int i=0; i<purchasedProducts.length&&!modified;i++){
			for(int k=0;k<purchasedProducts[i].length&&!modified;k++){
				if(purchasedProducts[i][k]!=null&&product.getIndentifier().equalsIgnoreCase(purchasedProducts[i][k].getIndentifier())){
					purchasedProducts[i][k]=product;
					modified=true;
				}
			}
		}
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
	*
	*Search Purchased Product: Searches for a purchased product at the specified row and column in the section.
	*@param row The row index of the purchased product.
	*@param columns The column index of the purchased product.
	*@return The purchased product at the specified row and column, or null if it doesn't exist.
	*/
	public BibliographicProduct searchPurshased(int row,int columns){
		BibliographicProduct search=purchasedProducts[row][columns];
		BibliographicProduct obj=null;
		if(search!=null){
			obj=purchasedProducts[row][columns];
		}else{
			obj=null;
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
						purchasedProducts[i][k]=product;
						acquire=true;
					}
				}
			}
		}
	}
	/**
	* Section Verification: Checks if the section has any available space for purchasing products.
	*
	* @return true if there is available space, false otherwise.
	*/
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
	/**
	* Finish Subscription: Removes the specified product from the purchased products in the section.
	*
	* @param product The product to be removed from the purchased products.
	* @return true if the product was successfully removed, false otherwise.
	*/
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
	/**
	* Products Library: Generates a string representation of the products in the section.
	*
	* @return The string representation of the products in the section.
	*/
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
	/**
	* Simulate Reading: Simulates a reading session with the specified product.
	*
	* @param product The product being read.
	* @return A string representing the reading session.
	*/
	public String simulateReading(BibliographicProduct product){
		String alert;
		alert="Reading session in progress:\n\nReading:"+product.getName()+"\n";
		return alert;
	}
	/**
	* Publication Date Verification: Checks the publication date of the product and rearranges the purchased products accordingly.
	*
	* @param product The product to be verified and rearranged.
	*/
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
				}
			}
		}
	}
	/**
	* Verification: Checks if the product should be rearranged based on its publication date.
	*
	* @param product The product to be verified.
	* @return true if the product needs to be rearranged, false otherwise.
	*/
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