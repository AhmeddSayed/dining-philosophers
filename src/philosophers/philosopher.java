package philosophers;

import static java.lang.Thread.sleep;
import java.util.Random;

public class philosopher extends Thread {

    public boolean isHungry, isEating, canEat;
    boolean isThinking;
    int ID, jobTime;

    public philosopher(int ID) {
        this.ID = ID;
        this.canEat = false;
        this.isEating = false;
        this.isHungry = false;
        this.isThinking = false;

        synchronized (this) {
            updateMood();
        }
    }

    private void updateMood() {

        int MoodID;
        Random randomNumber = new Random();
        MoodID = randomNumber.nextInt(15);

        this.jobTime = randomNumber.nextInt(5000);

        if (MoodID == 14 || MoodID == 7 || MoodID == 9) { // Hungry
            this.isHungry = true;
        } else {
            this.isThinking = true;
        }
    }

    public synchronized void startJob() {
        if (isHungry) {
            System.out.println("--- Philosopher " + ID + " is hungry ---");
            eat();

        } else {
            think();
        }
    }

    public void eat() {
        synchronized (this) {
            if (!this.canEat) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
            this.isEating = true;

            System.out.println("--- Philosopher " + ID + " started eating at " + System.currentTimeMillis() % 1000 + " ---");
            System.out.println("--- Philosopher " + ID + " will eat for " + jobTime + " seconds ---");
            try {
                sleep(jobTime);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
            }
            System.out.println("--- Philosopher " + ID + " finished eating ---");
            this.isEating = false;
            this.isHungry = false;

        }
    }

    private void think() {
        System.out.println("Philosopher " + ID + " is thinking.");
        this.isThinking = true;

        try {
            sleep(jobTime);
        } catch (InterruptedException ex) {
        }

        System.out.println("Philosopher " + ID + " finished thinking.");
        this.isThinking = false;
        updateMood();
    }

}
