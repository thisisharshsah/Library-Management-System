
//Importing package

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

//Building class for this java class while extending JFrame and Action listener
public class Main extends JFrame implements ActionListener {

    //Static variable are created to call in other classes of this project
    static ImageIcon imageIcon;
    static String instructor;
    static String student;

    //Initializing JLabel
    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JLabel jLabel4;

    //Initializing JLabel
    JTextField username;
    JTextField password;

    //Initializing JButton
    JButton loginButton;
    JButton registerButton;
    JButton changeButton;

    //Initializing JButton
    JRadioButton administratorButton;
    JRadioButton instructorButton;
    JRadioButton studentButton;

    //Initializing ButtonGroup
    ButtonGroup buttonGroup;

    //Building a constructor
    public Main() {

        //Adding variable initialized above in the JFrame
        imageIcon = new ImageIcon("");

        jLabel1 = new JLabel("Library Management System");
        jLabel1.setIcon(imageIcon);
        jLabel1.setHorizontalTextPosition(JLabel.CENTER);
        jLabel1.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabel1.setIconTextGap(20);
        jLabel1.setBounds(600, 50, 440, 300);

        jLabel2 = new JLabel("Login as");
        jLabel2.setBounds(700, 340, 100, 50);
        jLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        jLabel3 = new JLabel("Username:");
        jLabel3.setBounds(570, 440, 100, 50);
        jLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        username = new JTextField();
        username.setBounds(680, 455, 210, 20);

        jLabel4 = new JLabel("Password:");
        jLabel4.setBounds(570, 500, 100, 20);
        jLabel4.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        password = new JTextField();
        password.setBounds(680, 500, 210, 20);

        changeButton = new JButton("Change Password");
        changeButton.setBounds(565, 550, 140, 30);
        changeButton.addActionListener(this);

        loginButton = new JButton("Log In");
        loginButton.setBounds(700, 550, 100, 30);
        loginButton.addActionListener(this);

        registerButton = new JButton("Sign up");
        registerButton.setBounds(795, 550, 100, 30);
        registerButton.setVisible(false);
        registerButton.addActionListener(this);

        administratorButton = new JRadioButton("administrator");
        administratorButton.setBounds(570, 380, 120, 50);
        administratorButton.addActionListener(this);

        studentButton = new JRadioButton("student");
        studentButton.setBounds(780, 380, 100, 50);
        studentButton.addActionListener(this);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(administratorButton);
        buttonGroup.add(studentButton);

        //Making JFrame
        this.setTitle("Library Management System");
        this.setVisible(true);
        this.setSize(800, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(199, 219, 249));
        this.setIconImage(imageIcon.getImage());

        //Adding the variable declared above in the JFrame
        this.add(jLabel1);
        this.add(jLabel2);
        this.add(jLabel3);
        this.add(jLabel4);
        this.add(administratorButton);
        this.add(studentButton);
        this.add(username);
        this.add(password);
        this.add(loginButton);
        this.add(registerButton);
        this.add(changeButton);

    }

    //Method to get name of the button
    String getSelectedButtonText(ButtonGroup buttonGroup) {

        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {

            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    public static void main(String[] args) {
        new Main();
    }

    //Run while any action is performed inside the JFrame
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {

            //Exception Handling while connecting database
            try {

                String query = "SELECT * FROM `login` WHERE username=? and password =? and user =?";

                //Connecting Database
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root", "");

                //Passing query inside the PreparedStatement
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                pst.setString(3, String.valueOf(getSelectedButtonText(buttonGroup)));

                ResultSet rs = pst.executeQuery();

                //If the query result some data this statement will run
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "You are logged in as " + rs.getString("user"));

                    if (administratorButton.isSelected()) {

                        this.dispose();
                        //Calling Administrator Class
                        new Administrator();

                    }
                    else if (studentButton.isSelected()) {
                        student = username.getText();
                        this.dispose();
                        //Calling Student Class
                        new Student();

                    }
                } else {
                    //Dialog box to show message
                    JOptionPane.showMessageDialog(this, "Incorrect login credentials");

                }

            } catch (Exception e2) {

                e2.printStackTrace();

            }

        } else if (e.getSource() == changeButton) {

            this.dispose();
            //Calling Changepassword Class
            new Changepassword();

        } else if (studentButton.isSelected()) {

            registerButton.setVisible(true);

            if (e.getSource() == registerButton) {

                this.dispose();
                //Calling StudentSignup Class
                new StudentSignup();

            }

        } else if (administratorButton.isSelected()) {
            registerButton.setVisible(false);

        }


    }
}
