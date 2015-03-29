import java.util.*;
import java.io.*;
public class Ticket_Booking_new
{
	static Scanner in=new Scanner(System.in);
	static String s;
	static String seats[][]=new String[10][10];
	static int i,j;
	static String cName[] = {"A","B","C","D","E","F","G","H","I","J"};
	public void read()throws IOException
	{
			File file=new File("E:\\Rajath\\3rd Semester\\DSJ\\MINI PROJECT\\Ticket\\"+s+".txt");
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
	public void write()throws IOException
	{
			
			BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
        	PrintWriter writefile = new PrintWriter(new BufferedWriter(new FileWriter("E:\\Rajath\\3rd Semester\\DSJ\\MINI PROJECT\\Ticket\\"+s+".txt")));
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
	public void dispData()throws IOException
	{
			// Method that will display the array content
		for (i=0; i<10; ++i) 
		{
			for (j=0; j<10; ++j) 
			{
				System.out.print(seats[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
		
	write();
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
	public boolean chkCancel(String vData) {
		String s1;	// Method that will check for reservation availability
	for (i=0; i<10; ++i) {
		for (j=0; j<10; ++j) {
			if ((seats[i][j]).equalsIgnoreCase("X")) {
				s1=(i+1)+""+(char)(i+65);
				if(s1.equalsIgnoreCase(vData))
				{
					seats[i][j]=s1;
					return true;
				}
				
			}
		}
	}
	
	return false;
	}
	public boolean chkFull() 	// Method that will check if all reservations were occupied
	{
		for (i=0; i<5; ++i)
		{
			for (j=0; j<5; ++j) 
			{
				if (!(seats[i][j]).equals("X")) 
				{
					return false;
				}
			}
		}
		return true;
	}	
	public void create()throws IOException
	{
		int i,j;
		for (i=0; i<10; ++i) 
		{									// Initialized array with constant data
			for (j=0; j<10; ++j) 
				{
					seats[i][j] = new String((i+1) + cName[j]);
				}
		}
		write();
	}
	public void ticketBook()throws IOException
	{
		int c=0;
		String inData = new String("");
		do {													// Loop until user press X to exit
				dispData();
				if (chkFull())
				{
					System.out.println("Reservation is FULL");
					inData="X";
					break;
				}
				else 
				{
					System.out.print("Enter Seat Reservation : (Enter X to quit the process) ");
					inData = in.next();
					if (chkData(inData))
					{
						System.out.println("Reservation Successful!");
						c++;
					}
					else
					{
						System.out.println("Occupied Seat or the seat entered is invalid.Please Enter again!");
						System.out.println();
					}
				}		
			}while (!inData.equalsIgnoreCase("X"));
			System.out.println("Number of tickets booked = "+(c-1));
			
	
	}
	public void cancel()throws IOException
	{
		String inData = new String("");
		do
		{
		dispData();
		System.out.print("Enter Seat number to be cancelled : (Enter X to quit the process) ");
		inData = in.next();
		
		if (chkCancel(inData))
		{
			System.out.println("Cancelation Successful!");
			//c++;
		}
		else if(!inData.equalsIgnoreCase("X"))
		{
			System.out.println("Unoccupied Seat or the seat entered is invalid.Please Enter again!");
			System.out.println();
		}
	  }while (!inData.equalsIgnoreCase("X"));		
	}
	public static void main(String args[])throws IOException
	{
		System.out.println("Enter movie name");
		s=in.nextLine();
		Ticket_Booking_new tb=new Ticket_Booking_new();
		File f = new File("E:\\Rajath\\3rd Semester\\DSJ\\MINI PROJECT\\Ticket\\"+s+".txt");
		if(!f.exists())
			tb.create();
		tb.read();
		tb.ticketBook();
		tb.cancel();
	}
}