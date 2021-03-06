package kr.re.kitri.northwind.Service;

import kr.re.kitri.northwind.Model.Customer;
import kr.re.kitri.northwind.Model.Join;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danawacomputer on 2017-05-10.
 */
public class JdbcService {

    public List<Customer> makeList(String DB_URL, String USERNAME, String PASSWORD) {

        String query = "select * from customers order by contactname;";
        List<Customer> list = null;
        try {
            Connection conn =
                    DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                Customer customer = new Customer(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11));
                list.add(customer);
            }
            conn.close();


            System.out.println("connection closed..");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    public List<Join> makeList2(String DB_URL, String USERNAME, String PASSWORD) {

        String query = "select a.contactname, a.city, a.country, b.orderdate, b.shipcountry\n" +
                "    from customers a, orders b\n" +
                "    where a.customerid = b.customerid;";
        List<Join> list = null;
        try {
            Connection conn =
                    DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                Join join = new Join(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5));
                list.add(join);
            }
            conn.close();


            System.out.println("connection closed..");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

}
