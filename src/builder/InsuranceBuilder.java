package builder;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Insurance;

public class InsuranceBuilder {
	public static final String NAME_PATTERN	 = "^[a-zA-Z]{1,15}([- ][a-zA-Z0-9]{1,15}){0,3}$";
	
	private String name;
	private LocalDate year;
	private int price;

	public InsuranceBuilder setInsuranceName(String name) {
		this.name = name;
		return this;
	}
	public InsuranceBuilder setInsuranceDate(LocalDate year) {
		this.year = year;
		return this;
	}
	public InsuranceBuilder setInsurancePrice(int price) {
		this.price = price;
		return this;
	}
	
	
	public Insurance build() {
		Insurance first = new Insurance();
		
		Pattern namePattern = Pattern.compile(NAME_PATTERN);
		
		Matcher nameMatch = namePattern.matcher(this.name);
		
		if(!(nameMatch.matches()))
			throw new IllegalArgumentException("Enter correct Insurance name!");
		if(year.compareTo(LocalDate.now()) > 0)
			throw new IllegalArgumentException("Enter correct Insurance date!");
		if(price < 0)
			throw new IllegalArgumentException("Enter correct Insurance price!");
		
		first.setInsuranceName(name);
		first.setInsurancePrice(price);
		first.setInsuranceDate(year);
		
		return first;
	}
}
