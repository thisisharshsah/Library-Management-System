

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Building Classes
public class Database extends JFrame{

    //    method to make connection with database and execute query by calling this method
    void dbExecution(String sql, String sql1, String message) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "root", "");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            st.executeUpdate(sql1);
            JOptionPane.showMessageDialog(this, message);

        }
        con.close();


    }

}
