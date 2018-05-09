package yahtzee.dao;

import java.util.*;
import java.sql.*;
import yahtzee.domain.Highscore;

// @author rpulkka
/**
 * A dao class for managing highscores that are viewed in the end of each game.
 */
public class HighscoresDao {

    private Database highscores;

    public HighscoresDao(Database highscores) {
        this.highscores = highscores;
    }

    /**
     * Lists all of the top 10 highscores and sorts them.
     * 
     * @return highscoreList A sorted list of highscores.
     */
    public List<Highscore> findAll() throws SQLException {
        Connection connection = highscores.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Highscores");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Highscore> highscoreList = new ArrayList<Highscore>();
        while (resultSet.next()) {
            Highscore highscore = new Highscore(resultSet.getString("name"), resultSet.getInt("score"));
            highscore.setId(resultSet.getInt("id"));
            highscoreList.add(highscore);
        }
        Collections.sort(highscoreList);
        statement.close();
        resultSet.close();
        connection.close();
        return highscoreList;
    }

    /**
     * Decides if highscore list will be updated and if a highscore must be
     * deleted from the highscores (in case it will be replaced).
     * 
     * @see HighscoresDao#save(yahtzee.domain.Highscore) 
     * @see HighscoresDao#delete(java.lang.Integer) 
     * 
     * @return Saved highscore.
     */
    public Highscore updateOrNot(int total) throws SQLException {
        List<Highscore> highscoreList = findAll();
        if (highscoreList.size() == 10) {
            if (highscoreList.get(highscoreList.size() - 1).getScore() < total) {
                delete(highscoreList.get(highscoreList.size() - 1).getId());
            } else {
                return null;
            }
        }
        return save(new Highscore(" ", total));
    }

    /**
     * Saves the new top 10 highscore and returns it.
     * 
     * @return newHighscore The saved highscore.
     */
    public Highscore save(Highscore highscore) throws SQLException {
        Connection connection = highscores.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Highscores"
                + " (name, score)"
                + " VALUES (?, ?)");
        statement.setString(1, highscore.getName());
        statement.setInt(2, highscore.getScore());

        statement.executeUpdate();
        statement.close();

        statement = connection.prepareStatement("SELECT * FROM Highscores"
                + " WHERE name = ? AND score = ?");
        statement.setString(1, highscore.getName());
        statement.setInt(2, highscore.getScore());

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        Highscore newHighscore = new Highscore(resultSet.getString("name"), resultSet.getInt("score"));
        newHighscore.setId(resultSet.getInt("id"));

        statement.close();
        resultSet.close();

        connection.close();

        return newHighscore;
    }

    /**
     * Deletes a highscore from the database, which happens when an old 
     * highscore is displaced.
     */
    public void delete(Integer key) throws SQLException {
        Connection connection = highscores.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Highscores WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    /**
     * A function that sets the players nickname for the new highscore.
     */
    public void setHighscoreNickname(String nickname, int id) throws SQLException {
        Connection connection = highscores.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Highscores SET name = ? WHERE id = ?;");
        statement.setString(1, nickname);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
