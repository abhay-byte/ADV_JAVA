import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit; // Import TimeUnit for tryLock timeout
import java.util.Random; // Import Random for delays

class Philosopher extends Thread {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;
    private final Random random = new Random(); // Use Random for delays

    public Philosopher(int id, Lock leftFork, Lock rightFork, String name) {
        super(name); // Set thread name
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                if (pickUpForksWithTimeout()) { // Try to pick up forks
                    eat();
                    putDownForks();
                }
                // If couldn't get both forks, will automatically loop back to thinking
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Re-interrupt the thread
            System.out.println("Philosopher " + id + " was interrupted.");
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        // Simulate thinking time with a random delay
        Thread.sleep(random.nextInt(1000) + 500); // Sleep between 500ms and 1500ms
    }

    // Attempt to pick up forks with a timeout to prevent deadlock
    private boolean pickUpForksWithTimeout() throws InterruptedException {
        System.out.println("Philosopher " + id + " wants forks.");
        // Try to acquire the left fork with a timeout
        if (leftFork.tryLock(100, TimeUnit.MILLISECONDS)) { // Try for 100ms
            System.out.println("Philosopher " + id + " picked up left fork.");
            // Try to acquire the right fork with a timeout
            if (rightFork.tryLock(100, TimeUnit.MILLISECONDS)) { // Try for 100ms
                System.out.println("Philosopher " + id + " picked up right fork. Got both!");
                return true; // Successfully acquired both forks
            } else {
                // Couldn't get right fork, release left fork
                System.out.println("Philosopher " + id + " couldn't get right fork, releasing left.");
                leftFork.unlock();
                return false; // Failed to acquire both forks
            }
        } else {
            // Couldn't get left fork
             System.out.println("Philosopher " + id + " couldn't get left fork.");
            return false; // Failed to acquire both forks
        }
    }


    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        // Simulate eating time
        Thread.sleep(random.nextInt(1000) + 500); // Sleep between 500ms and 1500ms
    }

    private void putDownForks() {
        // Release forks in reverse order of acquisition (though order doesn't strictly matter here)
        rightFork.unlock();
        System.out.println("Philosopher " + id + " put down right fork.");
        leftFork.unlock();
        System.out.println("Philosopher " + id + " put down left fork.");
    }
}

public class DiningPhilosophers {
    public static void main(String[] args) {
        System.out.println("Name: Abhay Raj\nEnrolment No: 00976803122\n");
        int n = 5; // Number of philosophers and forks
        Lock[] forks = new ReentrantLock[n];

        // Initialize forks
        for (int i = 0; i < n; i++) {
            forks[i] = new ReentrantLock();
        }

        Philosopher[] philosophers = new Philosopher[n];
        // Create and start philosopher threads
        for (int i = 0; i < n; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % n]; // Ensures the last philosopher picks up fork 0

            // To prevent classic deadlock, make one philosopher (e.g., the last one)
            // pick up forks in the opposite order (right then left).
            // However, the tryLock approach generally handles this better.
            // Keeping the tryLock approach as it's more robust.

            philosophers[i] = new Philosopher(i, leftFork, rightFork, "Philosopher-" + i);
            philosophers[i].start();
        }

        // Optional: Let the simulation run for a while and then interrupt
        // try {
        //     Thread.sleep(10000); // Run for 10 seconds
        //     for (Philosopher p : philosophers) {
        //         p.interrupt();
        //     }
        // } catch (InterruptedException e) {
        //     Thread.currentThread().interrupt();
        // }
    }
}