package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;



@XmlRootElement 
public class Insurance{
	private String insuranceName;
	@JsonSerialize(using =dateSerializer. JsonLocDateSer.class)
	@JsonDeserialize(using = dateSerializer.JsonLocDateDes.class)
	private LocalDate insuranceDate;
	private int insurancePrice;
	
	public Insurance() {
		setInsuranceName(null);
		setInsuranceDate(null);
		setInsurancePrice(0);
	}
	
	public Insurance( String name, LocalDate date, int price ) {
		setInsuranceName(name);
		setInsuranceDate(date);
		setInsurancePrice(price);
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String name) {
		insuranceName = name;
	}
	@JsonIgnore
	public LocalDate getInsuranceDate() {
		return insuranceDate;
	}
	
	public void setInsuranceDate(LocalDate date) {
		insuranceDate = date;
	}

	public int getInsurancePrice() {
		return insurancePrice;
	}

	public void setInsurancePrice(int price) {
		insurancePrice = price;
	}
	@JsonIgnore
	public String InsPay() {
		String word = "";
		long daylast = ChronoUnit.DAYS.between(this.insuranceDate, LocalDate.now());
		System.out.println(daylast);
		if (daylast < 365) {
			word = "Car insurance is normal\nleft " + (365-daylast) + " days.\n";
		}else {
			word = "Car insurance is bad\novertime " + (daylast-365) + " days.\n";
		}
		return word;
	}
	@JsonIgnore	
	public static Map<String,ArrayList<Car>> sort(ArrayList<Car> allcars) {
		ArrayList<Car> allcarscop = allcars;
		ArrayList<Car> stateCars = new ArrayList<Car> ();
		Map<String,ArrayList<Car>> UkraineCars = new HashMap<String,ArrayList<Car>>();
		for (int i = 0; i < allcarscop.size(); i++) {
			Car first = allcarscop.get(i);
			String state = first.state();
			stateCars.clear();
			for(int j = i; j < allcarscop.size(); j++) {
				if (state == allcarscop.get(j).state()) {
					stateCars.add(allcarscop.get(j));
					
					if (j != i) {
						allcarscop.remove(j);
						--j;
					}	
				}
			}
			UkraineCars.put(state, stateCars);
		}
		return UkraineCars;
	}
	
	@Override
	public String toString() {	
		return  "\nÑar insurance information:\n " + 
				"\nIns name: "+ insuranceName + 
				";\nYear of start: " + insuranceDate + 
				";\nPrice: " + insurancePrice + "$;\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((insuranceName == null) ? 0 : insuranceName.hashCode());
		result = prime * result + insurancePrice;
		result = prime * result + ((insuranceDate == null) ? 0 : insuranceDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insurance other = (Insurance) obj;
		if (insuranceName == null) {
			if (other.insuranceName != null)
				return false;
		} else if (!insuranceName.equals(other.insuranceName))
			return false;
		if (insurancePrice != other.insurancePrice)
			return false;
		if (insuranceDate == null) {
			if (other.insuranceDate != null)
				return false;
		} else if (!insuranceDate.equals(other.insuranceDate))
			return false;
		return true;
	}
	
	

}