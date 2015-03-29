// Load Libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class cinemaSystem2 extends Ticket_Booking_new {
    // Global Variables
    JFrame frame = new JFrame(); // Creates JFrame
    JLabel title;
    final JToggleButton l[][]; // Names grid of JButtons
    JPanel panel;
    JButton done;
    private static Icon res = (UIManager.getIcon("OptionPane.errorIcon"));
    static int y,x;
    int d=0;
    // Constructor
    public cinemaSystem2()throws IOException{
        
        title = new JLabel("Cinema Booking");
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        title.setLocation(12,5);
        title.setSize(600, 60);
        
         
      
        
		Container contentPane = frame.getContentPane();
        frame.setLayout(null); // Setting Grid Layout
        // panel1.setLayout(new GridLayout(seat,row));
        l = new JToggleButton[10][10]; // Allocating Size of Grid
        panel = new JPanel(new GridLayout(10,10));
        panel.setBackground(Color.black);
        panel.setBounds(70, 100, 600, 300);
        s = JOptionPane.showInputDialog(frame, "Enter movie name");
        File f = new File(s+".txt");
        if(!f.exists())
        {
            create();
        }
        else
        {
            read();
        }
        
        selected();
        //	cancel();
        done=new JButton("DONE");
        done.setBounds(295,415,200,50);
        done.addActionListener(new ActionListener()
        {
         public void actionPerformed(ActionEvent e)
         {
         	//try
         	//{
         	JOptionPane.showMessageDialog (null, "Reservation Successful! \nNumber of tickets = " + d + "\nCost of each Ticket = "+175+"\nCost= Rs" + (d*175)  ); 
         	//}
         	//catch (IOException d)
         	//{
         		
         	//}
            
            
         }
      });
      JPanel pan=new JPanel();
        frame.setTitle("Ticket Booking");
        frame.setPreferredSize(new Dimension(800, 580));
        contentPane.add(title);
        contentPane.add(panel);
        contentPane.add(done);
        contentPane.add(pan);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //sets appropriate size for frame
        frame.setVisible(true); //makes frame visible
    }
    public void selected()
    {
        
        for(y = 0; y <10 ; y++){
            for(x = 0; x < 10; x++){
                if(seats[y][x].equals("X"))
                {
                    l[y][x] = new JToggleButton("X");
                    l[y][x].setIcon(res);
                    l[y][x].addActionListener(new ActionListener() {
                        
                        
                        public void actionPerformed(ActionEvent actionEvent) {
                            AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                            boolean selected = abstractButton.getModel().isSelected();
                            
                            
                            if (selected) {
                                JOptionPane.showMessageDialog (null, "Occupied Seat or the seat entered is invalid.");
                            }
                            
                            
                        }});
                        panel.add(l[y][x]);
                    }
                    else
                    {
                        if (chkFull())
                        {
                            JOptionPane.showMessageDialog (null, "Reservation Full!");
                            break;
                        }
                        else
                        {
                            l[y][x] = new JToggleButton((y+1)+""+(char)(x+65));
                            l[y][x].addActionListener(new ActionListener() {
                                
                                
                                public void actionPerformed(ActionEvent actionEvent) {
                                    AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                                    boolean selected = abstractButton.getModel().isSelected();
                                    
                                    
                                    if (selected) {
                                        
                                        //l[x][y].setIcon(res);
                                        abstractButton.setIcon(res);
                                        if (chkData(abstractButton.getText()))
                                        {
                                            
                                            //JOptionPane.showMessageDialog (null, "Reservation Successful!");
                                            d++;
                                        }
                                        //else
                                        //{
                                        
                                        //}
                                        
                                    } else {
                                        d--;
                                        if (chkCancel(abstractButton.getText()))
								{
									System.out.println("Cancelation Successful!");
			//c++;
								}
                                        abstractButton.setIcon(null);
                                        //
                                        
                                        
                                    }
                                    try
                                    {
                                        write();
                                    }
                                    catch (IOException e)
                                    {
                                        
                                    }
                                }
                                
                            });
                        }
                        
                        
                        // Creates New JButton
                        // l[x][y].addActionListener(this);
                        panel.add(l[y][x]); //adds button to grid
                    }
                }
                
            }

            
            
            
        }
      	public boolean chkCancel(String vData) {
		String s1;	// Method that will check for reservation availability
		for (i=0; i<10; ++i) {
			for (j=0; j<10; ++j) {
				
					s1=(i+1)+""+(char)(j+65);
					System.out.println(s1);
					if(s1.equalsIgnoreCase(vData))
					{
						seats[i][j]=s1;
						return true;
					}
				
					
				}
			}
	return false;
	}
        public boolean chkFull() 	// Method that will check if all reservations were occupied
        {
            for (i=0; i<10; ++i)
            {
                for (j=0; j<10; ++j)
                {
                    if (!(seats[i][j]).equals("X"))
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        public void cancel()throws IOException
        {
		for(y = 0; y <10 ; y++){
            for(x = 0; x < 10; x++){
                if(seats[y][x].equals("X"))
                {
                    l[y][x] = new JToggleButton("X");
                    l[y][x].setIcon(res);
                    l[y][x].addActionListener(new ActionListener() {
                        
                        
                        public void actionPerformed(ActionEvent actionEvent) {
                            AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                            boolean selected = abstractButton.getModel().isSelected();
                            
                            
                            if (selected) {
								if (chkCancel(abstractButton.getText()))
								{
									System.out.println("Cancelation Successful!");
			//c++;
								}
                            }
                            
                            
                        }});
                        panel.add(l[y][x]);
                    }
                    }
                    }            
            
        }
        
        public void write()throws IOException
        {
            
            BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writefile = new PrintWriter(new BufferedWriter(new FileWriter(s+".txt")));
            for(i=0;i<10;i++)
            {
                
                for(j=0;j<10;j++)
                {
                    writefile.print(seats[i][j]+ " ");
                }
                writefile.println();
            }
            writefile.println();
            writefile.close();
        }
        public boolean chkData(String vData) {	// Method that will check for reservation availability
            for (i=0; i<10; ++i) {
                for (j=0; j<10; ++j) {
                    if ((seats[i][j]).equalsIgnoreCase(vData)) {
                        seats[i][j]="X";
                        return true;
                    }
                }
            }
            
            return false;
        }
        public void read()throws IOException
        {
            File file=new File(s+".txt");
            Scanner scan=new Scanner(file);
            while(scan.hasNext())
            {
                for(i=0;i<10;i++)
                {
                    for(j=0;j<10;j++)
                    {
                        seats[i][j]=scan.next();
                    }
                }
            }
            
        }
        // Main Class
        public static void main(String[] args)throws IOException {
            
            
            EventQueue.invokeLater(new Runnable() {
                
                
                public void run() {
                    try
                    {
                        new cinemaSystem2();
                    }
                    catch (IOException e)
                    {
                        
                    }

                }
            });
            
            //makes new ButtonGrid with 2 parameters
        }
        
        
    }