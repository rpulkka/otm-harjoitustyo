
package yahtzee.dao;

import java.sql.*;

// @author rpulkka
/**
 * This class represents the database that is connected to the game.
 */
public class Database {

    private String address;

    public Database(String address) throws ClassNotFoundException {
        this.address = address;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(address);
    }
}
