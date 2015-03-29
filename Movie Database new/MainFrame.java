import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class MainFrame extends JFrame
{
   public MainFrame()
   {
      setTitle("Movie Database and Ticket Booking");
      setSize(1000,650);
      setLocation(50,50);
    
    
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
           addWindowListener(new WindowAdapter() 
      {
         public void window(WindowEvent e)
         {
            dispose();
            System.exit(0);
         }
      });
      Container contentPane = getContentPane();
  
      JLabel title=new JLabel("WELCOME TO MOVIE DATABASE AND TICKET BOOKING",SwingConstants.CENTER);
              title.setFont(new Font("Helvetica", Font.BOLD, 30));
        title.setLocation(0,0);
        title.setSize(1000, 60);
        //initialise the Buttons
    JButton admin = new JButton("Admin Mode ");
    JButton user = new JButton("User Mode ");
    JButton book_tickets = new JButton("Book Tickets ");
    JButton exit = new JButton("Exit ");

         //position the buttons
 user.setBounds(300,350,200,50);
 admin.setBounds(550,350,200,50);
 book_tickets.setBounds(300,450,200,50);
 exit.setBounds(550,450,200,50);
 
 JPanel panel = new JPanel();
   
  contentPane.add(title);  
    contentPane.add(user);  
    contentPane.add(admin);
    contentPane.add(book_tickets);
   contentPane.add(exit);
    contentPane.add(panel);
    
    //actionListeners for the Buttons
    
    admin.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
         new Admin1();
      }
    });
    
    user.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
         try{
               new Admin2();
            }
            catch(Exception f)
            {
            }
      }
    });
    
    book_tickets.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
      	//try
      //	{
      		new Test();
      //	}
      	//catch (IOException d)
      //	{
      //	}
         
      }
    });
    
    exit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);   
      }
    });
ImageImplement panel1 = new ImageImplement(new ImageIcon("themovies.png").getImage());
imagee panel2 = new imagee(new ImageIcon("interstellar.png").getImage());
imagee panel3 = new imagee(new ImageIcon("dark.png").getImage());
panel3.setLocation(750,0);
contentPane.add(panel2);
contentPane.add(panel3);
contentPane.add(panel1);

}
//end of constructor 



   
   }//end of mainframe
   
   
   
 
 
 


//end of class