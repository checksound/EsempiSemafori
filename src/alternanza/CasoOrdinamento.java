package alternanza;

import static java.lang.System.out;
import java.util.concurrent.Semaphore;

public class CasoOrdinamento {

    private static class Thread1 extends Thread {
        private Semaphore semaphore1;
        private Semaphore semaphore2;

        public Thread1(Semaphore semaphore1, Semaphore semaphore2) {
            this.semaphore1 = semaphore1;
            this.semaphore2 = semaphore2;
        }

        public void run() {
            try {
                semaphore1.acquire();
                out.println("x");          // step 1
                semaphore2.release();
                semaphore1.acquire();
                out.println("y");          // step 2
                semaphore2.release();
                semaphore1.acquire();
                out.println("z");          // step 3
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

    }  // fine Thread1

    private static class Thread2 extends Thread {
        private Semaphore semaphore1;
        private Semaphore semaphore2;
        public Thread2(Semaphore semaphore1, Semaphore semaphore2) {
            this.semaphore1 = semaphore1;
            this.semaphore2 = semaphore2;
        }


        public void run() {

            try {
                out.println("a");                // step 1
                semaphore1.release();
                semaphore2.acquire();
                out.println("b");                // step 2
                semaphore1.release();
                semaphore2.acquire();
                out.println("c");                // step 3
                semaphore1.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }  // fine Thread2


    public static void main(String[] args) {

        Semaphore semaphore1 = new Semaphore(0);
        Semaphore semaphore2 = new Semaphore(0);

        Thread t1 = new Thread1(semaphore1, semaphore2);
        Thread t2 = new Thread2(semaphore1, semaphore2);

        t1.start();
        t2.start();


    }


}
