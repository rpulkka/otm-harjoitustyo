
package yahtzee.dao;

import java.sql.*;

// @author rpulkka

public class Database {

    private String address;

    public Database(String address) throws ClassNotFoundException {
        this.address = address;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(address);
    }
}
