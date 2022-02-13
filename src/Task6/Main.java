package Task6;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
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
        System.out.println(Counter.getCounter());
    }

}

class Counter {
    private static int counter = 0;

    public static int getCounter() {
        return counter;
    }

    public void increaseCounter() {
            counter++;
    }

    public void decreaseCounter() {
            counter--;
    }
}
