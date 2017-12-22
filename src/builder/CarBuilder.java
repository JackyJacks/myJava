package builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Car;

public class CarBuilder {
	public static final String NAME_PATTERN	 = "^[A-Z][A-Za-z]{2,14}([- ][a-zA-Z]{1,15}){0,3}$";
	public static final String NUMBER_PATTERN = "^[A-C][ABCEHIKMHOPTX] ?[0-9]{4} ?[ABCEHIKMHOPTX]{2}$";
	public static final String MODEL_PATTERN = "^[A-Za-z0-9]{1,14}([- ][a-zA-Z0-9]{1,15})?$";	
	
	private String number, name, model, color;

	public CarBuilder setCarNumber(String number) {
		this.number = number;
		return this;
	}

	public CarBuilder setCarName(String name) {
		this.name = name;
		return this;
	}

	public CarBuilder setCarModel(String model) {
		this.model = model;
		return this;
	}

	public CarBuilder setCarColor(String color) {
		this.color = color;
		return this;
	}		
	
	public Car build() {
		Car first = new Car();
		
		Pattern namePattern = Pattern.compile(NAME_PATTERN);
		Pattern numberPattern = Pattern.compile(NUMBER_PATTERN);
		Pattern modelPattern = Pattern.compile(MODEL_PATTERN);
		
		Matcher numberMatch = numberPattern.matcher(this.number);
		Matcher nameMatch = namePattern.matcher(this.name);
		Matcher modelMatch = modelPattern.matcher(this.model);
		Matcher colorMatch = namePattern.matcher(this.color);
		
		if(!(numberMatch.matches()))
			throw new IllegalArgumentException("Enter correct Car Number!");
		if(!(nameMatch.matches()))
			throw new IllegalArgumentException("Enter correct Car name!");
		if(!(modelMatch.matches()))
			throw new IllegalArgumentException("Enter correct Car model!");
		if(!(colorMatch.matches()))
			throw new IllegalArgumentException("Enter correct Car color!(Contains latin letters up to 15!");
		
		
		first.setCarColor(color);
		first.setCarModel(model);
		first.setCarName(name);
		first.setCarNumber(number);
		
		return first;
	}

}
