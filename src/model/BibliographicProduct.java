package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class BibliographicProduct{
	//Atributes
	private String indentifier;
	private String name;
	private int pagesNumber;
	private Calendar publicationDate;
	private String url;
	private int numReadPages;
	private TypeProduct typeProduct;
	
	public BibliographicProduct(String id,String na,int pn,Calendar pd,String ur,int tB){
		indentifier=id;
		name=na;
		pagesNumber=pn;
		publicationDate=pd;
		url=ur;
		numReadPages=0;
		typeProduct=menuTypeProduct(tB);
	}
	public TypeProduct menuTypeProduct(int op){
		TypeProduct productOp=null;
		switch(op){
			case 1:
				productOp=TypeProduct.BOOK;
				break;
			case 2:
				productOp=TypeProduct.MAGAZINE;
				break;
		}
		return productOp;
	}
	public String getIndentifier(){
		return indentifier;
	}
	public String toString(){
		SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy");
		return indentifier+"  "+name+"   "+pagesNumber+"   "+timeStamp.format(publicationDate.getTime())+"   "+url+"   "+numReadPages+"     "+typeProduct;
	}
	public String getName(){
		return name;
	}
	public TypeProduct getType(){
		return typeProduct;
	}
	public void setName(String newName){
		name=newName;
	}
	public int getPagesNumber(){
		return pagesNumber;
	}
}