package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Country> getCountryFromName(String name) {
        List<Country> results = new ArrayList<Country>();

        try {
            this.getConnection();

            pstmt = con.prepareStatement("select * from country where Name = ?");

            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                Country country = new Country();
                country.setName(rs.getString("Name"));
                country.setPopulation(rs.getInt("Population"));

                results.add(country);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            this.close();
        }
        return results;
    }

    public void getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(
                "jdbc:mysql://localhost/world?useSSL=false",
                "root",
                "smile0220");
    }

    private void close() {
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