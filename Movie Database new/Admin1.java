import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;



//MyPanel extends the main JPanel class and the paintComponent method must be overridden
class MyPanel extends JPanel 
{
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
       Font f = new Font("SansSerif", Font.BOLD, 14);
	    Font fi = new Font("SansSerif", Font.BOLD + Font.ITALIC, 14);
	    FontMetrics fm = g.getFontMetrics(f);
	    FontMetrics fim = g.getFontMetrics(fi);
	    int cx = 75; int cy = 100;
	    g.setFont(f);
	    g.drawString("Welcome ", cx, cy);
	    cx += fm.stringWidth("Welcome ");
	    g.setFont(fi);
	    g.drawString("ADMIN!", cx, cy);
       cy+=25;
       g.setFont(f);
       cx-= fm.stringWidth("Welcome ");
       g.drawString("What would you like to do? ",cx,cy);
	    
      
   
   }
}


//main frame class

class MyFrame extends JFrame
{
   public MyFrame()
   {
      //generates the basic frame
      setTitle("Admin Mode ");
      setSize(720,720);
      setLocation(100,100);
      
      
      //when cross Button is pressed 
       addWindowListener(new WindowAdapter() 
      {
         public void window(WindowEvent e)
         {
            dispose();
            System.exit(0);
         }
      });
      Container contentPane = getContentPane();
      
      
      //initialise all the buttons
      JButton addB = new JButton("Add Movie ");
      JButton delB = new JButton("Remove Movie ");
      JButton passwrd = new JButton("Change Password ");
      JButton exit = new JButton("Exit ");
      
      
      //set button positions and size
      //parameters for setbounds
      //BUTTON_LOCATION X,BUTTON_LOCATION Y,BUTTON_SIZE X,BUTTON_SIZE Y
      int button_location_x = 75;
      int button_location_y = 150;
      final int button_size_x = 200;
      final int button_size_y = 70;
      
      
      addB.setBounds(button_location_x,button_location_y,button_size_x,button_size_y);
      delB.setBounds(button_location_x,button_location_y + 100,button_size_x,button_size_y);
      passwrd.setBounds(button_location_x,button_location_y + 200,button_size_x,button_size_y);
      exit.setBounds(button_location_x,button_location_y + 300,button_size_x,button_size_y);
      
      
      //add the buttons onto the panel
      contentPane.add(addB);
      contentPane.add(delB);
      contentPane.add(passwrd);
      contentPane.add(exit);
      
      
      //instantiate the Panel
      
      MyPanel panel = new MyPanel();
      
      contentPane.add(panel);
      
      
      //action Listeners
      
      addB.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            addMovie();
         }
      });
      
       delB.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
         	try
         	{
         		delMovie();
         	}
            catch (IOException g)
            {
            }
            
         }
      });
      
       passwrd.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            Admin1.change_password();
         }
      });
      
       exit.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            Admin1.closeAdminFrame();
         }
      });

      
     
   }//end of constructor
   
   public static add_movie_frame f;
   // made static so that a static method can access it
   // f is accessible to both cancel and addMovie methods
   public void addMovie()
   {  
       f = new add_movie_frame();
      f.show();   
   }
   public static void cancel()
   {
      f.dispose();
   }
   public void delMovie()throws IOException
   {
String s = null;
      int i=0;
      s = JOptionPane.showInputDialog("Enter the name of the movie name to be deleted ");
     
      PrintWriter writefile = new PrintWriter(new BufferedWriter(new FileWriter("Movies1.txt")));
  		BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
  		BufferedReader rfile = new BufferedReader(new FileReader("Movies.txt"));
  		String chk0,chk1,chk2,chk3,chk4,chk5;
  		while((chk0 = rfile.readLine()) != null)
  		{	chk1 = rfile.readLine();
    		chk2=rfile.readLine();
    		chk3=rfile.readLine();
    		chk4=rfile.readLine();
    		chk5=rfile.readLine();
  			if(!chk1.equalsIgnoreCase(s))
  			{	
  				writefile.println(chk0);
  				writefile.println(chk1);
  				writefile.println(chk2);
  				writefile.println(chk3);
  				writefile.println(chk4);
  				writefile.println(chk5);
  				
  			}
  		}
      //delete s
writefile.close();
rfile.close();



   PrintWriter writefile1 = new PrintWriter(new BufferedWriter(new FileWriter("Movies.txt")));
  		//BufferedReader keyin1=new BufferedReader(new InputStreamReader(System.in));
  		BufferedReader rfile1 = new BufferedReader(new FileReader("Movies1.txt"));
      String str ;
      while((str = rfile1.readLine())!=null)
      {
         writefile1.println(str);
      }
      writefile1.close();
      rfile1.close();
         
      
      
   }
   
   
}// end of frame class



//panel for addMovie 

class MoviePanel extends JPanel
{
   public void paintComponent(Graphics g)
   {
      
      Font f = new Font("Arial Black",Font.BOLD,18);
      g.setFont(f);
      g.drawString("Enter the Details of the New Movie",90,50);
      Font title_font = new Font("Comic Sans",Font.ITALIC,16);
      g.setFont(title_font);
      g.drawString("Movie Name ",75,150);
      g.drawString("Cast ", 75+150,150);
      g.drawString("Director ",75 + 300 ,150);
   }
   
}//end of addmovie panel



//addmovie frame

class add_movie_frame extends JFrame
{
   //Declaring the input textfields
       JTextField input_movie_name ;
       JTextField input_cast ;
      JTextField input_director; 
   
   
   
   public add_movie_frame()
   {
      setTitle("Add Movie ");
      setSize(500,500);
      setLocation(200,100);
      
      addWindowListener(new WindowAdapter() 
      {
         public void window(WindowEvent e)
         {
            dispose();
            System.exit(0);
         }
      });
 
      //initialises the input TextFields
      init();
      
       //set positions for the textfields
      int text_xpos = 75;
      int text_ypos = 200;
      final int text_xsize = 100;
      final int text_ysize = 30;
      
      

      //positioning the textfields
      input_movie_name.setBounds(text_xpos,text_ypos,text_xsize,text_ysize);
      input_cast.setBounds(text_xpos+150,text_ypos,text_xsize,text_ysize);
      input_director.setBounds(text_xpos+300,text_ypos,text_xsize,text_ysize);
     
      //initialising the buttons
      JButton done = new JButton("Done ");
      JButton cancel = new JButton("Cancel ");
      JButton reset = new JButton("Reset ");
      
      // set positions of buttons
      int button_location_x = 75;
      int button_location_y = 350;
      final int button_size_x = 70;
      final int button_size_y = 50;
      
            
      
      //positioning the buttons
      done.setBounds(button_location_x,button_location_y,button_size_x,button_size_y);
      cancel.setBounds(button_location_x+ 120,button_location_y,button_size_x+25,button_size_y);
      reset.setBounds(button_location_x+240,button_location_y,button_size_x,button_size_y);
      
      Container contentPane = getContentPane();
      
      contentPane.add(input_movie_name);
      contentPane.add(input_cast);
      contentPane.add(input_director);
      contentPane.add(done);
      contentPane.add(cancel);
      contentPane.add(reset);
      
      //instantiate the add movie panel
      
      MoviePanel pan = new MoviePanel();
      contentPane.add(pan);
      
       //action listeners for the buttons
      
       done.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            String movie_name,cast,director;
            movie_name = input_movie_name.getText();
            cast = input_cast.getText();
            director = input_director.getText();
        
            update_database(movie_name,cast,director);    
            JOptionPane.showMessageDialog(null,"Successfully Updated! ");
            MyFrame.cancel();
            
          
              
            //copy the details obtained to the doubly linked list
            
            /*System.out.println(movie_name+cast+director); */
         }
      });
      
      
       cancel.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
                MyFrame.cancel();
                
                //cancel is a static metod of the MyFrame class
                // add_movie_frame object is instantiated as a static object there
         }
      });
      
       reset.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            reset();
         }
      });
     
        
         
     
   }// end of add_movie_frame constructor
   
     
   public void update_database(String movie,String cast,String director)
   {
     
     /*movie = "\n" + movie ;
     cast = "\n" + cast;
     director = "\n" + director; */ 
     try
     {
      File file = new File("Movies.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
  FileWriter fw = new FileWriter("Movies.txt",true); //the true will append the new data
    fw.write("Movie_name \n");
    fw.write(movie + "\n");//appends the string to the file
      fw.write("Cast \n");
      fw.write(cast + "\n");
      fw.write("Director \n");
      fw.write(director + "\n");
      
      fw.close();
         
     }
     
     catch(Exception e)
     {
      e.printStackTrace();
     }
 
   }//end of update_database
  
   public void reset()
   {   
      input_movie_name.setText(" ");
      input_cast.setText(" ");
      input_director.setText(" ");
    }
   
   private void init()
   {
      input_movie_name = new JTextField(50);
      input_cast= new JTextField(50);
      input_director = new JTextField(50);
   }
}

//frame class for password

//end of password_frame

//main class
public class Admin1
{
   public static String passwrd = initial_pass();
   public static MyFrame f;
   public Admin1()
   {
         if(password_correct(passwrd))  
         {  
            f = new MyFrame();
            f.show();
         }
         
      else
      {
         JOptionPane.showMessageDialog(null,"You are naaat HEADmin luccha ");
      }
   
      
   }   // end of constructor
   
   
   
   public static void closeAdminFrame()
   {
      f.dispose();
   } 
   //shift this into constructor for integration
   //public static void main(String[] args)
   //{
      
      
  // }// end of main
   
   
   //function to change the password
   public static void change_password()
   {  
      String p ="";
      p = JOptionPane.showInputDialog("Enter new password ");
     
      
            

      try
      {File file = new File("pass.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(p);
			bw.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }   
   }
   
   //function to get the initial password from the pass.txt file
   
   public static String initial_pass()
   {
      String p = null;
      
     try
     {
     FileReader fileR = new FileReader("pass.txt");
      Scanner k = new Scanner(fileR);
      while(k.hasNext())
         p = k.next();
      fileR.close();
     } catch(Exception e)
      {
         e.printStackTrace();
      }
      
      
      return p;
   }
   
   //function to check whether the password entered by the user is correct
   public static boolean password_correct(String passwrd)
   {
      JPanel panel = new JPanel();
      JLabel label = new JLabel("Enter a password:");
      JPasswordField pass = new JPasswordField(10);
      panel.add(label);
      panel.add(pass);
      String[] options = new String[]{"OK", "Cancel"};
int option = JOptionPane.showOptionDialog(null, panel, "password",
                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                         null, options, options[1]);
if(option == 0) // pressing OK button
{
    String password = pass.getText();
    if(password.equals(passwrd))
      return true;
    else 
    return false;
}
else
   return false;
   }//end of password correct
   
    
   
}//end of Admin class