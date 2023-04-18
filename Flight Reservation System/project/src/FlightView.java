import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

/*
 * view class which display the information to user
 */
public class FlightView {
	
	// Using Swing framework to view GUI
	private JFrame frame;
	private JLabel flightNumberLabel, flightNameLabel,flightOriginLabel, flightDestinationLabel;
	private JLabel flightDurationLabel, totalSeatsLabel, flightCostLabel, sortFlightsLabel;
	private JTextField flightNumberTextField, flightNameTextField, flightOriginTextField, flightDestinationTextField;
	private JTextField flightDurationTextField, totalSeatsTextField,flightCostTextField;
	private JButton addFlightButton, quitButton;
	private JButton removeFlightButton;
	private JButton changeFlightButton;
	private JButton displayFlightsButton;
	private JButton clearFormButton;
	private JButton languageButton;
	private JComboBox<String> sortingFlights;
	JTable table;
	
	Locale enLocale, frLocale;
    ResourceBundle bundle;


	
	private JTextArea ta;
	JScrollPane scrollPane;
	
	private static int width = 900;
	private static int height = 650;

	public FlightView(String title) {
		
		enLocale = new Locale("en", "US");  
        frLocale = new Locale("fr", "FR");
        
        Locale.setDefault(enLocale);
        bundle = ResourceBundle.getBundle("MessageBundle");

		
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		// Create UI elements
		flightNumberLabel = new JLabel("Flight Number:");
		flightNameLabel = new JLabel("Flight Name:");
		flightOriginLabel = new JLabel("Origin:");
		flightDestinationLabel = new JLabel("Destination:");
		flightDurationLabel = new JLabel("Duration (min):");
		totalSeatsLabel = new JLabel("Total Seats:");
		flightCostLabel = new JLabel("Cost ($):");
		sortFlightsLabel = new JLabel("Sort Flights by:");
		
		flightNumberTextField = new JTextField();
		flightNameTextField = new JTextField();
		flightOriginTextField = new JTextField();
		flightDestinationTextField = new JTextField();
		flightDurationTextField = new JTextField();
		totalSeatsTextField = new JTextField();
		flightCostTextField = new JTextField();
		
		String[] columnNames = {"Flight Number",
                "Flight Name",
                "Flight Origin",
                "Flight Destination",
                "Flight Duration (min)",
                "Total Seats",
                "Flight Cost ($)"};
                				                

		//Object[][] data = {
		//			{"120", "AC320", "Montreal", "Toronto", "50","200", "260"},
		//			{"360", "AC330", "Toronto", "Montreal", "55","150", "220"}
		//
		//			  };

		String[][] data = new String[25][7];
		for(int i=0; i< data.length; i++)
		{
			data[i][0] = " ";
			data[i][1] = " ";
			data[i][2] = " ";
			data[i][3] = " ";
			data[i][4] = " ";
			data[i][5] = " ";
			data[i][6] = " ";

		}

		//System.out.println(javax.swing.UIManager.getDefaults().getFont("Label.font"));

        Color c1 = new Color(51, 255, 200); 
        Font headerFont = new Font("Dialog", Font.BOLD, 11);
        Font f = new Font("Dialog", Font.BOLD, 12);


		table = new JTable(data, columnNames);
		table.setBackground(c1);
		table.setFont(headerFont);
		
		JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.black);
	    header.setForeground(Color.yellow);
	    header.setFont(f);

        ta = new JTextArea("Flight Board"); 
        
        scrollPane = new JScrollPane(table);
        
        String[] sortingField =
			{
					"",
					"Flight Number",
					"Flight Name",
					"Flight Origin",
					"Flight Destination",
					"Flight Duration",
					"Total Seats",
					"Flight Cost"
			};


        sortingFlights = new JComboBox<String>(sortingField);
        
        
        
        MouseListener mouseListener = (MouseListener) new MouseAdapter() 
   	 {
   	     
   		 JButton button;
   		 Color c;
   		 @Override 
   		 public void mouseEntered(MouseEvent e) {
   	    	  
   	    	  button = (JButton) e.getSource();
   	    	  c = button.getBackground();
   	    	  button.setBackground(Color.YELLOW);
   	      }
   	      
   	      @Override
   	      public void mouseExited(MouseEvent e) {
   	    	  button = (JButton) e.getSource();
   	    	  button.setBackground(c);
   	  	  }
   	      
   	 };


		languageButton = new JButton("Change to FR");
		languageButton.addMouseListener(mouseListener);
		
		addFlightButton = new JButton("Add Flight");
		addFlightButton.addMouseListener(mouseListener);
		
		quitButton = new JButton("Exit");
		quitButton.addMouseListener(mouseListener);

		removeFlightButton = new JButton("Remove Flight");
		removeFlightButton.addMouseListener(mouseListener);

		changeFlightButton = new JButton("Change Flight");
		changeFlightButton.addMouseListener(mouseListener);

		
		displayFlightsButton = new JButton("Display Flights");
		clearFormButton = new JButton("Clear Input");
		clearFormButton.addMouseListener(mouseListener);

		
		JSeparator l1 = new JSeparator();
        JSeparator l2 = new JSeparator();
        
        l1.setOrientation(SwingConstants.HORIZONTAL);
        l2.setOrientation(SwingConstants.HORIZONTAL);
		
		 // Add UI element to frame
		GroupLayout layout = new GroupLayout(frame.getContentPane());
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			        .addComponent(scrollPane)
			        //.addComponent(passengerNameTextField)
			        .addGroup(layout.createSequentialGroup()
				        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				            		.addComponent(languageButton,130,130,130))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				            		.addComponent(quitButton,120,120,120)))
			        .addGroup(layout.createSequentialGroup()
				        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				        			.addComponent(addFlightButton,130,130,130))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(changeFlightButton,130,130,130))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					                .addComponent(removeFlightButton,130,130,130))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(clearFormButton,130,130,130))
				            .addGap(60)
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					                .addComponent(sortFlightsLabel,90,90,90))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(sortingFlights,120,120,120)))
			        
			        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			        				.addComponent(l1))
			        
			        			        
			        .addGroup(layout.createSequentialGroup()
				        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				        			.addComponent(flightNumberLabel,90,90,90))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(flightNumberTextField,90,90,90))
				            .addGap(40)
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					                .addComponent(flightOriginLabel,80,80,80))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(flightOriginTextField,120,120,120))
				            .addGap(40)
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					                .addComponent(flightDurationLabel,90,90,90))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(flightDurationTextField,60,60,60))
				            .addGap(40)
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					                .addComponent(totalSeatsLabel,80,80,80))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(totalSeatsTextField,40,40,40)))
			        
			        .addGroup(layout.createSequentialGroup()
				        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				        			.addComponent(flightNameLabel,90,90,90))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(flightNameTextField,90,90,90))
				            .addGap(40)
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					                .addComponent(flightDestinationLabel,80,80,80))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(flightDestinationTextField,120,120,120))
				            .addGap(40)
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					                .addComponent(flightCostLabel,90,90,90))
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(flightCostTextField,60,60,60)))
			        
				            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				            		.addComponent(l2))
				            
				 )

			);

			layout.setVerticalGroup(layout.createSequentialGroup()
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			        .addComponent(scrollPane))
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				        .addGroup(layout.createSequentialGroup()
				        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							        .addGap(60)
				        			.addComponent(languageButton)
				            		.addComponent(quitButton))))
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				        .addGroup(layout.createSequentialGroup()
				        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				        			.addComponent(addFlightButton)
				            		.addComponent(changeFlightButton)
				            		.addComponent(removeFlightButton)
				            		.addComponent(clearFormButton)
				            		.addComponent(sortFlightsLabel)
					                .addComponent(sortingFlights)))
		        		            .addGap(60))
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				        .addGroup(layout.createSequentialGroup()
				        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)

				            		.addComponent(l1)
				            		.addGap(20))))
				            
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			        .addGroup(layout.createSequentialGroup()

			            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			            		.addComponent(flightNumberLabel)
			            		.addComponent(flightNumberTextField)
			            		.addComponent(flightOriginLabel)
			            		.addComponent(flightOriginTextField)
			            		.addComponent(flightDurationLabel)
			            		.addComponent(flightDurationTextField)
			            		.addComponent(totalSeatsLabel)
			            		.addComponent(totalSeatsTextField))
			            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			            		.addComponent(flightNameLabel)
				                .addComponent(flightNameTextField)
				                .addComponent(flightDestinationLabel)
				                .addComponent(flightDestinationTextField)
				                .addComponent(flightCostLabel)
				                .addComponent(flightCostTextField)
				                .addGap(40))))
			    
			        
			        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					        .addGroup(layout.createSequentialGroup()
					        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)

					            		.addComponent(l2))))
			        
			        			        
			        
			        /*.addComponent(addFlightButton)*/);

        frame.getContentPane().setLayout(layout);
	}
 	
	public JFrame getFrame() {
		return frame;
    }
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
    public JLabel getFlightNumberLabel() {
        return flightNumberLabel;
    }
    
    public void setFlightNumberLabel(JLabel flightNumberLabel) {
        this.flightNumberLabel = flightNumberLabel;
    }
    
    public JLabel getFlightNameLabel() {
        return flightNameLabel;
    }
    
    public void setFlightNameLabel(JLabel flightNameLabel) {
        this.flightNameLabel = flightNameLabel;
    }

    public JLabel getFlightOriginLabel() {
        return flightOriginLabel;
    }
    
    public void setFlightOriginLabel(JLabel flightOriginLabel) {
        this.flightOriginLabel = flightOriginLabel;
    }

    public JLabel getFlightDestinationLabel() {
        return flightDestinationLabel;
    }
    
    public void setFlightDestinationLabel(JLabel flightDestinationLabel) {
        this.flightDestinationLabel = flightDestinationLabel;
    }
    
    public JLabel getFlightDurationLabel() {
        return flightDurationLabel;
    }
    
    public void setFlightDurationLabel(JLabel flightDurationLabel) {
        this.flightDurationLabel = flightDurationLabel;
    }

    public JLabel getTotalSeats() {
        return totalSeatsLabel;
    }
    
    public void setTotalSeats(JLabel totalSeatsLabel) {
        this.totalSeatsLabel = totalSeatsLabel;
    }

    public JLabel getFlightCostLabel() {
        return flightCostLabel;
    }
    
    public void setFlightCostLabel(JLabel flightCostLabel) {
        this.flightCostLabel = flightCostLabel;
    }

    public JTextField getFlightNumberTextField() {
        return flightNumberTextField;
    }
 
    public void setFlightNumberTextField(String str) {
        this.flightNumberTextField.setText(str); 
    }

    public JTextField getFlightNameTextField() {
        return flightNameTextField;
    }
 
    public void setFlightNameTextField(String str) {
        this.flightNameTextField.setText(str);
    }

    public JTextField getFlightOriginTextField() {
        return flightOriginTextField;
    }
 
    public void setFlightOriginTextField(String str) {
        this.flightOriginTextField.setText(str);
    }

    public JTextField getFlightDestinationTextField() {
        return flightDestinationTextField;
    }
 
    public void setFlightDestinationTextField(String str) {
        this.flightDestinationTextField.setText(str);
    }

    public JTextField getFlightDurationTextField() {
        return flightDurationTextField;
    }
 
    public void setFlightDurationTextField(String str) {
        this.flightDurationTextField.setText(str);
    }

    public JTextField getTotalSeatsTextField() {
        return totalSeatsTextField;
    }
 
    public void setTotalSeatsTextField(String str) {
        this.totalSeatsTextField.setText(str);
    }

    public JTextField getFlightCostTextField() {
        return flightCostTextField;
    }
 
    public void setFlightCostTextField(String str) {
        this.flightCostTextField.setText(str);
    }
    
    public JComboBox<String> getSortingFlights()
    {
        return sortingFlights;
    }
    
    public JButton getLanguageButton() {
        return languageButton;
    }
 
    public void setLanguageButton(JButton languageButton) {
        this.languageButton = languageButton;
    }
    
    public JButton getAddFlightButton() {
        return addFlightButton;
    }
 
    public void setAddFlightButton(JButton addFlightButton) {
        this.addFlightButton = addFlightButton;
    }   
 
    public JButton getRemoveFlightButton() {
        return removeFlightButton;
    }
 
    public void setRemoveFlightButtonButton(JButton removeFlightButton) {
        this.removeFlightButton = removeFlightButton;
    }
 
    public JButton getChangeFlightButton() {
        return changeFlightButton;
    }
 
    public void setChangeFlightButton(JButton changeFlightButton) {
        this.changeFlightButton = changeFlightButton;
    }
    
    public JButton getClearFormButton() {
        return clearFormButton;
    }
 
    public void setClearFormButton(JButton clearFormButton) {
        this.clearFormButton = clearFormButton;
    }
    
    public JButton getQuitButton() {
        return quitButton;
    }
 
    public void setQuitButton(JButton quitButton) {
        this.quitButton = quitButton;
    }
    
    public JTextArea getTeaxArea() {
        return ta;
    }
 
    public void setTextArea(JTextArea ta) {
        this.ta = ta;
    }
    
    public void setTable(String[][] data) {
        for(int i =0; i<data.length;i++)
        {
            table.setValueAt(data[i][0], i, 0);
            table.setValueAt(data[i][1], i, 1);
            table.setValueAt(data[i][2], i, 2);
            table.setValueAt(data[i][3], i, 3);
            table.setValueAt(data[i][4], i, 4);
            table.setValueAt(data[i][5], i, 5);
            table.setValueAt(data[i][6], i, 6);

        }
    }
    
    public void setFlightBoard(ArrayList<FlightModel> flights) {
    	
    	int j = 0;
    	for(FlightModel flight : flights)
        {
      	    
    		table.setValueAt(String.valueOf(flight.getFlightNumber()), j, 0);
    		table.setValueAt(flight.getFlightName(), j, 1);
    		table.setValueAt(flight.getFlightOrigin(), j, 2);
    		table.setValueAt(flight.getFlightDestination(), j, 3);
    		table.setValueAt(String.valueOf(flight.getFlightDuration()), j, 4);
    		table.setValueAt(String.valueOf(flight.getTotalSeats()), j, 5);
    		table.setValueAt(String.valueOf(flight.getFlightCost()), j, 6);   		
    		++j;
        }
      
    }
    
    public void clearFlightsBoard()
    {
    	int rows = table.getRowCount();
    	
    	for(int i=0; i< rows; i++)
		{
    		table.setValueAt(" ", i, 0);
    		table.setValueAt(" ", i, 1);
    		table.setValueAt(" ", i, 2);
    		table.setValueAt(" ", i, 3);
    		table.setValueAt(" ", i, 4);
    		table.setValueAt(" ", i, 5);
    		table.setValueAt(" ", i, 6);
		}
    }
       
    public void displayMessage(String message)
    {
    	//System.out.println("display message");
    	String msg = bundle.getString(message);
    	JOptionPane.showMessageDialog(null, msg ,"Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
        
    public void setJOptionPaneDialogueBoxLang() 
    {
    	UIManager.put("OptionPane.cancelButtonText", bundle.getString("cancel"));
    	UIManager.put("OptionPane.noButtonText", bundle.getString("no"));
    	UIManager.put("OptionPane.okButtonText", bundle.getString("ok"));
    	UIManager.put("OptionPane.yesButtonText", bundle.getString("yes"));
    }
    
    public void setTableHeaderLang()
    {
    	table.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("tfNumber"));
    	table.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("tfName"));
    	table.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("tfOrigin"));
    	table.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("tfDestination"));
    	table.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("tfDuration"));
    	table.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("ttSeats"));
    	table.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("tfCost"));
    	
	    table.getTableHeader().resizeAndRepaint();
    }
    
    public void setLabelsLang()
    {
    	flightNumberLabel.setText(bundle.getString("lfNumber"));
    	flightNameLabel.setText(bundle.getString("lfName"));
    	flightOriginLabel.setText(bundle.getString("lOrigin"));
    	flightDestinationLabel.setText(bundle.getString("lDestination"));
    	flightDurationLabel.setText(bundle.getString("lDuration"));
    	totalSeatsLabel.setText(bundle.getString("lfSeats"));
    	flightCostLabel.setText(bundle.getString("lCost"));
    	sortFlightsLabel.setText(bundle.getString("lSort"));
    }
    
    public void setButtonsLang()
    {
    	languageButton.setText(bundle.getString("buttonChangeLanguage"));
    	addFlightButton.setText(bundle.getString("buttonAdd"));
    	quitButton.setText(bundle.getString("buttonExit"));
    	removeFlightButton.setText(bundle.getString("buttonRemove"));
    	changeFlightButton.setText(bundle.getString("buttonChange"));
    	displayFlightsButton.setText(bundle.getString("buttonDisplay"));
    	clearFormButton.setText(bundle.getString("buttonClearForm")); 
    }
    
    public void setJComboBoxLang()
    {
    	
    	ActionListener[] listeners = sortingFlights.getActionListeners();
        for (int i = 0; i < listeners.length; i++)
            sortingFlights.removeActionListener(listeners[i]);
            
    	sortingFlights.removeAllItems();
        sortingFlights.addItem("");
        sortingFlights.addItem(bundle.getString("tfNumber"));
        sortingFlights.addItem(bundle.getString("tfName"));
        sortingFlights.addItem(bundle.getString("tfOrigin"));
        sortingFlights.addItem(bundle.getString("tfDestination"));
        sortingFlights.addItem(bundle.getString("tfDuration"));
        sortingFlights.addItem(bundle.getString("ttSeats"));
        sortingFlights.addItem(bundle.getString("tfCost"));
        // restore listeners
        for (int i = 0; i < listeners.length; i++)
            sortingFlights.addActionListener(listeners[i]);

    }
    
    public void setLocale( String language)
    {
    	if(language.equals("EN"))
    	{
            Locale.setDefault(enLocale);
            bundle = ResourceBundle.getBundle("MessageBundle");
    	}
    	else
    	{
            Locale.setDefault(frLocale);
            bundle = ResourceBundle.getBundle("MessageBundle");
    	}
    }

    
}
