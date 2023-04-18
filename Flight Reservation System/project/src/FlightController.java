import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/*
 * controller class
 */
public class FlightController {
	
	private FlightModel flightModel;
	private FlightView flightView;

	
    private Connection con;


	public FlightController(FlightModel m, FlightView v) {
		flightModel = m;
        flightView = v;
    }
	
	public FlightModel getFlightModel() {
		return this.flightModel;
	}

	public void setFlightModel(FlightModel flightModel) {
		this.flightModel = flightModel;
	}

	public FlightView getFlightView() {
		return this.flightView;
	}

	public void setFlightView(FlightView flightView) {
		this.flightView = flightView;
	}
	
	public Connection getConnectionToDatabase()
	{
		return this.con;
	}
	
	public void setConnectionToDatabase()
	{
		this.con = ConnectTo_Database.getConnection();
	}
	
	/*
	 * create flights table if not created and display flights on board
	 * then listen to events to handle
	 */
	public void initController() throws ClassNotFoundException, SQLException {
		setConnectionToDatabase();
		initDB();
		displayFlights();
		
		//Adding a Flight
		flightView.getAddFlightButton().addActionListener(e -> {
			try {
				//System.out.println("addFlight called");
			    boolean result = false;
			    FlightModel flight = getFlightInfo();
                if(flight.isComplete())
				       result = addFlight(flight);
                else 
                {
                	//flightView.displayResultMessage(message01);
                	flightView.displayMessage("message01");
                	return;
                }
				if(result)
				     displayFlights(); //update the flight board
				else {
                	flightView.displayMessage("message07");
                	return;
				}
					
			
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		// Sorting Flights
		flightView.getSortingFlights().addActionListener(e->{
			try {
				String str = String.valueOf(flightView.getSortingFlights().getSelectedItem());
				if(str.equals(""))
					return; //do not do anything, no field selected for sorting
						
				ArrayList<FlightModel> flights = sortingFlights();
				if(flights.size()>0)
				{
					//update flight board
			        clearBoard();
			        flightView.setFlightBoard(flights);
				}
				else
				{
                	flightView.displayMessage("message06");
					return;//no flights in database
				}
				
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		//Remove a Flight
		flightView.getRemoveFlightButton().addActionListener(e -> {
			try {
				
				String str = flightView.getFlightNumberTextField().getText();
				if(str.equals("")) {
                	flightView.displayMessage("message02");
			    	return;
				}
				boolean result = removeFlight(Integer.parseInt(str));
				if(result) {
					//update the flight board
					ArrayList<FlightModel> flights = getAllFlightsFromDB();
					clearBoard();
					flightView.setFlightBoard(flights);
				}
				else
				{
                	flightView.displayMessage("message03");
				}
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		//Change a Flight
		flightView.getChangeFlightButton().addActionListener(e -> {
			try {
				
				String id = flightView.getFlightNumberTextField().getText();

				if(id.equals("") || Integer.parseInt(id) <= 0) {
					//flight id is needed, fail operation
					flightView.displayMessage("message04");
					return;
				}
				
				HashMap<String,String> map = getChangeFlightInfo();
				if(map.size()<1)
				{
					//only Flight Number given so nothing to change
					flightView.displayMessage("message05");
					return;
				}
				
				int FlightNumber = Integer.parseInt(id);
				changeFlightInfo(map, FlightNumber);
				//update the flight board
				ArrayList<FlightModel> flights = getAllFlightsFromDB();
				clearBoard();
				flightView.setFlightBoard(flights);
		        
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//Clear Flight Form
		flightView.getClearFormButton().addActionListener(e -> clearFormData());
		
		//Change Language
		flightView.getLanguageButton().addActionListener(e -> {
			try 
			{
				changeLanguage();
		    	//JOptionPane.showMessageDialog(null, "Language changed" ,"Info", JOptionPane.INFORMATION_MESSAGE);

			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});	
		
		//Exit Program
		flightView.getQuitButton().addActionListener(e -> quit());
    }
	
	

	public void initDB() throws SQLException {
		
	      Statement stmt = null;
	      
          stmt = con.createStatement();
	      String sql = "CREATE TABLE IF NOT EXISTS FLIGHTS" +
	                        "(FlightNumber INT PRIMARY KEY   NOT NULL," +
	                        " FlightName           TEXT    NOT NULL, " + 
	                        " FlightOrigin         TEXT    NOT NULL, " + 
	                        " FlightDestination    TEXT    NOT NULL, " + 
	                        " FlightDuration       INT     NOT NULL, " +
	                        " TotalSeats           INT     NOT NULL," +
	                        " FlightCost           INT     NOT NULL);";
	         
	      stmt.executeUpdate(sql);
	      stmt.close();
	}
	
	/*
	 * Adding a flight, successful if all flight's data are given
	 */
	public boolean addFlight(FlightModel flight) throws SQLException {
		
		String flightNumber = Integer.toString(flight.getFlightNumber());
		String flightName = flight.getFlightName();
		String flightOrigin = flight.getFlightOrigin();
		String flightDestination = flight.getFlightDestination();
		String flightDuration = Integer.toString(flight.getFlightDuration());
		String totalSeats = Integer.toString(flight.getTotalSeats());
		String flightCost = Integer.toString(flight.getFlightCost());
		
		ArrayList<FlightModel> flights = getAllFlightsFromDB();
		
		for ( FlightModel f : flights ) 
		{
			if(f.getFlightNumber() == Integer.parseInt(flightNumber))
			{
				return false; // flight already exist
			}
		}

		
		Statement stmt = null;
	         
	         stmt = con.createStatement();
	         
	         String sql = "INSERT INTO FLIGHTS (FlightNumber,FlightName,FlightOrigin,FlightDestination,FlightDuration,TotalSeats,FlightCost)" +
	                   "VALUES ( " + flightNumber + ",\"" + flightName + "\"," + "\""+ flightOrigin + "\"," + "\""+ flightDestination +"\"," +
	                		   flightDuration +"," + totalSeats + "," + flightCost + ");";
		     // System.out.println("sql = " + sql);

	         stmt.executeUpdate(sql);	         

	         stmt.close();
	    
        return true;
	}
	
	public FlightModel getFlightInfo()
	{
		FlightModel fm = new FlightModel();
		
		String str = getFlightView().getFlightNumberTextField().getText();
		if(str.equals("")) {
	    	return fm;
		}
		
		int flightNumber = Integer.parseInt(str);
		fm.setFlightNumber(flightNumber);
		
		String flightName = getFlightView().getFlightNameTextField().getText();
		if(flightName.equals("")) {
	    	return fm;
		}
		fm.setFlightName(flightName);
		
		String flightOrigin = getFlightView().getFlightOriginTextField().getText();
		if(flightOrigin.equals("")) {
	    	return fm;
		}
		fm.setFlightOrigin(flightOrigin);
		
		String flightDestination = getFlightView().getFlightDestinationTextField().getText();
		if(flightDestination.equals("")) {
	    	return fm;
		}
		fm.setFlightDestination(flightDestination);
		
		str = getFlightView().getFlightDurationTextField().getText();
		if(str.equals("")) {
	    	return fm;
		}
		int flightDuration = Integer.parseInt(str);
		fm.setFlightDuration(flightDuration);
		
		str = getFlightView().getTotalSeatsTextField().getText();
		if(str.equals("")) {
	    	return fm;
		}
		int totalSeats = Integer.parseInt(str);
		fm.setTotalSeats(totalSeats);
		
		str = getFlightView().getFlightCostTextField().getText();
		if(str.equals("")) {
	    	return fm;
		}
		int flightCost = Integer.parseInt(str);
		fm.setFlightCost(flightCost);
		
		return fm;
	}
	
	
	public 	HashMap<String,String> getChangeFlightInfo()
	{
		HashMap<String,String> map = new HashMap<String,String>();  

		String str = flightView.getFlightNameTextField().getText();
		if(!str.equals("")) {
			map.put("FlightName", str);
		}
				
		str = flightView.getFlightOriginTextField().getText();
		if(!str.equals("")) {
			map.put("FlightOrigin", str);
		}
		
		str = flightView.getFlightDestinationTextField().getText();
		if(!str.equals("")) {
			map.put("FlightDestination", str);
		}
		
		str = flightView.getFlightDurationTextField().getText();
		if(!str.equals("")) {
			map.put("FlightDuration", str);
		}
		
		str = flightView.getTotalSeatsTextField().getText();
		if(!str.equals("")) {
			map.put("TotalSeats", str);
		}
		
		str = flightView.getFlightCostTextField().getText();
		if(!str.equals("")) {
			map.put("FlightCost", str);
		}

		return map;
	}
	

	public void changeFlightInfo(HashMap<String,String> map, int id) throws SQLException {
		
				
		//build the query
		String query = "";
		int i = map.size();
		
		for(Map.Entry<String,String> m : map.entrySet()){
			query = query + m.getKey() + " = ? " ;
			if(i != 1)
				query = query + ", ";
			--i;
		}  
		
		query = "UPDATE FLIGHTS SET " + query + " WHERE FlightNumber = ?";
		
		//System.out.println(query);
		
		PreparedStatement statement = con.prepareStatement(query);
		
		i = 1;
		for(Map.Entry<String,String> m : map.entrySet()){
			if(m.getKey().equals("FlightName"))
				statement.setString(i, m.getValue());
			else if(m.getKey().equals("FlightOrigin"))
				statement.setString(i, m.getValue());
			else if(m.getKey().equals("FlightDestination"))
				statement.setString(i, m.getValue());
			else if(m.getKey().equals("FlightDuration"))
				statement.setInt(i, Integer.parseInt(m.getValue()));
			else if(m.getKey().equals("TotalSeats"))
				statement.setInt(i, Integer.parseInt(m.getValue()));
			else if(m.getKey().equals("FlightCost"))
				statement.setInt(i, Integer.parseInt(m.getValue()));
			
			i++;
		} 
		ResultSet rs = statement.getGeneratedKeys();
		System.out.println(rs.toString());
		// add the FlightNumber as answer to "?" in WHERE part
		statement.setInt(i, id);	
		
		//System.out.println("statement = " + statement.toString());
		statement.executeUpdate();
		
		statement.close();		
	}
	
	public boolean removeFlight(int flightNumber) throws SQLException
	{
				
		Statement stmt = con.createStatement();
        
        String sql = "DELETE FROM FLIGHTS WHERE FlightNumber = " + flightNumber;
	    //System.out.println("sql = " + sql);

        stmt.executeUpdate(sql);	         

        stmt.close();
        
        return true;
        
	}
	
	private ArrayList<FlightModel> sortingFlights() throws SQLException
	{
		int index = flightView.getSortingFlights().getSelectedIndex();
		//System.out.println("sorting flight for " + str);
		ArrayList<FlightModel> flights = new ArrayList<FlightModel>();
		if (index == 0)
		{
			// no sorting field selected, do nothing
			return flights;
		}
		
		//get flights from database
        flights = getAllFlightsFromDB();
        if(flights.size()< 1)
        {
        	return flights;
        }
		
        //sort flights
        SortFlights object = new SortFlights(index);
        Collections.sort(flights, object);
        
        return flights;

	}
	
    
    /*
     * Returns all the existing flights in database
     */
    public ArrayList<FlightModel> getAllFlightsFromDB() throws SQLException
    {
    	Statement stmt = con.createStatement();

        String sql = "SELECT * FROM FLIGHTS";
        
        ResultSet rs = stmt.executeQuery(sql);
    	
    	ArrayList<FlightModel> flights = new ArrayList<FlightModel>();

        try {
        
            while(rs.next()) {
            	
            	FlightModel flight = new FlightModel();
            	flight.setFlightNumber(rs.getInt("FlightNumber"));
            	flight.setFlightName(rs.getString("FlightName"));
            	flight.setFlightOrigin(rs.getString("FlightOrigin"));
            	flight.setFlightDestination(rs.getString("FlightDestination"));
            	flight.setFlightDuration(rs.getInt("FlightDuration"));
            	flight.setTotalSeats(rs.getInt("TotalSeats"));
            	flight.setFlightCost(rs.getInt("FlightCost"));
            	flights.add(flight);
            }
    
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                
        }
        return flights;
    }
    
    private void clearFormData() 
    {
    	
    	flightView.setFlightNumberTextField("");
    	flightView.setFlightNameTextField("");
    	flightView.setFlightOriginTextField("");
    	flightView.setFlightDestinationTextField("");
    	flightView.setFlightDurationTextField("");
    	flightView.setTotalSeatsTextField("");
    	flightView.setFlightCostTextField("");
    }
    
    private void changeLanguage() throws ClassNotFoundException, SQLException 
    {
		//System.out.println("changeLanguage");
    	
    	Locale enLocale = new Locale("en", "US");  
        
    	//System.out.println("before Default Local is <" + Locale.getDefault() + ">");

    	//if(getLanguage().equals("EN"))
    	if(Locale.getDefault().equals(enLocale))
        	flightView.setLocale("FR");
        else
        	flightView.setLocale("EN");
        
        flightView.setTableHeaderLang();
        flightView.setLabelsLang();
        flightView.setButtonsLang();
        flightView.setJComboBoxLang();
        flightView.setJOptionPaneDialogueBoxLang();

    	//System.out.println("after Default Local is <" + Locale.getDefault() + ">");

   	}
	
	private void quit() {
		System.exit(0);
	}
	
	private void  displayFlights() throws ClassNotFoundException, SQLException {
		
		

		Statement stmt = con.createStatement();

        // default sql statement(if no attributes are specified
        String sql = "SELECT * FROM FLIGHTS";
        
        ResultSet rs = stmt.executeQuery(sql);
    	
    	ArrayList<FlightModel> flights = new ArrayList<FlightModel>();

        try {
        
            while(rs.next()) {
            	
            	FlightModel flight = new FlightModel();
            	flight.setFlightNumber(rs.getInt("FlightNumber"));
            	flight.setFlightName(rs.getString("FlightName"));
            	flight.setFlightOrigin(rs.getString("FlightOrigin"));
            	flight.setFlightDestination(rs.getString("FlightDestination"));
            	flight.setFlightDuration(rs.getInt("FlightDuration"));
            	flight.setTotalSeats(rs.getInt("TotalSeats"));
            	flight.setFlightCost(rs.getInt("FlightCost"));
            	flights.add(flight);
            }
    
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                
        }
        
        flightView.setFlightBoard(flights);
        
		stmt.close();

       
	}
	
	public void clearBoard ()
	{
		flightView.clearFlightsBoard();
	}
	
}
