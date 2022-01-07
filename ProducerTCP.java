import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProducerTCP {
    private Socket s;
    private DataInputStream dis;
    private ObjectOutputStream oos;
    private int producerNumber;
    private List<Data> toSend;

    public ProducerTCP(Socket s, int producerNumber) {
        try {
            this.s = s;
            this.dis = new DataInputStream(s.getInputStream());
            this.oos = new ObjectOutputStream(s.getOutputStream());
            this.producerNumber = producerNumber;
            this.toSend = toSend;
        } catch (IOException e) {
            closeEverything(s, dis, oos);
        }
    }

    public void produceData() {
        try {
            oos.writeObject(producerNumber);
            int i = 0;
            while (s.isConnected()) {
                i = i % 1024;
                i++;
                List<Data> toSend = new ArrayList<>();
                toSend.add(new DataA(i, producerNumber, Math.random() * (1.78 - 1.32) + 1.32));
                oos.writeObject(toSend);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (IOException e) {
            closeEverything(s, dis, oos);
        } catch (InterruptedException e) {
            closeEverything(s, dis, oos);
        }
    }

    public void closeEverything(Socket s, DataInputStream dis, ObjectOutputStream oos) {
        try {
            if (dis != null) dis.close();
            if (oos != null) oos.close();
            if (s != null) s.close();
        } catch (IOException e) {
            System.out.println("Exception caught");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter producer number: ");
        int producerNumber = sc.nextInt();
        Socket s = new Socket("localhost", 1234);
        ProducerTCP newProducer = new ProducerTCP(s, producerNumber);
        newProducer.produceData();
    }
}
