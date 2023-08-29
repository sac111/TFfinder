package Designite;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
//--------
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;


  
public class gui {

	static String Ians,Oans,go,disPath;
	static int tot,sml,tot2,s1,s2,s3;
	static int rec=0;

	
	private JFrame frmSmellsDetectorTool;

	/**
	 * Launch the application.
	 */
	public void mainGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frmSmellsDetectorTool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		}
	
	public String Ipass()
	{
		return Ians;
	}
	public String Opass()
	{
		return Oans;
	}
	public String pressed()
	{
		return go;
	}
	public void gotAns(int a,int b,int c,int d,int e,int f,int g)
	{
		rec=a;
		tot=b;
		sml=c;
		tot2=d;
		s1=e;
		s2=f;
		s3=g;
	}



	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	
	private void initialize() {	
		
		
		Ians="";
		Oans="";
		go="0";
	    JFileChooser chooser = new JFileChooser(); 
	    String choosertitle = null;
		frmSmellsDetectorTool = new JFrame();
		frmSmellsDetectorTool.setTitle("Smells Detection Tool [MAJOR PROJECT]");
		frmSmellsDetectorTool.getContentPane().setBackground(SystemColor.control);
		frmSmellsDetectorTool.setBounds(50, 30, 900, 613);
		frmSmellsDetectorTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSmellsDetectorTool.getContentPane().setLayout(null);
		


		
		JButton b1 = new JButton("Select");
		b1.setForeground(new Color(255, 255, 255));
		b1.setBackground(new Color(100,100,100));
		b1.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 18));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("button clicked");	
			    int result;
		        
			    
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle(choosertitle);
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			      System.out.println("getCurrentDirectory(): " 
			         +  chooser.getCurrentDirectory());
			      System.out.println("getSelectedFile() : " 
			         +  chooser.getSelectedFile());
			      String sin=chooser.getSelectedFile().toString();
			      sin = sin.replace("\\", "\\\\");
			      Ians=sin;
			      }
			    else {
			      System.out.println("No Selection ");
			      }
			    b1.setText("Selected");
			    b1.setBackground(new Color(64,64,64));
			}
		});
		b1.setBounds(218, 163, 120, 34);
		frmSmellsDetectorTool.getContentPane().add(b1);
		
		JLabel heading = new JLabel("Smells Detection Tool");
		heading.setBackground(SystemColor.textHighlight);
		heading.setForeground(new Color(0, 0, 51));
		heading.setFont(new Font("Malgun Gothic", Font.BOLD, 40));
		heading.setBounds(218, 28, 422, 82);
		frmSmellsDetectorTool.getContentPane().add(heading);
		
		JLabel Linp = new JLabel("Input Folder");
		Linp.setBackground(Color.WHITE);
		Linp.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 20));
		Linp.setForeground(SystemColor.textHighlight);
		Linp.setBounds(66, 164, 120, 34);
		frmSmellsDetectorTool.getContentPane().add(Linp);
		
		JLabel Lout = new JLabel("Output Folder");
		Lout.setForeground(SystemColor.textHighlight);
		Lout.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 19));
		Linp.setForeground(SystemColor.textHighlight);
		Lout.setBounds(489, 165, 129, 34);
		frmSmellsDetectorTool.getContentPane().add(Lout);
		
		JButton b2 = new JButton("Select");
		b2.setForeground(Color.WHITE);
		b2.setBackground(new Color(100,100,100));
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("button clicked");	
			    int result;
		        
			    
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle(choosertitle);
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			      System.out.println("getCurrentDirectory(): " 
			         +  chooser.getCurrentDirectory());
			      System.out.println("getSelectedFile() : " 
			         +  chooser.getSelectedFile());
			      String sout=chooser.getSelectedFile().toString();
			      disPath=sout;
			      sout = sout.replace("\\", "\\\\");
			      Oans=sout;
			    }
			    else {
			      System.out.println("No Selection ");
			      }
			    b2.setText("Selected");
			    b2.setBackground(new Color(64,64,64));
			
			}
		});
		b2.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 18));
		b2.setBounds(655, 163, 120, 34);
		frmSmellsDetectorTool.getContentPane().add(b2);
		
		JLabel lblNewLabel = new JLabel("RESULT");
		lblNewLabel.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 22));
		lblNewLabel.setForeground(new Color(199, 21, 133));
		lblNewLabel.setBounds(136, 333, 98, 44);
		frmSmellsDetectorTool.getContentPane().add(lblNewLabel);
		
		JLabel displayResult = new JLabel("");
		displayResult.setForeground(SystemColor.desktop);
		displayResult.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 18));
		displayResult.setBounds(305, 333, 406, 166);
		frmSmellsDetectorTool.getContentPane().add(displayResult);
		
		JLabel showPath = new JLabel("");
		showPath.setForeground(SystemColor.controlDkShadow);
		showPath.setFont(new Font("Consolas", Font.BOLD, 18));
		showPath.setBounds(66, 510, 768, 44);
		frmSmellsDetectorTool.getContentPane().add(showPath);
		
		JLabel per = new JLabel("");
		per.setForeground(SystemColor.desktop);
		per.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 20));
		per.setBounds(670, 222, 105, 82);
		frmSmellsDetectorTool.getContentPane().add(per);		
		
		JButton btnNewButton = new JButton("Go !");
		btnNewButton.setBackground(new Color(153, 51, 102));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				go="1";
				displayResult.setText("<html>" + "Please wait !"+ "<br />" +"<br />" + "Processing..." + "</html>");
				
                new SwingWorker<Void, String>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        // Worken hard or hardly worken...

        				while(true) {
        					try {
        						Thread.sleep(1000);
        						if(rec==1){
        							displayResult.setText("<html>" + "Lazy Class : "+ (s1*100/tot2)+"%" + "<br/>" +  "Large Class : "+ (s3*100/tot2)+"%"+"<br/>" +  "Feature Envy : "+ (s2*100/tot2)+"%"+ "<br/>" + "Temporary Field : "+ (sml*100/tot)+"%" +"</html>"); //+(sml*100/tot)+"%"
        							//per.setText(" "+(sml*100/tot)+"%");
        							
        							//displayResult.setText("<html>" + "Total Classes : "+tot + "<br />" + "Field Smell Classes : "+sml + "<br />"+"<br />" + "Percentage Smell : "+ "</html>"); //+(sml*100/tot)+"%"
        							//per.setText(" "+(sml*100/tot)+"%");
        							showPath.setText("<html>" + "Detailed results are stored at the following path : " + "<br />" + disPath + "</html>");
        							break;
        						}
        						
        					} catch (InterruptedException e2) {
        						e2.printStackTrace();
        					}
        				}
        			
        				btnNewButton.setText("Completed");
        				
        				
                        
                        return null;
                    }

                    @Override
                    protected void done() {
                        
                    }
                }.execute();
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 20));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(305, 246, 226, 44);
		frmSmellsDetectorTool.getContentPane().add(btnNewButton);
		

		

		

		
		

	}
}


