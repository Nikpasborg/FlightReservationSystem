

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;


public class ProjectTests {
	
	private FlightModel f1;
	private FlightModel f2;
	private FlightModel f3;
	private FlightView v;
	private FlightModel m;


	private FlightController fc = new FlightController( m, v);	
   

	ProjectTests() throws SQLException
	{

		initialize();
		fc.setConnectionToDatabase();
		fc.initDB();
	}	

	public void initialize()

	{
		
		
		//create three flights
		f1 = new FlightModel(300, "Airbus A220");
		f1.setFlightCost(235);
		f1.setFlightOrigin("Montreal");
		f1.setFlightDestination("Toronto");
		f1.setFlightDuration(55);
		f1.setTotalSeats(180);
		
		f2 = new FlightModel(415, "Douglas DC-8");
		f2.setFlightCost(185);
		f2.setFlightOrigin("Ottawa");
		f2.setFlightDestination("Montreal");
		f2.setFlightDuration(20);
		f2.setTotalSeats(120);
		
		f3 = new FlightModel(7769, "Boeing 737");
		f3.setFlightCost(310);
		f3.setFlightOrigin("Vancouver");
		f3.setFlightDestination("Calgary");
		f3.setFlightDuration(115);
		f3.setTotalSeats(220);
		
	}
	
	public void deleteAllFlights() throws SQLException {
		
	    Statement stmt = null;
	      
        stmt = fc.getConnectionToDatabase().createStatement();
	    String sql = "DELETE FROM FLIGHTS;";
	                        
	    stmt.executeUpdate(sql);
	    stmt.close();
	}


@Test
void addSortingByFlightNumberTest() {
	
	ArrayList<FlightModel> flights = new ArrayList<FlightModel>();
	flights.add(f3);
	flights.add(f2);
	flights.add(f1);
	
    assertTrue(flights.get(0).getFlightNumber() == f3.getFlightNumber());
    assertTrue(flights.get(1).getFlightNumber() == f2.getFlightNumber());
    assertTrue(flights.get(2).getFlightNumber() == f1.getFlightNumber()); 
	
	
	//sort by Flight Number	
	SortFlights object = new SortFlights(1);
    Collections.sort(flights, object);
    assertTrue(flights.get(0).getFlightNumber() == f1.getFlightNumber());
    assertTrue(flights.get(1).getFlightNumber() == f2.getFlightNumber());
    assertTrue(flights.get(2).getFlightNumber() == f3.getFlightNumber());  

}

@Test
void addSortingByFlightNameTest() {
	
	ArrayList<FlightModel> flights = new ArrayList<FlightModel>();
	flights.add(f2);
	flights.add(f3);
	flights.add(f1);
	
	assertEquals(flights.get(0).getFlightName(), f2.getFlightName());
    assertEquals(flights.get(1).getFlightName(), f3.getFlightName());
    assertEquals(flights.get(2).getFlightName(), f1.getFlightName());
	
	//sort by Flight Name		
	SortFlights object = new SortFlights(2);
    Collections.sort(flights, object);
    
    assertEquals(flights.get(0).getFlightName(), f1.getFlightName());
    assertEquals(flights.get(1).getFlightName(), f3.getFlightName());
    assertEquals(flights.get(2).getFlightName(), f2.getFlightName());  

}

@Test
void addSortingByFlightOriginTest() {
	
	ArrayList<FlightModel> flights = new ArrayList<FlightModel>();
	flights.add(f3);
	flights.add(f2);
	flights.add(f1);
	
	assertEquals(flights.get(0).getFlightOrigin(), f3.getFlightOrigin());
    assertEquals(flights.get(1).getFlightOrigin(), f2.getFlightOrigin());
    assertEquals(flights.get(2).getFlightOrigin(), f1.getFlightOrigin());
	
	//sort by Flight Name		
	SortFlights object = new SortFlights(3);
    Collections.sort(flights, object);
    
    assertEquals(flights.get(0).getFlightOrigin(), f1.getFlightOrigin());
    assertEquals(flights.get(1).getFlightOrigin(), f2.getFlightOrigin());
    assertEquals(flights.get(2).getFlightOrigin(), f3.getFlightOrigin());  

}

@Test
void addSortingByFlightDestinationTest() {
	
	ArrayList<FlightModel> flights = new ArrayList<FlightModel>();
	flights.add(f1);
	flights.add(f2);
	flights.add(f3);
	
	assertEquals(flights.get(0).getFlightDestination(), f1.getFlightDestination());
	assertEquals(flights.get(1).getFlightDestination(), f2.getFlightDestination());
	assertEquals(flights.get(2).getFlightDestination(), f3.getFlightDestination());
	
	//sort by Flight Name		
	SortFlights object = new SortFlights(4);
    Collections.sort(flights, object);
    
	assertEquals(flights.get(0).getFlightDestination(), f3.getFlightDestination());
	assertEquals(flights.get(1).getFlightDestination(), f2.getFlightDestination());
	assertEquals(flights.get(2).getFlightDestination(), f1.getFlightDestination());

}

@Test
void addSortingByFlightDurationTest() {
	
	ArrayList<FlightModel> flights = new ArrayList<FlightModel>();
	flights.add(f3);
	flights.add(f2);
	flights.add(f1);
	
	assertEquals(flights.get(0).getFlightDuration(), f3.getFlightDuration());
	assertEquals(flights.get(1).getFlightDuration(), f2.getFlightDuration());
	assertEquals(flights.get(2).getFlightDuration(), f1.getFlightDuration());
	
	//sort by Flight Name		
	SortFlights object = new SortFlights(5);
    Collections.sort(flights, object);
    
	assertEquals(flights.get(0).getFlightDuration(), f2.getFlightDuration());
	assertEquals(flights.get(1).getFlightDuration(), f1.getFlightDuration());
	assertEquals(flights.get(2).getFlightDuration(), f3.getFlightDuration());

}

@Test
void addSortingByFlightSeatsTest() {
	
	ArrayList<FlightModel> flights = new ArrayList<FlightModel>();
	flights.add(f1);
	flights.add(f2);
	flights.add(f3);
	
	assertEquals(flights.get(0).getTotalSeats(), f1.getTotalSeats());
	assertEquals(flights.get(1).getTotalSeats(), f2.getTotalSeats());
	assertEquals(flights.get(2).getTotalSeats(), f3.getTotalSeats());
	
	//sort by Flight Name		
	SortFlights object = new SortFlights(5);
    Collections.sort(flights, object);
    
	assertEquals(flights.get(0).getTotalSeats(), f2.getTotalSeats());
	assertEquals(flights.get(1).getTotalSeats(), f1.getTotalSeats());
	assertEquals(flights.get(2).getTotalSeats(), f3.getTotalSeats());

}

@Test
void addSortingByFlightCostTest() {
	
	ArrayList<FlightModel> flights = new ArrayList<FlightModel>();
	flights.add(f3);
	flights.add(f1);
	flights.add(f2);
	
	assertEquals(flights.get(0).getFlightCost(), f3.getFlightCost());
	assertEquals(flights.get(1).getFlightCost(), f1.getFlightCost());
	assertEquals(flights.get(2).getFlightCost(), f2.getFlightCost());
	
	//sort by Flight Name		
	SortFlights object = new SortFlights(5);
    Collections.sort(flights, object);
    
	assertEquals(flights.get(0).getFlightCost(), f2.getFlightCost());
	assertEquals(flights.get(1).getFlightCost(), f1.getFlightCost());
	assertEquals(flights.get(2).getFlightCost(), f3.getFlightCost());

}
@Test
void AddFlightExistTest() throws SQLException {
	
	//make sure flight does not exist
	assertEquals(fc.removeFlight(f1.getFlightNumber()),true);
	
	//add flight first time should be successful
	assertTrue(fc.addFlight(f1) == true);
	
	//add flight second time should fail
	assertTrue(fc.addFlight(f1) == false);	
	
}

@Test
void RemoveFlightExistTest() throws SQLException {
	
	//make sure flight does not exist
	assertEquals(fc.removeFlight(f1.getFlightNumber()),true);
	
	//add flight first time should be successful
	assertTrue(fc.addFlight(f1) == true);
	
	//remove flight
	assertEquals(fc.removeFlight(f1.getFlightNumber()),true);
	
	//add flight again should be successful since was removed before
	assertTrue(fc.addFlight(f1) == true);
	
}

@Test
void ChangeFlightExistTest() throws SQLException {
	
    //delete all flights to have a clean table
	deleteAllFlights();
	
	//add f1, f2 and f3 to FLIGHTS table
	fc.addFlight(f1);
	fc.addFlight(f2);
	fc.addFlight(f3);
	
	ArrayList<FlightModel> flights = fc.getAllFlightsFromDB();
	
	assertEquals(flights.size(),3);
		    
	String originalFlightName = f1.getFlightName();
	String newName = "AC300";

	HashMap<String,String> flightInfoMap = new HashMap<String,String>();	
	flightInfoMap.put("FlightName", newName);
	
	int flightNumber = f1.getFlightNumber();
	fc.changeFlightInfo(flightInfoMap, flightNumber);
	
    flights = fc.getAllFlightsFromDB();
    
	assertEquals(flights.get(0).getFlightNumber(),flightNumber);
	assertEquals(flights.get(0).getFlightName(),newName);
	
	//restore f1
	
	flightInfoMap.replace("FlightName",originalFlightName);

//	for(Map.Entry<String,String> m : flightInfoMap.entrySet()){
//    	System.out.println(m.getKey() + " -> " + m.getValue());
//	}  

	fc.changeFlightInfo(flightInfoMap, flightNumber);
	
    flights = fc.getAllFlightsFromDB();
	assertEquals(flights.get(0).getFlightNumber(),flightNumber);
	assertEquals(flights.get(0).getFlightName(),originalFlightName);
	
}



}




