package models;

import java.time.LocalDate;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;



public class Human {
	private String humanName;
	private String humanSecondName;
	@JsonSerialize(using =dateSerializer. JsonLocDateSer.class)
	@JsonDeserialize(using = dateSerializer.JsonLocDateDes.class)
	private LocalDate humanBirthd;
	private String humanAddres;
	
	public Human(String name, String secondName, LocalDate birthd, String addres ) {
		setHumanName(name);
		setHumanSecondName(secondName);
		setHumanBirthd(birthd);
		setHumanAddres(addres);	
	}
	
	public Human() {
		humanName = null;
		humanSecondName = null;
		humanBirthd = null;
		humanAddres = null;
	}
	

	public String getHumanName() {
		return humanName;
	}

	public void setHumanName(String name) {
		humanName = name;
	}

	public String getHumanSecondName() {
		return humanSecondName;
	}

	public void setHumanSecondName(String secondName) {
		humanSecondName = secondName;
	}
	
	@JsonIgnore
	public LocalDate getHumanBirthd() {
		return humanBirthd;
	}

	public void setHumanBirthd(LocalDate birthd) {
		humanBirthd = birthd;
	}

	public String getHumanAddres() {
		return humanAddres;
	}

	public void setHumanAddres(String addres) {
		humanAddres = addres;
	}
	
	@Override
	public String toString() {
		return "Holder iformation:\n" + 
				"\nName: " + humanName + 
				";\nLast name: " + humanSecondName + 
				";\nDate of birthday: " + humanBirthd + 
				";\nAddres: " + humanAddres + ";\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((humanAddres == null) ? 0 : humanAddres.hashCode());
		result = prime * result + ((humanBirthd == null) ? 0 : humanBirthd.hashCode());
		result = prime * result + ((humanName == null) ? 0 : humanName.hashCode());
		result = prime * result + ((humanSecondName == null) ? 0 : humanSecondName.hashCode());
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
		Human other = (Human) obj;
		if (humanAddres == null) {
			if (other.humanAddres != null)
				return false;
		} else if (!humanAddres.equals(other.humanAddres))
			return false;
		if (humanBirthd == null) {
			if (other.humanBirthd != null)
				return false;
		} else if (!humanBirthd.equals(other.humanBirthd))
			return false;
		if (humanName == null) {
			if (other.humanName != null)
				return false;
		} else if (!humanName.equals(other.humanName))
			return false;
		if (humanSecondName == null) {
			if (other.humanSecondName != null)
				return false;
		} else if (!humanSecondName.equals(other.humanSecondName))
			return false;
		return true;
	}
	
}
