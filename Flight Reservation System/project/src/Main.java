import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// Assemble all the pieces of MVC
		FlightModel model = new FlightModel();
		FlightView view = new FlightView("Flight Reservation System");
		FlightController controller = new FlightController(model, view);
		controller.initController();
 }
}
