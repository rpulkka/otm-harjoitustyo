
package yahtzee.domain;


// @author rpulkka

public class Highscore implements Comparable<Highscore>{
    private int id;
    private String name;
    private int score;
    
    public Highscore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Highscore highscore) {
        return highscore.getScore() - this.score;
    }
    
    
    public String toString() {
        return name + "  " + score;
    }
}
