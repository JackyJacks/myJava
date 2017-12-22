package builder;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Human;

public class HumanBuilder {
	public static final String NAME_PATTERN	 = "^[A-Z][a-z]{2,14}([- ][a-zA-Z]{1,15}){0,3}$";
	public static final String ADDRES_PATTERN = "^[A-Z][a-z]{1,14}([- ][a-zA-Z]{1,15})?[ /]{1,3}[A-Z][a-z]{1,14}[ /]{1,3}[A-Z][a-z]{1,14}([- ][a-zA-Z]{1,15})? [0-9]{1,3}[a-z]?$";
	
	private String name, secondName, addres;
	private LocalDate birthd;

	public HumanBuilder setHumanName(String name) {
		this.name = name;
		return this;
	}
	
	public HumanBuilder setHumanSecondName(String secondName) {
		this.secondName = secondName;
		return this;
	}

	public HumanBuilder setHumanBirthd(LocalDate birthd) {
		this.birthd = birthd;
		return this;
	}

	public HumanBuilder setHumanAddres(String addres) {
		this.addres = addres;
		return this;
	}
	public Human build() {
		Human first = new Human();
		int YearNow = LocalDate.now().getYear();
		
		Pattern namePattern = Pattern.compile(NAME_PATTERN);
		Pattern addresPattern = Pattern.compile(ADDRES_PATTERN);
		
		Matcher nameMatch = namePattern.matcher(this.name);
		Matcher secondNameMatch = namePattern.matcher(this.secondName);
		Matcher addresMatch = addresPattern.matcher(this.addres);

		if(!(nameMatch.matches()))
			throw new IllegalArgumentException("Enter correct name!(Contains latin letters up to 15!");
		if(!(secondNameMatch.matches()))
			throw new IllegalArgumentException("Enter correct second name!(Contains latin letters up to 15!");
		if(!(addresMatch.matches()))
			throw new IllegalArgumentException("Enter correct Adress!");
		if( birthd.getYear() >= (YearNow - 18) ||  birthd.getYear() < (YearNow - 70) )
				throw new IllegalArgumentException("Enter correct Birthday!");
		
		first.setHumanAddres(addres);
		first.setHumanBirthd(birthd);
		first.setHumanName(name);
		first.setHumanSecondName(secondName);
		
		return first;

	}
}
