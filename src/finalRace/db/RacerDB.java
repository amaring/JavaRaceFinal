package finalRace.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import finalRace.business.Racer;

/**
 * @author Amanda Maring
 * @date 2017-12-12
 */
public class RacerDB {
    
    public static List<Racer> getAll() throws DBException {
        String sql = "SELECT * FROM Racer ORDER BY RacerID";
        List<Racer> racers = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int racerId = rs.getInt("RacerID");
                String name = rs.getString("Name");
                int restChance = rs.getInt("RestChance");
                int speed = rs.getInt("Speed");
                
                Racer r = new Racer();
                r.setID(racerId);
                r.setName(name);
                r.setRestChance(restChance);
                r.setSpeed(speed);
                racers.add(r);
            }
            return racers;
            } catch (SQLException e) {
                throw new DBException(e);
            }
    }
    
    public static List<Racer> getName() throws DBException {
        String sql = "SELECT Name FROM Racer ORDER BY RacerID";
        List<Racer> racers = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("Name");
                Racer r = new Racer();
                r.setName(name);
                racers.add(r);
            }            
            return racers;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    public static Racer get(String racerName) throws DBException {
        String sql = "SELECT * FROM Racer WHERE Name = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, racerName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int racerId = rs.getInt("RacerID");
                int restChance = rs.getInt("RestChance");
                int speed = rs.getInt("Speed");
                rs.close();
                
                Racer r = new Racer();
                r.setID(racerId);
                r.setName(racerName);
                r.setRestChance(restChance);
                r.setSpeed(speed);
                
                return r;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    public static void add(Racer racer) throws DBException {
        String sql = "INSERT INTO Racer (Name, RestChance, Speed) "
                + "VALUES (?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, racer.getName());
            ps.setInt(2, racer.getRestChance());
            ps.setInt(3, racer.getSpeed());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    public static void update(Racer racer) throws DBException {
        String sql = "UPDATE Racer SET " 
                + "Name = ?, RestChance = ?, Speed = ? "
                + "WHERE RacerID = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, racer.getName());
            ps.setInt(2, racer.getRestChance());
            ps.setInt(3, racer.getSpeed());
            ps.setInt(4, racer.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    public static void resetWinsAndLosses(Racer racer) throws DBException {
        String sql = "UPDATE Racer SET wins = ?, losses = ?"
                + "WHERE RacerID = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(0, racer.getWins());
            ps.setInt(1, racer.getLosses());
            ps.setInt(2, racer.getID());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
    public static void delete(Racer racer) throws DBException {
        String sql = "DELETE FROM Racer WHERE RacerID = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, racer.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
