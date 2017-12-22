package testS;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.testng.annotations.Test;

import builder.InsuranceBuilder;
import models.Insurance;

import org.testng.annotations.DataProvider;

public class InsuranceTest {
  @Test(dataProvider = "dp")
  public void f( Insurance car, String res ) {
	  assertEquals( car.InsPay(), res);
  }

  @DataProvider
  public Object[][] dp() {
	Insurance i = new InsuranceBuilder().setInsuranceName("Casco O11").setInsurancePrice(999).setInsuranceDate(LocalDate.of(2016, 1, 1)).build();
	String res = "Car insurance is bad\novertime 356 days.\n";
	return new Object[][] {
    	{i, res}
    };
  }
}
