package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnectSample04 {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/world?useSSL=false",
                    "root",
                    "smile0220"
                );

            pstmt = con.prepareStatement("select * from country where Name = ?");

            String name = "Aruba";
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("Name"));
                System.out.println(rs.getInt("Population"));
        }
    } catch (SQLException e) {
        e.printStackTrace();

    }catch (ClassNotFoundException e) {
        e.printStackTrace();

    }finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
}
