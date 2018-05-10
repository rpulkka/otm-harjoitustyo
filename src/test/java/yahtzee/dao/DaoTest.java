package yahtzee.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import yahtzee.domain.Highscore;

// @author rpulkka
public class DaoTest {

    private Database highscores;
    private HighscoresDao dao;
    private Highscore matti;
    private Highscore maija;

    public DaoTest() {
    }

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        this.highscores = new Database("jdbc:sqlite:test.db");
        this.dao = new HighscoresDao(highscores);
        Connection connection = highscores.getConnection();
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE Highscores (id integer PRIMARY KEY, name varchar(10), score integer);");
        statement.execute();
        statement.close();
        connection.close();
        matti = dao.save(new Highscore("Matti", 100));
        maija = dao.save(new Highscore("Maija", 200));
    }

    @Test
    public void findingAndSortingWorks() throws SQLException {
        List<Highscore> highscoreList = dao.findAll();
        assertTrue(maija.getName().equals(highscoreList.get(0).getName()));
        assertTrue(maija.getScore() == highscoreList.get(0).getScore());
        assertTrue(matti.getName().equals(highscoreList.get(1).getName()));
        assertTrue(matti.getScore() == highscoreList.get(1).getScore());
    }

    @Test
    public void savingWorks() throws SQLException {
        Highscore compareMatti = new Highscore("Matti", 100);
        Highscore compareMaija = new Highscore("Maija", 200);
        assertEquals(matti.getName(), compareMatti.getName());
        assertEquals(maija.getName(), compareMaija.getName());
        assertEquals(matti.getScore(), compareMatti.getScore());
        assertEquals(maija.getScore(), compareMaija.getScore());
    }

    @Test
    public void deletesSupercededHighscores() throws SQLException {
        for (int i = 0; i < 9; i++) {
            dao.updateOrNot(150);
        }
        boolean mattiFound = false;
        boolean maijaFound = false;
        for (Highscore h : dao.findAll()) {
            if (matti.getName().equals(h.getName())) {
                mattiFound = true;
            } else if (maija.getName().equals(h.getName())) {
                maijaFound = true;
            }
        }
        assertEquals(mattiFound, false);
        assertEquals(maijaFound, true);
    }

    @Test
    public void setsNickname() throws SQLException {
        dao.setHighscoreNickname("Amanda", 2);
        boolean maijaFound = false;
        boolean amandaFound = false;
        for (Highscore h : dao.findAll()) {
            if (maija.getName().equals(h.getName())) {
                maijaFound = true;
            } else if ("Amanda".equals(h.getName())) {
                amandaFound = true;
            }
        }
        assertEquals(maijaFound, false);
        assertEquals(amandaFound, true);
    }

    @Test
    public void wontUpdateTooLowScore() throws SQLException {
        for (int i = 0; i < 9; i++) {
            dao.updateOrNot(150);
        }
        Highscore tooLow = dao.updateOrNot(100);
        assertEquals(tooLow, null);
    }

    @After
    public void tearDown() {
        boolean result = new File("test.db").delete();
    }
}
