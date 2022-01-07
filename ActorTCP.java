import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ActorTCP {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 1234);
        Scanner sc = new Scanner(System.in);
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        try {
            int i = 0;
            oos.writeObject(1);
            while (s.isConnected()) {
                i = i % 128;
                i++;
                List<Data> tosend = new ArrayList<>();
                tosend.add(new DataB(i, sc.nextInt(), 0));
                oos.writeObject(tosend);
            }
        } catch (Exception e) {
            System.out.println("Exception caught");
        } finally {
            sc.close();
            oos.close();
            s.close();
        }
    }
}
