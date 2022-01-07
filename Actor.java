import java.util.Scanner;
import java.util.concurrent.*;

public class Actor implements Runnable {
    Scanner sc = new Scanner(System.in);
    private BlockingQueue<DataB> userInput;

    public Actor (BlockingQueue<DataB> userInput) {
        this.userInput = userInput;
    }

    public void run() {
        try {
            int i = 0;
            while (sc.hasNext()) {
                i = i % 128;
                i++;
                userInput.put(new DataB(i, sc.nextInt(), 0));
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
