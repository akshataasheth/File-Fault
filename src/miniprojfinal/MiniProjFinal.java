/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojfinal;

import ae.java.awt.dnd.DnDConstants;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileView;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FilenameUtils;
//import java.io.*;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;


/**
 *
 * @author Karthik
 */
 class CoOrdinate {
    int x;
    int y;
    
    CoOrdinate(int x, int y){
        this.x=x;
        this.y=y;
    }
    
}


public class MiniProjFinal extends javax.swing.JFrame implements KeyListener {

    File f,plf,fimgpath,ftemp,fregistration;
    String imgpath;
    CoOrdinate setP[]= new CoOrdinate[10];
    CoOrdinate confirmP[]= new CoOrdinate[10];
    CoOrdinate loginP[]=new CoOrdinate[10];
    CoOrdinate loginP1[]=new CoOrdinate[10];
    String username,email,fusername;
    boolean registered=false;
    BufferedImage bi;
    ImageIcon icon;
    int m,pLength=0;
    int mugvar=0;
    private  String OTP1;
    
    private static final Pattern usrNamePtrn = Pattern.compile("^[a-zA-Z0-9_-]+{6,14}$");
    
    private static final Pattern emailPtrn = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
     
    public static boolean validateUserName(String userName){
         
        Matcher mtch = usrNamePtrn.matcher(userName);
        return mtch.matches();
    }
    
    public static boolean validateEmail(String Email){
         
        Matcher mtch = emailPtrn.matcher(Email);
        return mtch.matches();
    }
            
                
        
            
    
    /**
     * Creates new form MiniProjFinal
     */
    public MiniProjFinal() {
        initComponents();
        note.setVisible(false);
        Next.setVisible(false);
        Confirm.setVisible(false);
        LoginButton.setVisible(false);
        warning1.setVisible(false);
        warning.setVisible(false);
    }

    public static String generatePassword() {
        String chars = "abcdefghijklmnopqrstuvwxyz"
                     + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                     + "0123456789";

        final int PW_LENGTH = 7;
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++)
            pass.append(chars.charAt(rnd.nextInt(chars.length())));
        return pass.toString();
    }
    
    
    public void DragDrop ()
    {
        dragdrop.setDropTarget (new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt){
                try{
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles=(List<File>) evt.getTransferable().
                            getTransferData(DataFlavor.javaFileListFlavor);
                    for(File  fe : droppedFiles){
                        System.out.println("dropped");
                        String efilepath,efilename;
                        efilepath= fe.getAbsolutePath();
                        miniprojfinal.AESFileEncryption.encrypt(efilepath,"/home/akku/NetBeansProjects/MiniProjFinal/encrypted_files");
                        efilename=fe.getName();
                        JOptionPane.showMessageDialog(null,efilename+" has been successfully encrypted!" );
                        writeToLog(efilename,'e');
                        CardLayout card=(CardLayout)MainPanel.getLayout();
                        card.show(MainPanel,"Encrypt");
                    }
                }
                    catch(Exception e)
                            {
                            System.out.println(e);
                            }
     
            }
    });
    }
    
    
    public  void loadLogin()
    {
        String s1="/home/akku/NetBeansProjects/MiniProjFinal/temp.txt";
        try
        {
           if(checkFile(s1))
        {
           BufferedReader br = new BufferedReader(new FileReader("/home/akku/NetBeansProjects/MiniProjFinal/imgname.txt"));     
           String s=br.readLine();
           System.out.println(s);
           try
           {
               f=new File(s);
               imgpath= f.getAbsolutePath();
              // System.out.println(imgpath);
               
           }
           catch(Exception e)
                   {
                       System.out.println(e);
                   }
           
           try{
        Scanner inFile1 = new Scanner(new File("temp.txt")).useDelimiter("\\s*,\\s*");
        java.util.List<Integer> temps = new ArrayList<Integer>();
        while (inFile1.hasNext()) {
        int token1 = inFile1.nextInt();
        temps.add(token1);
        System.out.println("t="+temps);
        }
        //System.out.println("here");
        inFile1.close();
        Integer[] tempsArray = temps.toArray(new Integer[0]);

         for (Integer x : tempsArray) {
          System.out.println(s);
         }
         int m=0;
          for(int j=0;j<10;j=j+2)
          {
             
              loginP[m]=new CoOrdinate(tempsArray[j],tempsArray[j+1]);
              m++;
              
          }       
        }
        catch(Exception ee)
        {
         System.out.println(ee);   
        }
       try {
            BufferedReader brP = new BufferedReader(new FileReader("/home/akku/NetBeansProjects/MiniProjFinal/passlength.txt"));     
           pLength=Integer.parseInt(brP.readLine());
        } catch(IOException ioe)
       {
        System.out.println("file not found exception");
       }
           
           CardLayout card=(CardLayout)MainPanel.getLayout();
           card.show(MainPanel,"Login");
           LoadImage(ImageLabel2,loginP1);
 
        }
       else
           {
            System.out.println("in else");
            CardLayout card=(CardLayout)MainPanel.getLayout();
            card.show(MainPanel,"Registration");   
           }
       }
       catch(IOException ioe)
       {
        System.out.println("file not found exception");
       }
        
    }
   
        
  
    
    public boolean checkFile(String s) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(s));     
         if (br.readLine() == null) {
        System.out.println("file empty");
        return(false);
    
    }
         else
            return(true);
    }
    
    
    public void writeToLog (String fn, char type){
        // 1) create a java calendar instance
             String content;
             Calendar calendar = Calendar.getInstance();
 
             // 2) get a java.util.Date from the calendar instance.
             //    this date will represent the current instant, or "now".
             java.util.Date now = calendar.getTime();
                        
             if(type=='e')
                {
                    content = fn+" was encrypted at "+now+"\n";
                    System.out.println(content);
                }
             else
                {
                    content = fn+" was decrypted at "+now+"\n";
                    System.out.println(content);
                }
        try {
                        

		File lf = new File("/home/akku/NetBeansProjects/MiniProjFinal/logFile.txt");
                // File file = file.("/home/akku/NetBeansProjects/MiniProjFinal/logFile.txt");
		// if file doesnt exists, then create it
                    
		if (!lf.exists()) {
                    lf.createNewFile();
		}
		
                BufferedWriter bw = new BufferedWriter(new FileWriter(lf,true));
		bw.write(content);
                bw.close();

		System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
            
    
    
    public  void write (String filename, CoOrdinate[]x) throws IOException{
    BufferedWriter outputWriter = null;
    outputWriter = new BufferedWriter(new FileWriter(filename));
    for (int sd = 0; sd<=pLength; sd++) {
    // Maybe:
      System.out.println(x[sd].x+","+x[sd].y);
     // outputWriter.write(x[sd].x1+","+x[sd].y1);
    //Or:
    outputWriter.write(Integer.toString(x[sd].x));
    outputWriter.write(",");
    outputWriter.write(Integer.toString(x[sd].y));
    if(sd<pLength)
    outputWriter.write(",");
    //outputWriter.newLine();
  }
  outputWriter.flush();  
  outputWriter.close();  
}
  
    
   private void LoadImage(final javax.swing.JLabel imglabel1,final CoOrdinate co[]){
       
        m=0;
        bi = null;
        try {
        bi = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println("No image selected! ");
        //e.printStackTrace();
        }           
        
        icon = new ImageIcon(bi.getScaledInstance(imglabel1.getWidth(), 
                imglabel1.getHeight(), BufferedImage.SCALE_SMOOTH)); 
         m=0;
         imglabel1.setIcon(icon);
         imglabel1.setVisible(true);
         note.setVisible(true); 
         imglabel1.setOpaque(false); 
         imglabel1.addMouseListener(new MouseAdapter() 
         {  
             @Override
            public void mouseClicked(MouseEvent e) throws ArrayIndexOutOfBoundsException
         {   
			 try{
					co[m]=new CoOrdinate(e.getX(),e.getY());
					System.out.println("mouseClicked");
             if(co==confirmP)
             {
                 System.out.println(confirmP[m].x+","+confirmP[m].y);
                 if(confirmP[m].x<=setP[m].x+10 && confirmP[m].x>=setP[m].x-10 && confirmP[m].y<=setP[m].y+10 && confirmP[m].y>=setP[m].y-10)
                 {
                     Graphics g = imglabel1.getGraphics();
                     g.setColor(Color.red); //Setting color to red
                     g.fillOval(co[m].x,co[m].y, 10, 10); //Drawing the circle/point
                     g.drawString(Integer.toString(m+1), co[m].x, co[m].y );
                     g.dispose();
                     
                     if(m==pLength)
					 {
                         Confirm.setVisible(true);
                     }
                 }
                 else
                 {
                      warning1.setText("Please check your password!"); 
                      warning1.setVisible(true);
                      
                      m=-1;
                      repaint();
                      
                 }
             }
             
             else if(co==loginP1)
                 {
                  System.out.println(co[m].x+","+co[m].y);
                  System.out.println(pLength);
                  System.out.println(loginP[m].x+","+loginP[m].y);
                 if(co[m].x<=loginP[m].x+10 && co[m].x>=loginP[m].x-10 && co[m].y<=loginP[m].y+10 && co[m].y>=loginP[m].y-10)
                 {
                     Graphics g = imglabel1.getGraphics();
                     g.setColor(Color.red); //Setting color to red
                     g.fillOval(co[m].x,co[m].y, 10, 10); //Drawing the circle/point
                     g.drawString(Integer.toString(m+1), co[m].x, co[m].y );
                     g.dispose();
                     
                     if(m==pLength)
                    {
                         LoginButton.setVisible(true);
                         DragDrop();
                    }
                 }
                 else
                 {
                      if(m==pLength)
                    {
                      mugvar++;
                      if(mugvar==3)
                        {
                            Dimension[] nonStandardResolutions = new Dimension[] {
			WebcamResolution.PAL.getSize(),
			WebcamResolution.HD720.getSize(),
			new Dimension(2000, 1000),
			new Dimension(1000, 500),
		};
		//@formatter:on

		// your camera have to support HD720p to run this code
		Webcam webcam = Webcam.getDefault();
		webcam.setCustomViewSizes(nonStandardResolutions);
		webcam.setViewSize(WebcamResolution.HD720.getSize());
		webcam.open();
                try {
					String name = String.format("test-%d.jpg", System.currentTimeMillis());
					ImageIO.write(webcam.getImage(), "JPG", new File(name));
					System.out.format("File %s has been saved\n", name);
				} catch (Exception t) {
					t.printStackTrace();
				}
                        }
                    }
                      warning2.setText("Please check your password!"); 
                      warning2.setVisible(true);
                      m=-1;
                      repaint();
                        
                 }   
                 }
             
             else
             {
		Graphics g = imglabel1.getGraphics();
		g.setColor(Color.red); //Setting color to red
		g.fillOval(co[m].x,co[m].y, 10, 10); //Drawing the circle/point
		g.drawString(Integer.toString(m+1), co[m].x, co[m].y );
		g.dispose();
				
             }
             System.out.println(co[m].x+","+co[m].y);
             m++;
            }
             
             catch(ArrayIndexOutOfBoundsException ae)
             {  warning.setText("You have exceeded Password limit!"); 
                warning.setVisible(true);
                Next.setVisible(false);
             }
             if(m>4)
             {     
                 Next.setVisible(true);
             }
           
             
             
         } 
         });
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        Registration = new javax.swing.JPanel();
        userName = new javax.swing.JTextField();
        eID = new javax.swing.JTextField();
        choosePassword = new javax.swing.JButton();
        invalidName = new javax.swing.JLabel();
        invalidEmail = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        SetPassword = new javax.swing.JPanel();
        ChooseImage = new javax.swing.JButton();
        ImageLabel = new javax.swing.JLabel();
        note = new javax.swing.JLabel();
        warning = new javax.swing.JLabel();
        Next = new javax.swing.JButton();
        ConfirmPassword = new javax.swing.JPanel();
        ImageLabel1 = new javax.swing.JLabel();
        warning1 = new javax.swing.JLabel();
        Confirm = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        Login = new javax.swing.JPanel();
        ImageLabel2 = new javax.swing.JLabel();
        warning2 = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        forgotPassword = new javax.swing.JButton();
        enterOTP = new javax.swing.JPanel();
        otpSent = new javax.swing.JLabel();
        otp = new javax.swing.JTextField();
        submitOPT = new javax.swing.JButton();
        Encrypt = new javax.swing.JPanel();
        encryptButton = new javax.swing.JButton();
        dragdrop = new javax.swing.JLabel();
        wallet = new javax.swing.JLabel();
        help = new javax.swing.JLabel();
        activityLog = new javax.swing.JLabel();
        myFiles = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        MyFilesP = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        MainPanel.setLayout(new java.awt.CardLayout());

        Registration.setLayout(null);

        userName.setText("Enter Username");
        userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameActionPerformed(evt);
            }
        });
        Registration.add(userName);
        userName.setBounds(98, 76, 259, 38);

        eID.setText("Enter email ID");
        Registration.add(eID);
        eID.setBounds(98, 156, 259, 38);

        choosePassword.setText("Choose a Password");
        choosePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosePasswordActionPerformed(evt);
            }
        });
        Registration.add(choosePassword);
        choosePassword.setBounds(98, 242, 259, 23);
        Registration.add(invalidName);
        invalidName.setBounds(98, 120, 259, 30);
        Registration.add(invalidEmail);
        invalidEmail.setBounds(98, 200, 259, 30);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/miniprojfinal/vau.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        Registration.add(jLabel1);
        jLabel1.setBounds(-1, 0, 530, 370);

        jTextField1.setText("jTextField1");
        Registration.add(jTextField1);
        jTextField1.setBounds(220, 290, 59, 20);

        MainPanel.add(Registration, "card7");
        Registration.getAccessibleContext().setAccessibleName("Registration");

        SetPassword.setName("SetPassword"); // NOI18N

        ChooseImage.setText("CHOOSE IMAGE");
        ChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseImageActionPerformed(evt);
            }
        });

        note.setText("*Note: Select a minimum of 5 points on the image and remember it as password.");

        Next.setText("NEXT");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SetPasswordLayout = new javax.swing.GroupLayout(SetPassword);
        SetPassword.setLayout(SetPasswordLayout);
        SetPasswordLayout.setHorizontalGroup(
            SetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetPasswordLayout.createSequentialGroup()
                .addGroup(SetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SetPasswordLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(ChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SetPasswordLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SetPasswordLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(SetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Next)
                            .addGroup(SetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SetPasswordLayout.setVerticalGroup(
            SetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetPasswordLayout.createSequentialGroup()
                .addComponent(ChooseImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(note)
                .addGap(7, 7, 7)
                .addComponent(Next)
                .addContainerGap())
        );

        MainPanel.add(SetPassword, "SetPassword");

        ConfirmPassword.setName("SetPassword"); // NOI18N

        warning1.setText("Please check your password!");

        Confirm.setText("CONFIRM");
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        Back.setText("BACK");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConfirmPasswordLayout = new javax.swing.GroupLayout(ConfirmPassword);
        ConfirmPassword.setLayout(ConfirmPasswordLayout);
        ConfirmPasswordLayout.setHorizontalGroup(
            ConfirmPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmPasswordLayout.createSequentialGroup()
                .addGroup(ConfirmPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConfirmPasswordLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(warning1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConfirmPasswordLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ImageLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConfirmPasswordLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Back)
                .addGap(31, 31, 31)
                .addComponent(Confirm)
                .addGap(20, 20, 20))
        );
        ConfirmPasswordLayout.setVerticalGroup(
            ConfirmPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmPasswordLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(ImageLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warning1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(ConfirmPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Confirm)
                    .addComponent(Back))
                .addContainerGap())
        );

        MainPanel.add(ConfirmPassword, "ConfirmPassword");

        Login.setName("SetPassword"); // NOI18N

        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        forgotPassword.setText("Forgot Password?");
        forgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(forgotPassword)
                .addGap(18, 18, 18)
                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addGroup(LoginLayout.createSequentialGroup()
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ImageLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(warning2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(ImageLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warning2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginButton)
                    .addComponent(forgotPassword))
                .addGap(32, 32, 32))
        );

        MainPanel.add(Login, "Login");

        otpSent.setText("                 Enter the OTP sent to ypur registered email ID");

        otp.setText("OTP");

        submitOPT.setText("Submit");
        submitOPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitOPTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout enterOTPLayout = new javax.swing.GroupLayout(enterOTP);
        enterOTP.setLayout(enterOTPLayout);
        enterOTPLayout.setHorizontalGroup(
            enterOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterOTPLayout.createSequentialGroup()
                .addGroup(enterOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(enterOTPLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(otpSent, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(enterOTPLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(enterOTPLayout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(submitOPT)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        enterOTPLayout.setVerticalGroup(
            enterOTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterOTPLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(otpSent, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(submitOPT)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        MainPanel.add(enterOTP, "card8");

        encryptButton.setText("Encrypt a file");
        encryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptButtonActionPerformed(evt);
            }
        });

        dragdrop.setIcon(new javax.swing.ImageIcon("C:\\Users\\Akshata Sheth\\Documents\\NetBeansProjects\\MiniProjFinal\\MiniProjFinal\\plus.png")); // NOI18N
        dragdrop.setText("Drop your files here!");
        dragdrop.setAlignmentX(20.0F);
        dragdrop.setAlignmentY(20.0F);
        dragdrop.setBorder(new javax.swing.border.MatteBorder(null));

        wallet.setIcon(new javax.swing.ImageIcon("C:\\Users\\Akshata Sheth\\Documents\\NetBeansProjects\\MiniProjFinal\\MiniProjFinal\\picto-cb.gif")); // NOI18N
        wallet.setText("  Wallet");
        wallet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                walletMouseClicked(evt);
            }
        });

        help.setIcon(new javax.swing.ImageIcon("C:\\Users\\Akshata Sheth\\Documents\\NetBeansProjects\\MiniProjFinal\\MiniProjFinal\\icon40x40help.png")); // NOI18N
        help.setText("   Help");

        activityLog.setIcon(new javax.swing.ImageIcon("C:\\Users\\Akshata Sheth\\Documents\\NetBeansProjects\\MiniProjFinal\\MiniProjFinal\\40px-Activity-log.svg.png")); // NOI18N
        activityLog.setText(" Activity log");
        activityLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activityLogMouseClicked(evt);
            }
        });

        myFiles.setIcon(new javax.swing.ImageIcon("C:\\Users\\Akshata Sheth\\Documents\\NetBeansProjects\\MiniProjFinal\\MiniProjFinal\\upload-file-icon-png-28.png")); // NOI18N
        myFiles.setText(" My Files");
        myFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myFilesMouseClicked(evt);
            }
        });

        jLabel2.setText("Drop Your Files here!");

        jButton1.setText("Decrypt a file");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EncryptLayout = new javax.swing.GroupLayout(Encrypt);
        Encrypt.setLayout(EncryptLayout);
        EncryptLayout.setHorizontalGroup(
            EncryptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(EncryptLayout.createSequentialGroup()
                .addGroup(EncryptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EncryptLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(activityLog, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EncryptLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(EncryptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wallet, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(EncryptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dragdrop, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)))
            .addGroup(EncryptLayout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(encryptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        EncryptLayout.setVerticalGroup(
            EncryptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EncryptLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(EncryptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encryptButton)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(EncryptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EncryptLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(myFiles))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EncryptLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addGroup(EncryptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EncryptLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(wallet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(help)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(activityLog, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 57, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EncryptLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dragdrop, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        MainPanel.add(Encrypt, "Encrypt");
        Encrypt.getAccessibleContext().setAccessibleName("Encrypt");

        javax.swing.GroupLayout MyFilesPLayout = new javax.swing.GroupLayout(MyFilesP);
        MyFilesP.setLayout(MyFilesPLayout);
        MyFilesPLayout.setHorizontalGroup(
            MyFilesPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        MyFilesPLayout.setVerticalGroup(
            MyFilesPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        MainPanel.add(MyFilesP, "card8");
        MyFilesP.getAccessibleContext().setAccessibleName("MyFilesP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        MainPanel.getAccessibleContext().setAccessibleName("MainPanel");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseImageActionPerformed
        JFileChooser jfc= new JFileChooser();
         FileFilter imgfilter = new FileNameExtensionFilter(
        "Image files", ImageIO.getReaderFileSuffixes());
         jfc.setFileFilter(imgfilter);
        jfc.showOpenDialog(this);
         f=jfc.getSelectedFile();
         imgpath= f.getAbsolutePath();
        //System.out.println(imgpath);
        LoadImage(ImageLabel,setP);
    }//GEN-LAST:event_ChooseImageActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
       
        pLength=m-1;
        System.out.println(pLength);
        CardLayout card=(CardLayout)MainPanel.getLayout();
        card.show(MainPanel,"ConfirmPassword");
        LoadImage(ImageLabel1,confirmP);
 
       
    }//GEN-LAST:event_NextActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
            try
                     {
                         ftemp=new File("temp.txt");
                         write("temp.txt",confirmP);
                         System.out.println("pLength="+pLength);
                         plf= new File("/home/akku/NetBeansProjects/MiniProjFinal/passlength.txt");
                         if (!plf.exists()) {
                            plf.createNewFile();
                         }
                         BufferedWriter plw = new BufferedWriter(new FileWriter(plf,true));
                         plw.write(pLength+"");
                         plw.flush();
                         plw.close();
                        // BufferedReader br = new BufferedReader(new FileReader("/home/akku/NetBeansProjects/MiniProjFinal/imgname.txt"));     
                       // String s=br.readLine();
                         
                     }
                     catch(IOException ioe)
                     {
                          System.out.println("err here");
                          System.out.println("IO exception encuntered");
                     }
            //  registered=true;
              
             try
             {
              fimgpath=new File("imgname.txt");
              PrintWriter writer = new PrintWriter("imgname.txt", "UTF-8");
              writer.println(f);
              writer.close();
             }
             
             catch(Exception e)
             {
                 System.out.println(e);
             }
             /*
             try {
             if(!checkFile("/home/akku/NetBeansProjects/MiniProjFinal/logFile.txt"))
             {
        try {
            PrintWriter logWriter = new PrintWriter("logFile.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(MiniProjFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
             }
             } catch (Exception e) {
                 System.out.println(e);
             }
             */
        
             CardLayout card=(CardLayout)MainPanel.getLayout();
             card.show(MainPanel,"Encrypt");
    }//GEN-LAST:event_ConfirmActionPerformed

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        CardLayout card=(CardLayout)MainPanel.getLayout();
         card.show(MainPanel,"Encrypt");
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        CardLayout card=(CardLayout)MainPanel.getLayout();
         card.show(MainPanel,"SetPassword");
    }//GEN-LAST:event_BackActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        loadLogin();
    }//GEN-LAST:event_formWindowActivated

    private void choosePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosePasswordActionPerformed
        // JPanel password= new JPanel();
        username=userName.getText();
        email=eID.getText();
        if(!validateUserName(username))
        invalidName.setText("Enter valid Username!");
        if(!validateEmail(email))
        invalidEmail.setText("Enter valid email ID!");
        if(validateEmail(email) & validateUserName(username))
        {
             choosePassword.setVisible(true);
             System.out.println(username+", "+email);
             try{  
                BufferedWriter outputWriter = null;
                outputWriter = new BufferedWriter(new FileWriter("/home/akku/NetBeansProjects/MiniProjFinal/registration.txt",true));
                outputWriter.write(email+"\n"+username);
                outputWriter.flush();  
                outputWriter.close();
                }
             catch(Exception e){
                 System.out.println(e);
              }
               
               CardLayout card=(CardLayout)MainPanel.getLayout();
               card.show(MainPanel,"SetPassword");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_choosePasswordActionPerformed

    private void forgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forgotPasswordActionPerformed
       
        final String Susername = "tempo4991@gmail.com";
	final String Spassword = "P@$$word4991";
        
        try{
           // BufferedReader r = new BufferedReader(new FileReader("registration.txt")).useDelimiter("\\s*,\\s*");
            BufferedReader r = new BufferedReader(new FileReader("/home/akku/NetBeansProjects/MiniProjFinal/registration.txt"));
            
            //System.out.println("email id="+r.readLine());
            fusername=r.readLine();
            System.out.println("email id="+fusername);
        }
        catch(Exception io)
        {
            System.out.println(io);
        }
          
        Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
             
	Session session = Session.getInstance(props,
	new javax.mail.Authenticator() {
         @Override
	protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(Susername, Spassword);
		}
	});

	try {

	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(Susername));
	message.setRecipients(Message.RecipientType.TO,
	InternetAddress.parse(fusername));
        final String OTP = generatePassword();
        OTP1=OTP;
        System.out.println("OTP = " + OTP);
	message.setSubject("Password Recovery");
	message.setText("Here is your one time password: \n"+ OTP);
        Transport.send(message);
        System.out.println("stooping here");
	JOptionPane.showMessageDialog(null,"A one time password has been mailed to your registered email ID!" );

		} catch (MessagingException e) {
                        System.out.println(e);
                    //JOptionPane.showMessageDialog(null,"Failed! Please Check Your Internet Connection" );
		} 
    }//GEN-LAST:event_forgotPasswordActionPerformed

    private void submitOPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitOPTActionPerformed
       String checkOTP=otp.getText();
       if(OTP1.equals(otp))
       {
           fimgpath.delete();
           ftemp.delete();
           CardLayout card=(CardLayout)MainPanel.getLayout();
           card.show(MainPanel,"SetPassword");
       }
    }//GEN-LAST:event_submitOPTActionPerformed

    private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptButtonActionPerformed
            try {
            JFileChooser fc= new JFileChooser();
            fc.showOpenDialog(this);
            File fe;
            fe=fc.getSelectedFile();
            String efilepath,efilename;
            efilepath= fe.getAbsolutePath();
            miniprojfinal.AESFileEncryption.encrypt(efilepath,"C:\\Users\\Akshata Sheth\\Documents\\NetBeansProjects\\MiniEncfinal");
            efilename=fe.getName();
            JOptionPane.showMessageDialog(null,efilename+" has been successfully encrypted!" );
            writeToLog(efilename,'e');
            CardLayout card=(CardLayout)MainPanel.getLayout();
            card.show(MainPanel,"Encrypt");
            
        } catch (Exception ex) {
            //Logger.getLogger(MiniProjFinal.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("This is an exception in encryptButtonActionPerformed!");
        }
      
       
    }//GEN-LAST:event_encryptButtonActionPerformed

    private void walletMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_walletMouseClicked
        
        // TODO add your handling code here:
    }//GEN-LAST:event_walletMouseClicked

    private void activityLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activityLogMouseClicked
        try {
            File alf= new File("/home/akku/NetBeansProjects/MiniProjFinal/logFile.txt");
            alf.setReadOnly();
            java.awt.Desktop.getDesktop().open(alf);
            // TODO add your handling code here:
        } catch (IOException ex) {
            System.out.println("Activity log file not found");
            //Logger.getLogger(MiniProjFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_activityLogMouseClicked

    private void myFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myFilesMouseClicked
        JTextField field = new JTextField("Hello, World");
        JPanel accessory = new JPanel();
        accessory.setLayout(new FlowLayout());
        
        
        miniprojfinal.SingleRootFileSystemView fsv;
        final File dirToLock = new File("/home/akku/NetBeansProjects/MiniProjFinal/encrypted_files/");
        fsv = new SingleRootFileSystemView(dirToLock);
        JFileChooser fc = new JFileChooser(fsv);
        //fc.showOpenDialog(this);
        int ret = fc.showOpenDialog(this);
        accessory.add(field);
        fc.setAccessory(accessory);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(fc, BorderLayout.CENTER);
        panel.add(field, BorderLayout.SOUTH);
        fc.setFileView(new FileView() {
        @Override
        public Boolean isTraversable(File f) {
         return dirToLock.equals(f);
        
    }
});
        
      //  JFileChooser jfc= new JFileChooser("/home/akku/NetBeansProjects/MiniProjFinal/encrypted_files");
     //   jfc.showOpenDialog(this);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_myFilesMouseClicked

    private void userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            JFileChooser f= new JFileChooser();
            f.showOpenDialog(this);
            File fe;
            fe=f.getSelectedFile();
            String der= fe.getAbsolutePath();
            f.showSaveDialog(null);
            // System.out.println(f.getCurrentDirectory());
            //File direc=f.getCurrentDirectory();
            JFileChooser fd = new JFileChooser(new File("C:\\Users\\Akshata Sheth\\Documents\\NetBeansProjects\\MiniEncfinal"));
            fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fd.showSaveDialog(null);
            //System.out.println(f.getCurrentDirectory());
            File direc=fd.getSelectedFile();
            String dir=direc.getAbsolutePath();
            
            miniprojfinal.AESFileDecryption.decrypt(der,dir);
        } catch (Exception ex) {
            Logger.getLogger(MiniProjFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
       
        

    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:
          char key = evt.getKeyChar();
        if (key == 27) System.exit(0);
    }//GEN-LAST:event_formKeyTyped

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased
/**/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiniProjFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiniProjFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiniProjFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiniProjFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiniProjFinal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton ChooseImage;
    private javax.swing.JButton Confirm;
    private javax.swing.JPanel ConfirmPassword;
    private javax.swing.JPanel Encrypt;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JLabel ImageLabel1;
    private javax.swing.JLabel ImageLabel2;
    private javax.swing.JPanel Login;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel MyFilesP;
    private javax.swing.JButton Next;
    private javax.swing.JPanel Registration;
    private javax.swing.JPanel SetPassword;
    private javax.swing.JLabel activityLog;
    private javax.swing.JButton choosePassword;
    private javax.swing.JLabel dragdrop;
    private javax.swing.JTextField eID;
    private javax.swing.JButton encryptButton;
    private javax.swing.JPanel enterOTP;
    private javax.swing.JButton forgotPassword;
    private javax.swing.JLabel help;
    private javax.swing.JLabel invalidEmail;
    private javax.swing.JLabel invalidName;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel myFiles;
    private javax.swing.JLabel note;
    private javax.swing.JTextField otp;
    private javax.swing.JLabel otpSent;
    private javax.swing.JButton submitOPT;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel wallet;
    private javax.swing.JLabel warning;
    private javax.swing.JLabel warning1;
    private javax.swing.JLabel warning2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
