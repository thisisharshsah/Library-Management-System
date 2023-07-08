
//Importing package

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//Building Classes
public class Changepassword extends JFrame implements ActionListener {


    Main main = new Main();
    Database database = new Database();

    //Initializing variable
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;

    JTextField username;
    JTextField oldPassword;
    JTextField newPassword;

    JButton changePassword;
    JButton backButton;

    JComboBox<String> userBox;

    //    making a constructor
    public Changepassword() {

        //Declaring Variable
        label1 = new JLabel("Change Password");
        label1.setIcon(main.imageIcon);

        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setIconTextGap(20);
        label1.setBounds(500, 30, 450, 250);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        label2 = new JLabel("Select User: ");
        label2.setBounds(600, 300, 150, 30);
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        userBox = new JComboBox<>();
        userCombobox();
        userBox.setBounds(700, 300, 150, 30);

        label3 = new JLabel("Username:");
        label3.setBounds(570, 350, 100, 15);
        label3.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        username = new JTextField();
        username.setBounds(700, 350, 200, 20);

        label4 = new JLabel("Old password:");
        label4.setBounds(570, 400, 130, 20);
        label4.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        oldPassword = new JTextField();
        oldPassword.setBounds(700, 400, 200, 20);

        label5 = new JLabel("New password:");
        label5.setBounds(570, 450, 130, 20);
        label5.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        newPassword = new JTextField();
        newPassword.setBounds(700, 450, 200, 20);

        changePassword = new JButton("Change Password");
        changePassword.setBounds(710, 500, 140, 30);
        changePassword.addActionListener(this);

        this.setVisible(true);
        this.setTitle("Change Password");
        this.setSize(800, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(199, 219, 249));

        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);

        this.add(username);
        this.add(oldPassword);
        this.add(newPassword);
        this.add(changePassword);
        this.add(userBox);
        back();
        main.dispose();
    }
    void back() {

        backButton = new JButton("Back");
        backButton.setBounds(40, 20, 100, 25);
        backButton.addActionListener(this);
        this.add(backButton);

    }
    //    method to fetch user content from database
    void userCombobox() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT user FROM `login`;");
            while (rs.next()) {

                String name = rs.getString("user");
                userBox.addItem(name);

            }
            con.close();
        } catch (Exception e6) {

            JOptionPane.showMessageDialog(this, e6.getMessage());

        }

    }
    //    method to Update password from database
    void setChangePassword() {
        try {

            String rSql = "SELECT * FROM `login` ";
            String sql = "UPDATE `login` SET `password`= '" + newPassword.getText() + "' WHERE username = '" + username.getText() + "'&& password = '" + oldPassword.getText() + "'&& user = '" + userBox.getSelectedItem() + "';";
            String message = "Password changed successfully";
            database.dbExecution(rSql, sql, message);
        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }
    }

    public static void main(String[] args) {

        new Changepassword();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changePassword) {

            //making connection with database
            try {
                String query = "SELECT * FROM `login` WHERE username=? and password =?";
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root", "");
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, username.getText());
                pst.setString(2, oldPassword.getText());
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {

                    setChangePassword();
                }
                con.close();
            } catch (Exception e1) {

                JOptionPane.showMessageDialog(this, e1.getMessage());


            }

        }else if (e.getSource() == backButton) {

            this.dispose();
            new Main();

        }
    }
}

