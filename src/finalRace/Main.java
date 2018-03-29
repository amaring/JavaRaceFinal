package finalRace;

import java.util.List;
import finalRace.business.Racer;
import finalRace.business.RacerThread;
import finalRace.db.DBException;
import finalRace.db.DBUtil;
import finalRace.db.RacerDB;
import finalRace.ui.Console;
import finalRace.ui.StringUtil;
import finalrace.ui.MainUI;


public class Main {
    
    public static void main(String[] args) {
        MainUI frame = new MainUI();
        /*
        // display a welcome message
        System.out.println("Welcome to the Animal Race application\n");

        // display the command menu
        displayMenu();

        // perform 1 or more actions
        String action;
        while (true) {
            // get the input from the user
            action = Console.getString("Enter a command: ");
            System.out.println();

            if (action.equalsIgnoreCase("list")) {
                displayAllRunners();
            } else if (action.equalsIgnoreCase("add")) {
                addRunner();
            } else if (action.equalsIgnoreCase("del") || 
                       action.equalsIgnoreCase("delete")) {
                deleteRunner();
            } else if (action.equalsIgnoreCase("race")) {
                startRace();
                break;
            } else if (action.equalsIgnoreCase("help") || 
                       action.equalsIgnoreCase("menu")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit") || 
                       action.equalsIgnoreCase("quit")) {
                exit();
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        }

    }
    
    public static void displayMenu() {
        System.out.println("COMMAND MENU");
        System.out.println("list    - List all runners");
        System.out.println("add     - Add a runner");
        System.out.println("del     - Delete a runner");
        System.out.println("race    - Start the race");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }
        
    public static void displayAllRunners() {
        Console.display("RUNNER LIST");
            
        List<Racer> racers = null;
        try {
            racers = RacerDB.getAll();
        } catch (DBException e) {
            Console.display(e + "\n");
        }
        
        if (racers == null) {
            Console.display("Error! Unable to get runners.\n");
        } else {
            Racer r;
            StringBuilder sb = new StringBuilder();
            sb.append(StringUtil.padWithSpaces("Name", 10));
            sb.append(StringUtil.padWithSpaces("Speed", 7));
            sb.append(StringUtil.padWithSpaces("Rests", 7));
            sb.append("\n");
            for (Racer racer : racers) {
                r = racer;
                sb.append(StringUtil.padWithSpaces(r.getName(), 10));
                sb.append(StringUtil.padWithSpaces(Integer.toString(r.getSpeed()), 7));
                sb.append(StringUtil.padWithSpaces(Integer.toString(r.getRestChance()), 7));
                sb.append("\n");
            }
            Console.display(sb.toString());
        }
    }

    public static void addRunner() {
        String name = Console.getString("Enter animal name: ");
        int restChance = Console.getInt("Enter rest percentage as a whole number: ");
        int speed = Console.getInt("Enter speed: ");
        int wins = 0;
        int losses = 0;
        
        Racer racer = new Racer();
        racer.setName(name);
        racer.setRestChance(restChance);
        racer.setSpeed(speed);
        racer.setWins(wins);
        racer.setLosses(losses);
        
        try {
            RacerDB.add(racer);
            Console.display(racer.getName() + " was added to the database.\n");
        } catch (DBException e) {
            Console.display("Error! Unable to add customer.");
            Console.display(e + "\n");
        }
    }
    
    public static void updateRunner() {
        String ident = Console.getString("Enter name of the animal you want to update: ");
        Racer racer;
        try {
            racer = RacerDB.get(ident);
            if (racer == null) {
                throw new Exception("Animal not found.");
            }
        } catch (Exception e) {
            Console.display("Error! Unable to update animal.");
            Console.display(e + "\n");
            return;
        }
        
        String name = Console.getString("Enter animal name: ");
        int restChance = Console.getInt("Enter rest percentage as a whole number: ");
        int speed = Console.getInt("Enter speed: ");
        String resetWins = Console.getString("Reset number of wins and losses? ");
        
        if (resetWins.equalsIgnoreCase("yes") || resetWins.equalsIgnoreCase("y")) {
            int wins = 0;
            int losses = 0;
            
            racer.setWins(wins);
            racer.setLosses(losses);
            
            try {
                RacerDB.resetWinsAndLosses(racer);
            } catch (DBException e) {
                Console.display("Error! Unable to update runner.");
                Console.display(e + "\n");
            }
        }
        
        racer.setName(name);
        racer.setRestChance(restChance);
        racer.setSpeed(speed);
        try {
            RacerDB.update(racer);
        } catch (DBException e) {
            Console.display("Error! Unable to update runner.");
            Console.display(e + "\n");
        }
        
        Console.display(racer.getName() + " was updated successfully.\n");
    }
    
    public static void deleteRunner() {
        String name = Console.getString("Enter name of animal to delete: ");
        
        Racer racer;
        try {
            racer = RacerDB.get(name);
            if (racer == null) {
                throw new Exception("Animal not found.");
            }
        } catch (Exception e) {
            Console.display("Error! Unable to delete animal.");
            Console.display(e + "\n");
            return;
        }
        try {
            RacerDB.delete(racer);
        } catch (DBException e) {
            Console.display("Error! Unable to delete animal.");
            Console.display(e + "\n");
        }
        Console.display(racer.getName() + " was deleted successfully.");
    }
    
    public static void startRace() {
        int i = Console.getInt("Enter the number of runners in this race: ");
        Thread raceThreads[] = new Thread[i];
        for (int j = 0; j < i; j++) {
            String runnerName1 = Console.getString("Enter the animal you want to add to the race: ");
            try {
                Racer racer = RacerDB.get(runnerName1);
                if (racer == null) {
                    Console.display("Error! Unable to add runner.\n");
                } else {
                    raceThreads[j] = new RacerThread(racer);
                    
                }
            } catch (DBException e) {
                Console.display(e + "\n");
            }
        }
        
        Console.display("Get set ... Go!");       
        for (int j = 0; j < i; j ++) {
            raceThreads[j].start();
        }     
    }
    
    public static void exit() {
        try {
            DBUtil.closeConnection();
        } catch (DBException e) {
            Console.display("Error! Unable to close connection.");
            Console.display(e + "\n");
        }
        System.out.println("Bye.\n");
        System.exit(0);
*/
    }
}
