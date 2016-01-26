package philosophers;

public class dinnerTable {

    int N = 5; // number of Philosophers
    public final philosopher[] philosophers = new philosopher[N];
    public waiter tableWaiter = new waiter(); //Assigning monitor 

    public dinnerTable() {
        for (int i = 0; i < philosophers.length; i++) { //initialization
            philosophers[i] = new philosopher(i + 1);
        }

    }

    public synchronized void startGame() {

        int next, previous;

        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i].startJob();
            if (philosophers[i].isHungry) {

                if (i == 0) {
                    previous = 4;
                    next = 1;
                } else if (i == 4) {
                    next = 0;
                    previous = 3;
                } else {
                    next = i + 1;
                    previous = i - 1;
                }

                philosophers[i].canEat = tableWaiter.check(philosophers[i].ID, philosophers[next].isEating, philosophers[previous].isEating);
                notifyAll();
            }
        }
    }
}
