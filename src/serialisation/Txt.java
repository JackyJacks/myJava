package serialisation;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import models.Car;
import models.Human;
import models.Insurance;

public class Txt{
		
    public static void toFile(ArrayList<Car> cars, File file) throws IOException {
		FileWriter outFile = null;
		String groupInfo = null;
		
		try {
			groupInfo = cars.toString();
			outFile = new FileWriter(file);
			outFile.write(groupInfo);
		}
		finally {
			if(outFile != null)
				outFile.close();
		}
		
	}
	    
    public static ArrayList<Car> fromFile(File file) throws IOException{
	ArrayList<Car> cars = new ArrayList<Car>();
	Insurance insurance = new Insurance();
	Human human = new Human();
	Car car = new Car();
	FileReader inFile = null;
	Scanner input = null;
	String tmp = null;
		
	try {
		inFile = new FileReader(file);
		input = new Scanner(inFile);
		input.useDelimiter("(=)|:|;|\\$|\\{|\\[|\\]");

		while(input.hasNext()) {
			tmp = input.next().trim();
			//System.out.println(tmp);
			
			switch(tmp) {
			case "Name":
				//System.out.println("name = " + input.next().trim());
				human.setHumanName(input.next().trim());
				break;
			case "Last name":
				//System.out.println("name = " + input.next().trim());
				human.setHumanSecondName(input.next().trim());
				break;
			case "Date of birthday":
				human.setHumanBirthd(LocalDate.parse(input.next().trim()));
				break;
			case "Addres":
				//System.out.println("Addres = " + input.next().trim());
				human.setHumanAddres(input.next().trim());
				break;
			case "Number":
				//System.out.println("Number = " + input.next().trim());
				car.setCarNumber(input.next().trim());
				break;
			case "Car name":
				//System.out.println("Car name = " + input.next().trim());
				car.setCarName(input.next().trim());
				break;
			case "Model":
				//System.out.println("Model = " + input.next().trim());
				car.setCarModel(input.next().trim());
				break;
			case "Color":
				//System.out.println("Color = " + input.next().trim());
				car.setCarColor(input.next().trim());
				break;
			case "Ins name":
				//System.out.println("Ins name = " + input.next().trim());
				insurance.setInsuranceName(input.next().trim());
				break;
			case "Year of start":
				//System.out.println("Year of start = " + input.next().trim());
				insurance.setInsuranceDate(LocalDate.parse(input.next().trim()));
				break;
			case "Price":
				//System.out.println("Price = " + Integer.parseInt(input.next().trim()));
				insurance.setInsurancePrice(Integer.parseInt(input.next().trim()));
				break;
			case ", Holder iformation":
				car.setHuman(human);
				car.setInsurance(insurance);
				cars.add(car);
				break;
			}
			
		}
		
		car.setHuman(human);
		car.setInsurance(insurance);
		cars.add(car);
		}
		finally {
			if(inFile != null)
				inFile.close();
			if(input != null)
				input.close();
		}
		return cars;
	}
}
