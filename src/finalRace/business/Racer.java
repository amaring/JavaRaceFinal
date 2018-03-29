package finalRace.business;

import java.util.Random;

/**
 * @author Amanda Maring
 * @date 2017-12-12
 */
public class Racer {
    int racerId;
    String name;
    int restChance;
    int speed;
    int wins;
    int losses;

    public int getID() {
        return racerId;
    }

    public void setID(int racerID) {
        this.racerId = racerID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestChance() {
        return restChance;
    }

    public void setRestChance(int restChance) {
        this.restChance = restChance;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }    
    
    public int formatString(String x) {
        
        // May want to reconsider this - may want to make a try / catch to return errors if needed.
        int y = Integer.parseInt(x);
        return y;
    }
}
