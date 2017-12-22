package testS;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import builder.CarBuilder;
import builder.HumanBuilder;
import builder.InsuranceBuilder;
import models.Car;
import models.Human;
import models.Insurance;
import serialisation.Serialisation;

public class XmlTest {
	
	private boolean equalsCollection(ArrayList<?> arrayList, ArrayList<?> expected) {
		for(int i = 0; i < arrayList.size(); i ++)
			if(!arrayList.get(i).equals(expected.get(i)))
				return false;
		return true;
	}
	
	@Test(dataProvider = "dp")
	public void f( ArrayList<Car> allcars) throws Exception {
		Serialisation<Car> car;
		car = new serialisation.Xml<Car>(Car.class);
		car.toFile(allcars, new File("../myXml.xml"));
		ArrayList<Car> allcars2 = car.fromFile(new File("../myXml.xml"));
		assertTrue( equalsCollection(allcars, allcars2) );
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
		  return new Object[][] {
	    	{allcars}
	    };
	  }
}
