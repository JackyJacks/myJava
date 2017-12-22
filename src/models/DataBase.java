package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import builder.CarBuilder;
import builder.HumanBuilder;
import builder.InsuranceBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBase {

	private String url;
	private String uname;
	private String pass;
	private static Connection con;
	
	public static void setConnection(Connection con) {
		DataBase.con = con;
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	public DataBase() throws ClassNotFoundException, SQLException{
		url = "jdbc:mysql://localhost:3306/carbase";
		uname = "root";
		pass = "root";

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Connecting to database...");
		con = DriverManager.getConnection(url, uname, pass);
	}
	
	public static void createDatabase() throws ClassNotFoundException, SQLException {

		System.out.println("Creating statement...");
		Statement st = con.createStatement();
		
		String query = "create table Insurances (id_ins int auto_increment primary key," + 
				"InsuranceName varchar(50), InsurancePrice int , InsuranceYear date);";
		st.execute(query);
		
		query = "create table Drivers (id_driver int auto_increment primary key," + 
				"DriverName varchar(50), DriverSName varchar(50), DriverBirthd date, DriverAddres varchar(50));";
		st.execute(query);
		
		query = "create table Cars (id_car int auto_increment primary key," + 
					"CarName varchar(50), CarModel varchar(50), CarColor varchar(50), CarNumber varchar(50)," +
					"id_ins int not null, id_driver int not null, foreign key(id_ins) references Insurances(id_ins)," +
					"foreign key(id_driver) references Drivers(id_driver));";
		st.execute(query);
		
		st.close();
	}	
	
	public static ArrayList<Human> getFromDataBaseDrivers() throws SQLException{
		ArrayList<Human> drivers = new  ArrayList<Human>();
		ResultSet rs = null;
		Statement st = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from drivers");
			while(rs.next()) {
				String name = rs.getString("DriverName");
				String sname = rs.getString("DriverSName");
				LocalDate birthdate = rs.getDate("DriverBirthd").toLocalDate();
				String addres = rs.getString("DriverAddres");
				Human human = new HumanBuilder().setHumanName(name).setHumanSecondName(sname).setHumanBirthd(birthdate).setHumanAddres(addres).build();
				drivers.add(human);
			}
		} finally {
			if (rs != null )
				rs.close();
			if (st != null )
				st.close();
		}
		return drivers;
	}
	
	public static ArrayList<Car> getFromDataBaseCars() throws SQLException{
		ArrayList<Car> drivers = new  ArrayList<Car>();
		ResultSet rs = null;
		Statement st = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from cars");
			while(rs.next()) {
				String name = rs.getString("CarName");
				String model = rs.getString("CarModel");
				String number = rs.getString("CarNumber");
				String color = rs.getString("CarColor");
				Car car = new CarBuilder().setCarName(name).setCarModel(model).setCarNumber(number).setCarColor(color).build();
				drivers.add(car);
			}
		} finally {
			if (rs != null )
				rs.close();
			if (st != null )
				st.close();
		}
		return drivers;
	}
	
	public static ArrayList<Insurance> getFromDataBaseInsurance() throws SQLException{
		ArrayList<Insurance> insurance = new  ArrayList<Insurance>();
		ResultSet rs = null;
		Statement st = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from insurances");
			while(rs.next()) {
				String name = rs.getString("InsuranceName");
				int price = rs.getInt("InsurancePrice");
				LocalDate date = rs.getDate("InsuranceYear").toLocalDate();
				Insurance ins = new InsuranceBuilder().setInsuranceName(name).setInsurancePrice(price).setInsuranceDate(date).build();
				insurance.add(ins);
			}
		} finally {
			if (rs != null )
				rs.close();
			if (st != null )
				st.close();
		}
		return insurance;
	}
	
	public static ArrayList<Car> getFromDataBase() throws SQLException{
		ArrayList<Car> fullBase = new ArrayList<Car>();
		ArrayList<Car> allCars = new ArrayList<Car>();
		Car car = new Car();
		Insurance ins = new Insurance();
		Human driver = new Human();
		
		allCars = getFromDataBaseCars();
		for (Car c : allCars) {
			ins = getCarInsurance(c);
			driver = getCarDriver(c);
			car = c;
			car.setHuman(driver);
			car.setInsurance(ins);
			fullBase.add(car);
		}
		
		
		return fullBase;
	}
	
	public static Insurance getInsuranceById(int id_ins) throws SQLException {
		Insurance insurance = new Insurance();
		ResultSet rs = null;
		Statement st = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from insurances where id_ins = " + id_ins + ";");
			rs.next();
			String name = rs.getString("InsuranceName");
			int price = rs.getInt("InsurancePrice");
			LocalDate date = rs.getDate("InsuranceYear").toLocalDate();
			insurance = new InsuranceBuilder().setInsuranceName(name).setInsurancePrice(price).setInsuranceDate(date).build();
		} finally {
			if (rs != null )
				rs.close();
			if (st != null )
				st.close();
		}
		return insurance;
	}
	
	public static Human getDriverById(int id_driver) throws SQLException {
		Human driver = new Human();
		ResultSet rs = null;
		Statement st = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from drivers where id_driver = " + id_driver + ";");
			rs.next();
			String name = rs.getString("DriverName");
			String sname = rs.getString("DriverSName");
			LocalDate birthdate = rs.getDate("DriverBirthd").toLocalDate();
			String addres = rs.getString("DriverAddres");
			driver = new HumanBuilder().setHumanName(name).setHumanSecondName(sname).setHumanBirthd(birthdate).setHumanAddres(addres).build();
		} finally {
			if (rs != null )
				rs.close();
			if (st != null )
				st.close();
		}
		return driver;
	}
	
	public static boolean isExist(Object ob) throws SQLException {
		boolean result = false;
		ArrayList<?> list = null;
		String myClass = ob.getClass().getSimpleName();
		if (myClass.equals("Human")) {
			list = getFromDataBaseDrivers();	
		}else if(myClass.equals("Insurance")) {
			list = getFromDataBaseInsurance();
		}else if(myClass.equals("Car")) {
			list = getFromDataBaseCars();
		}else {
			return result;
		}
		for(Object c : list ) {
			if(c.equals(ob)) {
				result = true;
				return result;
			}
		}
		return result;
	}
	
	public static void addToDatabaseDriver(Human driver) throws SQLException {
		PreparedStatement st = null;
		if (isExist(driver))
			return;
		try {
			st = con.prepareStatement("insert into Drivers(DriverName, DriverSName, DriverBirthd, DriverAddres) values (?, ?, ?, ?)");
			st.setString(1, driver.getHumanName());
			st.setString(2, driver.getHumanSecondName());
			st.setDate(3, Date.valueOf(LocalDate.of(
					driver.getHumanBirthd().getYear(),
					driver.getHumanBirthd().getMonth(),
					driver.getHumanBirthd().getDayOfMonth())));
			st.setString(4, driver.getHumanAddres());
			st.execute();
		}
		finally{
			if(st!=null) {
				st.close();
			}
		}
	}
	
	public static void addToDatabaseCars(Car car, int insId, int driverId) throws SQLException {
		PreparedStatement st = null;
		if (isExist(car))
			return;
		try {
			st = con.prepareStatement("insert into Cars(CarName, CarModel, CarColor, CarNumber, id_ins, id_driver) values (?, ?, ?, ?, ?, ?)");
			st.setString(1, car.getCarName());
			st.setString(2, car.getCarModel());
			st.setString(3, car.getCarColor());
			st.setString(4, car.getCarNumber());
			st.setInt(5, insId);
			st.setInt(6, driverId);
			st.execute();
		}
		finally{
			if(st!=null) {
				st.close();
			}
		}
	}
	
	public static void addToDatabaseInsurance(Insurance insurance) throws SQLException {
		PreparedStatement st = null;
		if (isExist(insurance)) {
			return;
		}
		try {
			st = con.prepareStatement("insert into Insurances(InsuranceName, InsurancePrice, InsuranceYear) values (?, ?, ?)");
			st.setString(1, insurance.getInsuranceName());
			st.setInt(2, insurance.getInsurancePrice());
			st.setDate(3, Date.valueOf(LocalDate.of(
					insurance.getInsuranceDate().getYear(),
					insurance.getInsuranceDate().getMonth(),
					insurance.getInsuranceDate().getDayOfMonth())));
			st.execute();
		}
		finally{
			if(st!=null) {
				st.close();
			}
		}
	}
	
	public static int findId(Object ob) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = null;
		int result = 0;
		ArrayList<?> list = null;
		String myClass = ob.getClass().getSimpleName();
		String query = "";
		String gtint = "";
		if (myClass.equals("Human")) {
			Human human = (Human) ob;
			list = getFromDataBaseDrivers();
			query = "select id_driver from drivers where DriverName = '" + human.getHumanName() + "' and DriverSName = '" + human.getHumanSecondName() + "';";
			gtint = "id_driver";
		}else if(myClass.equals("Insurance")) {
			Insurance ins = (Insurance) ob;
			list = getFromDataBaseInsurance();
			query = "select id_ins from insurances where InsuranceName = '" + ins.getInsuranceName() + "' and InsuranceYear = '" + ins.getInsuranceDate() + "';";
			gtint = "id_ins";
		}else {
			return result;
		}
		for(Object c : list ) {
			if(c.equals(ob)) {
				rs = st.executeQuery(query);
				rs.next();
				result = rs.getInt(gtint);
				return result;
			}else
				result = 0;
		}
		return result;
		
	}
	
	public static void addToDatabase(ArrayList<Car> allcars) throws SQLException {
	    System.out.println("Creating statement...");
	    Statement st = con.createStatement();
	    int idIns = 0;
	    int idDrive = 0;
	    try {
		    for(Car c : allcars ) {
		    	addToDatabaseInsurance(c.getInsurance());
		    	addToDatabaseDriver(c.getHuman());
		    	idIns = findId(c.getInsurance());
		    	idDrive = findId(c.getHuman());
		    	addToDatabaseCars(c, idIns, idDrive);
		    }
	    }finally {
			if(st != null)
				st.close();
		}
	  }	
	
	public static void updateInsurance(Car car, Insurance newInsurance) throws SQLException {
		if (!isExist(car))
			throw new IllegalArgumentException("This car is not in Data Base!");
		if (!isExist(newInsurance))
			addToDatabaseInsurance(newInsurance);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement("update cars, insurances set cars.id_ins=insurances.id_ins where cars.CarNumber=? and insurances.insurancename=?");
			st.setString(1, car.getCarNumber());
			st.setString(2, newInsurance.getInsuranceName());
			st.execute();
		}finally {
			if(st != null)
				st.close();
		}
	}
	
	public static void updateDriver(Car car, Human newDriver) throws SQLException {
		if (!isExist(car))
			throw new IllegalArgumentException("This car is not in Data Base!");
		if (!isExist(newDriver))
			addToDatabaseDriver(newDriver);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement("update cars, drivers set cars.id_driver=drivers.id_driver where cars.CarName=? and drivers.drivername=?");
			st.setString(1, car.getCarName());
			st.setString(2, newDriver.getHumanName());
			st.execute();
		}finally {
			if(st != null)
				st.close();
		}
	}
	
	public static Insurance getCarInsurance(Car car) throws SQLException {
		if (!isExist(car))
			throw new IllegalArgumentException("This car is not in Data Base!");
		Insurance ins = new Insurance();
		Statement st = null;
		ResultSet rs = null;
		int id_ins = 0;
		try {
			String query = "select id_ins from cars where carnumber = '" + car.getCarNumber() + "';";
			st = con.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			id_ins = rs.getInt("id_ins");
			ins = getInsuranceById(id_ins);
		}finally {
			if(st != null)
				st.close();
			if(rs != null)
				rs.close();
		}
		return ins;
	}
	
	public static Human getCarDriver(Car car) throws SQLException {
		if (!isExist(car))
			throw new IllegalArgumentException("This car is not in Data Base!");
		Human driver = new Human();
		Statement st = null;
		ResultSet rs = null;
		int id_driver = 0;
		try {
			String query = "select id_driver from cars where carnumber = '" + car.getCarNumber() + "';";
			st = con.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			id_driver = rs.getInt("id_driver");
			driver = getDriverById(id_driver);
		}finally {
			if(st != null)
				st.close();
			if(rs != null)
				rs.close();
		}
		return driver;
	}
	
	public static void deleteCarByNumber(String number) throws SQLException {
		PreparedStatement st = null;
		try {
			st = con.prepareStatement("delete from cars where carnumber=?");
			st.setString(1, number);
			st.execute();
		}finally {
			if(st != null)
				st.close();
		}
	}
	
	public static void deleteInsuranceById(int id) throws SQLException {
		PreparedStatement st = null;
		try {
			st = con.prepareStatement("delete from insurances where id_ins=?");
			st.setInt(1, id);
			st.execute();
		}finally {
			if(st != null)
				st.close();
		}
	}
	
	public static void deleteDriverByFullName(String name, String lastName) throws SQLException {
		PreparedStatement st = null;
		try {
			st = con.prepareStatement("delete from drivers where drivername=? and driversname=?");
			st.setString(1, name);
			st.setString(2, lastName);
			st.execute();
		}finally {
			if(st != null)
				st.close();
		}
	}
	
	public static int getCountOfCars() throws SQLException {
		ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM cars");
            rs.next();
            return rs.getInt("rowcount");
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
	}
	
	public static int getCountOfInsurances() throws SQLException {
		ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Insurances");
            rs.next();
            return rs.getInt("rowcount");
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
	}
	
	public static int getCountOfDrivers() throws SQLException {
		ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Drivers");
            rs.next();
            return rs.getInt("rowcount");
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
	}
	
	public static void clearDatabase() throws SQLException {
		Statement st = con.createStatement();
		
		String query = "delete from Cars where id_car > 0;";
		st.execute(query);
		query = "delete from Drivers where id_driver > 0;";
		st.execute(query);
		query = "delete from Insurances where id_ins > 0;";
		st.execute(query);
		st.close();
	}
	
	public static void dropDatabase() throws ClassNotFoundException, SQLException {		
		System.out.println("Creating statement...");
		Statement st = con.createStatement();
		
		String query = "drop table Cars;";
		st.execute(query);
		query = "drop table Drivers;";
		st.execute(query);
		query = "drop table Insurances;";
		st.execute(query);
		st.close();
	}
}
