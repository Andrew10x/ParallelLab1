package Task6;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        Counter2 counter = new Counter2();
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
        System.out.println(Counter2.getCounter());
    }

}

class Counter2 {
    private static int counter = 0;

    public static int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        synchronized (this) {
            counter++;
        }
    }

    public void decreaseCounter() {
        synchronized (this) {
            counter--;
        }
    }
}
