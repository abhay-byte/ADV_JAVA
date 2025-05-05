class SharedResource {
    private int data = 0;
    private int readers = 0; // Use 0 for no readers/writers, positive for readers, -1 for writer

    // Corrected logic: writers wait if readers > 0 OR writer is active (readers == -1)
    // readers wait if writer is active (readers == -1)
    public synchronized void read() throws InterruptedException {
        // Wait while a writer is writing
        while (readers == -1) {
            wait();
        }
        readers++; // Increment reader count
        System.out.println(Thread.currentThread().getName() + " reads: " + data);
        // Simulate reading time
        try {
            Thread.sleep((long) (Math.random() * 50));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted during read");
        }
        readers--; // Decrement reader count
        // If this was the last reader, notify waiting writers
        if (readers == 0) {
            notifyAll();
        }
    }

    public synchronized void write(int newData) throws InterruptedException {
        // Wait while there are readers OR another writer is writing
        while (readers != 0) { // Checks for both readers (readers > 0) and writer (readers == -1)
            wait();
        }
        readers = -1; // Mark as writing
        System.out.println(Thread.currentThread().getName() + " writes: " + newData);
        data = newData;
        // Simulate writing time
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted during write");
        }
        readers = 0; // Mark writing as finished
        notifyAll(); // Notify waiting readers and writers
    }
}

class Reader extends Thread {
    private SharedResource resource;

    public Reader(SharedResource resource, String name) {
        super(name); // Set thread name
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) { // Read multiple times
                resource.read();
                Thread.sleep((long) (Math.random() * 100)); // Pause between reads
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Re-interrupt the thread
            System.err.println(getName() + " interrupted.");
        }
    }
}

class Writer extends Thread {
    private SharedResource resource;
    private int value = 0;

    public Writer(SharedResource resource, String name) {
        super(name); // Set thread name
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) { // Write multiple times
                resource.write(value++);
                Thread.sleep((long) (Math.random() * 200)); // Pause between writes
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Re-interrupt the thread
            System.err.println(getName() + " interrupted.");
        }
    }
}

public class ReaderWriterProblem {
    public static void main(String[] args) {
        System.out.println("Name: Abhay Raj\nEnrolment No: 00976803122\n"); // Print name first
        SharedResource resource = new SharedResource();

        // Create multiple readers and writers
        Reader reader1 = new Reader(resource, "Reader-1");
        Reader reader2 = new Reader(resource, "Reader-2");
        Reader reader3 = new Reader(resource, "Reader-3");
        Writer writer1 = new Writer(resource, "Writer-1");
        Writer writer2 = new Writer(resource, "Writer-2");

        // Start threads
        writer1.start();
        reader1.start();
        reader2.start();
        writer2.start();
        reader3.start();

        // Optional: Wait for threads to finish (not strictly necessary for this demo)
        try {
            writer1.join();
            reader1.join();
            reader2.join();
            writer2.join();
            reader3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread interrupted.");
        }
         System.out.println("\nAll threads finished.");
    }
}