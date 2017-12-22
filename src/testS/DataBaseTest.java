package testS;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import builder.CarBuilder;
import builder.HumanBuilder;
import builder.InsuranceBuilder;
import models.Car;
import models.DataBase;
import models.Human;
import models.Insurance;

public class DataBaseTest {
	DataBase carbase = null;
	
	Human firstDriver = null;
	Human secondDriver = null;
	Human thirdDriver = null;
//	Human fourthDriver = null;
//	Human fifthDriver = null;
	
	Insurance firstInsurance = null;
	Insurance secondInsurance = null;
	Insurance thirdInsurance = null;
	Insurance fourthInsurance = null;
//	Insurance fifthInsurance = null;
	
	Car firstCar = null;
	Car secondCar = null;
	Car thirdCar = null;
	Car fourthCar = null;
//	Car fifthCar = null;
//	Car sixthCar = null;
//	Car seventhCar = null;
//	Car eighthCar = null;
//	Car ninthCar = null;
	
	private boolean equalsCollection(ArrayList<?> arrayList, ArrayList<?> expected) {
		for(int i = 0; i < arrayList.size(); i ++)
			if(!arrayList.get(i).equals(expected.get(i)))
				return false;
		return true;
	}
	
	@SuppressWarnings("static-access")
	public void deleteAndCreate() throws ClassNotFoundException, SQLException {
		carbase.dropDatabase();
		carbase.createDatabase();
	}
	
	@BeforeTest
    public void initDataBase() throws ClassNotFoundException, SQLException{
		carbase = new DataBase();
    }
	
	@SuppressWarnings("static-access")
	public void initialize() throws SQLException, ClassNotFoundException {	
		
		deleteAndCreate();
		
		ArrayList<Car> allcars = new ArrayList<Car>();
		
		try {
			firstDriver = new HumanBuilder()
					.setHumanName("Anatoliy")
					.setHumanSecondName("Klamuk")
					.setHumanAddres("Kyiv / Kyiv / Zarvanska 42a")
					.setHumanBirthd(LocalDate.of(1988, 10, 11))
					.build();
		} catch (RuntimeException e) {
            System.out.println(e);
        }
		try {
			secondDriver = new HumanBuilder()
					.setHumanName("Vitaliy")
					.setHumanSecondName("Stepaniyk")
					.setHumanAddres("Hmelnitska / Dynaivtsi / Hmelstreet 15")
					.setHumanBirthd(LocalDate.of(1985, 2, 25))
					.build();
		} catch (RuntimeException e) {
            System.out.println(e);
        }
		try {
			thirdDriver = new HumanBuilder()
					.setHumanName("Dmitro")
					.setHumanSecondName("Olegnov")
					.setHumanAddres("Symu / Symu / Holovna 19")
					.setHumanBirthd(LocalDate.of(1979, 12, 1))
					.build();
		} catch (RuntimeException e) {
            System.out.println(e);
        }
		
		try {
			firstInsurance = new InsuranceBuilder()
					.setInsuranceName("Casco O11")
					.setInsurancePrice(999)
					.setInsuranceDate(LocalDate.of(2014, 1, 1))
					.build();
		} catch (RuntimeException e) {
            System.out.println(e);
        }
		try {
			secondInsurance = new InsuranceBuilder()
					.setInsuranceName("Full Care")
					.setInsurancePrice(547)
					.setInsuranceDate(LocalDate.of(2015, 11, 21))
					.build(); 
		} catch (RuntimeException e) {
            System.out.println(e);
        }
		try {
			thirdInsurance = new InsuranceBuilder()
					.setInsuranceName("Standart")
					.setInsurancePrice(301)
					.setInsuranceDate(LocalDate.of(2016, 1, 2))
					.build(); 
		} catch (RuntimeException e) {
            System.out.println(e);
        }
		try {
			fourthInsurance = new InsuranceBuilder()
					.setInsuranceName("Full Care")
					.setInsurancePrice(547)
					.setInsuranceDate(LocalDate.of(2016, 12, 1))
					.build(); 
		} catch (RuntimeException e) {
            System.out.println(e);
        }
		
		try {
			firstCar = new CarBuilder()
					.setCarName("Mercedes")
					.setCarModel("CLC 300")
					.setCarNumber("AA2255CB")
					.setCarColor("Gray")
					.build();
		} catch (RuntimeException e) {
            System.out.println(e);
        }
		try {
			secondCar = new CarBuilder()
					.setCarName("BMW")
					.setCarModel("M3")
					.setCarNumber("AC2865CB")
					.setCarColor("Red")
					.build();
		} catch (RuntimeException e) {
            System.out.println(e);
        }
		try {
			thirdCar = new CarBuilder()
					.setCarName("Lada")
					.setCarModel("2101")
					.setCarNumber("BC1859AA")
					.setCarColor("White")
					.build();
		} catch (RuntimeException e) {
            System.out.println(e);
        }
		try {
			fourthCar = new CarBuilder()
					.setCarName("Mercedes")
					.setCarModel("S 320")
					.setCarNumber("CA3359CB")
					.setCarColor("Blue")
					.build(); 
		} catch (RuntimeException e) {
            System.out.println(e);
        }
				
		firstCar.setHuman(firstDriver);
		firstCar.setInsurance(firstInsurance);
		secondCar.setHuman(secondDriver);
		secondCar.setInsurance(secondInsurance);
		thirdCar.setHuman(thirdDriver);
		thirdCar.setInsurance(thirdInsurance);
		fourthCar.setHuman(secondDriver);
		fourthCar.setInsurance(fourthInsurance);
		
		allcars.add(firstCar);
		allcars.add(secondCar);
				
		carbase.addToDatabase(allcars);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	@org.testng.annotations.Test(dataProvider = "addDriver")
    public void addDriverTest(int firstCount, int secondCount){
        assertNotEquals(firstCount,secondCount);
    }
    @DataProvider
    public Object[][] addDriver() throws SQLException, ClassNotFoundException {
    	initialize();
        @SuppressWarnings("static-access")
		int firstCount = carbase.getCountOfDrivers();
        DataBase.addToDatabaseDriver(thirdDriver);
        @SuppressWarnings("static-access")
		int secondCount = carbase.getCountOfDrivers();
        return new Object[][]{{firstCount,secondCount}};
    }
    
    @org.testng.annotations.Test(dataProvider = "getInsurances")
    public void getInsuranceTest(Insurance getedIns, Insurance makedIns){
        assertTrue(getedIns.equals(makedIns));
    }
    @SuppressWarnings("static-access")
	@DataProvider
    public Object[][] getInsurances() throws SQLException, ClassNotFoundException {
    	initialize();
    	Insurance makedIns = thirdInsurance;
    	Insurance getedIns = null;
    	ArrayList<Insurance> list = new ArrayList<Insurance>();
    	carbase.addToDatabaseInsurance(makedIns);
        list = carbase.getFromDataBaseInsurance();
        getedIns = list.get(list.size()-1);
        return new Object[][]{{getedIns,makedIns}};
    }

	  @org.testng.annotations.Test(dataProvider = "addCars")
	  public void updateDriverTest(Human driver, Human driverAfter){
	      assertNotEquals(driver, driverAfter);
	  }
		@SuppressWarnings("static-access")
		@DataProvider
	  public Object[][] addCars() throws SQLException, ClassNotFoundException {
		initialize();
	  	Human driver = new Human();
	  	Human driverAfter = new Human();
	  	driver = carbase.getCarDriver(firstCar);
	  	carbase.updateDriver(firstCar, secondDriver);
	  	driverAfter = carbase.getCarDriver(firstCar);
	    return new Object[][]{{driver, driverAfter}};
	  }
		
		@org.testng.annotations.Test(dataProvider = "prepareDataBase")
	public void setAndGetDataBaseTest(ArrayList<Car> checked, ArrayList<Car> checking){
	    assertTrue(equalsCollection(checked, checking));
	}
		@SuppressWarnings("static-access")
		@DataProvider
	public Object[][] prepareDataBase() throws SQLException, ClassNotFoundException {
		initialize();
		ArrayList<Car> checking = new ArrayList<Car>();
		ArrayList<Car> checked = new ArrayList<Car>();
		checking.add(firstCar);
		checking.add(secondCar);
		checking.add(thirdCar);
		checked.add(thirdCar);
		carbase.addToDatabase(checked);
		checked.clear();
		checked = carbase.getFromDataBase();
		
	  return new Object[][]{{checked, checking}};
	}
	
	@org.testng.annotations.Test(dataProvider = "getCarIns")
	public void getCarInsuranceTest(Insurance getIns, Insurance existIns){
		assertTrue(getIns.equals(existIns));
	}
		@SuppressWarnings("static-access")
		@DataProvider
	public Object[][] getCarIns() throws SQLException, ClassNotFoundException {
		initialize();
		Insurance getIns = new Insurance();
		getIns = carbase.getCarInsurance(firstCar);
	  return new Object[][]{{getIns, firstInsurance}};
	}
		
		@SuppressWarnings("static-access")
		@org.testng.annotations.Test(dataProvider = "deleteCar")
		public void deleteCarByNumberTest() throws SQLException{
			assertFalse(carbase.isExist(firstCar));
		}
			@SuppressWarnings("static-access")
			@DataProvider
		public Object[][] deleteCar() throws SQLException, ClassNotFoundException {
			initialize();
			carbase.deleteCarByNumber(firstCar.getCarNumber());
		  return new Object[][]{{}};
		}
}
