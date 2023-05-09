package model;
import java.util.Calendar;

public class Book extends BibliographicProduct{
	//Atributes
	private String review;
	private GenderType gender;
	private double value;
	private int numSold;
	
	public Book(String indentifier,String name,int pagesNumber,Calendar publicationDate,String url,String rd, int gen, double val, int typeProduct){
		super(indentifier, name, pagesNumber, publicationDate, url, typeProduct);
		review=rd;
		gender=menuGender(gen);
		value=val;
		numSold=0;
	}
	public GenderType menuGender(int op){
		GenderType genderOp=null;
		switch(op){
			case 1:
				genderOp=GenderType.SCIENCE_FICTION;
				break;
			case 2:
				genderOp=GenderType.FANTASY;
				break;
			case 3:
				genderOp=GenderType.HISTORICAL_NOVEL;
				break;
		}
		return genderOp;
	}
	public String toString(){
		return super.toString()+"   "+review+"   "+gender+"   "+value+"   "+numSold;
	}
	public double getValue(){
		return value;
	}
	public void addSold(){
		numSold++;
	}
	public void setValue(double nv){
		value=nv;
	}
}