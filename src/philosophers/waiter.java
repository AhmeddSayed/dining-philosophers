package philosophers;

public class waiter {

    public waiter() {

    }

    public boolean check(int ID, boolean isEating1, boolean isEating2) {
        boolean canEat;
        if (isEating1 && isEating2) {
            System.out.println("Hungry philosopher " + ID + " is asked to wait.");
            canEat = false;
        } else {
            canEat = true;
            System.out.println("Philosopher " + ID + " can now eat");
        }
        return canEat;

    }
}
