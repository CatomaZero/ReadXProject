package model;
import java.util.Calendar;

public class Magazine extends BibliographicProduct{
	//Atributes
	private CategoryType category;
	private double suscriptionValue;
	private Frequency publicationFrequency;
	private int numSuscriptions;
	
	public Magazine(String indentifier,String name,int pagesNumber,Calendar publicationDate,String url,int cate, double sv, int pf,int typeProduct){
		super(indentifier, name, pagesNumber, publicationDate, url, typeProduct);
		category=menuCategory(cate);
		suscriptionValue=sv;
		publicationFrequency=menuFrequency(pf);
		numSuscriptions=0;
	}
	public CategoryType menuCategory(int op){
		CategoryType categoryOp=null;
		switch(op){
			case 1:
				categoryOp=CategoryType.VARIETY;
				break;
			case 2:
				categoryOp=CategoryType.DESIGN;
				break;
			case 3:
				categoryOp=CategoryType.SCIENTIFIC;
				break;
		}
		return categoryOp;
	}
		public Frequency menuFrequency(int op){
		Frequency frequencyOp=null;
		switch(op){
			case 1:
				frequencyOp=Frequency.DAILY;
				break;
			case 2:
				frequencyOp=Frequency.MONTHLY;
				break;
			case 3:
				frequencyOp=Frequency.YEARLY;
				break;
		}
		return frequencyOp;
	}
	public String toString(){
		return super.toString()+"   "+category+"   "+suscriptionValue+"   "+publicationFrequency+"   "+ numSuscriptions;
	}
	public double getSuscriptionValue(){
		return suscriptionValue;
	}
	public void addSuscription(){
		numSuscriptions++;
	}
	public void eliminatedSuscription(){
		numSuscriptions--;
	}
	public void setSuscriptionValue(double ns){
		suscriptionValue=ns;
	}
}