import java.io.*;
import java.util.*;
import java.net.*;
import java.util.ArrayList;

public class ConsumerTCP {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(1234);
        ArrayList<DataA> output = new ArrayList<DataA>();
        for (int i = 0; i < 5; i++) {
            output.add(new DataA(0, 0, 0));
        }

        while (true) {
            Socket s = null;
            try {
                s = ss.accept();
                System.out.println("A new client has connected!");
                Thread t = new ClientHandler(s, output);
                t.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class ClientHandler extends Thread {
    static ArrayList<ClientHandler> clientHandlerArrayList = new ArrayList<>();
    private Socket s;
    private ObjectInputStream ois;
    private DataOutputStream dos;
    private int producerNumber;
    private ArrayList<DataA> output;

    public ClientHandler(Socket s, ArrayList<DataA> output) {
        try {
            this.s = s;
            this.ois = new ObjectInputStream(s.getInputStream());
            this.dos = new DataOutputStream(s.getOutputStream());
            this.producerNumber = (Integer) ois.readObject();
            clientHandlerArrayList.add(this);
            this.output = output;
        } catch (IOException e) {
            closeEverything(s, ois, dos);
        } catch (ClassNotFoundException e) {
            closeEverything(s, ois, dos);
        }
    }

    @Override
    public void run() {
        List<Data> received;
        DataA messageA = new DataA(0, 0, 0);
        while (s.isConnected()) {
            try {
                received = (List<Data>) ois.readObject();

                // from producer
                if (received.get(0) instanceof DataA) {
                    messageA = (DataA) received.get(0);
                    output.set(messageA.getSrcid(), messageA);
                }

                // from actor: user input
                if (received.get(0) instanceof DataB) {
                    DataB messageB = (DataB) received.get(0);
                    int user_input = messageB.getSrcid();
                    if (user_input > 0 && user_input < 5) {
                        output.get(user_input).print();
                    } else if (user_input == 5) {
                    } else if (user_input == 0) {
                        Collections.sort(output, new DataACompare());
                        for (int i = 0; i < output.size() - 1; i++) {
                            output.get(i).print();
                        }
                        Collections.sort(output, new SrcidCompare());
                    } else {
                        System.out.println("Invalid input");
                    }
                }

            } catch (IOException e) {
                closeEverything(s, ois, dos);
                break;
            } catch (ClassNotFoundException e) {
                closeEverything(s, ois, dos);
                break;
            }
        }
    }

    public void closeEverything(Socket s, ObjectInputStream ois, DataOutputStream dos) {
        clientHandlerArrayList.remove(this);
        try {
            if (ois != null) ois.close();
            if (dos != null) dos.close();
            if (s != null) s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
