package yahtzee.dao;

import java.sql.*;

// @author rpulkka
/**
 * This class represents the database that is connected to the game.
 */
public class Database {

    private String address;
    private Connection connection;

    public Database(String uri) throws ClassNotFoundException, SQLException {
        this.address = "jdbc:sqlite:" + uri;
        this.connection = DriverManager.getConnection(address);
        createTable();
    }

    public void createTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Highscores "
                + "(id integer PRIMARY KEY, name varchar(10), "
                + "score integer);");
        statement.execute();
        statement.close();
        connection.close();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(address);
    }
}
