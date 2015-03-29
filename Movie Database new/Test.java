import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class Test extends JFrame {
	public Test()
	{
				JFrame frame = new JFrame("Ticket Booking");
				 Container contentPane = getContentPane();
		JLabel title;
		JButton seat;
		
		// This is an empty content area in the frame
		title = new JLabel("WELCOME TO TICKET BOOKING",SwingConstants.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        title.setLocation(500,500);
        title.setSize(600, 60);
        JPanel pan=new JPanel();
        seat=new JButton("Seat Reservation");
        seat.setBounds(425,375,200,50);
        seat.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
         	try
         	{
         		new cinemaSystem2();
         	}
         	catch (IOException d)
         	{
         		
         	}
            
         }
      });
        //pan.add(seat);
        frame.setPreferredSize(new Dimension(1000, 500));

		contentPane.add(title, BorderLayout.NORTH);
		contentPane.add(seat);
		contentPane.add(pan);
		ImageImplement panel1 = new ImageImplement(new ImageIcon("images.png").getImage());
		contentPane.add(panel1);

		frame.add(contentPane);
		frame.pack();
		frame.setVisible(true);
	}

	//public static void main(String s[])throws IOException {
		

	//}
}