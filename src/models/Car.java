package models;

	public class Car{
		private String carNumber;
		private String carName;
		private String carModel;
		private String carColor;
		private Human human = new Human();
		private Insurance insurance = new Insurance();
				
		public Car(String number, String name, String model, String color, Human human, Insurance insurance ) {
			setCarNumber(number);
			setCarName(name);
			setCarModel(model);
			setCarColor(color);
			setHuman(human);
			setInsurance(insurance);
		}
		
		public Car() {
			carNumber = null;
			carName = null;
			carModel = null;
			carColor = null;
			setHuman(null);
			setInsurance(null);
		}


		public String getCarNumber() {
			return carNumber;
		}

		public void setCarNumber(String number) {
			carNumber = number;
		}

		public String getCarName() {
			return carName;
		}

		public void setCarName(String name) {
			carName = name;
		}

		public String getCarModel() {
			return carModel;
		}

		public void setCarModel(String model) {
			carModel = model;
		}

		public String getCarColor() {
			return carColor;
		}

		public void setCarColor(String color) {
			carColor = color;
		}
		
		public Human getHuman() {
			return human;
		}

		public void setHuman(Human human) {
			this.human = human;
		}
		
		public Insurance getInsurance() {
			return insurance;
		}

		public void setInsurance(Insurance insurance) {
			this.insurance = insurance;
		}
		
		public String state() {
			char[] state = this.carNumber.toCharArray();
			switch(state[0]) {
				case('A'):{
					switch(state[1]) {
					case('A'):
						return "Kyiv";
					case('B'):
						return "Vinetsya";
					case('C'):
						return "Volynsk";
					case('E'):
						return "Dnipro";
					case('H'):
						return "Donetsk";
					case('M'):
						return "Gitomer";
					case('O'):
						return "Zakarpatskaya";
					case('P'):
						return "Zaporizska";
					case('T'):
						return "Ivano-Frankivsk";
					case('I'):
						return "Kivska";
					case('X'):
						return "Kharkivska";
					}
				}
				case('B'):{
					switch(state[1]) {
					case('A'):
						return "Kirovograd";
					case('B'):
						return "Lygansk";
					case('C'):
						return "Lviv";
					case('E'):
						return "Mikolayiv";
					case('H'):
						return "Odesa";
					case('M'):
						return "Symu";
					case('O'):
						return "Ternopil";
					case('K'):
						return "Rivne";
					case('T'):
						return "Herson";
					case('I'):
						return "Poltava";
					case('X'):
						return "Khmelnitskiy";
					}
				}
				case('C'):{
					switch(state[1]) {
					case('A'):
						return "Cherkasu";
					case('B'):
						return "Chernihiv";
					case('E'):
						return "Chernivtsi";
					case('H'):
						return "Sevastopol";
					}
				}
			}
			return "";
		}

		@Override
		public String toString() {
			return 	human +
					"\nCar information:\n " + 
					"\nNumber: " + carNumber + 
					";\nCar name: " + carName + 
					";\nModel: " + carModel +
					";\nColor: " + carColor + 
					";\n" + insurance ;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((carColor == null) ? 0 : carColor.hashCode());
			result = prime * result + ((carModel == null) ? 0 : carModel.hashCode());
			result = prime * result + ((carName == null) ? 0 : carName.hashCode());
			result = prime * result + ((carNumber == null) ? 0 : carNumber.hashCode());
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
			Car other = (Car) obj;
			if (carColor == null) {
				if (other.carColor != null)
					return false;
			} else if (!carColor.equals(other.carColor))
				return false;
			if (carModel == null) {
				if (other.carModel != null)
					return false;
			} else if (!carModel.equals(other.carModel))
				return false;
			if (carName == null) {
				if (other.carName != null)
					return false;
			} else if (!carName.equals(other.carName))
				return false;
			if (carNumber == null) {
				if (other.carNumber != null)
					return false;
			} else if (!carNumber.equals(other.carNumber))
				return false;
			return true;
		}

			
	
	}


