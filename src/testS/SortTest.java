package testS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import builder.CarBuilder;
import builder.HumanBuilder;
import builder.InsuranceBuilder;
import models.Car;
import models.Human;
import models.Insurance;

import org.testng.annotations.DataProvider;


public class SortTest {
	@Test(dataProvider = "dp")
	  public void f( Car ob, Map<String,ArrayList<Car>> res ) {
		  ArrayList<Car> cars = new ArrayList<Car> ();
		  cars.add(ob);
		  res.equals(Insurance.sort(cars));
	  }

	  @DataProvider
	  public Object[][] dp() {
		  Human h = new HumanBuilder().setHumanName("Anatoliy").setHumanAddres("Kyiv / Kyiv / Zarvanska 42a").setHumanBirthd(LocalDate.of(1988, 10, 11)).setHumanSecondName("Klamuk").build();
			Insurance i = new InsuranceBuilder().setInsuranceName("Casco O11").setInsurancePrice(999).setInsuranceDate(LocalDate.of(2014, 1, 1)).build();
			Car c = new CarBuilder().setCarName("Mercedes").setCarModel("CLC 300").setCarNumber("AA2255CB").setCarColor("Gray").build();
			c.setHuman(h);
			c.setInsurance(i);
			ArrayList<Car> allcars = new ArrayList<Car>();
			allcars.add(c);
		  Map<String,ArrayList<Car>> res = new HashMap<String,ArrayList<Car>>();
		  res.put("Kyiv", allcars);
			
		  return new Object[][] {
	    	{c, res}
	    };
	  }
	}