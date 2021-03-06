    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    import java.io.*;
    import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
 
    
    public class Order
    {
    // window frame
            private JFrame frame; 
                     // radio buttons and button group
             private JRadioButton regularCrustButton;
             private JRadioButton thinCrustButton;
             private JRadioButton handCrustButton;
             private JRadioButton deepCrustButton;
             private ButtonGroup crustButtonGroup;

             // check boxes
             private JCheckBox pepperoniBox;
             private JCheckBox sausageBox;
             private JCheckBox cheeseBox;
             private JCheckBox pepperBox;
             private JCheckBox onionBox;
             private JCheckBox mushroomBox;
             private JCheckBox oliveBox;
             private JCheckBox anchovyBox;

             // text fields
             JTextField breadSticksText;
             private JTextField buffaloWingsText;
             JTextField nameText;
             private JTextField addressText;
             private JTextField cityText;
             
             private JPanel contentPane;


             public static void main (String[]args) 
            {
                Order gui = new Order();
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                gui.start();

            }
        
    public void start() 
    {
        frame = new JFrame("PizzaDB");

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        makeMenus(); 
        makeContent();

        frame.pack();
        frame.setVisible(true);
    }    
    private void makeMenus()
    {
        JMenuBar menuBar;
        
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        // set up menus
        menuBar.add(makeFileMenu());
        menuBar.add(makeHelpMenu());
    } 
    private JMenu makeFileMenu()
    {
        JMenu menu;
        JMenuItem menuItem;        // set up the File menu
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        
        // add New menu item
        menuItem = new JMenuItem("New Order");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.addActionListener(new NewListener());
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N,
                                       Event.CTRL_MASK));
        menu.add(menuItem);
          
        // add Save menu item
        menuItem = new JMenuItem("Save Order");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.addActionListener(new SaveListener());
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                       Event.CTRL_MASK));
        menu.add(menuItem);        // add Exit menu item
        menu.addSeparator();
        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic(KeyEvent.VK_X);
        menuItem.addActionListener(new ExitListener());
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                                       Event.CTRL_MASK));
        menu.add(menuItem);
          
        return menu;
    }    
    private JMenu makeHelpMenu()
    {
        JMenu menu;
        JMenuItem menuItem;        // set up the Help menu
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        
        // add About menu item
        menuItem = new JMenuItem("About GUI Pizza");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.addActionListener(new AboutListener());
        menu.add(menuItem);
          
        return menu;
    }
    private void makeContent()
    {
        contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(6,6));
        contentPane.setBorder(BorderFactory.createEmptyBorder(6,6,6,6)); //added from example
        makeNorthRegion();
        makeWestRegion();
        makeCenterRegion();
        makeEastRegion();
        makeSouthRegion();
        
    }    
    private void makeNorthRegion()
    {
        JLabel imgLabel = new JLabel(new ImageIcon("pizza.jpg"), JLabel.CENTER);
        contentPane.add(imgLabel, BorderLayout.NORTH);
    }
         private void makeWestRegion()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Choose a Crust"));
        crustButtonGroup = new ButtonGroup();
        regularCrustButton = new JRadioButton("Regular Crust", true);
        crustButtonGroup.add(regularCrustButton);
        panel.add(regularCrustButton);
        thinCrustButton = new JRadioButton("Thin Crust", false);
        crustButtonGroup.add(thinCrustButton);
        panel.add(thinCrustButton);
        handCrustButton = new JRadioButton("Hand-Tossed Crust", false);
        crustButtonGroup.add(handCrustButton);
        panel.add(handCrustButton);
        deepCrustButton = new JRadioButton("Deep-Dish Crust", false);
        crustButtonGroup.add(deepCrustButton);
        panel.add(deepCrustButton);
        
        contentPane.add(panel, BorderLayout.WEST);


    }
         private void makeCenterRegion()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Select Toppings"));
        pepperoniBox = new JCheckBox("Pepperoni", false);
        panel.add(pepperoniBox);
        sausageBox = new JCheckBox("Sausage", false);
        panel.add(sausageBox);
        cheeseBox = new JCheckBox("Extra Cheese", false);
        panel.add(cheeseBox);
        pepperBox = new JCheckBox("Bell Peppers", false);
        panel.add(pepperBox);
        onionBox = new JCheckBox("Onion", false);
        panel.add(onionBox);
        mushroomBox = new JCheckBox("Mushrooms", false);
        panel.add(mushroomBox);        
        oliveBox = new JCheckBox("Olives", false);
        panel.add(oliveBox);
        anchovyBox = new JCheckBox("Anchovy", false);
        panel.add(anchovyBox);
        
        contentPane.add(panel, BorderLayout.CENTER);
        
        
    }
    private void makeEastRegion()
    {
        // set up side orders with quantities in EAST
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Sides (Enter Quantity)"));
        //panel.setPreferredSize(new Dimension(150,0));
        panel.setPreferredSize(new Dimension(200,0));

        
        JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new BoxLayout(smallPanel,BoxLayout.X_AXIS));
        breadSticksText = new JTextField("",2); //I'll keep the minimul col of 2, even though the sample doesn't
        breadSticksText.setMaximumSize(new Dimension(20,24));
        smallPanel.add(breadSticksText);
        smallPanel.add(new JLabel(" Bread Sticks"));
        smallPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(smallPanel);
        
        smallPanel = new JPanel();
        smallPanel.setLayout(new BoxLayout(smallPanel,BoxLayout.X_AXIS));
        buffaloWingsText = new JTextField("",2);
        buffaloWingsText.setMaximumSize(new Dimension(20,24));
        smallPanel.add(buffaloWingsText);
        smallPanel.add(new JLabel(" Buffalo Wings"));
        smallPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(smallPanel);
        
        contentPane.add(panel, BorderLayout.EAST);
    }
    private void makeSouthRegion()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Deliver To:"));
        
        JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new BoxLayout(smallPanel,BoxLayout.Y_AXIS));
        smallPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3)); 

        smallPanel.add(new JLabel("Name:"));
        smallPanel.add(new JLabel("Address:"));
        smallPanel.add(new JLabel("City, St, Zip:"));

        panel.add(smallPanel);
        
        smallPanel = new JPanel();
        smallPanel.setLayout(new BoxLayout(smallPanel,BoxLayout.Y_AXIS));
        smallPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        nameText = new JTextField();
        addressText = new JTextField();
        cityText = new JTextField();
        smallPanel.add(nameText);
        smallPanel.add(addressText);
        smallPanel.add(cityText);

        panel.add(smallPanel);
        
        contentPane.add(panel, BorderLayout.SOUTH);




    }
    private class AboutListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(frame, 
                    "PizzaDB\n\nVersion 1.0\nBuild B20080226-1746\n\n" +
                        "(c) Copyright Daniel Day 2020\nAll rights reserved\n\n" +
                        "Visit /\nEducation To Go\n" +
                        "Intermediate Java Course", 
                    "About PizzaDB", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
    private class NewListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            regularCrustButton.setSelected(true);
            pepperoniBox.setSelected(false);
            sausageBox.setSelected(false);
            cheeseBox.setSelected(false);
            pepperBox.setSelected(false);
            onionBox.setSelected(false);
            mushroomBox.setSelected(false);
            oliveBox.setSelected(false);
            anchovyBox.setSelected(false);
            
            breadSticksText.setText("");
            buffaloWingsText.setText("");
            nameText.setText("");
            addressText.setText("");
            cityText.setText("");

           
        }
    }
    
    private class SaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {            
               String crustText = "";          
               if (regularCrustButton.isSelected())
               crustText += "Regular";
               else if (thinCrustButton.isSelected())
               crustText += "Thin";
               else if (deepCrustButton.isSelected())
               crustText += "Deep-Dish";
               else if (handCrustButton.isSelected())
               crustText += "Hand-Tossed";
               else
               JOptionPane.showMessageDialog(frame, 
               "You must select a crust type!",
               "Crust Type Error", 
               JOptionPane.ERROR_MESSAGE);
               
               //crustText.getText();
               
               String toppingsText = "";
               if (pepperoniBox.isSelected())
               toppingsText += "     Pepperoni\n";
               if (sausageBox.isSelected())
               toppingsText += "     Sausage\n";
               if (cheeseBox.isSelected())
               toppingsText += "     Extra Cheese\n";
               if (pepperBox.isSelected())
               toppingsText += "     Peppers\n";
               if (onionBox.isSelected())
               toppingsText += "     Onions\n";
               if (mushroomBox.isSelected())
               toppingsText += "     Mushrooms\n";
               if (oliveBox.isSelected())
               toppingsText += "     Olives\n";
               if (anchovyBox.isSelected())
               toppingsText += "     Anchovies\n";
               
               String toppingsTextDB = "";
               if (pepperoniBox.isSelected())
               toppingsTextDB += "RON ";
               if (sausageBox.isSelected())
               toppingsTextDB += "SAU ";
               if (cheeseBox.isSelected())
               toppingsTextDB += "XCH ";
               if (pepperBox.isSelected())
               toppingsTextDB += "BEL ";
               if (onionBox.isSelected())
               toppingsTextDB += "ONS ";
               if (mushroomBox.isSelected())
               toppingsTextDB += "MUS ";
               if (oliveBox.isSelected())
               toppingsTextDB += "OLI ";
               if (anchovyBox.isSelected())
               toppingsTextDB += "ANC ";
    
               String order = "Pizza Order\n" +
               "===========\n" + "Crust:\n" + 
               "     " + crustText + "\n"; 
               System.out.println("branch 0");
               order += "Toppings:\n" + toppingsText + "\n";

               
               int bs = 0;
               int bw = 0;
               try
               {
                   if (!breadSticksText.getText().isEmpty())
                   bs = Integer.parseInt(breadSticksText.getText());
                   if (!buffaloWingsText.getText().isEmpty())
                   bw = Integer.parseInt(buffaloWingsText.getText());
                }
                catch (NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(frame, 
                    "Side order entries must be numeric,\n" +
                    "and must be whole numbers", 
                    "Side Order Error", 
                    JOptionPane.ERROR_MESSAGE);
                }
               if (bs > 0 || bw > 0)
               {
                   order += "Sides:\n";
                   if (bs > 0)
                   order += "     " + bs + " Bread Sticks\n";
                   if (bw > 0)
                   order += "     " + bw + " Buffalo Wings\n";
               }
               
               if (nameText.getText().isEmpty() ||
               addressText.getText().isEmpty() ||
               cityText.getText().isEmpty())
               JOptionPane.showMessageDialog(frame, 
               "Address fields may not be empty.",
               "Address Error", 
               JOptionPane.ERROR_MESSAGE);
               else
               {
                   order += "Deliver To:\n";
                   order += "     " + nameText.getText() + "\n";
                   order += "     " + addressText.getText() + "\n";
                   order += "     " + cityText.getText() + "\n";
               }
               order += "\n***END OF ORDER ***\n";    
               System.out.println("branch 1");
               try{
                    PrintStream oFile = new PrintStream("PizzaOrder.txt");
                    oFile.print(order);
                    oFile.close();
                }
                catch(IOException ioe)
                    {
                        System.out.println("\n*** I/O Error ***\n" + ioe);
                    }
               
               try 
                   {// Step 1: Allocate a database 'Connection' object
                       
                       
            try{
              Class.forName("com.mysql.jdbc.Driver");
            } 
            catch (Exception ex) 
            {
        
            }
                       
                       
         System.out.println("in  the SQL statement" + "\n"); 
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/pizza_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "root", "password");   // For MySQL only
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
 
         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
         
         // INSERT a record
         int pizzaID;
         
         String loadCrustText;
         loadCrustText = crustText;
         String loadToppingsText;
         loadToppingsText = toppingsTextDB;
         String loadNameText;
         loadNameText = nameText.getText();
         String loadAddressText;
         loadAddressText = addressText.getText();
         System.out.println("load address " + loadAddressText);
         String loadCityText;
         loadCityText = cityText.getText();
         int loadBS;
         loadBS = Integer.parseInt(breadSticksText.getText());
         System.out.println("load int " + loadBS);
         int loadBW;
         loadBW = Integer.parseInt(buffaloWingsText.getText());
         System.out.println("load int " + loadBW);
         
         
         
         
         
         
         //String sqlInsert = "insert into pizza (name,bstick) values ('Joe', 1)";
         //INSERT INTO pizza(name,bstick) VALUES ('Shemp', 19);
         //String sql = "INSERT INTO pizza VALUES (?, ?)";
         String sql = "INSERT into pizza (name, address, city, crust, toppings, bstick, bwing) VALUES (?,?,?,?,?,?,?)";
         //PreparedStatement preparedStatement = 
         //conn.prepareStatement("INSERT into pizza (name, bstick) VALUES (?,?)", 
         //Statement.RETURN_GENERATED_KEYS);
         //int autoGeneratedID = tableKeys.getInt(1);
         //preparedStatement.setInt(1,autoGeneratedID);
         PreparedStatement bob = conn.prepareStatement(sql);
         bob.setString(1, loadNameText);
         bob.setString(2, loadAddressText);
         bob.setString(3, loadCityText);
         bob.setString(4, loadCrustText);
         bob.setString(5, loadToppingsText);
         bob.setInt(6, loadBS);
         bob.setInt(7, loadBW);
         bob.executeUpdate();
         //System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
         //int countInserted = stmt.executeUpdate(sqlInsert);
         //System.out.println(countInserted + " records inserted.\n");

      
        
        
         // Step 3: Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
         //String strSelect = "select name, bstick, from pizza_db";
         String strSelect = "select * from pizza_db.pizza";
         System.out.println("the SQL statement is: " + strSelect + "\n"); // Echo For debugging
 
         ResultSet rset = stmt.executeQuery(strSelect);
 
         // Step 4: Process the ResultSet by scrolling the cursor forward via next().
         //  For each row, retrieve the contents of the cells with getXxx(columnName).
         System.out.println("The records selected are:");
         int rowCount = 0;
         while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            String name = rset.getString("name");
            String address = rset.getString("address");
            String city = rset.getString("city");
            String crust = rset.getString("crust");
            String toppings = rset.getString("toppings");
            double bstick = rset.getDouble("bstick");
            System.out.println(name + ", " + bstick);
            double bwing = rset.getDouble("bwing");
            System.out.println(name + ", " + bwing);
            ++rowCount;
         }
         System.out.println("Total number of records = " + rowCount);
 
      } 
      catch(SQLException ex) {
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7) 
                    

        }
        
    }    
}