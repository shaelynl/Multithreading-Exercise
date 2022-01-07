import java.util.concurrent.*;

public class Test {
    public static void main (String args[]) {
        BlockingQueue<DataA> sharedQueue = new LinkedBlockingQueue<DataA>();
        BlockingQueue<DataB> userInput   = new LinkedBlockingQueue<DataB>();

        Thread prodThread1 = new Thread(new Producer(sharedQueue,"A", 1));
        Thread prodThread2 = new Thread(new Producer(sharedQueue,"B", 2));
        Thread prodThread3 = new Thread(new Producer(sharedQueue,"C", 3));
        Thread prodThread4 = new Thread(new Producer(sharedQueue,"D", 4));

        Thread actorThread = new Thread(new Actor(userInput));

        Thread consThread = new Thread(new Consumer(sharedQueue, userInput));

        prodThread1.start();
        prodThread2.start();
        prodThread3.start();
        prodThread4.start();
        actorThread.start();
        consThread.start();
    }
}
