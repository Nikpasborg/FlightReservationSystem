/*
 *  Model class which manages data and logic
 */
public class FlightModel {
	
	private int flightNumber;
	private String flightName;
	private String flightOrigin;
	private String flightDestination;
	private int flightDuration;
	private int totalSeats;
	private int flightCost;
	
	public FlightModel(int flightNumber, String flightName) {
		this.flightNumber = flightNumber;
		this.flightName = flightName;
	}
	
	public FlightModel() {
		
	}
	

	public int getFlightNumber(){
		return flightNumber;
	}
	
	public final void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public final String getFlightName() {
		return flightName;
	}

	public final void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public final String getFlightOrigin() {
		return flightOrigin;
	}

	public final void setFlightOrigin(String flightOrigin) {
		this.flightOrigin = flightOrigin;
	}

	public final String getFlightDestination() {
		return flightDestination;
	}

	public final void setFlightDestination(String flightDestination) {
		this.flightDestination = flightDestination;
	}

	public final int getFlightDuration() {
		return flightDuration;
	}

	public final void setFlightDuration(int flightDuration) {
		this.flightDuration = flightDuration;
	}

	public final int getTotalSeats() {
		return totalSeats;
	}

	public final void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public final int getFlightCost() {
		return flightCost;
	}

	public final void setFlightCost(int flightCost) {
		this.flightCost = flightCost;
	}
	
	public boolean isComplete()
	{
		if(getFlightNumber()<1)
			return false;
		
		if(getFlightCost()<1)
			return false;
		
        if(getTotalSeats()<1)
        	return false;
        
        if(getFlightCost()<1)
        	return false;

        if(getFlightOrigin().equals(""))
        	return false;
        
        if(getFlightDestination().equals(""))
        	return false;
        if(getFlightName().equals(""))
        	return false;
        
		return true;
	}
	
	
	@Override
	public String toString() {
		return "["+ flightNumber + ", "+ flightName + ", "+ flightOrigin + " to " + flightDestination + ", Duration = " + flightDuration
				+ ", Free Seats=" + totalSeats + ", " + flightCost + "$" + "]";
	}

}