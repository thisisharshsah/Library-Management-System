//Importing package

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

//Building Classes
public class StudentSignup extends JFrame implements ActionListener {

    Main main = new Main();
    Database database = new Database();

    //Initializing variable
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;

    JLabel label6;


    JTextField name;
    JTextField username;

    JTextField password;


    JComboBox<String> facultyBox;

    JButton registerButton;
    JButton backButton;
    int count;

    //    making a constructor
    public StudentSignup() {

        //Declaring Variable
        label1 = new JLabel("Student Signup");
        label1.setIcon(main.imageIcon);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setIconTextGap(20);
        label1.setBounds(500, 30, 450, 250);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        label2 = new JLabel("Enter your Name: ");
        label2.setBounds(500, 300, 120, 20);

        name = new JTextField();
        name.setBounds(610, 300, 250, 20);

        label3 = new JLabel("Username: ");
        label3.setBounds(500, 350, 100, 20);

        username = new JTextField();
        username.setBounds(610, 350, 100, 20);

        label4 = new JLabel("@texascollege.edu.np");
        label4.setBounds(710,350,150,20);

        label5 = new JLabel("Password: ");
        label5.setBounds(500, 400, 100, 20);

        password = new JTextField();
        password.setBounds(610, 400, 250, 20);

        label6 = new JLabel("Select faculty: ");
        label6.setBounds(500, 450, 100, 20);

        facultyBox = new JComboBox<>();
        facultyBox.setBounds(610, 450, 250, 20);
        facultyCombobox();
        facultyBox.addActionListener(this);

        registerButton = new JButton("Register");
        registerButton.setBounds(685, 510, 100, 30);
        registerButton.addActionListener(this);

        this.setVisible(true);
        this.setTitle("Register Student");
        this.setSize(800, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(199, 219, 249));

        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);

        this.add(name);
        this.add(username);
        this.add(password);

        this.add(facultyBox);

        this.add(registerButton);
        back();

        main.dispose();
    }

    public static void main(String[] args) {

        new StudentSignup();

    }

    void back() {

        backButton = new JButton("Back");
        backButton.setBounds(40, 20, 100, 25);
        backButton.addActionListener(this);

        this.add(backButton);

    }
    //    method to fetch course  content from database
    void facultyCombobox() {

        facultyBox.addItem("-----Select-----");
        facultyBox.addItem("BBS");
        facultyBox.addItem("BIT");
        facultyBox.addItem("BBA");
        facultyBox.addItem("BHM");
        facultyBox.addItem("Bcsit");
        facultyBox.addItem("MCS");
        facultyBox.addItem("MBA");


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backButton) {

            this.dispose();
            new Main();

        } else if (e.getSource() == registerButton) {

            if (name.getText().equals("")) {

                JOptionPane.showMessageDialog(this, "Name field is Empty");

            }
            if (username.getText().equals("")) {

                JOptionPane.showMessageDialog(this, "Username field is Empty");

            } else if (facultyBox.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(this, "Faculty Field is Empty");

            } else {

                try {

                    String rSql = "SELECT * FROM `login`;";
                    String sql = "INSERT INTO `login` (`name`, `username`, `password`, `user`, `faculty`) VALUES ('" + name.getText() + "','" + username.getText()+ "@texascollege.edu.np','" + password.getText() + "','student','" + facultyBox.getSelectedItem() + "')";
                    String message = " " + username.getText() + " Account Created Successfully";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root", "");

                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(rSql);

                    while (rs.next()) {

                        st.executeUpdate(sql);
                        JOptionPane.showMessageDialog(this, message);

                    }
                    con.close();


                } catch (Exception e3) {

                    JOptionPane.showMessageDialog(this, e3.getMessage());

                }
            }

        }

    }

}

