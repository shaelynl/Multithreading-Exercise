import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.Collections;

public class Consumer implements Runnable {
    private final BlockingQueue<DataA> sharedQueue;
    private final BlockingQueue<DataB> userInput;

    public Consumer (BlockingQueue<DataA> sharedQueue, BlockingQueue<DataB> userInput) {
        this.sharedQueue = sharedQueue;
        this.userInput = userInput;
    }

    @Override
    public void run() {
        try {
            ArrayList<DataA> output = new ArrayList<DataA>();
            for (int i = 0; i < 5; i++) {
                output.add(new DataA(0, 0, 0));
            }
            int user_srcid = 0;
            while (true) {
                DataA head = (DataA) sharedQueue.poll();
                DataB user = (DataB) userInput.poll();
                if (head != null) {
                    output.set(head.getSrcid(), head);
                }
                if (user != null) {
                    user_srcid = user.getSrcid();
                }
                if (head == null && user == null) {
                    continue;
                }
                if (user_srcid > 0 && user_srcid < 5) {
                    output.get(user_srcid).print();
                } else if (user_srcid == 5) {
                } else if (user_srcid == 0) {
                    Collections.sort(output, new DataACompare());
                    for (int i = 0; i < output.size() - 1; i++) {
                        output.get(i).print();
                    }
                    Collections.sort(output, new SrcidCompare());
                    System.out.println("==========================");
                } else {
                    System.out.println("Invalid input");
                }
//                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
