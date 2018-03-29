package finalRace.business;

import java.util.Random;

public class RacerThread extends Thread {
    String name;
    int restChance;
    int speed;
    int progress = 0;
    Random random = new Random();
    
    public RacerThread(Racer racer) {
        this.name = racer.getName();
        this.restChance = racer.getRestChance();
        this.speed = racer.getSpeed();
    }
    
    @Override
    public void run() {
        while (progress < 1000) {
            // decide whether runner should run or rest
            int i = runOrRest(restChance);
            //System.out.println(runOrRest(restChance));
            if (i == 1) {
                // if runner is running, add speed value for the runner
                progress += speed;
                System.out.println(name + ": " + progress);
            }
            try {
                //sleep for 300 milliseconds before end of loop
                Thread.sleep(100);
            } catch (InterruptedException ex) {}
                //Something here?
            }
            System.out.println(name + ": I finished!");

    }
    public int runOrRest(int restChance) {
        int randomNumber = random.nextInt(100) + 1;
        if (restChance >= randomNumber) {
            // runner rests
            //return ("Runner Waits: " + restChance + " chance : random " + randomNumber);
            return 0;
        } else {
            //return ("Runner Goes: " + restChance + " chance : random " + randomNumber);
            return 1;
        }      
    }
}

