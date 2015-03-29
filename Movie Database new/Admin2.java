import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
public class Admin2 extends JFrame
{
    final int width= 720;
    int lol=0;
    final int height =720;
    private JPanel panel;
    private JButton name;
    private JTextField namet;
    private JButton actor;
    private JTextField actort;
    private JButton all;
    private JButton director;
    private JTextField directort;
    
    private JLabel welcome;
    Tree tree=new Tree();
    public Admin2()throws IOException
    {
        File file=new File("Movies.txt");
        Scanner rohan=new Scanner(file);
        String name="",cast="",director="";
        int ctr = 0;
        
        while(rohan.hasNextLine())
        {   
            String s = rohan.nextLine();
            if(s.equals("Movie_name "))
              { ctr++;
               name=rohan.nextLine();
              }
            else if(s.equals("Cast "))
               {   ctr++;
                  cast = rohan.nextLine();
               }
               
            else if(s.equals("Director "))
              { ctr++;
               director = rohan.nextLine();
               }
               if(ctr==3)
               {
                  tree.insert(name,cast,director);
                  ctr = 0;
               }
               
        }
        
        
        setTitle("USER MODE");
        setSize(width,height);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanel();
        add(panel);
        setVisible(true);       
        
         addWindowListener(new WindowAdapter() 
      {
         public void window(WindowEvent e)
         {
            dispose();
         }
      });
     
    }
    private void buildPanel()
    {
        welcome= new JLabel("Welcome to User Mode");
        name=new JButton("Search by Movie Name");
        actor=new JButton("Search by Actor");
        director=new JButton("Search by Director");
        all=new JButton("Show All Movies");
        namet=new JTextField("Enter the movie name");
        actort=new JTextField("Enter the actor name");
        directort=new JTextField("Enter the director name");
        panel= new JPanel();
        name.setBounds(250,250,250,30);
        welcome.setBounds(300,10,300,100);
        actor.setBounds(50,90,250,30);
        director.setBounds(400,90,250,30);
        all.setBounds(150,400,400,50);
        namet.setBounds(250,300,250,30);
        actort.setBounds(50,130,250,30);
        directort.setBounds(400,130,250,30); 
        name.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
               Node node;
               node=tree.search(namet.getText());
               if(node!=null)
               JOptionPane.showMessageDialog(null,"Movie Name : "+node.name+ "\n"+"Cast : "+node.cast+ "\n"+"Director : "+node.director+ "\n");
               else
               JOptionPane.showMessageDialog(null,"Sorry, this movie is not available in our records");
        }
        });
        
        director.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                tree.display2(directort.getText());
                
            }
           
        
        });
        all.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            tree.display();
        }
        });   
        actor.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
               tree.display3(actort.getText());
        }
        });
       
       
      // 
       
        
        //panel = new JPanel(new GridLayout(3, 3, 1, 1));
        panel= new JPanel();
        Container contentPane = getContentPane();
       contentPane.add(panel);  
        
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));      
        contentPane.add(welcome);
        contentPane.add(all);
        contentPane.add(director);
        contentPane.add(directort);
        contentPane.add(name);
       contentPane.add(namet);
       contentPane.add(actor);
       contentPane.add(actort);
        
       

        
    }
    class Node
    {
        Node right;
        Node left;
        String name;
        String cast;
        String director;        
        Node(String name,String cast,String director)
        {
            right=left=null;
            this.name=name;
            this.cast = cast;
            this.director = director;
            
            
        }
    }
    public class Tree
    {
        Node root;
        Tree()
        {
            root=null;
        }
        public void display()
        {
            inorder(root);
            
        }
        //for searching directorwise
        public void display2(String dir)
        {
            inorder2(root,dir);
            
        }
        
         public void display3(String actor)
        {
            inorder3(root,actor);
            
        }
       
        public void inorder(Node root)
        {
            if(root!=null)
            {
                inorder(root.left);
                
                JOptionPane.showMessageDialog(null,root.name);
                inorder(root.right);
            }
        }    
        public void inorder2(Node root, String dir)
        {
            if(root!=null)
            {
                boolean flag=false;
                inorder2(root.left,dir);
                if(dir.toLowerCase().equals(root.director.toLowerCase()))
                //.toLowerCase changes everything to lower case before comparing
                {
                    JOptionPane.showMessageDialog(null,"Movie: "+root.name+"\nCast: "+root.cast+"\nDirector: "+root.director);
                    flag=true;
                }
                inorder2(root.right,dir);
            }
            
        }
        
        public void inorder3(Node root, String actor)
        {
            if(root!=null)
            {
                boolean flag=false;
                inorder3(root.left,actor);
                if(root.cast.toLowerCase().contains(actor.toLowerCase()))
                {
                    JOptionPane.showMessageDialog(null,"Movie: "+root.name+"\nCast: "+root.cast+"\nDirector: "+root.director);
                    flag=true;
                }
                inorder3(root.right,actor);
            }
            
        }        

       
        public void insert(String movie,String cast,String director)
        {
            Node temp=new Node(movie.toLowerCase(),cast.toLowerCase(),director.toLowerCase());
            root=insertR(root,temp);
            
        }
        Node insertR(Node root, Node temp)
        {
            // 1. empty tree
            if(root == null)
                root = temp;
            // 2. check based on the predicate( < )
            else if(temp.name.compareTo(root.name)<0) // insert temp in left subtree
                root.left = insertR(root.left, temp);
            else // 3. insert temp in right subtree
                root.right = insertR(root.right, temp);
            return root;    
        }
        public Node search(String key)
        {
            return searchR(root, key);
        }
        private Node searchR(Node root, String key)
        {
            if(root == null)
                return null;
            else if(root.name.toLowerCase().contains(key.toLowerCase()))
                return root;
            else if(key.toLowerCase().compareTo(root.name.toLowerCase())<0)
                return searchR(root.left, key);
            else
                return searchR(root.right, key);        
        }
    }
}

        
        

    