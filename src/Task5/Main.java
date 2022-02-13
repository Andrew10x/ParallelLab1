package Task5;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Task5Right();
    }

    public static void Task5() {
        Runnable r1 = () -> {
            for(int i=0; i<100; i++) {
                System.out.print('-');
            }
        };

        Runnable r2 = () -> {
            for(int i=0; i<100; i++) {
                System.out.print('|');
            }
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);
        t1.start();
        t2.start();


    }

    public static void Task5Right() {
        AtomicInteger counter = new AtomicInteger(0);
        Show show = new Show();
        Runnable r1 = () -> {
            show.showSync("-");
        };

        Runnable r2 = () -> {
            show.showSync("|");
        };


        var t1 = new Thread(r1);
        var t2 = new Thread(r2);
        t1.start();
        t2.start();


    }

}
    class Show {
        public synchronized void showSync(String str) {
            for(int i=0; i<100; i++) {
                System.out.print(str);
                notify();
                try {
                    wait();
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

