import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Details extends JFrame {
     JPanel panel1;
     JButton button1;
     JTable table1;
  static   Details d;

    Connection con;
    PreparedStatement pst;


    public static void main(String[] args) {
     d  = new Details();
        d.Detail();
    }
    public  void Detail(){

        connect();
        JFrame frame = new JFrame("Details");
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        customerMenu();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
                Home hmm= new Home();
                hmm.Homee();

            }
        });
    }
    public void connect() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mySql://localhost:3307/dhaba", "root", "Satyam@123");

            if (con.isClosed()) {
                System.out.println("Connection isn't created");
            } else {
                System.out.println("Connection Created...");
            }

        } catch (Exception e) {

            System.out.println(e + "\nSomething Went Wrong...!");
            // TODO: handle exception
        }
    };
    void customerMenu(){
        try {
            String q = "Select * from Customer where cstmr_id=?";

            pst = con.prepareStatement(q);
            String str=Home.customerrr;
            System.out.println(str+"Details");
            pst.setString(1,str);

            ResultSet set = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
