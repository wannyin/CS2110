/** NetId: ys764. Time spent: 1 hours.  
 * An instance maintains info about the PhD of a person. */

public class PhD {
	private String name;
	private int year;
	private int month;
	private char gender;
	private PhD first_advisor;
	private PhD second_advisor;
	private int num_adv;
	
	public PhD(String n, char g, int y, int m){
		name = n;
		gender = g;
		year = y;
		month = m;
		first_advisor = null;
		second_advisor = null;
		num_adv = 0;	
		
		assert name != null && name.length() >= 1;
		assert month > 0 && month < 13;
		assert gender == 'M' || gender == 'F';
		assert (second_advisor==null && first_advisor == null) || first_advisor != null;
		
	}
	
	public PhD(String n, char g, int y, int m, PhD adv){
		this(n, g, y, m);
		this.first_advisor = adv;
		adv.num_adv +=1;
		assert first_advisor != null;
	}
	
	public PhD(String n, char g, int y, int m, PhD adv1, PhD adv2){
		this(n, g, y, m, adv1);
		this.second_advisor = adv2;
		adv2.num_adv +=1;
		assert second_advisor != null && adv1 != adv2;
	}
	
	public String getName(){
		return name;
	}
	
	public int getYear(){
		return year;
	}
	
	public int getMonth(){
		return month;
	}
	
	public boolean isMale(){
		return gender == 'M';
	}
	
	public PhD getFirstAdvisor(){
		return first_advisor;
	}
	
	public PhD getSecondAdvisor(){
		return second_advisor;
	}
		
	public int numAdvisees(){
		return num_adv;
	}
	
	public void addFirstAdvisor(PhD p){
		if (first_advisor == null){
			first_advisor =p;
			p.num_adv += 1;
		}		
	}
	
	public void addSecondAdvisor(PhD p){
		if (first_advisor != null && second_advisor == null && p!= first_advisor){
			second_advisor =p;
			p.num_adv += 1;
		}
	}
	
	public boolean isYoungerThan(PhD p){
		return this.year > p.year || (this.year == p.year && this.month >= p.month);		
	}
	
	public boolean isPhDSibling(PhD p){
		return this!=p && ((this.first_advisor == p.first_advisor && 
				(this.first_advisor != null) && (p.first_advisor != null)) || 
				(this.first_advisor == p.second_advisor && 
				(this.first_advisor != null) && (p.second_advisor != null)) ||
				(this.second_advisor == p.first_advisor && 
				(this.second_advisor != null) && (p.first_advisor != null)) ||
				(this.second_advisor == p.second_advisor && 
				(this.second_advisor != null) && (p.second_advisor != null)));
	}

}
