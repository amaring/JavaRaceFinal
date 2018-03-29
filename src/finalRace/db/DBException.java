package finalRace.db;

public class DBException extends Exception {
    DBException() {}
    
    DBException(Exception e) {
        super(e);
    }
}
