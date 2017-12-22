package testS;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import builder.CarBuilder;

import org.testng.annotations.DataProvider;

public class CarTest {
  @Test(dataProvider = "dp")
  public void f(String cnu, String cn, String cm, String cc, String sss) {
	  assertEquals(new CarBuilder().setCarNumber(cnu).setCarColor(cc).setCarModel(cm).setCarName(cn).build().state(), sss);
   
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
    	{"AB8595BC", "Toyota", "Corola", "White", "Vinetsya"}
    };
  }
}
