package Task6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main3 {

    public static void main(String[] args) throws InterruptedException {
        Counter3 counter = new Counter3();
        Runnable r1 = () -> {
            for(int i=0; i<100000; i++) {
                counter.increaseCounter();
            }
        };

        Runnable r2 = () -> {
            for(int i=0; i<100000; i++) {
                counter.decreaseCounter();
            }
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(Counter3.getCounter());
    }

}

class Counter3 {
    private static int counter = 0;
    private final Lock counterLock = new ReentrantLock();;


    public static int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        counterLock.lock();
        counter++;
        counterLock.unlock();
    }

    public void decreaseCounter() {
        counterLock.lock();
        counter--;
        counterLock.unlock();
    }
}