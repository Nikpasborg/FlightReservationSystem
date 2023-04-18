import java.util.Comparator;

public class SortFlights  implements Comparator<FlightModel> {
	  
	public int index;
	
	public SortFlights(int index){
		this.index = index;
	}
	  
	    // Static method
	    // Static method to create instance of Singleton class
		
		@Override
		public int compare(FlightModel f1, FlightModel f2) {
			
			int result = 0;
			if(index == 1)
				  result = f1.getFlightNumber() - f2.getFlightNumber();
			else if(index == 2)
			      result = f1.getFlightName().compareTo(f2.getFlightName());
			else if(index == 3)
			      result = f1.getFlightOrigin().compareTo(f2.getFlightOrigin());
			else if(index == 4)
			      result = f1.getFlightDestination().compareTo(f2.getFlightDestination());
			else if(index == 5)
			      result = f1.getFlightDuration() - f2.getFlightDuration();
			else if(index == 6)
			      result = f1.getTotalSeats() - f2.getTotalSeats();
			else if(index == 7)
			      result = f1.getFlightCost() - f2.getFlightCost();
			
			return result;
			   
		}
		
		public int getIndex() {
			return this.index;
		}
		
		public void setIndex(int index) {
			this.index = index;
		}
		
		
}
