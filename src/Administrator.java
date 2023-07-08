
//Importing package

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//Building Classes
public class Administrator extends JFrame implements ActionListener {

    //Calling Main class
//    Main main = new Main();

    //Calling Database class
    Database database = new Database();

    //Initializing variable
    JLabel label1;

    JLabel label9;
    JLabel label10;
    JLabel label11;

    JTextField bookName;

    JTextField bookNumber;

    JComboBox<String> bookBox;
    JButton deleteBook;
    JButton addBook;
    JButton backButton;

    //    making a constructor
    public Administrator() {

//        Declaring variable
        label1 = new JLabel("Administrator Page");
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setIconTextGap(20);
        label1.setBounds(500, 30, 450, 250);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));


        label9 = new JLabel("Books");
        label9.setBounds(100, 250, 100, 20);
        label9.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        label10 = new JLabel("Book Name: ");
        label10.setBounds(30, 300, 100, 20);

        bookName = new JTextField();
        bookName.setBounds(120, 300, 300, 20);

        bookBox = new JComboBox<>();
        bookBox.setBounds(120, 330, 300, 20);
        bookCombobox();
        bookBox.addActionListener(this);



        label11 = new JLabel("No of Books: ");
        label11.setBounds(30, 360, 150, 20);

        bookNumber = new JTextField();
        bookNumber.setBounds(120, 360, 300, 20);

        addBook = new JButton("Add");
        addBook.setBounds(125, 400, 80, 20);
        addBook.addActionListener(this);

        deleteBook = new JButton("Remove");
        deleteBook.setBounds(325, 400, 80, 20);
        deleteBook.addActionListener(this);

        this.setVisible(true);
        this.setTitle("Administrator");
        this.setSize(800, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(199, 219, 249));

        this.add(label1);

        this.add(label9);
        this.add(label10);
        this.add(label11);


        this.add(bookName);
        this.add(bookBox);
        this.add(bookNumber);
        this.add(addBook);
        this.add(deleteBook);






        back();
//        main.dispose();
    }

    public static void main(String[] args) {


        new Administrator();

    }
    //Adding Back button in the JFrame
    void back() {

        backButton = new JButton("Back");
        backButton.setBounds(40, 20, 100, 25);
        backButton.addActionListener(this);

        this.add(backButton);

    }

    //    method to fetch book content from database
    void bookCombobox() {

        try {
            bookBox.addItem("-----Select-----");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT bookname FROM books;");

            while (rs.next()) {

                String name = rs.getString("bookname");

                bookBox.addItem(name);

            }
            con.close();

        } catch (Exception e2) {

            JOptionPane.showMessageDialog(this, e2.getMessage());

        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if  (e.getSource() == bookBox) {

            bookName.setText((String) bookBox.getSelectedItem());

        } else if (e.getSource() == backButton) {

            this.dispose();
            new Main();

        } else if (e.getSource() == deleteBook) {
            try {

                String rSql = "SELECT * FROM `books`;";
                String sql = "DELETE FROM `books` WHERE bookname = '" + bookName.getText() + "' ";
                String message = " " + bookName.getText() + " Book removed successfully";
                database.dbExecution(rSql, sql, message);

            } catch (Exception e8) {

                JOptionPane.showMessageDialog(this, e8.getMessage());

            }

        } else if (e.getSource() == addBook) {

            try {

                String rSql = "SELECT * FROM `books`;";
                String sql = "INSERT INTO `books` (`bookname`, `quantity`) VALUES ('" + bookName.getText() + "','" + bookNumber.getText()+ "')";
                String message = " " + bookNumber.getText() + " Book added Successfully";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root", "");

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(rSql);

                while (rs.next()) {

                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(this, message);

                }
                con.close();


            } catch (Exception e9) {

                JOptionPane.showMessageDialog(this, e9.getMessage());

            }

        }

    }

}
