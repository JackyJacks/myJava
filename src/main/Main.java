package main;

import serialisation.Serialisation;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import builder.CarBuilder;
import builder.HumanBuilder;
import builder.InsuranceBuilder;
import models.Car;
import models.DataBase;
import models.Human;
import models.Insurance;

public class Main {
		
	@SuppressWarnings("static-access")
	public static void main (String args[]) throws Exception{
		Human h = new HumanBuilder().setHumanName("Anatoliy").setHumanAddres("Kyiv / Kyiv / Zarvanska 42a").setHumanBirthd(LocalDate.of(1988, 10, 11)).setHumanSecondName("Klamuk").build();
		Insurance i = new InsuranceBuilder().setInsuranceName("Casco O11").setInsurancePrice(999).setInsuranceDate(LocalDate.of(2014, 1, 1)).build();
		Car c = new CarBuilder().setCarName("Mercedes").setCarModel("CLC 300").setCarNumber("AA2255CB").setCarColor("Gray").build();
		c.setHuman(h);
		c.setInsurance(i);
		ArrayList<Car> allcars = new ArrayList<Car>();
		allcars.add(c);
//		System.out.println(allcars);
//		Map<String,ArrayList<Car>> UkraineCars = Insurance.sort(allcars);
//		System.out.println(UkraineCars);

		new DataBase();
		
		DataBase.clearDatabase();
		DataBase.dropDatabase();
//		DataBase.createDatabase();
//		
//		Serialisation<Car> car;
//		car = new serialisation.Xml<Car>(Car.class);
//		ArrayList<Car> allcars2 = car.fromFile(new File("../myFullXml.xml"));
//		DataBase.addToDatabase(allcars2);
//		DataBase.addToDatabaseInsurance(i);
//		DataBase.addToDatabaseDriver(h);
//		DataBase.addToDatabaseCars(c, 1, 1);
//		System.out.println(DataBase.getFromDataBaseInsurance());
//		DataBase.clearDatabase();
//		DataBase.dropDatabase();
//		DataBase.createDatabase();
//		Serialisation<Car> car;
		
//		try {
////			car = new serialisation.Json<Car>(Car.class);
////			car.toFile(allcars, new File("../myJson.json"));
////			System.out.println(car.fromFile(new File("../myJson.json")));
////			serialisation.Txt.toFile(allcars, new File("../myTxt.txt"));
////			System.out.println(serialisation.Txt.fromFile(new File("../myTxt.txt")));
////			car = new serialisation.Xml<Car>(Car.class);
////			car.toFile(allcars, new File("../myXml.xml"));
////			System.out.println("\nַ xml פאיכא " + car.fromFile(new File("../myXml.xml")));
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
