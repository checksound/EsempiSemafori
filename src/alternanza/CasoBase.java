package alternanza;

import static java.lang.System.out;

public class CasoBase {

    private static class Thread1 extends Thread {

        public void run() {
            out.println("x");  // step 1
            out.println("y");  // step 2
            out.println("z");  // step 3
        }

    }  // fine Thread1

    private static class Thread2 extends Thread {

        public void run() {
            out.println("a");  // step 1
            out.println("b");  // step 2
            out.println("c");  // step 3
        }

    }  // fine Thread2


    public static void main(String[] args) {

        Thread t1 = new Thread1();
        Thread t2 = new Thread2();

        t1.start();
        t2.start();


    }


}
