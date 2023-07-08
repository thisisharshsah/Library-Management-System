

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

//Build a Main class extends JFrame implements ActionListener
public class Student extends JFrame implements ActionListener {

    Main main = new Main();

    //Initializing variable
    JLabel label1;
    JLabel label2;
    JButton reserve;
    JButton viewBorrowedBooks;
    JButton backButton;
    JTable table;

    JComboBox<String> bookComboBox;

    //    making a constructor
    public Student() {

        //Declaring Variable
        label1 = new JLabel("Student Page");
        label1.setIcon(main.imageIcon);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setIconTextGap(20);
        label1.setBounds(500, 30, 450, 250);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        label2 = new JLabel("Choose Book: ");
        label2.setBounds(500, 300, 100, 20);

        bookComboBox = new JComboBox<>();
        bookComboBox.setBounds(600, 300, 250, 20);
        bookBox();
        bookComboBox.addActionListener(this);

        reserve = new JButton("Reserve");
        reserve.setBounds(575, 350, 200, 40);
        reserve.addActionListener(this);

        viewBorrowedBooks = new JButton("Borrowed Books");
        viewBorrowedBooks.setBounds(575, 400, 200, 40);
        viewBorrowedBooks.addActionListener(this);

//        tabledata();
//        table = new JTable(model);
//        table.setBounds(530, 430, 290, 100);
//        table.setEnabled(false);
//        table.setForeground(Color.black);
//        table.setBackground(Color.white);
//        table.setModel(model);
//        table.setVisible(false);


        this.setVisible(true);
        this.setTitle("Student");
        this.setSize(800, 800);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(199, 219, 249));

        this.add(label1);
        this.add(label2);
        this.add(reserve);
//        this.add(table);
        this.add(bookComboBox);
        this.add(viewBorrowedBooks);
        back();
        main.dispose();

    }

    public static void main(String[] args) {

        new Student();

    }

    void back() {

        backButton = new JButton("Back");
        backButton.setBounds(40, 20, 100, 25);
        backButton.addActionListener(this);

        this.add(backButton);

    }
    //Creating Table in JFrame
//    void tabledata() {
//
//        try {
//
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root",
//                    "");
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT DISTINCT `bookname` FROM `books`;");
//
//            model = new DefaultTableModel();
//
//            model.addColumn("Available Books");
//
//            while (rs.next()) {
//
//                String module = rs.getString("bookname");
//
//                model.addRow(new Object[]{module});
//
//            }
//
//            con.close();
//
//        } catch (Exception e1) {
//
//            JOptionPane.showMessageDialog(this, e1.getMessage());
//
//        }
//
//    }

//
    //    method to fetch module content from database
    void bookBox() {

        try {
            bookComboBox.addItem("--Select--");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root",
                    "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT bookname FROM books;");
            while (rs.next()) {

                String name = rs.getString("bookname");
                bookComboBox.addItem(name);

            }
            con.close();

        } catch (Exception e3) {

            JOptionPane.showMessageDialog(this, e3.getMessage());

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == reserve) {

//            table.setVisible(false);

        } else if (e.getSource() == backButton) {

            this.dispose();
            new Main();

        } else if (e.getSource() == viewBorrowedBooks) {

//            table.setVisible(true);


        }

    }

}

