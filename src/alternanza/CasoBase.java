package alternanza;

import static java.lang.System.out;

public class CasoBase {

    private static class Thread1 extends Thread {

        public void run() {
            out.println("x");
            out.println("y");
            out.println("z");
        }

    }  // fine Thread1

    private static class Thread2 extends Thread {

        public void run() {
            out.println("a");
            out.println("b");
            out.println("c");
        }

    }  // fine Thread2


    public static void main(String[] args) {

        Thread t1 = new Thread1();
        Thread t2 = new Thread2();

        t1.start();
        t2.start();


    }


}
