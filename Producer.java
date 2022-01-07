import java.util.concurrent.*;

public class Producer implements Runnable {
    private final BlockingQueue<DataA> sharedQueue;
    private String threadName;
    private int srcid;

    public Producer(BlockingQueue<DataA> sharedQueue, String threadName, int srcid) {
        this.sharedQueue = sharedQueue;
        this.threadName = threadName;
        this.srcid = srcid;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                i = i % 1024;
                i++;
                sharedQueue.put(new DataA(i, srcid, Math.random() * (1.78 - 1.32) + 1.32));
                Thread.sleep(1000);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
